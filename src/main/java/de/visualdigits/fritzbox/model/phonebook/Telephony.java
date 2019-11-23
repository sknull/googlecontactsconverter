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
import java.util.List;

@XmlRootElement(name = "telephony")
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
@Accessors(fluent = true)
@ToString
@NoArgsConstructor
public class Telephony {

    @XmlAttribute
    private int nid;

    @XmlElement(name = "number")
    private List<TelephonyNumber> numbers;
}
