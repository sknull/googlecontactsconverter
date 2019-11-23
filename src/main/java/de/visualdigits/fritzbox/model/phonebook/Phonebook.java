package de.visualdigits.fritzbox.model.phonebook;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

@XmlRootElement(name = "phonebook")
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
@Accessors(fluent = true)
@ToString
@NoArgsConstructor
public class Phonebook implements Iterable<FritzboxContact> {

    @XmlAttribute
    private int owner;

    @XmlAttribute
    private String name;

    @XmlElement(name = "contact")
    private List<FritzboxContact> fritzboxContacts;

    @Override
    public Iterator<FritzboxContact> iterator() {
        return fritzboxContacts.iterator();
    }

    @Override
    public void forEach(Consumer<? super FritzboxContact> action) {
        fritzboxContacts.forEach(action);
    }
}
