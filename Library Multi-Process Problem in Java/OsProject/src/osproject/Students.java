
//Ömer Benek 200315006 - Ayseli Erem Batı 170316006
package osproject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Students implements Runnable {

    private boolean[] books = new boolean[7];
    Random rand = new Random();
    int id;
    Books b1;
    Books b2;
    Books b3;
    Books b4;
    Books b5;
    Books b6;
    Librarians lib1;
    Librarians lib2;
    Librarians lib3;
    public static long randomWait;
    int choosenBook;
    int choosenLibrary;
    int count = 0;

    public Students(int id, Books b1, Books b2, Books b3, Books b4, Books b5, Books b6) {
        this.id = id;
        this.b1 = b1;
        this.b2 = b2;
        this.b3 = b3;
        this.b4 = b4;
        this.b5 = b5;
        this.b6 = b6;
        lib1 = new Librarians(1);
        lib2 = new Librarians(2);
        lib3 = new Librarians(3);
        for (int i = 1; i < this.books.length; i++) {
            this.books[i] = false;
        }

    }

  
    //The run() function works as long as the value from the bookControl() method is false.
    //So when the student has read all the books, bookControl() returns true and the student reads all the books and graduates.
    //The randomComing() and catchLibrarians() functions run in this loop.
    @Override
    public void run() {
        try {
            while (!bookControl()) {
                randomComing();
                catchLibrarian();
            }
            System.out.println("---------------"+id+". Student graduated---------------");
        } catch (Exception ex) {}
    }
    //It catches librarian and chooses book.
    private synchronized void catchLibrarian() throws Exception {

        choosenLibrary = rand.nextInt(3) + 1;       //generates a random number between one and three, which is used to select the librarian. 
        choosenLibrary = controlLibrarians(choosenLibrary); //It checks if the librarian is idle by sending this number to the controlLibrarians function
        choosenBook = rand.nextInt(6) + 1;  //chooses a book between 1 and 6 randomly. 

        //In the loop, it checks from the array whether the student has read that book. If he has read it, he randomly chooses a number again.
        while (this.books[choosenBook]) {
            choosenBook = rand.nextInt(6) + 1;
        }
        //we created an object from the library class and put it into a thread object.
        Library lib = new Library(choosenLibrary, b1, b2, b3, b4, b5, b6, choosenBook, id, lib1, lib2, lib3);
        Thread tlib = new Thread(lib);
        tlib.start();
        
        System.out.println("Student " + id + " choose Book " + choosenBook);
        //made the index of the selected book true from the array
        this.books[choosenBook] = true;

    }
    //scans all the elements of the books array and keeps the true values ​​in count. 
    public boolean bookControl() throws InterruptedException {

        for (int i = 1; i < 7; i++) {
            if (this.books[i] == true) {
                count++;
                break;
            }
        }
        // Returns true if our count is 6.
        if (count == 6) {
            return true;

        } 
        //Otherwise it returns false.
        else {
            return false;
        }

    }
    //provides a delay to the threads by generating random numbers, which means students arrive at different times.
    void randomComing() {
        try {
            long start = System.currentTimeMillis();
            Thread.sleep((long) (1000 * Math.random()));
            System.out.println((System.currentTimeMillis() - start) + " seconds later Student " + id + " comes library ");
            randomWait = System.currentTimeMillis() - start;
        } catch (InterruptedException x) {
        }
    }
    // checks the availability value of the librarians  
    public int controlLibrarians(int sayı) {
        switch (sayı) {
            case 1:
                if (lib1.free) {
                    lib1.free = false;//sets it to false if true.
                    return sayı;
                } else {
                     // If false, it calls itself recursively, generating a random number between 1 and 3.
                    choosenLibrary = rand.nextInt(3) + 1; 
                    controlLibrarians(choosenLibrary);
                }
            case 2:
                if (lib2.free) {
                    lib2.free = false;//sets it to false if true.
                    return sayı;

                } else {
                    // If false, it calls itself recursively, generating a random number between 1 and 3.
                    choosenLibrary = rand.nextInt(3) + 1;
                    controlLibrarians(choosenLibrary);
                }
            case 3:
                if (lib3.free) {
                    lib3.free = false;//sets it to false if true.
                    return sayı;
                } else {
                    // If false, it calls itself recursively, generating a random number between 1 and 3.
                    choosenLibrary = rand.nextInt(3) + 1; 
                    controlLibrarians(choosenLibrary);
                }
        }
        return 0;
    }
}
