package ch.zhaw.ssdd.pas.ports.inbound;

import ch.zhaw.ssdd.pas.domain.user.model.Address;
import ch.zhaw.ssdd.pas.domain.user.model.ContactData;

import java.util.Objects;

/**
 * A command object encapsulating the data required to register a new Adopter.
 */
public record RegisterAdopterCommand(
        String requestedUserId,
        ContactData contactData,
        Address address,
        boolean hasGarden,
        boolean hasChildren
) {
    public RegisterAdopterCommand {
        Objects.requireNonNull(requestedUserId, "Requested User ID cannot be null.");
        Objects.requireNonNull(contactData, "ContactData cannot be null.");
        Objects.requireNonNull(address, "Address cannot be null.");
    }
}
