package by.leverx.pets_proxy.service;

import by.leverx.pets_proxy.dto.animal.AnimalCreateDto;
import by.leverx.pets_proxy.dto.animal.AnimalFullDto;
import by.leverx.pets_proxy.dto.animal.AnimalUpdateDto;
import by.leverx.pets_proxy.repository.DestinationRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sap.cloud.security.xsuaa.http.HttpHeader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.Header;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

import static java.lang.String.format;
import static org.apache.http.entity.ContentType.APPLICATION_JSON;

@Slf4j
@Service
@RequiredArgsConstructor
public class AnimalService {

    private final static String ANIMALS = "/animals";
    private final static String HEADER_NAME = "Authorization";

    private final ObjectMapper objectMapper;
    private final DestinationRepository repository;

    public List<AnimalFullDto> findAll(String bearerToken) throws IOException {
        HttpGet httpGet = new HttpGet();

        httpGet.addHeader(HEADER_NAME, bearerToken);
        HttpResponse response = repository.destination(httpGet, ANIMALS);

        return objectMapper.readValue(EntityUtils.toString(response.getEntity()), new TypeReference<List<AnimalFullDto>>() {});
    }

    public AnimalFullDto findById(Long id, String token) throws IOException {
        HttpGet httpGet = new HttpGet();

        httpGet.addHeader(HEADER_NAME, token);

        HttpResponse response = repository.destination(httpGet, format("%s/%s", ANIMALS,  id));

        return objectMapper.readValue(EntityUtils.toString(response.getEntity()), AnimalFullDto.class);
    }

    public AnimalFullDto create(AnimalCreateDto createDto, String token) throws IOException {
        String httpEntity = objectMapper.writeValueAsString(createDto);

        HttpPost httpPost = new HttpPost();
        httpPost.addHeader(HEADER_NAME, token);
        httpPost.setEntity(new StringEntity(httpEntity, APPLICATION_JSON));

        HttpResponse response = repository.destination(httpPost, ANIMALS);
        InputStream content = response.getEntity().getContent();

        return objectMapper.readValue(content, AnimalFullDto.class);
    }

    public AnimalFullDto update(AnimalUpdateDto updateDto, String token) throws IOException {
        String httpEntity = objectMapper.writeValueAsString(updateDto);

        HttpPut httpPut = new HttpPut();
        httpPut.addHeader(HEADER_NAME, token);
        httpPut.setEntity(new StringEntity(httpEntity, APPLICATION_JSON));

        HttpResponse response = repository.destination(httpPut, ANIMALS);
        InputStream content = response.getEntity().getContent();

        return objectMapper.readValue(content, AnimalFullDto.class);
    }

    public void deleteById(Long id, String token) {
        HttpDelete httpDelete = new HttpDelete();
        httpDelete.addHeader(HEADER_NAME, token);

        try {
            repository.destination(httpDelete, format("%s/%s", ANIMALS, id));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
