package ch.zhaw.ssdd.pas.adapters.outbound.jpa.user.entity;

import ch.zhaw.ssdd.pas.adapters.outbound.jpa.AddressEntity;
import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "app_user")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "user_type",
        discriminatorType = DiscriminatorType.STRING)
public abstract class UserEntity {

    @Id
    private UUID id;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private AddressEntity address;

    @Column(length = 16)
    private String phoneNumber;
    
    @Column(unique = true)
    private String email;

    protected UserEntity() {
    }

    public UserEntity(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public AddressEntity getAddress() {
        return address;
    }

    public void setAddress(AddressEntity address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
