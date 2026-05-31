package ch.zhaw.ssdd.pas.adapters.inbound.rest.dto;

import ch.zhaw.ssdd.pas.domain.user.model.Address;
import org.jspecify.annotations.NonNull;

public record AddressDTO(
        @NonNull String street,
        @NonNull String houseNumber,
        int plz,
        @NonNull String city
) {
    public static AddressDTO of(Address address) {
        return new AddressDTO(
                address.street(),
                address.houseNumber(),
                address.plz().value(),
                address.city()
        );
    }
}
