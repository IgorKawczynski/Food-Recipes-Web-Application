package org.igogrzeg.recipes.user.dtos;

import lombok.Builder;

public record UserRegistrationDto(String email, String password) {

    @Builder public UserRegistrationDto {}
}
