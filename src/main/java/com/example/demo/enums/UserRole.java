package com.example.demo.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum UserRole {
    CLIENT("Клиент"); // Потом вместо дефолтного CLIENT можно дать возможность стать, например, SELLER

    private final String title;

    UserRole(String title) {
        this.title = title;
    }

    @JsonValue
    public String getTitle() {
        return title;
    }

}
