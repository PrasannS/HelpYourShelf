package com.scdevs.helpyourshelf.DBModels;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity(nameInDb = "bookshelf")
public class BookShelf {

    @Id(autoincrement = true)
    Long ID;
    @Property(nameInDb = "name")
    String name;
    @Property(nameInDb = "description")
    String description;

    public BookShelf(){

    }

    @Generated(hash = 15051983)
    public BookShelf(Long ID, String name, String description) {
        this.ID = ID;
        this.name = name;
        this.description = description;
    }

    public Long getID() {
        return this.ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
