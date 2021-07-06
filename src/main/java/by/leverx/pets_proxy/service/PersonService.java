package by.leverx.pets_proxy.service;

import by.leverx.pets_proxy.dto.person.PersonCreateDto;
import by.leverx.pets_proxy.dto.person.PersonFullDto;
import by.leverx.pets_proxy.dto.person.PersonPreviewDto;
import by.leverx.pets_proxy.dto.person.PersonUpdateDto;
import by.leverx.pets_proxy.repository.DestinationRepository;
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
public class PersonService {

    private final static String PERSON = "/persons";

    private final ObjectMapper objectMapper;
    private final DestinationRepository repository;


    public List<PersonPreviewDto> findAll() throws IOException {
        HttpGet httpGet = new HttpGet();

        HttpResponse destination = repository.destination(httpGet, PERSON);
        InputStream content = destination.getEntity().getContent();

        return objectMapper.readValue(content, new TypeReference<List<PersonPreviewDto>>() {});
    }

    public PersonFullDto findById(Long id) throws IOException {
        HttpGet httpGet = new HttpGet();

        HttpResponse response = repository.destination(httpGet, format("%s/%s", PERSON, id));
        InputStream content = response.getEntity().getContent();

        return objectMapper.readValue(content, PersonFullDto.class);
    }

    public PersonFullDto create(PersonCreateDto createDto) throws IOException {
        String httpEntity = objectMapper.writeValueAsString(createDto);

        HttpPost httpPost = new HttpPost();
        httpPost.setEntity(new StringEntity(httpEntity, APPLICATION_JSON));

        HttpResponse response = repository.destination(httpPost, PERSON);
        InputStream content = response.getEntity().getContent();

        return objectMapper.readValue(content, PersonFullDto.class);
    }

    public PersonFullDto update(PersonUpdateDto updateDto) throws IOException {
        String httpEntity = objectMapper.writeValueAsString(updateDto);

        HttpPut httpPut = new HttpPut();
        httpPut.setEntity(new StringEntity(httpEntity, APPLICATION_JSON));

        HttpResponse response = repository.destination(httpPut, PERSON);
        InputStream content = response.getEntity().getContent();

        return objectMapper.readValue(content, PersonFullDto.class);
    }

    public void deleteById(Long id) {
        HttpDelete httpDelete = new HttpDelete();

        try {
            repository.destination(httpDelete, format("%s/%s", PERSON, id));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
