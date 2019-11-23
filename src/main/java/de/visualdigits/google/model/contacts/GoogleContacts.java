package de.visualdigits.google.model.contacts;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvParser;
import com.google.common.annotations.VisibleForTesting;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Top level object which represents a contacts set of a Google contacts list.
 */
public class GoogleContacts implements Iterable<GoogleContact> {

    private TreeSet<GoogleContact> contacts = null;

    @VisibleForTesting
    GoogleContacts() {
        this.contacts = new TreeSet<>();
    }

    private GoogleContacts(TreeSet<GoogleContact> contacts) {
        this.contacts = contacts;
    }

    @Override
    public String toString() {
        return StringUtils.join(this.contacts, "\n");
    }

    /**
     * Unmarshals  the given file (which is treated as a goole formatted contacts csv file)
     * into a GoogleContacts object.
     * The unmarshalling result is also returned as a GoogleContacts object.
     *
     * @param fin The input file.
     * @return GoogleContacts
     */
    public static GoogleContacts unmarshal(File fin) {
        try (InputStream ins = new FileInputStream(fin)) {
            return unmarshal(ins);
        } catch (IOException e) {
            throw new IllegalStateException("Could not unmarshal file: " + fin, e);
        }
    }

    /**
     * Unmarshals  the given inputstream (which is treated as a goole formatted contacts csv file)
     * into a GoogleContacts object.
     * The unmarshalling result is also returned as a GoogleContacts object.
     * The caller is responsible for closing streams.
     *
     * @param ins The input stream.
     * @return GoogleContacts
     */
    public static GoogleContacts unmarshal(InputStream ins) {
        final TreeSet<GoogleContact> rows = new TreeSet<>();
        try {
            final CsvMapper mapper = new CsvMapper();
            mapper.enable(CsvParser.Feature.WRAP_AS_ARRAY);
            final MappingIterator<String[]> it = mapper.readerFor(String[].class).readValues(ins);
            String[] header = null;
            if (it.hasNext()) {
                header = it.next();
            }
            while (it.hasNext()) {
                final String[] row = it.next();
                final LinkedHashMap<String, String> mrow = new LinkedHashMap<>();
                for (int i = 0; i < header.length; i++) {
                    mrow.put(header[i], row[i]);
                }
                rows.add(new GoogleContact(mrow));
            }
        } catch (Exception e) {
            throw new IllegalStateException("Could not read csv", e);
        }
        return new GoogleContacts(rows);
    }

    /**
     * Marshals this instance into the given file as a Google formatted CSV file.
     * The marshalling result will also be returned as a string.
     *
     * @param fout The output file.
     * @return String
     */
    public final String marshal(File fout) {
        try (OutputStream outs = new FileOutputStream(fout)) {
            return marshal(outs);
        } catch (IOException e) {
            throw new IllegalStateException("Could not marshal file: " + fout, e);
        }
    }

    /**
     * Marshals this instance into the given stream as a Google formatted CSV file.
     * The marshalling result will also be returned as a string.
     * The caller is responsible for closing streams.
     *
     * @param outs The output stream.
     * @return String
     */
    public final String marshal(OutputStream outs) {
        String marshalled = "";
        if (!contacts.isEmpty()) {
            final StringBuilder sb = new StringBuilder();
            final List<GoogleContact> lcontacts = new ArrayList<>(contacts);
            final GoogleContact first = lcontacts.get(0);
            final Set<String> keys = first.keySet();
            sb.append(StringUtils.join(keys, ",")).append('\n');
            for (GoogleContact contact : contacts) {
                sb.append(StringUtils.join(contact.values(), ",")).append('\n');
            }
            marshalled = sb.toString().trim();
            if (outs != null) {
                try {
                    IOUtils.write(marshalled.getBytes(StandardCharsets.UTF_8), outs);
                } catch (IOException e) {
                    throw new IllegalStateException("Could not marshal into stream", e);
                }
            }
        }
        return marshalled;
    }

    public void add(LinkedHashMap<String, String> contact) {
        add(new GoogleContact(contact));
    }

    public void add(GoogleContact contact) {
        this.contacts.add(contact);
    }

    public Set<GoogleContact> getContacts() {
        return contacts;
    }

    /**
     * Returns a GoogleContacts object which will contain only
     * contacts which are member of the given group.
     * The returned object will be a deep copy of all entries
     * in question..
     *
     * @param group The group to filter for.
     * @return GoogleContacts
     */
    public GoogleContacts subSet(String group) {
        final TreeSet<GoogleContact> contacts = new TreeSet(this.contacts
                .stream()
                .filter(c ->
                        c.containsGroup(group)).map(GoogleContact::clone)
                .collect(Collectors.toSet()));
        return new GoogleContacts(contacts);
    }

    /**
     * Returns a GoogleContacts object which will contain only
     * contacts which are member of at least one of the given list of groups.
     * The returned object will be a deep copy of all entries
     * in question..
     *
     * @param groups The groups to filter for.
     * @return GoogleContacts
     */
    public GoogleContacts subSet(String... groups) {
        GoogleContacts subset = new GoogleContacts();
        for (String group : groups) {
            GoogleContacts other = subSet(group);
            subset.join(other);
        }
        return subset;
    }

    @Override
    public GoogleContacts clone() {
        final TreeSet<GoogleContact> clone = new TreeSet<>();
        contacts.forEach(c -> clone.add(c.clone()));
        return new GoogleContacts(clone);
    }

    public GoogleContact get(int index) {
        return new ArrayList<>(contacts).get(index);
    }

    /**
     * Merges the giiven GoogleContacts into this instance.
     * For the merge a deep copy of the other object is used.
     *
     * @param other
     */
    public void join(GoogleContacts other) {
        contacts.addAll(other.clone().contacts);
    }

    public int size() {
        return contacts.size();
    }

    @Override
    public Iterator<GoogleContact> iterator() {
        return contacts.iterator();
    }

    @Override
    public void forEach(Consumer<? super GoogleContact> action) {
        contacts.forEach(action);
    }

    public <R> Stream<R> map(Function<? super GoogleContact, ? extends R> mapper) {
        return contacts.stream().map(mapper);
    }
}
