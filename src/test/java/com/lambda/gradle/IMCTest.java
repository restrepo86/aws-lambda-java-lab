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

    @Test
    public void stateInfrapesoSeveroIMC() throws Exception{
        StateFactory stateFactory = new StateFactory();
        State stateIMC = stateFactory.getState(15.0);
        String state = stateIMC.get();
        assertEquals("Infrapeso: Delgadez Severa",state);
    }

    @Test
    public void stateInfrapesoModeradoIMC() throws Exception{
        StateFactory stateFactory = new StateFactory();
        State stateIMC = stateFactory.getState(16.5);
        String state = stateIMC.get();
        assertEquals("Infrapeso: Delgadez moderada",state);
    }

    @Test
    public void stateInfrapesoAceptableIMC() throws Exception{
        StateFactory stateFactory = new StateFactory();
        State stateIMC = stateFactory.getState(17.5);
        String state = stateIMC.get();
        assertEquals("Infrapeso: Delgadez aceptable",state);
    }

    @Test
    public void stateNormalIMC() throws Exception{
        StateFactory stateFactory = new StateFactory();
        State stateIMC = stateFactory.getState(22.5);
        String state = stateIMC.get();
        assertEquals("Peso Normal",state);
    }

    @Test
    public void stateSobrepesoIMC() throws Exception{
        StateFactory stateFactory = new StateFactory();
        State stateIMC = stateFactory.getState(28.5);
        String state = stateIMC.get();
        assertEquals("Sobrepeso",state);
    }

    @Test
    public void stateObesoTipo1IMC() throws Exception{
        StateFactory stateFactory = new StateFactory();
        State stateIMC = stateFactory.getState(32.5);
        String state = stateIMC.get();
        assertEquals("Obeso: Tipo I",state);
    }

    @Test
    public void stateObesoTipo2IMC() throws Exception{
        StateFactory stateFactory = new StateFactory();
        State stateIMC = stateFactory.getState(36.5);
        String state = stateIMC.get();
        assertEquals("Obeso: Tipo II",state);
    }

    @Test
    public void stateObesoTipo3IMC() throws Exception{
        StateFactory stateFactory = new StateFactory();
        State stateIMC = stateFactory.getState(43.5);
        String state = stateIMC.get();
        assertEquals("Obeso: Tipo III",state);
    }

    @Test
    public void getNormalWeight() throws Exception{
        IMC objectIMC = new IMC();
        Double normalWeight = objectIMC.getNormalWeight(80,1.70);
        assertEquals(72,normalWeight,0.2);
    }

}
