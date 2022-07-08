package footballmanager.dto.request;

import lombok.Data;

@Data
public class TransferRequestDto {
    private Long playerId;
    private Long teamSellerId;
    private Long teamBuyerId;
}