package ch.zhaw.ssdd.pas.domain;

import ch.zhaw.ssdd.pas.annotations.Aggregate;

@Aggregate.Root
public class Adopter extends User {

    protected Adopter(UserId userId, ContactData contactData, Address address) {
        super(userId, contactData, address);
    }

    @Override
    public User withNewAddress(Address address) {
        return null; //TODO
    }
}
