package org.szymon.user.WebApi.DataTransferObjects;

import jakarta.validation.constraints.NotNull;

public record AddressRequest(
        @NotNull
        String city,
        @NotNull
        String street,
        @NotNull
        String home
) {
}
