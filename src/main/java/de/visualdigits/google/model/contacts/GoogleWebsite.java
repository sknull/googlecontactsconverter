package de.visualdigits.google.model.contacts;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Map;

@Getter
@Setter
@Accessors(fluent = true)
@ToString
public class GoogleWebsite {

    private String type;

    private String value;

    public GoogleWebsite(Map<String, String> map, int index) {
        type(map.get("Website " + index + " - Type"))
        .value(map.get("Website " + index + " - Value"));
    }
}
