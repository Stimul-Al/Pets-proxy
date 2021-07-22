package by.leverx.pets_proxy.service;

import by.leverx.pets_proxy.dto.animal.AnimalCreateDto;
import by.leverx.pets_proxy.dto.animal.AnimalFullDto;
import by.leverx.pets_proxy.dto.animal.AnimalUpdateDto;

import java.io.IOException;
import java.util.List;

public interface AnimalService {

    List<AnimalFullDto> findAll(String bearerToken) throws IOException;

    AnimalFullDto findById(Long id, String token) throws IOException;

    AnimalFullDto create(AnimalCreateDto createDto, String token) throws IOException;

    AnimalFullDto update(AnimalUpdateDto updateDto, String token) throws IOException;

    void deleteById(Long id, String token);
}
