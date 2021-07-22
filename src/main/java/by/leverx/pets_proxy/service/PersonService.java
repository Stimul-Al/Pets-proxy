package by.leverx.pets_proxy.service;

import by.leverx.pets_proxy.dto.animal.AnimalCreateDto;
import by.leverx.pets_proxy.dto.animal.AnimalFullDto;
import by.leverx.pets_proxy.dto.animal.AnimalUpdateDto;
import by.leverx.pets_proxy.dto.person.PersonCreateDto;
import by.leverx.pets_proxy.dto.person.PersonFullDto;
import by.leverx.pets_proxy.dto.person.PersonPreviewDto;
import by.leverx.pets_proxy.dto.person.PersonUpdateDto;

import java.io.IOException;
import java.util.List;

public interface PersonService {

    List<PersonPreviewDto> findAll(String bearerToken) throws IOException;

    PersonFullDto findById(Long id, String token) throws IOException;

    PersonFullDto create(PersonCreateDto createDto, String token) throws IOException;

    PersonFullDto update(PersonUpdateDto updateDto, String token) throws IOException;

    void deleteById(Long id, String token);
}
