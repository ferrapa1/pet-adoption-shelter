package ch.zhaw.ssdd.pas.domain.user;

import ch.zhaw.ssdd.pas.domain.user.model.Address;
import ch.zhaw.ssdd.pas.domain.user.model.ContactData;
import ch.zhaw.ssdd.pas.domain.user.model.UserId;
import ch.zhaw.ssdd.pas.stereotypes.EntityId;

import java.util.Objects;

public abstract class User {

    @EntityId
    private final UserId userId;
    private final ContactData contactData;
    private final Address address;

    protected User(UserId userId, ContactData contactData, Address address) {
        Objects.requireNonNull(userId, "UserId cannot be null.");
        Objects.requireNonNull(contactData, "ContactData cannot be null.");
        Objects.requireNonNull(address, "Address cannot be null.");

        this.userId = userId;
        this.contactData = contactData;
        this.address = address;
    }

    public abstract User withNewAddress(Address address);

    public UserId getUserId() {
        return userId;
    }

    public ContactData getContactData() {
        return contactData;
    }

    public Address getAddress() {
        return address;
    }
}
