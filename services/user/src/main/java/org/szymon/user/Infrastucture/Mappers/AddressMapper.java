package org.szymon.user.Infrastucture.Mappers;

import org.szymon.user.Domain.Model.Address;
import org.szymon.user.WebApi.DataTransferObjects.AddressRequest;

public class AddressMapper {

    public static Address Map(AddressRequest addressRequest) {
        return Address.builder()
                .city(addressRequest.city())
                .street(addressRequest.street())
                .home(addressRequest.home()).build();
    }
}
