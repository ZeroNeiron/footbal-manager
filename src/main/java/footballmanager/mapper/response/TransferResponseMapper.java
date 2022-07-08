package footballmanager.mapper.response;

import footballmanager.dto.response.TransferResponseDto;
import footballmanager.mapper.ResponseDtoMapper;
import footballmanager.model.Transfer;
import org.springframework.stereotype.Component;

@Component
public class TransferResponseMapper implements ResponseDtoMapper<TransferResponseDto, Transfer> {
    @Override
    public TransferResponseDto mapToDto(Transfer transfer) {
        TransferResponseDto transferResponseDto = new TransferResponseDto();
        transferResponseDto.setId(transfer.getId());
        transferResponseDto.setTransferPrice(transfer.getTransferPrice());
        return transferResponseDto;
    }
}