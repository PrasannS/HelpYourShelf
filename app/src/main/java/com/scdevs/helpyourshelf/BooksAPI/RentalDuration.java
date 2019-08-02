
package com.scdevs.helpyourshelf.BooksAPI;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RentalDuration {

    @SerializedName("unit")
    @Expose
    private String unit;
    @SerializedName("count")
    @Expose
    private Double count;

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Double getCount() {
        return count;
    }

    public void setCount(Double count) {
        this.count = count;
    }

}
