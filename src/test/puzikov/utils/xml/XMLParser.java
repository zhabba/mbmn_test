package test.puzikov.utils.xml;

import test.puzikov.exceptions.TestException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;

public class XMLParser {
    private Unmarshaller um;
    public XMLParser (Class... classes) {
        try {
            JAXBContext context = JAXBContext.newInstance(classes);
            um = context.createUnmarshaller();
        } catch (JAXBException e) {
            throw new TestException("Unmarshaller init failed", e);
        }
    }

    public <T> T unmarshal (String xml) {
        try {
            return (T) um.unmarshal(new StringReader(xml));
        } catch (JAXBException e) {
            throw new TestException("Unmarshalling failed" + e.toString(), e);
        }
    }
}
