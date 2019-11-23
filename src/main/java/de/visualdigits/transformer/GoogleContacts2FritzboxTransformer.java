package de.visualdigits.transformer;

import de.visualdigits.fritzbox.model.phonebook.FritzboxContact;
import de.visualdigits.fritzbox.model.phonebook.Phonebook;
import de.visualdigits.fritzbox.model.phonebook.Phonebooks;
import de.visualdigits.google.model.contacts.GoogleContacts;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GoogleContacts2FritzboxTransformer {

    /**
     * Transforms the given input file (which is treated as a Google formatted CSV file) into the
     * given output file as a FritzBox phonebook XML file.
     *
     * @param name The phonebook name which will be displayed in the FritzBox.
     * @param fin The input file.
     * @param fout The output file.
     * @param groups The Google contact groups to include.
     */
    public Phonebooks transform(String name, File fin, File fout, String... groups) {
        try (InputStream ins = new FileInputStream(fin); OutputStream outs = new FileOutputStream(fout)) {
            return transform(name, ins, outs, groups);
        } catch (IOException e) {
            throw new IllegalStateException("Could not transorm files: " + fin + " -> "+ fout, e);
        }
    }

    /**
     * Transforms the given input stream (which is treated as a Google formatted CSV file) into the
     * given output stream as a FritzBox phonebook XML file.
     * The caller is responsible for closing streams.
     *
     * @param name The phonebook name which will be displayed in the FritzBox.
     * @param ins The input stream.
     * @param outs The output stream.
     * @param groups The Google contact groups to include.
     */
    public Phonebooks transform(String name, InputStream ins, OutputStream outs, String... groups) {
        GoogleContacts contacts = GoogleContacts.unmarshal(ins);
        if (groups != null && groups.length > 0) {
            contacts = contacts.subSet(groups);
        }
        final GoogleContact2FritzboxTransformer transformer = new GoogleContact2FritzboxTransformer();
        final List<FritzboxContact> fritzboxContacts = contacts
                .map(transformer::transform)
                .collect(Collectors.toList())
                .stream()
                .filter(c -> !c.telephony().numbers().isEmpty())
                .collect(Collectors.toList());
        final List<Phonebook> phonebookList = new ArrayList<>();
        phonebookList.add(new Phonebook()
            .name(name)
            .owner(1)
            .fritzboxContacts(fritzboxContacts));
        final Phonebooks phonebooks = new Phonebooks()
                .phonebooks(phonebookList);
        if (outs != null) {
            phonebooks.marshal(outs);
        }
        return phonebooks;
    }
}
