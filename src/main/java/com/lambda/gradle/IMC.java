package com.lambda.gradle;

public class IMC {


    public double getIMC(double weight, double height){

        return weight/(Math.pow(height,2));
    }

    public double getNormalWeight(double weight, double height) {
      Double imc = getIMC(weight,height);

      while (imc > 24.99){
          weight--;
          imc = getIMC(weight,height);
      }
      return weight;
    }
}
