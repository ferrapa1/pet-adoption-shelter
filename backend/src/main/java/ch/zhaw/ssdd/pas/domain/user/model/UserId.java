package ch.zhaw.ssdd.pas.domain.user.model;

import ch.zhaw.ssdd.pas.stereotypes.EntityId;
import org.springframework.util.ObjectUtils;

import java.util.UUID;

@EntityId
public record UserId(UUID value) {

    public UserId {
        if (ObjectUtils.isEmpty(value)) {
            throw new IllegalArgumentException("UserId cannot be empty.");
        }
    }

    public static UserId of(String userIdStr) {
        // If the input is not a valid UUID, an IllegalArgumentException is thrown.
        return new UserId(UUID.fromString(userIdStr));
    }
}
