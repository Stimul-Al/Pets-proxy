package by.leverx.pets_proxy.service.impl;

import by.leverx.pets_proxy.dto.person.PersonCreateDto;
import by.leverx.pets_proxy.dto.person.PersonFullDto;
import by.leverx.pets_proxy.dto.person.PersonPreviewDto;
import by.leverx.pets_proxy.dto.person.PersonUpdateDto;
import by.leverx.pets_proxy.connection.impl.DestinationConnectionImpl;
import by.leverx.pets_proxy.service.PersonService;
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
public class PersonServiceImpl implements PersonService {

    private final static String PERSON = "/persons";
    private final static String HEADER_NAME = "Authorization";

    private final ObjectMapper objectMapper;
    private final DestinationConnectionImpl repository;


    @Override public List<PersonPreviewDto> findAll(String token) throws IOException {
        HttpGet httpGet = new HttpGet();
        httpGet.addHeader(HEADER_NAME, token);

        HttpResponse destination = repository.destination(httpGet, PERSON);
        InputStream content = destination.getEntity().getContent();

        return objectMapper.readValue(content, new TypeReference<List<PersonPreviewDto>>() {});
    }

    @Override public PersonFullDto findById(Long id, String token) throws IOException {
        HttpGet httpGet = new HttpGet();
        httpGet.addHeader(HEADER_NAME, token);

        HttpResponse response = repository.destination(httpGet, format("%s/%s", PERSON, id));
        InputStream content = response.getEntity().getContent();

        return objectMapper.readValue(content, PersonFullDto.class);
    }

    @Override public PersonFullDto create(PersonCreateDto createDto, String token) throws IOException {

        HttpPost httpPost = new HttpPost();
        httpPost.addHeader(HEADER_NAME, token);
        httpPost.setEntity(new StringEntity(objectMapper.writeValueAsString(createDto), APPLICATION_JSON));

        HttpResponse response = repository.destination(httpPost, PERSON);
        InputStream content = response.getEntity().getContent();

        return objectMapper.readValue(content, PersonFullDto.class);
    }

    @Override public PersonFullDto update(PersonUpdateDto updateDto, String token) throws IOException {
        HttpPut httpPut = new HttpPut();
        httpPut.addHeader(HEADER_NAME, token);
        httpPut.setEntity(new StringEntity(objectMapper.writeValueAsString(updateDto), APPLICATION_JSON));

        HttpResponse response = repository.destination(httpPut, PERSON);
        InputStream content = response.getEntity().getContent();

        return objectMapper.readValue(content, PersonFullDto.class);
    }

    @Override public void deleteById(Long id, String token) {
        HttpDelete httpDelete = new HttpDelete();
        httpDelete.addHeader(HEADER_NAME, token);

        try {
            repository.destination(httpDelete, format("%s/%s", PERSON, id));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
