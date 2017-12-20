package com.lambda.gradle;

public class IMC {
    private double weight;
    private double height;
    private double imc;

    public IMC() {
    }

    public double getIMC(double weight, double height){
        this.weight = weight;
        this.height = height;

        double imc = weight/(Math.pow(height,2));
        return imc;
    }

    public String getState(double imc){
        this.imc = imc;
        String state;

        if (imc < 16.00){
            state = "Infrapeso: Delgadez Severa";
        }else if (imc > 16.00 && imc < 16.99){
            state = "Infrapeso: Delgadez moderada";
        }else if (imc > 17.00 && imc < 18.49){
            state = "Infrapeso: Delgadez aceptable";
        }else if (imc > 18.50 && imc < 24.99){
            state = "Peso Normal";
        }else if (imc > 25.00 && imc < 29.99){
            state = "Sobrepeso";
        }else if (imc > 30.00 && imc < 34.99){
            state = "Obeso: Tipo I";
        }else if (imc > 35.00 && imc < 40.00){
            state = "Obeso: Tipo II";
        }else {
            state = "Obeso: Tipo III";
        }
        return state;
    }
}
