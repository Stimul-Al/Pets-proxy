package by.leverx.pets_proxy.service;

import by.leverx.pets_proxy.dto.AllDto;
import by.leverx.pets_proxy.dto.animal.AnimalFullDto;
import by.leverx.pets_proxy.dto.deal.DealDto;
import by.leverx.pets_proxy.dto.person.PersonPreviewDto;
import by.leverx.pets_proxy.repository.DestinationRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toSet;
import static org.apache.http.entity.ContentType.APPLICATION_JSON;

@Slf4j
@Service
@RequiredArgsConstructor
public class GeneralService {

    private final static String DEAL = "/deals";
    private final static String PERSON = "/persons";

    private final DestinationRepository repository;
    private final ObjectMapper objectMapper;

    public void deal(DealDto dealDto) throws IOException {
        String entity = objectMapper.writeValueAsString(dealDto);

        HttpPost httpPost = new HttpPost();
        httpPost.setEntity(new StringEntity(entity, APPLICATION_JSON));

        repository.destination(httpPost, DEAL);
    }

    public AllDto getAll() throws IOException {
        HttpGet httpGet = new HttpGet();
        HttpResponse response = repository.destination(httpGet, PERSON);
        InputStream content = response.getEntity().getContent();

        List<PersonPreviewDto> persons = objectMapper
                .readValue(content, new TypeReference<List<PersonPreviewDto>>() {
                });

        Set<AnimalFullDto> animals = new HashSet<>();
        persons.forEach(person -> animals.addAll(person.getAnimals()));

        Set<AnimalFullDto> dogs = animals.stream()
                .filter(dog -> "DOG".equals(dog.getTypeAnimal()))
                .collect(toSet());

        Set<AnimalFullDto> cats = animals.stream()
                .filter(dog -> "CAT".equals(dog.getTypeAnimal()))
                .collect(toSet());

        return AllDto.builder()
                .persons(persons)
                .animals(animals)
                .dogs(dogs)
                .cats(cats)
                .build();
    }
}
