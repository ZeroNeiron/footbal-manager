package footballmanager.dto.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PlayerRequestDto {
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @Min(value = 18)
    @Max(value = 35)
    private int age;
    @NotNull
    @Min(value = 1)
    private int monthsOfExperience;
    @NotNull
    private Long teamId;
}