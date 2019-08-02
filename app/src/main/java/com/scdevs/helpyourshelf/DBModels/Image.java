package com.scdevs.helpyourshelf.DBModels;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity(nameInDb = "image")
public class Image {

    @Id(autoincrement = true)
    Long ID;
    @Property(nameInDb = "bookid")
    Long bookID;
    @Property(nameInDb = "bksid")
    Long bookshelfID;
    @Property(nameInDb = "shelfid")
    Long shelfID;
    @Property(nameInDb = "url")
    String url;

    public Image(){

    }

    @Generated(hash = 1197203480)
    public Image(Long ID, Long bookID, Long bookshelfID, Long shelfID, String url) {
        this.ID = ID;
        this.bookID = bookID;
        this.bookshelfID = bookshelfID;
        this.shelfID = shelfID;
        this.url = url;
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

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
