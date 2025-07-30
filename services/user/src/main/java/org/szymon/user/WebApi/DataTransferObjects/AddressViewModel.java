package org.szymon.user.WebApi.DataTransferObjects;

import jakarta.validation.constraints.NotNull;

public record AddressViewModel(
        long id,
        @NotNull
        String city,
        @NotNull
        String street,
        @NotNull
        String home
) {
}
