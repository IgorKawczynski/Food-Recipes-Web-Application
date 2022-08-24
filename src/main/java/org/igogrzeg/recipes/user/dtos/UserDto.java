package org.igogrzeg.recipes.user.dtos;

import lombok.Builder;

public record UserDto(String email) {

    @Builder public UserDto {}

}
