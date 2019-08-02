package com.scdevs.helpyourshelf.DBModels;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity(nameInDb = "shelf")
public class Shelf {

    @Id(autoincrement = true)
    Long ID;
    @Property(nameInDb = "bksid")
    Long bookshelfID;
    @Property(nameInDb = "row")
    int row;

    public Shelf(){

    }

    @Generated(hash = 1887922637)
    public Shelf(Long ID, Long bookshelfID, int row) {
        this.ID = ID;
        this.bookshelfID = bookshelfID;
        this.row = row;
    }

    public Long getID() {
        return this.ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public Long getBookshelfID() {
        return this.bookshelfID;
    }

    public void setBookshelfID(Long bookshelfID) {
        this.bookshelfID = bookshelfID;
    }

    public int getRow() {
        return this.row;
    }

    public void setRow(int row) {
        this.row = row;
    }
}