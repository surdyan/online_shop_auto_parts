package com.car.parts.shop.configuration;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class OrderRegistration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long OID;

    String Parts_name;

    String Price;

    String busername;

    public String getBusername() {
        return busername;
    }

    public void setBusername(String busername) {
        this.busername = busername;
    }

    public Long getOID() {
        return OID;
    }

    public String getParts_name() {
        return Parts_name;
    }

    public void setParts_name(String parts_name) {
        Parts_name = parts_name;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public void setOID(Long oID) {
        OID = oID;
    }

}