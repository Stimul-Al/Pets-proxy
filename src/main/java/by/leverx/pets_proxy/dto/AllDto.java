package by.leverx.pets_proxy.dto;

import by.leverx.pets_proxy.dto.animal.AnimalFullDto;
import by.leverx.pets_proxy.dto.person.PersonPreviewDto;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Set;

/**
 *
 * @author aliaksei.babashau
 */
@Data
@Builder
public class AllDto {

    List<PersonPreviewDto> persons;

    Set<AnimalFullDto> animals;

    Set<AnimalFullDto> dogs;

    Set<AnimalFullDto> cats;
}
