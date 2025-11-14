package com.visa.apply.xml;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import java.io.FileNotFoundException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.util.Assert;
import org.springframework.util.ResourceUtils;

public class LoadXmlTest {

    @ParameterizedTest
    @ValueSource(strings = { "schema/xml/MOW00527980.xml", "schema/xml/MOW00527981.xml" })
    public void loadFile(String fileName) throws FileNotFoundException, JAXBException {
        var jaxbContext = JAXBContext.newInstance(DLoadosf.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        var file = ResourceUtils.getFile("classpath:".concat(fileName));
        var dLoadosf = (DLoadosf) unmarshaller.unmarshal(file);
        Assert.noNullElements(new Object[] { dLoadosf.dBasta }, "Elements should not be null");
    }
}
