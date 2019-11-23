package de.visualdigits.google.model.contacts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

public class GoogleContact implements Comparable<GoogleContact> {

    private static final String SEPARATOR = " ::: ";
    
    private LinkedHashMap<String, String> map = null;

    public GoogleContact(LinkedHashMap<String, String> map) {
        this.map = map;
        for (int index = 1; index < 9; index++) {
            if (map.containsKey("Address " + index + " - Type") && !map.get("Address " + index + " - Type").isEmpty()) {
                addresses.add(new GoogleAddress(map, index));
            }
            if (map.containsKey("E-mail " + index + " - Type") && !map.get("E-mail " + index + " - Type").isEmpty()) {
                emails.add(new GoogleEmail(map, index));
            }
            if (map.containsKey("Phone " + index + " - Type") && !map.get("Phone " + index + " - Type").isEmpty()) {
                phoneNumbers.add(new GooglePhoneNumber(map, index));
            }
            if (map.containsKey("Organization " + index + " - Type") && !map.get("Organization " + index + " - Type").isEmpty()) {
                organizations.add(new GoogleOrganization(map, index));
            }
            if (map.containsKey("IM " + index + " - Type") && !map.get("IM " + index + " - Type").isEmpty()) {
                ims.add(new GoogleIM(map, index));
            }
            if (map.containsKey("Website " + index + " - Type") && !map.get("Website " + index + " - Type").isEmpty()) {
                websites.add(new GoogleWebsite(map, index));
            }
            if (map.containsKey("Event " + index + " - Type") && !map.get("Event " + index + " - Type").isEmpty()) {
                events.add(new GoogleEvent(map, index));
            }
        }
    }

    private List<GoogleAddress> addresses = new ArrayList<>();

    private List<GoogleEmail> emails = new ArrayList<>();

    private List<GooglePhoneNumber> phoneNumbers = new ArrayList<>();

    private List<GoogleIM> ims = new ArrayList<>();

    private List<GoogleOrganization> organizations = new ArrayList<>();

    private List<GoogleWebsite> websites = new ArrayList<>();

