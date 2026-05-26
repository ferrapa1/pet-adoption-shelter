package ch.zhaw.ssdd.pas.adapters.outbound.jpa;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class AddressEntity {

    @Id
    private UUID id;
    private String street;
    private String houseNumber;
    private String plz;
    private String city;
}
