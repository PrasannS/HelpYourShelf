
package com.scdevs.helpyourshelf.BooksAPI;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Offer {

    @SerializedName("finskyOfferType")
    @Expose
    private Integer finskyOfferType;
    @SerializedName("listPrice")
    @Expose
    private ListPrice_ listPrice;
    @SerializedName("retailPrice")
    @Expose
    private RetailPrice_ retailPrice;
    @SerializedName("giftable")
    @Expose
    private Boolean giftable;
    @SerializedName("rentalDuration")
    @Expose
    private RentalDuration rentalDuration;

    public Integer getFinskyOfferType() {
        return finskyOfferType;
    }

    public void setFinskyOfferType(Integer finskyOfferType) {
        this.finskyOfferType = finskyOfferType;
    }

    public ListPrice_ getListPrice() {
        return listPrice;
    }

    public void setListPrice(ListPrice_ listPrice) {
        this.listPrice = listPrice;
    }

    public RetailPrice_ getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(RetailPrice_ retailPrice) {
        this.retailPrice = retailPrice;
    }

    public Boolean getGiftable() {
        return giftable;
    }

    public void setGiftable(Boolean giftable) {
        this.giftable = giftable;
    }

    public RentalDuration getRentalDuration() {
        return rentalDuration;
    }

    public void setRentalDuration(RentalDuration rentalDuration) {
        this.rentalDuration = rentalDuration;
    }

}