    private List<GoogleEvent> events = new ArrayList<>();

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder(
                "Name='" + getName() + '\'' +
                ", givenName='" + getGivenName() + '\'' +
                ", additionalName='" + getAdditionalName() + '\'' +
                ", familyName='" + getFamilyName() + '\'' +
                ", yomiName='" + getYomiName() + '\'' +
                ", givenNameYomi='" + getGivenNameYomi() + '\'' +
                ", additionalNameYomi='" + getAdditionalNameYomi() + '\'' +
                ", familyNameYomi='" + getFamilyNameYomi() + '\'' +
                ", namePrefix='" + getNamePrefix() + '\'' +
                ", nameSuffix='" + getNameSuffix() + '\'' +
                ", initials='" + getInitials() + '\'' +
                ", nickname='" + getNickname() + '\'' +
                ", shortName='" + getShortName() + '\'' +
                ", maidenName='" + getMaidenName() + '\'' +
                ", birthday='" + getBirthday() + '\'' +
                ", gender='" + getGender() + '\'' +
                ", location='" + getLocation() + '\'' +
                ", billingInformation='" + getBillingInformation() + '\'' +
                ", directoryServer='" + getDirectoryServer() + '\'' +
                ", mileage='" + getMileage() + '\'' +
                ", occupation='" + getOccupation() + '\'' +
                ", hobby='" + getHobby() + '\'' +
                ", sensitivity='" + getSensitivity() + '\'' +
                ", priority='" + getPriority() + '\'' +
                ", subject='" + getSubject() + '\'' +
                ", notes='" + getNotes() + '\'' +
                ", language='" + getLanguage() + '\'' +
                ", photo='" + getPhoto() + '\'' +
                ", groups=" + getGroups());
        for (GoogleAddress address : addresses) {
            res.append(", ").append(address);
        }
        for (GoogleEmail email : emails) {
            res.append(", ").append(email);
        }
        for (GooglePhoneNumber phoneNumber : phoneNumbers) {
            res.append(", ").append(phoneNumber);
        }
        for (GoogleOrganization organization : organizations) {
            res.append(", ").append(organization);
        }
        for (GoogleIM im : ims) {
            res.append(", ").append(im);
        }
        for (GoogleWebsite website : websites) {
            res.append(", ").append(website);
        }
        for (GoogleEvent event : events) {
            res.append(", ").append(event);
        }
        return res.toString();
    }

    @Override
    public GoogleContact clone() {
        final LinkedHashMap<String, String> clone = new LinkedHashMap<>();
        if (map != null) {
            map.forEach(clone::put);
        }
        return new GoogleContact(clone);
    }

    public Set<String> keySet() {
        return map.keySet();
    }

    public Collection<String> values() {
        return map.values();
    }

    public String get(String key) {
        return map.get(key);
    }

    public String getName() {
        return this.map.get("Name");
    }

    public String getGivenName() {
        return this.map.get("Given Name");
    }

    public String getAdditionalName() {
        return this.map.get("Additional Name");
    }

    public String getFamilyName() {
        return this.map.get("Family Name");
    }

    public String getYomiName() {
        return this.map.get("Yomi Name");
    }

    public String getGivenNameYomi() {
        return this.map.get("Given_Name Yomi");
    }

    public String getAdditionalNameYomi() {
        return this.map.get("Additional_Name Yomi");
    }

    public String getFamilyNameYomi() {
        return this.map.get("Family_Name Yomi");
    }

    public String getNamePrefix() {
        return this.map.get("Name Prefix");
    }

    public String getNameSuffix() {
        return this.map.get("Name Suffix");
    }

    public String getInitials() {
        return this.map.get("Initials");
    }

    public String getNickname() {
        return this.map.get("Nickname");
    }

    public String getShortName() {
        return this.map.get("Short Name");
    }

    public String getMaidenName() {
        return this.map.get("Maiden Name");
    }

    public String getBirthday() {
        return this.map.get("Birthday");
    }

    public String getGender() {
        return this.map.get("Gender");
    }

    public String getLocation() {
        return this.map.get("Location");
    }

    public String getBillingInformation() {
        return this.map.get("Billing Information");
    }

    public String getDirectoryServer() {
        return this.map.get("Directory Server");
    }

    public String getMileage() {
        return this.map.get("Mileage");
    }

    public String getOccupation() {
        return this.map.get("Occupation");
    }

    public String getHobby() {
        return this.map.get("Hobby");
    }

    public String getSensitivity() {
        return this.map.get("Sensitivity");
    }

    public String getPriority() {
        return this.map.get("Priority");
    }

    public String getSubject() {
        return this.map.get("Subject");
    }

    public String getNotes() {
        return this.map.get("Notes");
    }

    public String getLanguage() {
        return this.map.get("Language");
    }

    public String getPhoto() {
        return this.map.get("Photo");
    }

    public Set<String> getGroups() {
        final Set<String> groups = new HashSet<>();
        final String sgroups = this.map.get("Group Membership");
        if (sgroups != null) {
            groups.addAll(Arrays.asList(sgroups.split(SEPARATOR)));
        }
        return groups;
    }

    public boolean containsGroup(String group) {
        return getGroups().stream().anyMatch(g -> g.contains(group));
    }

    public List<GoogleEmail> getEmails() {
        return emails;
    }

    public GoogleEmail getEmail(int index) {
        return this.emails.get(index);
    }

    public List<GoogleIM> getIms() {
        return ims;
    }

    public GoogleIM getIM(int index) {
        return this.ims.get(index);
    }

    public List<GooglePhoneNumber> getPhoneNumbers() {
        return phoneNumbers;
    }

    public GooglePhoneNumber getPhoneNumber(int index) {
        return this.phoneNumbers.get(index);
    }

    public List<GoogleAddress> getAddresses() {
        return addresses;
    }

    public GoogleAddress getAddress(int index) {
        return this.addresses.get(index);
    }

    public List<GoogleOrganization> getOrganizations() {
        return organizations;
    }

    public GoogleOrganization getOrganization(int index) {
        return this.organizations.get(index);
    }

    public List<GoogleWebsite> getWebsites() {
        return websites;
    }

    public GoogleWebsite getWebsite(int index) {
        return this.websites.get(index);
    }

    public List<GoogleEvent> getEvents() {
        return events;
    }

    public GoogleEvent getEvent(int index) {
        return this.events.get(index);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GoogleContact that = (GoogleContact) o;
        return getName().equals(((GoogleContact) o).getName());
    }

    @Override
    public int hashCode() {
        return getName().hashCode();
    }

    @Override
    public int compareTo(GoogleContact o) {
        int c = map.size() - o.map.size();
        if (c != 0) {
            return c;
        }
        return getName().compareTo(o.getName());
    }
}
