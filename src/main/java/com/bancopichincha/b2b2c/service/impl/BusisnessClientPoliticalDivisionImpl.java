package com.bancopichincha.b2b2c.service.impl;

import com.bancopichincha.b2b2c.service.BusisnessClientService;
import com.bancopichincha.b2b2c.service.IBusisnessClientPoliticalDivision;
import com.bancopichincha.b2b2c.service.dto.BusisnessClientDto;
import com.bancopichincha.b2b2c.service.dto.PoliticalDivisionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class BusisnessClientPoliticalDivisionImpl implements IBusisnessClientPoliticalDivision {

    private final BusisnessClientService busisnessService;

    @Autowired
    public BusisnessClientPoliticalDivisionImpl(BusisnessClientService busisnessService) {
        this.busisnessService = busisnessService;
    }

    @Override
    public List<PoliticalDivisionDto> calculatedClientByPoliticalDivision(Integer busisness_id) {

        List<BusisnessClientDto> informationBusisness = busisnessService.findByBusisness(busisness_id);

        return informationBusisness
                .stream()
                .flatMap(infoBusisItem -> {

                    AtomicReference<Stream<String>> addressOp = new AtomicReference<>(Stream.empty());
                    infoBusisItem.getClient().getAddressOptional().ifPresent(address -> addressOp.set(Stream.of(address.getCity())));
                    return addressOp.get();

                })
                .distinct()
                .map(city -> PoliticalDivisionDto
                        .builder()
                        .city(city)
                        .value(
                                informationBusisness.stream().filter(infoBusisItem -> infoBusisItem.getClient().getAddressOptional().isPresent() &&
                                        infoBusisItem.getClient().getAddress().getCity().equals(city)).count()
                        )
                        .build())
                .collect(Collectors.toList());
    }
}
