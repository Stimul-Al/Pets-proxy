package by.leverx.pets_proxy.service;

import by.leverx.pets_proxy.dto.animal.AnimalCreateDto;
import by.leverx.pets_proxy.dto.animal.AnimalFullDto;
import by.leverx.pets_proxy.dto.animal.AnimalUpdateDto;
import by.leverx.pets_proxy.repository.DestinationRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static java.lang.String.format;
import static org.apache.http.entity.ContentType.APPLICATION_JSON;

@Slf4j
@Service
@RequiredArgsConstructor
public class AnimalService {

    private final static String ANIMALS = "/animals";

    private final ObjectMapper objectMapper;
    private final DestinationRepository repository;

    public List<AnimalFullDto> findAll() throws IOException {
        HttpGet httpGet = new HttpGet();

        HttpResponse destination = repository.destination(httpGet, ANIMALS);
        InputStream content = destination.getEntity().getContent();

        return objectMapper.readValue(content, new TypeReference<List<AnimalFullDto>>() {
        });
    }

    public AnimalFullDto findById(Long id) throws IOException {
        HttpGet httpGet = new HttpGet();

        HttpResponse response = repository.destination(httpGet, format("%s/%s", ANIMALS,  id));
        InputStream content = response.getEntity().getContent();

        return objectMapper.readValue(content, AnimalFullDto.class);
    }

    public AnimalFullDto create(AnimalCreateDto createDto) throws IOException {
        String httpEntity = objectMapper.writeValueAsString(createDto);

        HttpPost httpPost = new HttpPost();
        httpPost.setEntity(new StringEntity(httpEntity, APPLICATION_JSON));

        HttpResponse response = repository.destination(httpPost, ANIMALS);
        InputStream content = response.getEntity().getContent();

        return objectMapper.readValue(content, AnimalFullDto.class);
    }

    public AnimalFullDto update(AnimalUpdateDto updateDto) throws IOException {
        String httpEntity = objectMapper.writeValueAsString(updateDto);

        HttpPut httpPut = new HttpPut();
        httpPut.setEntity(new StringEntity(httpEntity, APPLICATION_JSON));

        HttpResponse response = repository.destination(httpPut, ANIMALS);
        InputStream content = response.getEntity().getContent();

        return objectMapper.readValue(content, AnimalFullDto.class);
    }

    public void deleteById(Long id) {
        HttpDelete httpDelete = new HttpDelete();

        try {
            repository.destination(httpDelete, format("%s/%s", ANIMALS, id));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
