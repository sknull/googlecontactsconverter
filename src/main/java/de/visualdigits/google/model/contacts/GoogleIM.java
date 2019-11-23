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
public class GoogleIM {

    private String type;

    private String service;

    private String value;

    public GoogleIM(Map<String, String> map, int index) {
        type(map.get("IM " + index + " - Type"))
        .service(map.get("IM " + index + " - Service"))
        .value(map.get("IM " + index + " - Value"));
    }
}
