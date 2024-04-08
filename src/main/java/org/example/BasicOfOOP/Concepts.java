package org.example.BasicOfOOP;

class Bird {
    public void sing() {
        System.out.println("Bird is singing");
    }
}

class Sparrow extends Bird {
    public void sing() {
        System.out.println("Sparrow is singing");
    }
}
public class Concepts {
    public static void main(String[] args) {
        Bird myBird = new Sparrow();
        myBird.sing(); // Outputs: Sparrow is singing
    }
}
