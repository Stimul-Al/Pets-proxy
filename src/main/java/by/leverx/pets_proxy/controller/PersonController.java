package by.leverx.pets_proxy.controller;

import by.leverx.pets_proxy.dto.person.PersonCreateDto;
import by.leverx.pets_proxy.dto.person.PersonFullDto;
import by.leverx.pets_proxy.dto.person.PersonPreviewDto;
import by.leverx.pets_proxy.dto.person.PersonUpdateDto;
import by.leverx.pets_proxy.service.impl.PersonServiceImpl;
import com.sap.cloud.security.xsuaa.http.HttpHeaders;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/persons")
@RequiredArgsConstructor
public class PersonController {

    private final PersonServiceImpl service;

    @GetMapping("/{id}")
    public PersonFullDto findById(@PathVariable Long id, @RequestHeader(HttpHeaders.AUTHORIZATION) String bearerToken) throws Exception {
        return service.findById(id, bearerToken);
    }

    @GetMapping
    public List<PersonPreviewDto> findAll(@RequestHeader(HttpHeaders.AUTHORIZATION) String bearerToken) throws Exception {
        return service.findAll(bearerToken);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id, @RequestHeader(HttpHeaders.AUTHORIZATION) String bearerToken) {
        service.deleteById(id, bearerToken);
    }

    @PostMapping
    public PersonFullDto create(@RequestBody PersonCreateDto createDto, @RequestHeader(HttpHeaders.AUTHORIZATION) String bearerToken) throws Exception {
        return service.create(createDto, bearerToken);
    }

    @PutMapping
    public PersonFullDto update(@RequestBody PersonUpdateDto updateDto, @RequestHeader(HttpHeaders.AUTHORIZATION) String bearerToken) throws Exception {
        return service.update(updateDto, bearerToken);
    }
}
