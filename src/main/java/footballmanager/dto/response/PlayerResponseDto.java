package footballmanager.dto.response;

import lombok.Data;

@Data
public class PlayerResponseDto {
    private Long id;
    private String firstName;
    private String lastName;
    private int age;
    private int monthsOfExperience;
    private Long teamId;
}