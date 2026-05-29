package ch.zhaw.ssdd.pas.adapters.outbound.jpa;

import ch.zhaw.ssdd.pas.domain.pet.Pet;
import ch.zhaw.ssdd.pas.domain.pet.model.Comment;
import ch.zhaw.ssdd.pas.domain.pet.model.PetId;
import ch.zhaw.ssdd.pas.domain.pet.model.PetPhoto;
import ch.zhaw.ssdd.pas.domain.pet.model.PetSearchCriteria;
import ch.zhaw.ssdd.pas.domain.shared.LocalFilePath;
import ch.zhaw.ssdd.pas.domain.shared.UploadTimestamp;
import ch.zhaw.ssdd.pas.domain.user.model.UserId;
import ch.zhaw.ssdd.pas.ports.outbound.PetPersistence;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Implements the outbound port for Pet persistence.
 * This adapter connects the application core to the database.
 */
@Service
public class PetPersistenceAdapter implements PetPersistence {

    private final PetEntityRepository petEntityRepository;

    public PetPersistenceAdapter(PetEntityRepository petEntityRepository) {
        this.petEntityRepository = petEntityRepository;
    }

    @Override
    public Optional<Pet> findById(PetId id) {
        UUID uuid = UUID.fromString(id.value());
        return petEntityRepository.findById(uuid)
                .map(this::toDomain);
    }

    @Override
    public Pet save(Pet pet) {
        PetEntity entity = toEntity(pet);
        PetEntity savedEntity = petEntityRepository.save(entity);
        return toDomain(savedEntity);
    }

    @Override
    public List<Pet> search(PetSearchCriteria searchCriteria) {
        return petEntityRepository.search(searchCriteria.searchText())
                .stream()
                .map(this::toDomain)
                .toList();
    }

    private PetEntity toEntity(Pet domain) {
        PetEntity entity = new PetEntity();
        entity.setId(UUID.fromString(domain.getPetId().value()));
        entity.setShelterId(UUID.fromString(domain.getShelterId().value()));
        entity.setName(domain.getName());
        entity.setDateOfBirth(domain.getDateOfBirth());
        entity.setBreed(domain.getBreed());
        entity.setAdoptionStatus(domain.getAdoptionStatus());

        List<CommentEntity> commentEntities = domain.getComments().stream()
                .map(comment -> new CommentEntity(
                        UUID.randomUUID(), // No ID because it's a ValueObject, generate one for the DB
                        UUID.fromString(comment.getAuthorId().value()),
                        comment.getContent(),
                        comment.getTimestamp(),
                        comment.getParentId() != null ? UUID.fromString(comment.getParentId()) : null,
                        entity // Link back to parent
                ))
                .collect(Collectors.toList());
        entity.setComments(commentEntities);

        List<PictureEntity> pictureEntities = domain.getPetPhotos().stream()
                .map(picture -> new PictureEntity(
                        UUID.randomUUID(), // No ID because it's a ValueObject, generate one for the DB
                        picture.localFilePath().localPath(),
                        entity // Link back to parent
                ))
                .collect(Collectors.toList());
        entity.setPictures(pictureEntities);

        return entity;
    }

    private Pet toDomain(PetEntity entity) {
        
        List<Comment> domainComments = entity.getComments().stream()
                .map(c -> new Comment(
                        new UserId(c.getAuthorId().toString()),
                        c.getContent(),
                        c.getTimestamp(),
                        c.getParentId() != null ? c.getParentId().toString() : null
                ))
                .collect(Collectors.toList());

        List<PetPhoto> domainPhotos = entity.getPictures().stream()
                .map(p -> new PetPhoto(
                        new LocalFilePath(p.getUrl()),
                        new UploadTimestamp(LocalDateTime.now()), // Or store upload time in PictureEntity
                        new PetId(entity.getId().toString())

                ))
                .collect(Collectors.toList());

        Pet pet = new Pet(
                new PetId(entity.getId().toString()),
                new UserId(entity.getShelterId().toString()),
                entity.getDateOfBirth(),
                entity.getSpecies(),
                entity.getBreed(),
                entity.getName(),
                domainPhotos,
                domainComments
        );

        return pet;
    }
}
