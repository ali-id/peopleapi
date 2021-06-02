package com.ali.personapi.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PhoneType {

    HOME("hOME"),
    MOBILE("Mobile"),
    COMMERCIAL("Commercial");

    private final String description;

}
