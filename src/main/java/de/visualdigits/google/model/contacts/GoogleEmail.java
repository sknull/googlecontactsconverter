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
public class GoogleEmail {

    private String type;

    private String value;

    public GoogleEmail(Map<String, String> map, int index) {
        type(map.get("E-mail " + index + " - Type"))
        .value(map.get("E-mail " + index + " - Value"));
    }
}
