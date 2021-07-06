package by.leverx.pets_proxy.dto.animal;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 *
 * @author aliaksei.babashau
 */
@Data
@Builder
public class AnimalCreateDto {

    @NotBlank
    private String name;

    @NotNull
    private String typeAnimal;
}
