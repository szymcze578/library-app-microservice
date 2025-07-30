package org.szymon.user.WebApi.DataTransferObjects;

import jakarta.validation.constraints.NotNull;
import org.szymon.user.Domain.Model.RoleType;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record UserViewModel(
        long id,
        @NotNull
        String username,
        @NotNull
        String email,
        @NotNull
        String firstName,
        @NotNull
        String lastName,
        boolean isActive,
        @NotNull
        LocalDate birthDate,
        RoleType role,
        @NotNull
        AddressViewModel address
) {

}
