package ch.zhaw.ssdd.pas.domain.pet.model;

import ch.zhaw.ssdd.pas.domain.shared.LocalFilePath;
import ch.zhaw.ssdd.pas.domain.shared.UploadTimestamp;
import ch.zhaw.ssdd.pas.domain.user.model.UserId;
import ch.zhaw.ssdd.pas.stereotypes.ValueObject;

import java.util.Objects;

@ValueObject
public record PetPhoto(LocalFilePath localFilePath, UploadTimestamp uploadTimestamp, PetId petId) {

    //TODO add hash
    public PetPhoto {
        Objects.requireNonNull(localFilePath);
        Objects.requireNonNull(uploadTimestamp);
        Objects.requireNonNull(petId);
    }
}
