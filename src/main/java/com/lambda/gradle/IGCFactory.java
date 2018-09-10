package com.lambda.gradle;

public class IGCFactory {
    public double getIGC(double imc, char genero, int edad){
        if (genero == 'f'){
            IGCFemenino igcFemenino = new IGCFemenino();
            return igcFemenino.get(imc,edad);
        }
        if (genero == 'm'){
            IGCMasculino igcMasculino = new IGCMasculino();
            return igcMasculino.get(imc,edad);
        }else {
            return 0;
        }
    }
}
