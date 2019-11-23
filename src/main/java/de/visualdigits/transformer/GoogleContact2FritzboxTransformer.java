package de.visualdigits.transformer;

import de.visualdigits.fritzbox.model.phonebook.Features;
import de.visualdigits.fritzbox.model.phonebook.FritzboxContact;
import de.visualdigits.fritzbox.model.phonebook.FritzboxEmail;
import de.visualdigits.fritzbox.model.phonebook.Person;
import de.visualdigits.fritzbox.model.phonebook.Services;
import de.visualdigits.fritzbox.model.phonebook.Telephony;
import de.visualdigits.fritzbox.model.phonebook.TelephonyNumber;
import de.visualdigits.fritzbox.model.phonebook.Value;
import de.visualdigits.google.model.contacts.GoogleContact;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GoogleContact2FritzboxTransformer {

    private int emailId = 1;

    private int phoneNumberId = 1;

    private int nid = 1;

    private int uniqueId = 1;

    public GoogleContact2FritzboxTransformer() {
    }

    /**
     * Transforms the given Google contact into a FritzBox contact.
     *
     * @param  googleContact The Google contact to transform.
     * @return FritzboxContact
     */
    public FritzboxContact transform(GoogleContact googleContact) {
        final List<TelephonyNumber> numbers = new ArrayList<>();
        googleContact.getPhoneNumbers().forEach(p ->
                p.values().forEach(n ->
                        numbers.add(new TelephonyNumber()
                                .id(phoneNumberId++)
                                .type(p.type().toLowerCase())
                                .prio(0)
                                .number(n))
                        ));
        return new FritzboxContact()
            .category(new Value().value(googleContact.containsGroup("vip") ? "1" : "0"))
            .features(new Features().doorphone("0"))
            .modTime("")
            .services(new Services()
                    .nid(nid++)
                    .fritzboxEmails(
                    googleContact.getEmails()
                            .stream()
                            .map(e ->
                                    new FritzboxEmail()
                                            .id(emailId++)
                                            .classifier(e.type().toLowerCase().replace("*", "").trim())
                                            .email(e.value())
                            )
                            .collect(Collectors.toList())))
            .uniqueId(uniqueId++)
            .telephony(new Telephony()
                    .nid(nid++)
                    .numbers(numbers))
            .person(new Person()
                .realName(new Value().value(googleContact.getName())));
    }
}
