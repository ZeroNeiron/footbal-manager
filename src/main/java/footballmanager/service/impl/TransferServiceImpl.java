package footballmanager.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.NoSuchElementException;
import footballmanager.dto.request.TransferRequestDto;
import footballmanager.dto.response.TransferResponseDto;
import footballmanager.mapper.response.TransferResponseMapper;
import footballmanager.model.Player;
import footballmanager.model.Team;
import footballmanager.model.Transfer;
import footballmanager.repository.PlayerRepository;
import footballmanager.repository.TeamRepository;
import footballmanager.repository.TransferRepository;
import footballmanager.service.TransferService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransferServiceImpl implements TransferService {
    private final TransferRepository transferRepository;
    private final TeamRepository teamRepository;
    private final PlayerRepository playerRepository;

    public TransferServiceImpl(TransferRepository transferRepository,
                               TeamRepository teamRepository, PlayerRepository playerRepository) {
        this.transferRepository = transferRepository;
        this.teamRepository = teamRepository;
        this.playerRepository = playerRepository;
    }

    @Transactional
    @Override
    public TransferResponseDto transferPlayer(TransferRequestDto transferRequestDto) {
        Team sellerTeam = teamRepository.findById(transferRequestDto.getTeamSellerId())
                .orElseThrow(() -> new NoSuchElementException("Can't get sellerTeam by id"));

        Team buyerTeam = teamRepository.findById(transferRequestDto.getTeamBuyerId())
                .orElseThrow(() -> new NoSuchElementException("Can't get buyerTeam by id"));

        Player player = playerRepository.findById(transferRequestDto.getPlayerId())
                .orElseThrow(() -> new NoSuchElementException("Can't get player by id"));

        BigDecimal transferPrice = calculateTransferPrice(player, sellerTeam);

        if (buyerTeam.getBalance().compareTo(transferPrice) < 0) {
            throw new RuntimeException("Insufficient balance in buyer's account");
        }

        updateBalances(sellerTeam, buyerTeam, transferPrice);
        changePlayerTeam(player, buyerTeam);

        Transfer transfer = createTransfer(transferPrice, player, buyerTeam, sellerTeam);

        return new TransferResponseMapper().mapToDto(transfer);
    }

    private Transfer createTransfer(BigDecimal transferPrice, Player player,
                                    Team buyerTeam, Team sellerTeam) {
        Transfer transfer = new Transfer();
        transfer.setTransferPrice(transferPrice);
        transfer.setPlayer(player);
        transfer.setBuyerTeam(buyerTeam);
        transfer.setSellerTeam(sellerTeam);
        return transferRepository.save(transfer);
    }

    private void updateBalances(Team sellerTeam, Team buyerTeam, BigDecimal transferPrice) {
        buyerTeam.setBalance(buyerTeam.getBalance().subtract(transferPrice));
        sellerTeam.setBalance(sellerTeam.getBalance().add(transferPrice));

        teamRepository.save(buyerTeam);
        teamRepository.save(sellerTeam);
    }

    private void changePlayerTeam(Player player, Team buyerTeam) {
        player.setTeam(buyerTeam);
        playerRepository.save(player);
    }

    private BigDecimal calculateTransferPrice(Player player, Team sellerTeam) {
        BigDecimal transferPriceForPlayer = BigDecimal.valueOf(player.getMonthsOfExperience())
                .multiply(BigDecimal.valueOf(100000))
                .divide(BigDecimal.valueOf(player.getAge()), RoundingMode.CEILING);
        BigDecimal commissionOfTransfer = transferPriceForPlayer
                .divide(BigDecimal.valueOf(100), RoundingMode.CEILING)
                .multiply(BigDecimal.valueOf(sellerTeam.getCommissionPercent()));
        return transferPriceForPlayer.add(commissionOfTransfer);
    }
}