package com.lambda.gradle;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class IGCTest {
    @Test
    public void getIGCFemenino() throws Exception{
        IGCFactory igcFactory = new IGCFactory();
        double igc = igcFactory.getIGC(22, 'f', 27);
        assertEquals(27.21,igc,0.2);
    }

    @Test
    public void getIGCMasculino() throws Exception{
        IGCFactory igcFactory = new IGCFactory();
        double igc = igcFactory.getIGC(27.68, 'm', 23);
        assertEquals(22.30,igc,0.2);
    }
}
