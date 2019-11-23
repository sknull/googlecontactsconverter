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

@XmlRootElement(name = "number")
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
@Accessors(fluent = true)
@ToString
@NoArgsConstructor
public class TelephonyNumber {

    @XmlAttribute
    private String type;

    @XmlAttribute
    private int prio;

    @XmlAttribute
    private int id;

    @XmlAttribute
    private int quickdial;

    @XmlValue
    private String number;
}
