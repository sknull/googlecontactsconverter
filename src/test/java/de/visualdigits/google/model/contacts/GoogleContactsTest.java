package de.visualdigits.google.model.contacts;

import de.visualdigits.util.TestUtil;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.LinkedHashMap;

import static org.junit.Assert.assertEquals;

public class GoogleContactsTest {

    private static final String[] KEYS = new String[] {
            "Name",
            "Given Name",
            "Additional Name",
            "Family Name",
            "Yomi Name",
            "Given Name Yomi",
            "Additional Name Yomi",
            "Family Name Yomi",
            "Name Prefix",
            "Name Suffix",
            "Initials",
            "Nickname",
            "Short Name",
            "Maiden Name",
            "Birthday",
            "Gender",
            "Location",
            "Billing Information",
            "Directory Server",
            "Mileage",
            "Occupation",
            "Hobby",
            "Sensitivity",
            "Priority",
            "Subject",
            "Notes",
            "Language",
            "Photo",
            "Group Membership",
            "E-mail 1 - Type",
            "E-mail 1 - Value",
            "E-mail 2 - Type",
            "E-mail 2 - Value",
            "IM 1 - Type",
            "IM 1 - Service",
            "IM 1 - Value",
            "Phone 1 - Type",
            "Phone 1 - Value",
            "Phone 2 - Type",
            "Phone 2 - Value",
            "Phone 3 - Type",
            "Phone 3 - Value",
            "Phone 4 - Type",
            "Phone 4 - Value",
            "Address 1 - Type",
            "Address 1 - Formatted",
            "Address 1 - Street",
            "Address 1 - City",
            "Address 1 - PO Box",
            "Address 1 - Region",
            "Address 1 - Postal Code",
            "Address 1 - Country",
            "Address 1 - Extended Address",
            "Organization 1 - Type",
            "Organization 1 - Name",
            "Organization 1 - Yomi Name",
            "Organization 1 - Title",
            "Organization 1 - Department",
            "Organization 1 - Symbol",
            "Organization 1 - Location",
            "Organization 1 - Job Description",
            "Website 1 - Type",
            "Website 1 - Value",
            "Event 1 - Type",
            "Event 1 - Value"};

    @Test
    public void testMarshal() {
        final String expectedCsv = TestUtil.readResourceFile("de/visualdigits/google/model/contacts/expected_marshalled_contacts.csv");
        final GoogleContacts contacts = new GoogleContacts();
        for (int i = 1; i <= 10; i++) {
            final LinkedHashMap<String, String> map = new LinkedHashMap<>();
            for (String key : KEYS) {
                map.put(key, key + "_" + StringUtils.leftPad(String.valueOf(i), 2, '0'));
            }
            contacts.add(map);
        }
        final String marshalledCsv = contacts.marshal((OutputStream) null);
        assertEquals("Marshalled CSV not as expected", expectedCsv, marshalledCsv);
    }

    @Test
    public void testUnmarshal() {
        final InputStream ins = TestUtil.getInputStreamForResource("de/visualdigits/google/model/contacts/expected_marshalled_contacts.csv");
        final GoogleContacts contacts = GoogleContacts.unmarshal(ins);
        for (int i = 0; i < 10; i++) {
            GoogleContact contact = contacts.get(i);
            for (String key : KEYS) {
                assertEquals("Value not as expected", key + "_" + StringUtils.leftPad(String.valueOf(i + 1), 2, '0'), contact.get(key));
            }
        }
    }

    @Test
    public void testJoin() {
        final String expectedJoinedCsv = TestUtil.readResourceFile("de/visualdigits/google/model/contacts/expected_joined_1.csv");
        final InputStream ins1 = TestUtil.getInputStreamForResource("de/visualdigits/google/model/contacts/contacts_1.csv");
        final InputStream ins2 = TestUtil.getInputStreamForResource("de/visualdigits/google/model/contacts/contacts_2.csv");
        final GoogleContacts c1 = GoogleContacts.unmarshal(ins1);
        assertEquals(3, c1.size());
        final GoogleContacts c2 = GoogleContacts.unmarshal(ins2);
        assertEquals(7, c2.size());
        c1.join(c2);
        final String joinedCsv = c1.marshal((OutputStream) null);
        assertEquals(10, c1.size());
        assertEquals(expectedJoinedCsv, joinedCsv);
    }

    @Test
    public void testJoinGroup1And3() {
        final String expectedJoinedCsv = TestUtil.readResourceFile("de/visualdigits/google/model/contacts/expected_joined_2.csv");
        final InputStream ins1 = TestUtil.getInputStreamForResource("de/visualdigits/google/model/contacts/contacts_1.csv");
        final InputStream ins2 = TestUtil.getInputStreamForResource("de/visualdigits/google/model/contacts/contacts_3.csv");
        final GoogleContacts c1 = GoogleContacts.unmarshal(ins1).subSet("g1", "g3");
        assertEquals(2, c1.size());
        final GoogleContacts c2 = GoogleContacts.unmarshal(ins2).subSet("g1", "g3");
        assertEquals(5, c2.size());
        c1.join(c2);
        final String joinedCsv = c1.marshal((OutputStream) null);
        assertEquals(5, c1.size());
        assertEquals(expectedJoinedCsv, joinedCsv);
    }
}