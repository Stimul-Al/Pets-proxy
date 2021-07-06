package by.leverx.pets_proxy.dto.animal;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 *
 * @author aliaksei.babashau
 */
@Data
public class AnimalUpdateDto {

    private Long id;

    @NotBlank
    private String name;

    @NotNull
    private String typeAnimal;
}
