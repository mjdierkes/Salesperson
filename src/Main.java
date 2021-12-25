package com.masondierkes;

public class Main {

    public static void main(String[] args) {
        //define 4 points forming a square
        Point a = new Point(100.0, 100.0);
        Point b = new Point(500.0, 100.0);
        Point c = new Point(500.0, 500.0);
        Point d = new Point(100.0, 500.0);

        Tour squareTour = new Tour(a, b, c, d);

        squareTour.show();
        squareTour.draw();
        System.out.println(squareTour.distance());
    }

}
