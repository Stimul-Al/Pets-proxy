package by.leverx.pets_proxy.dto.person;

import by.leverx.pets_proxy.dto.animal.AnimalCreateDto;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 *
 * @author aliaksei.babashau
 */
@Data
@Builder
public class PersonCreateDto {

    @NotBlank
    private String name;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String password;

    private List<AnimalCreateDto> animals;
}
