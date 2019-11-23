package de.visualdigits.fritzbox.model.phonebook;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.apache.commons.io.IOUtils;
import org.xml.sax.InputSource;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.transform.sax.SAXSource;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

/**
 * Top level object which represents a FritzBox phonebook.
 */
@XmlRootElement(name = "phonebooks")
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
@Accessors(fluent = true)
@ToString
@NoArgsConstructor
public class Phonebooks implements Iterable<Phonebook> {

    @XmlElement(name = "phonebook")
    private List<Phonebook> phonebooks;

    /**
     * Unmarshals the given input file (which is treated as a valid FritzBox phonebook XML).
     * Unfortunately their is no schema, therefore no validation can be done.
     * The unmarshalling result is also returned as a Phonebooks object.
     *
     * @param fin The input file.
     * @return Phonebooks.
     */
    public static Phonebooks unmarshal(File fin) {
        try (InputStream ins = new FileInputStream(fin)) {
            return unmarshal(ins);
        } catch (IOException
                e) {
            throw new IllegalStateException("Could not unmarshal file: " + fin, e);
        }
    }

    /**
     * Unmarshals the given input stream (which is treated as a valid FritzBox phonebook XML).
     * Unfortunately their is no schema, therefore no validation can be done.
     * The unmarshalling result is also returned as a Phonebooks object.
     * The caller is responsible for closing streams.
     *
     * @param ins The input stream.
     * @return Phonebooks.
     */
    public static Phonebooks unmarshal(InputStream ins) {
        final Phonebooks phonebooks;
        try {
            final JAXBContext jaxbContext = JAXBContext.newInstance(Phonebooks.class);
            final Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            final InputSource is = new InputSource(ins);
            final SAXSource source = new SAXSource(is);
            phonebooks = (Phonebooks) unmarshaller.unmarshal(source);
        } catch (JAXBException e) {
            throw new IllegalStateException("Could not unmarshall phonebook", e);
        }
        return phonebooks;
    }

    /**
     * Marshals this instance into the given output file as a FritzBox XML file.
     * The marshalling result will also be returned as a string.
     *
     * @param fout The output file.
     * @return String
     */
    public String marshal(File fout) {
        try (OutputStream outs = new FileOutputStream(fout)) {
            return marshal(outs);
        } catch (IOException e) {
            throw new IllegalStateException("Could not marshal file: " + fout, e);
        }
    }

    /**
     * Marshals this instance into the given output stream as a FritzBox XML file.
     * The marshalling result will also be returned as a string.
     * The caller is responsible for closing streams.
     *
     * @param outs The output stream.
     * @return String
     */
    public String marshal(OutputStream outs) {
        InputStream ins = null;
        try (final OutputStream baos = new ByteArrayOutputStream()) {
            final JAXBContext jaxbContext = JAXBContext.newInstance(Phonebooks.class);
            final Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(this, baos);
            final String marshalled = baos.toString().trim();
            ins = new ByteArrayInputStream(marshalled.getBytes(StandardCharsets.UTF_8));
            if (outs != null) {
                IOUtils.copy(ins, outs);
            }
            return marshalled;
        } catch (JAXBException | IOException e) {
            throw new IllegalStateException("Could not marshall phonebook", e);
        } finally {
            if (ins != null) {
                try {
                    ins.close();
                } catch (IOException e) {
                    throw new IllegalStateException("Could not close stream", e);
                }
            }
        }
    }

    @Override
    public Iterator<Phonebook> iterator() {
        return phonebooks.iterator();
    }

    @Override
    public void forEach(Consumer<? super Phonebook> action) {
        phonebooks.forEach(action);
    }
}
