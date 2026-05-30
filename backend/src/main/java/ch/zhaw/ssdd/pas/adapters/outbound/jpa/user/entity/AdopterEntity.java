package ch.zhaw.ssdd.pas.adapters.outbound.jpa.user.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.util.UUID;

@Entity
@DiscriminatorValue("ADOPTER")
public class AdopterEntity extends UserEntity {

    protected AdopterEntity() {
    }

    public AdopterEntity(UUID id) {
        super(id);
    }
    private boolean hasGarden;

    private boolean hasChildren;

    public boolean hasGarden() {
        return hasGarden;
    }

    public void setHasGarden(boolean hasGarden) {
        this.hasGarden = hasGarden;
    }

    public boolean hasChildren() {
        return hasChildren;
    }

    public void setHasChildren(boolean hasChildren) {
        this.hasChildren = hasChildren;
    }
}
