package ch.zhaw.ssdd.pas.domain.user.model;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UserIdTest {

    @Test
    void testValid() {
        UUID randomUUID = UUID.randomUUID();
        UserId userId = new UserId(randomUUID);
        assertEquals(randomUUID, userId.value());
    }

    @Test
    void testNull() {
        assertThrows(IllegalArgumentException.class,() -> new UserId(null));
    }

}
