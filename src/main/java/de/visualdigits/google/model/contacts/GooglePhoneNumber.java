package de.visualdigits.google.model.contacts;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@Accessors(fluent = true)
@ToString
public class GooglePhoneNumber {

    private String type;

    private List<String> values;

    public GooglePhoneNumber(Map<String, String> map, int index) {
        final String svalues = map.get("Phone " + index + " - Value");
        final List<String> values = new ArrayList<>();
        if (svalues != null) {
            values.addAll(Arrays.asList(svalues.split(" ::: ")));
        }
        type(map.get("Phone " + index + " - Type"))
        .values(values);
    }
}
