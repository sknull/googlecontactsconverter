package de.visualdigits.transformer;

import de.visualdigits.fritzbox.model.phonebook.FritzboxContact;
import de.visualdigits.google.model.contacts.GoogleContact;
import de.visualdigits.transformer.GoogleContact2FritzboxTransformer;
import org.junit.Test;

import java.util.LinkedHashMap;

import static org.junit.Assert.assertEquals;

public class GoogleContact2FritzboxTransformerTest {

    @Test
    public void testTransformer() {
        final GoogleContact2FritzboxTransformer transformer = new GoogleContact2FritzboxTransformer();
        final LinkedHashMap<String, String> map = new LinkedHashMap<String, String>() {
            {
                put("Name", "Foo Bar");
                put("Group Membership", "vip ::: phonebook ::: * friends ::: * myContacts");

                put("Phone 1 - Type", "Work");
                put("Phone 1 - Value", "1");
                put("Phone 2 - Type", "Mobile");
                put("Phone 2 - Value", "2");
                put("Phone 3 - Type", "Home");
                put("Phone 3 - Value", "3");
                put("Phone 4 - Type", "Foo");
                put("Phone 4 - Value", "4");

                put("E-mail 1 - Type", "Work");
                put("E-mail 1 - Value", "foo.bar-work@foobaz.com");
                put("E-mail 3 - Type", "Home");
                put("E-mail 3 - Value", "foo.bar-home@foobaz.com");
                put("E-mail 4 - Type", "Foo");
                put("E-mail 4 - Value", "foo.bar-foo@foobaz.com");
            }
        };
        final FritzboxContact fritzboxContact = transformer.transform(new GoogleContact(map));
        assertEquals("FritzboxContact(category=Value(value=1), person=Person(realName=Value(value=Foo Bar)), telephony=Telephony(nid=2, numbers=[TelephonyNumber(type=work, prio=0, id=1, quickdial=0, number=1), TelephonyNumber(type=mobile, prio=0, id=2, quickdial=0, number=2), TelephonyNumber(type=home, prio=0, id=3, quickdial=0, number=3), TelephonyNumber(type=foo, prio=0, id=4, quickdial=0, number=4)]), services=Services(nid=1, fritzboxEmails=[FritzboxEmail(classifier=work, id=1, email=foo.bar-work@foobaz.com), FritzboxEmail(classifier=home, id=2, email=foo.bar-home@foobaz.com), FritzboxEmail(classifier=foo, id=3, email=foo.bar-foo@foobaz.com)]), setup=null, features=Features(doorphone=0), modTime=, uniqueId=1)", fritzboxContact.toString());
    }
}