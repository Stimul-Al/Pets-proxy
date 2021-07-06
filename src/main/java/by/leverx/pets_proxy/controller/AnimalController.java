package by.leverx.pets_proxy.controller;

import by.leverx.pets_proxy.dto.animal.AnimalCreateDto;
import by.leverx.pets_proxy.dto.animal.AnimalFullDto;
import by.leverx.pets_proxy.dto.animal.AnimalUpdateDto;
import by.leverx.pets_proxy.service.AnimalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/animals")
@RequiredArgsConstructor
public class AnimalController {

    private final AnimalService service;

    @GetMapping("/{id}")
    public AnimalFullDto findById(@PathVariable Long id) throws Exception {
        return service.findById(id);
    }

    @GetMapping
    public List<AnimalFullDto> findAll() throws Exception {
        return service.findAll();
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }

    @PostMapping
    public AnimalFullDto create(@RequestBody AnimalCreateDto createDto) throws Exception {
        return service.create(createDto);
    }

    @PutMapping
    public AnimalFullDto update(@RequestBody AnimalUpdateDto updateDto) throws Exception {
        return service.update(updateDto);
    }
}
