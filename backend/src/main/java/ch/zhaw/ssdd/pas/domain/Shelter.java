package ch.zhaw.ssdd.pas.domain;

import ch.zhaw.ssdd.pas.annotations.Aggregate;

@Aggregate.Root
public class Shelter extends User {

    public Shelter(UserId userId, ContactData contactData, Address address) {
        super(userId, contactData, address);
    }

    @Override
    public User withNewAddress(Address address) {
        return null;
    }
}
