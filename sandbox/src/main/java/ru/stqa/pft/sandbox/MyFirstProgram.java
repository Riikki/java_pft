package ru.stqa.pft.sandbox;

public class MyFirstProgram {
  public static void main(String[] args){
    Point p1 = new Point(3,7);
    Point p2 = new Point(6,3);
    double result = Point.distance(p1, p2);

    System.out.println("Pасстояние между двумя точками" + " P1 и P2 = " + result );
  }

}


