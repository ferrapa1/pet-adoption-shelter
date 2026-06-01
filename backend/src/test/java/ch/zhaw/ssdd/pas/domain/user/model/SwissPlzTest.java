package ch.zhaw.ssdd.pas.domain.user.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SwissPlzTest {

    @Test
    void testValid() {
        SwissPlz plz = new SwissPlz(8001);
        assertEquals(8001, plz.value());
    }

    @Test
    void testRangeLowLimit() {
        SwissPlz plz = new SwissPlz(1000);
        assertEquals(1000, plz.value());
    }

    @Test
    void testRangeHighLimit() {
        SwissPlz plz = new SwissPlz(9999);
        assertEquals(9999, plz.value());
    }

    @Test
    void testTooLow() {
        assertThrows(IllegalArgumentException.class, () -> new SwissPlz(99));
    }

    @Test
    void testTooHigh() {
        assertThrows(IllegalArgumentException.class, () -> new SwissPlz(99999));
    }

    @Test
    void testZero() {
        assertThrows(IllegalArgumentException.class, () -> new SwissPlz(0));
    }

    @Test
    void testNegative() {
        assertThrows(IllegalArgumentException.class, () -> new SwissPlz(-100));
    }

}
