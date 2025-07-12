package org.szymon.publicationservice.Enums;

public enum PublicationType {
    BOOK("Book"),
    MAGAZINE("Magazine");

    private final String value;

    // Constructor to assign string value
    PublicationType(String value) {
        this.value = value;
    }

    // Getter to access the string value
    public String getValue() {
        return value;
    }

    public static PublicationType fromValue(String value) {
        for (PublicationType type : PublicationType.values()) {
            if (type.getValue().equalsIgnoreCase(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown type of publication: " + value);
    }
}
