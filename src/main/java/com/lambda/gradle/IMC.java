package com.lambda.gradle;

public class IMC {
    private double weight;
    private double height;

    public IMC() {
    }

    public double getIMC(double weight, double height){
        this.weight = weight;
        this.height = height;

        double imc = weight/(Math.pow(height,2));
        return imc;
    }
}
