package ch.zhaw.ssdd.pas.adapters.inbound.rest.dto;

import ch.zhaw.ssdd.pas.domain.user.Adopter;
import org.jspecify.annotations.NonNull;

public record AdopterDTO(
        @NonNull String userId,
        @NonNull String email,
        @NonNull String phone,
        @NonNull AddressDTO address,
        boolean hasGarden,
        boolean hasChildren
) {
    public static AdopterDTO of(Adopter adopter) {
        return new AdopterDTO(
                adopter.getUserId().toString(),
                adopter.getContactData().email().value(),
                adopter.getContactData().phone().value(),
                AddressDTO.of(adopter.getAddress()),
                adopter.hasGarden(),
                adopter.hasChildren()
        );
    }
}
