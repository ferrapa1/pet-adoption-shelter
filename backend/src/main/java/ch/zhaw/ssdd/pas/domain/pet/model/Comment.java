package ch.zhaw.ssdd.pas.domain.pet.model;

import ch.zhaw.ssdd.pas.domain.user.model.UserId;
import ch.zhaw.ssdd.pas.stereotypes.ValueObject;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

/**
 * Represents a comment on a Pet's profile.
 * This is an Entity within the Pet Aggregate, as it has a stable identity.
 */
@ValueObject
public class Comment {

    private final UserId authorId;
    private final String content;
    private final LocalDateTime timestamp;
    private final String parentId; // Nullable. If null, it's a top-level comment.

    // Constructor for a top-level comment
    public Comment(UserId authorId, String content) {
        this(authorId, content, null);
    }

    // Constructor for a reply to an existing comment
    public Comment(UserId authorId, String content, String parentId) {
        this.authorId = Objects.requireNonNull(authorId, "AuthorId cannot be null.");
        this.content = Objects.requireNonNull(content, "Content cannot be null.");
        this.timestamp = LocalDateTime.now();
        this.parentId = parentId;
    }

    // Constructor for reconstituting from persistence
    public Comment(UserId authorId, String content, LocalDateTime timestamp, String parentId) {
        this.authorId = authorId;
        this.content = content;
        this.timestamp = timestamp;
        this.parentId = parentId;
    }

    public UserId getAuthorId() {
        return authorId;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getParentId() {
        return parentId;
    }

    public boolean isReply() {
        return parentId != null;
    }
}
