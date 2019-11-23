package de.visualdigits.transformer;

import de.visualdigits.fritzbox.model.phonebook.Phonebooks;
import de.visualdigits.util.TestUtil;
import org.junit.Test;

import java.io.InputStream;
import java.io.OutputStream;

import static org.junit.Assert.assertEquals;

public class GoogleContacts2FritzboxTransformerTest {

    @Test
    public void testTransformer() throws Exception {
        final String expectedPhonebook = TestUtil.readResourceFile("de/visualdigits/google/model/contacts/expected_transformed_phonebook.xml");
        final InputStream ins = TestUtil.getInputStreamForResource("de/visualdigits/google/model/contacts/expected_marshalled_contacts.csv");
        final GoogleContacts2FritzboxTransformer transformer = new GoogleContacts2FritzboxTransformer();
        final Phonebooks phonebooks = transformer.transform("Google Phonebook", ins, null);
        final String marshalledPhonebook = phonebooks.marshal((OutputStream) null);
        assertEquals("Transformed phonebook not as expected", expectedPhonebook, marshalledPhonebook);
    }
}