//Ömer Benek 200315006 - Ayseli Erem Batı 170316006
package osproject;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Library implements Runnable {

    int id;
    Books book1;
    Books book2;
    Books book3;
    Books book4;
    Books book5;
    Books book6;
    int bookid;
    int stdid;
    Librarians lib1;
    Librarians lib2;
    Librarians lib3;

  

    public Library(int id, Books b1, Books b2, Books b3, Books b4, Books b5, Books b6, int bid, int ogrid, Librarians lib1, Librarians lib2, Librarians lib3) {
        this.id = id;
        this.book1 = b1;
        this.book2 = b2;
        this.book3 = b3;
        this.book4 = b4;
        this.book5 = b5;
        this.book6 = b6;
        this.bookid = bid;
        this.stdid = ogrid;
        this.lib1 = lib1;
        this.lib2 = lib2;
        this.lib3 = lib3;
        System.out.println("Librarian " + id + " welcomes to Student " + ogrid);
    }

    @Override
    public void run() {
        try {
            bookControl();
        } catch (InterruptedException ex) {}
    }
    
    
    //It checks whether that book is idle, and if it is, it keeps the free value of the book False for a certain period of time.It then sets the book's Free value to True.
    private void bookControl() throws InterruptedException {
        switch (bookid) {
            case 1: 
                if (book1.free) {
                    book1.free = false;  
                    randomBack();
                    book1.free = true;
                }
                break;
            case 2:
                if (book2.free) {
                    book2.free = false;
                    randomBack();
                    book2.free = true;
                }
                break;
            case 3:
                if (book3.free) {
                    book3.free = false;
                    randomBack();
                    book3.free = true;
                }
                break;
            case 4:
                if (book4.free) {
                    book4.free = false;
                    randomBack();
                    book4.free = true;
                }
                break;
            case 5:
                if (book5.free) {
                    book5.free = false;
                    randomBack();
                    book5.free = true;
                }
                break;
            case 6:
                if (book6.free) {
                    book6.free = false;
                    randomBack();
                    book6.free = true;
                }
                break;
        }
    }
    // It decides how many seconds the student reads the book and returns it. 
    void randomBack() {
        try {
            long start = System.currentTimeMillis();
            //It works by generating random numbers.
            Thread.sleep((long) (1* Math.random()));
            long totalTime = Students.randomWait+System.currentTimeMillis() - start;
            //It prints the book that the student brought back to the screen.
            System.out.println((totalTime) + " seconds later Student " + stdid + " gave back " + bookid + ". Book");
            //It calls the librarianSetTrue() function and sets the free value of the relevant librarian to True.
            librarianSetTrue();
        } catch (InterruptedException x) {
        }
    }
    // It makes the free value of the librarian true with whichever librarian the student has completed his function
    void librarianSetTrue(){
                switch (id) {
                case 1:
                    lib1.free = true;
                    break;
                case 2:
                    lib2.free = true;
                    break;
                case 3:
                    lib3.free = true;
                    break;
            }
    }

}
