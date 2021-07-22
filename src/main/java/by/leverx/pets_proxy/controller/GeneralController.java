package by.leverx.pets_proxy.controller;

import by.leverx.pets_proxy.dto.AllDto;
import by.leverx.pets_proxy.dto.deal.DealDto;
import by.leverx.pets_proxy.service.impl.GeneralServiceImpl;
import com.sap.cloud.security.xsuaa.http.HttpHeaders;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/general")
@RequiredArgsConstructor
public class GeneralController {

    private final GeneralServiceImpl generalService;

    @PostMapping("/deal")
    public void deal(@RequestBody DealDto dealDto, @RequestHeader(HttpHeaders.AUTHORIZATION) String bearerToken) throws Exception {
        generalService.deal(dealDto, bearerToken);
    }

    @GetMapping("/all")
    public AllDto getAll(@RequestHeader(HttpHeaders.AUTHORIZATION) String bearerToken) throws IOException {
        return generalService.getAll(bearerToken);
    }
}
