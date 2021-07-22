package by.leverx.pets_proxy.controller;

import by.leverx.pets_proxy.dto.animal.AnimalCreateDto;
import by.leverx.pets_proxy.dto.animal.AnimalFullDto;
import by.leverx.pets_proxy.dto.animal.AnimalUpdateDto;
import by.leverx.pets_proxy.service.impl.AnimalServiceImpl;
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
@RequestMapping("/animals")
@RequiredArgsConstructor
public class AnimalController {

    private final AnimalServiceImpl service;

    @GetMapping("/{id}")
    public AnimalFullDto findById(@PathVariable Long id, @RequestHeader(HttpHeaders.AUTHORIZATION) String bearerToken) throws Exception {
        return service.findById(id, bearerToken);
    }

    @GetMapping
    public List<AnimalFullDto> findAll(@RequestHeader(HttpHeaders.AUTHORIZATION) String bearerToken) throws Exception {
        return service.findAll(bearerToken);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id, @RequestHeader(HttpHeaders.AUTHORIZATION) String bearerToken) {
        service.deleteById(id, bearerToken);
    }

    @PostMapping
    public AnimalFullDto create(@RequestBody AnimalCreateDto createDto, @RequestHeader(HttpHeaders.AUTHORIZATION) String bearerToken) throws Exception {
        return service.create(createDto, bearerToken);
    }

    @PutMapping
    public AnimalFullDto update(@RequestBody AnimalUpdateDto updateDto, @RequestHeader(HttpHeaders.AUTHORIZATION) String bearerToken) throws Exception {
        return service.update(updateDto, bearerToken);
    }
}
