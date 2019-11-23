package de.visualdigits.fritzbox.model.phonebook;

import de.visualdigits.util.TestUtil;
import org.junit.Test;

import java.io.InputStream;
import java.io.OutputStream;

import static org.junit.Assert.assertEquals;

public class PhonebooksTest {

    @Test
    public void testUnmarshallMarshallFritzPhonebooks() throws Exception {
        final InputStream ins = TestUtil.getInputStreamForResource("de/visualdigits/fritzbox/model/phonebook/FRITZ.Box_Telefonbuch.xml");
        final String expectedXml = TestUtil.readResourceFile("de/visualdigits/fritzbox/model/phonebook/expected_marshalled_phonebook.xml");
        final Phonebooks phonebooks = Phonebooks.unmarshal(ins);
        final String marshalledXml = phonebooks.marshal((OutputStream) null);
        assertEquals("Marshalled XML not as expected", expectedXml, marshalledXml);
    }
}