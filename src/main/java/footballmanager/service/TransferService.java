package footballmanager.service;

import footballmanager.dto.request.TransferRequestDto;
import footballmanager.dto.response.TransferResponseDto;

public interface TransferService {
    TransferResponseDto transferPlayer(TransferRequestDto transferRequestDto);
}
