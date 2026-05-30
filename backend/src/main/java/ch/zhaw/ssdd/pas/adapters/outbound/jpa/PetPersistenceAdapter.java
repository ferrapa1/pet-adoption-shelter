package ch.zhaw.ssdd.pas.adapters.outbound.jpa;

import ch.zhaw.ssdd.pas.adapters.outbound.jpa.user.entity.UserEntity;
import ch.zhaw.ssdd.pas.adapters.outbound.jpa.user.repository.UserEntityRepository;
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
    private final UserEntityRepository userEntityRepository;

    public PetPersistenceAdapter(PetEntityRepository petEntityRepository, UserEntityRepository userEntityRepository) {
        this.petEntityRepository = petEntityRepository;
        this.userEntityRepository = userEntityRepository;
    }

    @Override
    public Optional<Pet> findById(PetId id) {
        return petEntityRepository.findById(id.value())
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
        PetEntity entity = new PetEntity(domain.getPetId().value());

        UserEntity shelterEntity = userEntityRepository.findById(domain.getShelterId().value())
                .orElseThrow(() -> new IllegalStateException("Cannot save Pet: Shelter user not found with ID " + domain.getShelterId().value()));
        entity.setShelterId(shelterEntity.getId());
        
        entity.setName(domain.getName());
        entity.setDateOfBirth(domain.getDateOfBirth());
        entity.setBreed(domain.getBreed());
        entity.setAdoptionStatus(domain.getAdoptionStatus());
        entity.setSpecies(domain.getSpecies());

        List<CommentEntity> commentEntities = domain.getComments().stream()
                .map(comment -> {
                    UserEntity authorEntity = userEntityRepository.findById(comment.getAuthorId().value())
                            .orElseThrow(() -> new IllegalStateException("Cannot save Comment: Author user not found with ID " + comment.getAuthorId().value()));
                            
                    return new CommentEntity(
                        UUID.randomUUID(), // No ID because it's a ValueObject, generate one for the DB
                        authorEntity.getId(),
                        comment.getContent(),
                        comment.getTimestamp(),
                        comment.getParentId() != null ? UUID.fromString(comment.getParentId()) : null,
                        entity,
                            null //TODO rework
                    );
                })
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
                .map(c -> {
                    UserEntity authorEntity = userEntityRepository.findById(c.getAuthorId())
                            .orElseThrow(() -> new IllegalStateException("Data integrity error: Comment author UUID not found " + c.getAuthorId()));
                    
                    return new Comment(
                        new UserId(authorEntity.getId()),
                        c.getContent(),
                        c.getTimestamp(),
                        c.getParentId() != null ? c.getParentId().toString() : null
                    );
                })
                .collect(Collectors.toList());

        List<PetPhoto> domainPhotos = entity.getPictures().stream()
                .map(p -> new PetPhoto(
                        new LocalFilePath(p.getUrl()),
                        new UploadTimestamp(LocalDateTime.now()), // Or store upload time in PictureEntity
                        new PetId(entity.getId())

                ))
                .collect(Collectors.toList());

        UserEntity shelterEntity = userEntityRepository.findById(entity.getShelterId())
                 .orElseThrow(() -> new IllegalStateException("Data integrity error: Shelter UUID not found " + entity.getShelterId()));

        Pet pet = new Pet(
                new PetId(entity.getId()),
                new UserId(shelterEntity.getId()),
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
