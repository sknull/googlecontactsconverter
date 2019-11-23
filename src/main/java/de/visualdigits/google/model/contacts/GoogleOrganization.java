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
public class GoogleOrganization {

    private String type;

    private String name;

    private String yomiName;

    private String title;

    private String department;

    private String symbol;

    private String location;

    private String jobDescription;

    public GoogleOrganization(Map<String, String> map, int index) {
        type(map.get("Organization " + index + " - Type"))
        .name(map.get("Organization " + index + " - Name"))
        .yomiName(map.get("Organization " + index + " -_Yomi Name"))
        .title(map.get("Organization " + index + " - Title"))
        .department(map.get("Organization " + index + " - Department"))
        .symbol(map.get("Organization " + index + " - Symbol"))
        .location(map.get("Organization " + index + " - Location"))
        .jobDescription(map.get("Organization " + index + " -_Job Description"));
    }
}
