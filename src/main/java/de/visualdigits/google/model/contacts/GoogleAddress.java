package de.visualdigits.google.model.contacts;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Map;

@Getter
@Setter
@Accessors(fluent = true)
@ToString
public class GoogleAddress {

    private String type;

    private String formatted;

    private String street;

    private String city;

    private String poBox;

    private String region;

    private String postalCode;

    private String country;

    private String extendedAddress;

    public GoogleAddress(Map<String, String> map, int index) {
        type(map.get("Address " + index + " - Type"))
        .formatted(map.get("Address " + index + " - Formatted"))
        .street(map.get("Address " + index + " - Street"))
        .city(map.get("Address " + index + " - City"))
        .poBox(map.get("Address " + index + " -_PO Box"))
        .region(map.get("Address " + index + " - Region"))
        .postalCode(map.get("Address " + index + " -_Postal Code"))
        .country(map.get("Address " + index + " - Country"))
        .extendedAddress(map.get("Address " + index + " -_Extended Address"));
    }
}
