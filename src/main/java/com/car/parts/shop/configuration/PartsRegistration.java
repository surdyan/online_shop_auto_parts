package com.car.parts.shop.configuration;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class PartsRegistration {

    @Id
    String Parts_title;

    String Manufactur;

    String Manufacturing_country;

    String rate;

    String Price;

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getParts_title() {
        return Parts_title;
    }

    public void setParts_title(String parts_title) {
        Parts_title = parts_title;
    }

    public String getManufactur() {
        return Manufactur;
    }

    public void setManufactur(String manufactur) {
        Manufactur = manufactur;
    }

    public String getManufacturing_country() {
        return Manufacturing_country;
    }

    public void setManufacturing_country(String manufacturing_country) {
        Manufacturing_country = manufacturing_country;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

}
