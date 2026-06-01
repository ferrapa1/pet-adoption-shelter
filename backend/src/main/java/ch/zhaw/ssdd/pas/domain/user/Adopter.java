package ch.zhaw.ssdd.pas.domain.user;

import ch.zhaw.ssdd.pas.domain.user.model.Address;
import ch.zhaw.ssdd.pas.domain.user.model.ContactData;
import ch.zhaw.ssdd.pas.domain.user.model.UserId;
import ch.zhaw.ssdd.pas.stereotypes.Aggregate;

@Aggregate.Root
public class Adopter extends User {

    private boolean hasChildren;
    private boolean hasGarden;

    public Adopter(UserId userId, ContactData contactData, Address address) {
        super(userId, contactData, address);
    }

    @Override
    public Adopter withNewAddress(Address address) {
        return null; //TODO
    }

    public Adopter withChildren(boolean hasChildren) {
        this.hasChildren = hasChildren;
        return this;
    }

    public Adopter withGarden(boolean hasGarden) {
        this.hasGarden = hasGarden;
        return this;
    }

    public boolean hasChildren() {
        return hasChildren;
    }

    public boolean hasGarden() {
        return hasGarden;
    }
}
