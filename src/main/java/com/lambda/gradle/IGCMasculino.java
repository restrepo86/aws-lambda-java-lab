package com.lambda.gradle;

public class IGCMasculino implements IGC{
    @Override
    public double get(double imc, int edad){
        return (1.20*imc)+(0.23*edad)-(10.8*1)-5.4;
    }
}
