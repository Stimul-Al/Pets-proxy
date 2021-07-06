package by.leverx.pets_proxy;

import by.leverx.pets_proxy.dto.animal.AnimalCreateDto;
import by.leverx.pets_proxy.dto.person.PersonCreateDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class PersonMapperTest {

    @Test
    void parseToStringJsonFromPersonCreateDto() throws IOException {
        //given
        ObjectMapper objectMapper = new ObjectMapper();

        String email = "alex@gmail.com";
        AnimalCreateDto animalCreateDto = AnimalCreateDto.builder()
                .name("Sam")
                .typeAnimal("CAT")
                .build();

        PersonCreateDto personCreateDto = PersonCreateDto.builder()
                .name("Alex")
                .email(email)
                .password("qwerty12345")
                .animals(asList(animalCreateDto))
                .build();

        HttpPost post = new HttpPost("https://localhost:8080/persons");

        //when
        String json = objectMapper.writeValueAsString(personCreateDto);
        StringEntity entity = new StringEntity(json);

        post.setEntity(entity);

        //then
        assertNotNull(json);
        assertTrue(json.contains(personCreateDto.getEmail()));
        assertNotNull(post.getEntity());
    }

}
