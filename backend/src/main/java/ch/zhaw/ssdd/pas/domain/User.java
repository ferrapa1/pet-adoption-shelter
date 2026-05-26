package ch.zhaw.ssdd.pas.domain;

import java.util.Objects;

public abstract class User {

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
