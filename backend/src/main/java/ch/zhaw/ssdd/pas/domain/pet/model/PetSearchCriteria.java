package ch.zhaw.ssdd.pas.domain.pet.model;

import ch.zhaw.ssdd.pas.stereotypes.ValueObject;

@ValueObject
public record PetSearchCriteria(String searchText) {

    public PetSearchCriteria {
        if (searchText != null) {
            searchText = searchText.toUpperCase();
        } else {
            searchText = "";
        }
    }
}
