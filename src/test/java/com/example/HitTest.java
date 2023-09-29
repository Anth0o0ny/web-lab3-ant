package com.example;

import com.example.objective.Hit;
import org.junit.Before;
import org.junit.Test;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import static org.junit.Assert.*;

public class HitTest {

    private Hit hit;

    @Before
    public void setUp() {
        hit = new Hit();
    }

    @Test
    public void testSetAndGetXY() {
        hit.setX(3.0);
        hit.setyLine("4.0");
        assertEquals(3.0, hit.getX(), 0.001);
        assertEquals(4.0, hit.getY(), 0.001);
    }


    @Test
    public void testParseYLineWithComma() {
        hit.setyLine("6,6");
        assertEquals(6.6, hit.getY(), 0.001);
    }

    @Test
    public void testSetAndGetR() {
        hit.setR(50);
        assertEquals(0.5, hit.getR(), 0.001);
    }

    @Test
    public void testSetAndGetSuccess() {
        hit.setSuccess(true);
        assertTrue(hit.isSuccess());
        assertEquals("Hit", hit.getSuccessLine());

        hit.setSuccess(false);
        assertFalse(hit.isSuccess());
        assertEquals("Miss", hit.getSuccessLine());
    }

    @Test
    public void testSetAndGetDate() {
        hit.setDate(LocalDateTime.parse("2023-09-28 15:30:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        assertEquals("2023-09-28 15:30:00", hit.getDate());
    }
}
