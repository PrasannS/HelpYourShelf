package com.scdevs.helpyourshelf.DBModels;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity(nameInDb = "book")
public class Book {

    @Id(autoincrement = true)
    Long ID;
    @Property(nameInDb = "bookid")
    Long bookID;
    @Property(nameInDb = "bksid")
    Long bookshelfID;
    @Property(nameInDb = "shelfid")
    Long shelfID;
    @Property(nameInDb = "rating")
    double rating;
    @Property(nameInDb = "read")
    boolean read;

    public Book(){

    }

    @Generated(hash = 1653485820)
    public Book(Long ID, Long bookID, Long bookshelfID, Long shelfID, double rating,
            boolean read) {
        this.ID = ID;
        this.bookID = bookID;
        this.bookshelfID = bookshelfID;
        this.shelfID = shelfID;
        this.rating = rating;
        this.read = read;
    }

    public Long getID() {
        return this.ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public Long getBookID() {
        return this.bookID;
    }

    public void setBookID(Long bookID) {
        this.bookID = bookID;
    }

    public Long getBookshelfID() {
        return this.bookshelfID;
    }

    public void setBookshelfID(Long bookshelfID) {
        this.bookshelfID = bookshelfID;
    }

    public Long getShelfID() {
        return this.shelfID;
    }

    public void setShelfID(Long shelfID) {
        this.shelfID = shelfID;
    }

    public double getRating() {
        return this.rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public boolean getRead() {
        return this.read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }
}
