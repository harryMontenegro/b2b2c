package com.bancopichincha.b2b2c.service.impl.generateinformation;

import com.bancopichincha.b2b2c.service.IGenerateInformationPorcentClient;
import com.bancopichincha.b2b2c.service.dto.ClientDto;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.List;

@Service
public class GenerateInformationPorcentsClientImpl implements IGenerateInformationPorcentClient {

    @Override
    public Double getPorcents(List<ClientDto> clientDto, Integer total) {
        double resu =  (clientDto.size() / (double) total);
        DecimalFormat df = new DecimalFormat("##.00");
        return Double.valueOf(df.format(resu * 100));
    }

}
