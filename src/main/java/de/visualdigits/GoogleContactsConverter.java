package de.visualdigits;

import de.visualdigits.transformer.GoogleContacts2FritzboxTransformer;
import net.sourceforge.argparse4j.ArgumentParsers;
import net.sourceforge.argparse4j.inf.ArgumentParser;
import net.sourceforge.argparse4j.inf.ArgumentParserException;
import net.sourceforge.argparse4j.inf.Namespace;

import java.io.File;
import java.util.List;

public class GoogleContactsConverter {

    public static void main(String[] args) {
        ArgumentParser parser = ArgumentParsers.newFor("Google Contacts Converter").build()
                .defaultHelp(true)
                .description("Converts a Google formatted CSV contacts file into a valid FritzBox XML File.");
        parser.addArgument("name")
                .type(String.class)
                .help("The name of the resulting FritzBox phonebook as dieplayed in the box");
        parser.addArgument("in")
                .type(File.class)
                .help("The Google contacts source file");
        parser.addArgument("out")
                .type(File.class)
                .help("The FritzBox XML target file");
        parser.addArgument("groups").nargs("*")
                .help("A coma separated list of Google contact groups to include (no filter if omitted)");
        Namespace ns = null;
        try {
            ns = parser.parseArgs(args);
            String name = ns.get("name");
            File fin = ns.get("in");
            File fout = ns.get("out");
            List<String> groups = ns.get("groups");
            final GoogleContacts2FritzboxTransformer transformer = new GoogleContacts2FritzboxTransformer();
            transformer.transform(name, fin, fout, groups.toArray(new String[]{}));
        } catch (ArgumentParserException e) {
            parser.handleError(e);
            System.exit(1);
        }
    }
}
