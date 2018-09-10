package com.lambda.gradle;

public class StateFactory {

    public State getState(double imc){

        if (imc < 16.00){
            return new DelgadezSevera();
        }else if (imc > 16.00 && imc < 16.99){
            return new DelgadezModerada();
        }else if (imc > 17.00 && imc < 18.49){
            return new DelgadezAceptable();
        }else if (imc > 18.50 && imc < 24.99){
            return new PesoNormal();
        }else if (imc > 25.00 && imc < 29.99){
            return new Sobrepeso();
        }else if (imc > 30.00 && imc < 34.99){
            return new ObesoTipo1();
        }else if (imc > 35.00 && imc < 40.00){
            return new ObesoTipo2();
        }else {
            return new ObesoTipo3();
        }
    }
}
