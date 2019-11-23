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
public class GoogleEvent {

    private String type;

    private String value;

    public GoogleEvent(Map<String, String> map, int index) {
        type(map.get("Event " + index + " - Type"))
        .value(map.get("Event " + index + " - Value"));
    }
}
