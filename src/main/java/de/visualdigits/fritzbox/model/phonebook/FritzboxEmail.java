package de.visualdigits.fritzbox.model.phonebook;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

@XmlRootElement(name = "email")
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
@Accessors(fluent = true)
@ToString
@NoArgsConstructor
public class FritzboxEmail {

    @XmlAttribute
    private String classifier;

    @XmlAttribute
    private int id;

    @XmlValue
    private String email;
}
