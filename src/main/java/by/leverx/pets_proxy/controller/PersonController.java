package by.leverx.pets_proxy.controller;

import by.leverx.pets_proxy.dto.person.PersonCreateDto;
import by.leverx.pets_proxy.dto.person.PersonFullDto;
import by.leverx.pets_proxy.dto.person.PersonPreviewDto;
import by.leverx.pets_proxy.dto.person.PersonUpdateDto;
import by.leverx.pets_proxy.service.PersonService;
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
@RequestMapping("/persons")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService service;

    @GetMapping("/{id}")
    public PersonFullDto findById(@PathVariable Long id) throws Exception {
        return service.findById(id);
    }

    @GetMapping
    public List<PersonPreviewDto> findAll() throws Exception {
        return service.findAll();
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }

    @PostMapping
    public PersonFullDto create(@RequestBody PersonCreateDto createDto) throws Exception {
        return service.create(createDto);
    }

    @PutMapping
    public PersonFullDto update(@RequestBody PersonUpdateDto updateDto) throws Exception {
        return service.update(updateDto);
    }
}
