package com.visa.apply.controller;

import com.visa.apply.dto.LoadosfDto;
import com.visa.apply.service.v1.VisaService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Validated
@RestController
@AllArgsConstructor
@RequestMapping(value = "/visa")
@Secured({"ROLE_USER"})
@Slf4j
public class VisaController {

    private final VisaService visaService;

    @GetMapping
    public List<LoadosfDto> getAll() {
        return visaService.getAll();
    }

    @DeleteMapping
    public void deleteApplications(@NonNull @RequestBody List<Long> ids) {
        visaService.deleteAll(ids);
    }

}
