package de.visualdigits.google.model.contacts;

import org.junit.Test;

import java.util.LinkedHashMap;

import static org.junit.Assert.assertEquals;

public class GoogleContactTest {

    @Test
    public void testConstructor() {
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
        final GoogleContact contact = new GoogleContact(map);
        assertEquals("Contact not as expected", "Name='Foo Bar', givenName='null', additionalName='null', familyName='null', yomiName='null', givenNameYomi='null', additionalNameYomi='null', familyNameYomi='null', namePrefix='null', nameSuffix='null', initials='null', nickname='null', shortName='null', maidenName='null', birthday='null', gender='null', location='null', billingInformation='null', directoryServer='null', mileage='null', occupation='null', hobby='null', sensitivity='null', priority='null', subject='null', notes='null', language='null', photo='null', groups=[* friends, * myContacts, phonebook, vip], GoogleEmail(type=Work, value=foo.bar-work@foobaz.com), GoogleEmail(type=Home, value=foo.bar-home@foobaz.com), GoogleEmail(type=Foo, value=foo.bar-foo@foobaz.com), GooglePhoneNumber(type=Work, values=[1]), GooglePhoneNumber(type=Mobile, values=[2]), GooglePhoneNumber(type=Home, values=[3]), GooglePhoneNumber(type=Foo, values=[4])", contact.toString());
    }
}