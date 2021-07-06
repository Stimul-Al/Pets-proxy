package by.leverx.pets_proxy.dto.person;

import by.leverx.pets_proxy.dto.animal.AnimalFullDto;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author aliaksei.babashau
 */
@Data
public class PersonPreviewDto {

    private Long id;
    private String name;
    private List<AnimalFullDto> animals = new ArrayList<>();
}
