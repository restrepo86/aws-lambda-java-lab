package com.lambda.gradle;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class IMCTest {
    @Test
    public void normalIMC() throws Exception {
        IMC objectIMC = new IMC();
        double imc = objectIMC.getIMC(72,1.70);
        assertEquals(24.91, imc, 25);
    }

    @Test
    public void infrapesoSeveroIMC() throws Exception {
        IMC objectIMC = new IMC();
        double imc = objectIMC.getIMC(40,1.70);
        assertEquals(13.84, imc, 0.2);
    }

    @Test
    public void infrapesoModeradoIMC() throws Exception {
        IMC objectIMC = new IMC();
        double imc = objectIMC.getIMC(47,1.70);
        assertEquals(16.26, imc, 0.2);
    }

    @Test
    public void infrapesoAceptableIMC() throws Exception {
        IMC objectIMC = new IMC();
        double imc = objectIMC.getIMC(50,1.70);
        assertEquals(17.3, imc, 0.2);
    }

    @Test
    public void sobrepesoIMC() throws Exception {
        IMC objectIMC = new IMC();
        double imc = objectIMC.getIMC(80,1.70);
        assertEquals(27.68, imc, 0.2);
    }

    @Test
    public void obesoTipo1IMC() throws Exception {
        IMC objectIMC = new IMC();
        double imc = objectIMC.getIMC(87,1.70);
        assertEquals(30.1, imc, 0.2);
    }

    @Test
    public void obesoTipo2IMC() throws Exception {
        IMC objectIMC = new IMC();
        double imc = objectIMC.getIMC(102,1.70);
        assertEquals(35.29, imc, 0.2);
    }

    @Test
    public void obesoTipo3IMC() throws Exception {
        IMC objectIMC = new IMC();
        double imc = objectIMC.getIMC(120,1.70);
        assertEquals(41.52, imc, 0.2);
    }

}
