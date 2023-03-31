package com.bancopichincha.b2b2c.service.impl.generateinformation;

import com.bancopichincha.b2b2c.service.IGenerateInformationAllClient;
import com.bancopichincha.b2b2c.service.dto.ClientDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenerateInformationAllClientImpl implements IGenerateInformationAllClient {

    @Override
    public Double getPorcents(List<ClientDto> clientDto, Integer total) {
        double resu =  (clientDto.size() / (double) total);
        return resu * 100;
    }

}
