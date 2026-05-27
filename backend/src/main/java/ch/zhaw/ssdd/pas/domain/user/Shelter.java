package ch.zhaw.ssdd.pas.domain.user;

import ch.zhaw.ssdd.pas.domain.user.model.Address;
import ch.zhaw.ssdd.pas.domain.user.model.ContactData;
import ch.zhaw.ssdd.pas.domain.user.model.UserId;
import ch.zhaw.ssdd.pas.stereotypes.Aggregate;

@Aggregate.Root
public class Shelter extends User {

    public Shelter(UserId userId, ContactData contactData, Address address) {
        super(userId, contactData, address);
    }

    @Override
    public Shelter withNewAddress(Address address) {
        return null;
    }
}
