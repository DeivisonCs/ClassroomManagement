package com.ifba.ms_user.dto;

import com.ifba.ms_user.models.Occupation;

public record OccupationDto(Long id, String name) {
	public OccupationDto(Occupation occupation) {
		this(occupation.getId(), occupation.getName());
	}
}
