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

@XmlRootElement(name = "contact")
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
@Accessors(fluent = true)
@ToString
@NoArgsConstructor
public class FritzboxContact {

    @XmlElement
    private Value category;

    @XmlElement
    private Person person;

    @XmlElement
    private Telephony telephony;

    @XmlElement
    private Services services;

    @XmlElement
    private Object setup;

    @XmlElement
    private Features features;

    @XmlAttribute(name = "mod_time")
    private String modTime;

    @XmlAttribute(name = "uniqueid")
    private int uniqueId;
}
