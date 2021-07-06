package by.leverx.pets_proxy.dto.person;

import by.leverx.pets_proxy.dto.animal.AnimalFullDto;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author aliaksei.babashau
 */
@Data
public class PersonUpdateDto {

    private Long id;

    @NotBlank
    private String name;

    private List<AnimalFullDto> animals = new ArrayList<>();
}
