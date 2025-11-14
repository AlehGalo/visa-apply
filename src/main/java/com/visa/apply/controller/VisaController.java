package com.visa.apply.controller;

import com.visa.apply.dto.LoadosfDto;
import com.visa.apply.entity.Loadosf;
import com.visa.apply.repository.DLoadosfEntityRepository;
import com.visa.apply.repository.v1.LoadosfRepository;
import com.visa.apply.service.mapper.jaxb.LoadosfJaxbMapper;
import com.visa.apply.service.v1.VisaService;
import com.visa.apply.xml.DLoadosf;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/visa")
@Secured({ "ROLE_USER" })
@Slf4j
public class VisaController {

    private final VisaService visaService;
    private final LoadosfRepository loadosfRepository;
    private final DLoadosfEntityRepository dLoadosfEntityRepository;
    private final LoadosfJaxbMapper mapper;

    @GetMapping
    public List<LoadosfDto> getAll() {
        return visaService.getAll();
    }

    @GetMapping("/export/xml")
    public ResponseEntity<Resource> streamCsvExport() throws JAXBException {
        List<Loadosf> all = loadosfRepository.findAll();
        List<DLoadosf> jaxbDto = mapper.toJaxbDto(all);
        JAXBContext jaxbContext = JAXBContext.newInstance(DLoadosf.class);

        // Create a Marshaller
        Marshaller marshaller = jaxbContext.createMarshaller();

        // Optional: Set formatting for pretty printing
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        // Marshal the object to an XML string
        //        StringWriter sw = new StringWriter();
        ByteArrayOutputStream os = new ByteArrayOutputStream();

        //        JAXBElement<LoadOsf> wrappedObj = new JAXBElement<>(new QName("namespaceURI", "localPart"), LoadOsf.class, jaxbDto.get(0));

        marshaller.marshal(jaxbDto.get(0), os);
        //        String xmlString = sw.toString();

        //
        //        ByteArrayOutputStream out = new ByteArrayOutputStream();
        //        PrintWriter writer = new PrintWriter(out);
        //
        //        writer.println("id,name,email");
        //        for (int i = 1; i <= 100; i++) {
        //            writer.printf("%d,User%d,user%d@example.com%n", i, i, i);
        //        }
        //        writer.flush();

        InputStream input = new ByteArrayInputStream(os.toByteArray());
        Resource resource = new InputStreamResource(input);

        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"users.csv\"")
            .contentLength(os.size())
            .contentType(MediaType.TEXT_PLAIN)
            .body(resource);
    }

    @DeleteMapping
    public void deleteApplications(@NonNull @RequestBody List<Long> ids) {
        visaService.deleteAll(ids);
    }
}
