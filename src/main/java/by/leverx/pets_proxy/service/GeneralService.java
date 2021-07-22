package by.leverx.pets_proxy.service;

import by.leverx.pets_proxy.dto.AllDto;
import by.leverx.pets_proxy.dto.deal.DealDto;

import java.io.IOException;

public interface GeneralService {

    void deal(DealDto dealDto, String token) throws IOException;

    AllDto getAll(String token) throws IOException;
}
