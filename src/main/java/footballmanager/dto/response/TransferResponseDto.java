package footballmanager.dto.response;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class TransferResponseDto {
    private Long id;
    private BigDecimal transferPrice;
}