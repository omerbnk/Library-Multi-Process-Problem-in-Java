//Ömer Benek 200315006 - Ayseli Erem Batı 170316006
package osproject;

import java.util.Random;

public class Test {

    public static void main(String[] args) {

        // We define our 6 books.
        Books book1 = new Books("Book1");
        Books book2 = new Books("Book2");
        Books book3 = new Books("Book3");
        Books book4 = new Books("Book4");
        Books book5 = new Books("Book5");
        Books book6 = new Books("Book6");

        //We created an array of type Students to hold 40 students.
        Students[] students = new Students[41];

        // we created threads for 40 Students objects with the for loop and called the start() method.
        for (int i = 1; i < students.length; i++) {
            students[i] = new Students(i, book1, book2, book3, book4, book5, book6);
            Thread t = new Thread(students[i]);
            t.start();
        }

    }

}
