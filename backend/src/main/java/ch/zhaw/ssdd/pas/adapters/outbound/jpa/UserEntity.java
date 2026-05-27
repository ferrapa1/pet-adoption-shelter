package ch.zhaw.ssdd.pas.adapters.outbound.jpa;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "USER_TYPE",
        discriminatorType = DiscriminatorType.STRING)
public abstract class UserEntity {

    @Id
    private UUID id;

    @Column(unique = true)
    private String userId;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private AddressEntity address;

    @Column(length = 16)
    private String phoneNumber;
    @Column(length = 50)
    private String email;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }
}
