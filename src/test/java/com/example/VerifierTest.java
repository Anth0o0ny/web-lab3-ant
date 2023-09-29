package com.example;
import com.example.verify.Verifier;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class VerifierTest {

    private Verifier verifier;

    @Before
    public void setUp() {
        verifier = new Verifier();
    }

    @Test
    public void testFirstQuaterInside() {
        assertTrue(verifier.verifyHit(0.4, 0.3, 2.0));
    }

    @Test
    public void testFirstQuaterOutside() {
        assertFalse(verifier.verifyHit(1.0, 1.0, 2.0));
    }

    @Test
    public void testFirstQuaterBorder() {
        assertTrue(verifier.verifyHit(1.0, 0.5, 2.0));
    }
    @Test
    public void testSecondQuaterInside() {
        assertTrue(verifier.verifyHit(-1.0, 1.0, 2.0));
    }

    @Test
    public void testSecondQuaterOutside() {
        assertFalse(verifier.verifyHit(-1.0, 3.0, 2.0));
    }

    @Test
    public void testSecondQuaterBorder() {
        assertTrue(verifier.verifyHit(-2.0, 2.0, 2.0));
    }
    @Test
    public void testThirdQuater() {
        assertFalse(verifier.verifyHit(-1.0, -1.0, 2.0));
        // всегда возвращает false
    }

    @Test
    public void testFourthQuaterInside() {
        assertTrue(verifier.verifyHit(1.0, -1.0, 2.0));
    }

    @Test
    public void testFourthQuaterOutside() {
        assertFalse(verifier.verifyHit(2.0, -2.0, 2.0));
    }

    @Test
    public void testFourthQuaterBorder() {
        assertTrue(verifier.verifyHit(1.41, -1.41, 2.0));
    }
}



