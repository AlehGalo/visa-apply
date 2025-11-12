package com.visa.apply.service.v1;

import com.visa.apply.dto.LoadosfDto;
import com.visa.apply.mapper.LoadosfMapper;
import com.visa.apply.repository.v1.LoadosfRepository;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class VisaService {

    private final LoadosfRepository loadosfRepository;
    private final LoadosfMapper loadosfMapper;

    public List<LoadosfDto> getAll() {
        return loadosfRepository.findAll().stream().map(loadosfMapper::toDto).toList();
    }

    public void deleteAll(@NonNull List<Long> ids){
        loadosfRepository.removeAllByIdIn(ids);
    }
}
