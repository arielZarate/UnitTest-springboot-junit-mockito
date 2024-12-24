package com.arielZarate.demoUnitTest;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DemoUnitTestApplicationTest {

    @Test
    public void testMainMethod() {
        try {
            DemoUnitTestApplication.main(new String[] {});
        } catch (Exception e) {
            // Si se lanza alguna excepción, la prueba fallará
            e.printStackTrace();
            fail("La aplicación no debería lanzar excepciones al arrancar.");
        }
    }

}