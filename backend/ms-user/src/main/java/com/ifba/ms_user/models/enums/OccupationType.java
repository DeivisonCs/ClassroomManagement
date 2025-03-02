package com.ifba.ms_user.models.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum OccupationType {
	ADMINISTRADOR,
	PROFESSOR;
	
	@JsonCreator
    public static OccupationType fromString(String key) {
        return key == null ? null : OccupationType.valueOf(key.toUpperCase());
    }

    @JsonValue
    public String toValue() {
        return this.name();
    }
}
