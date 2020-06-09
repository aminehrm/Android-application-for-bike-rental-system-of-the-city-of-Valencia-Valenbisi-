package com.amine.hrimech.uv.es;

import java.io.Serializable;

public class Station implements Serializable {
    private String name;
    private String adress;
    private int number;
    private int avai_number;
    private int free;
    private int total;
    private double coordinate1;
    private double coordinate2;
    private double distance;
    private String last_update;

    public Station(String name, String adress, int number, int avai_number, int free, int total, double coordinate1, double coordinate2,String last_update) {
        this.name = name;
        this.adress = adress;
        this.number = number;
        this.avai_number = avai_number;
        this.free = free;
        this.total = total;
        this.coordinate1 = coordinate1;
        this.coordinate2 = coordinate2;
        this.last_update=last_update;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getAVNumber() {
        return avai_number;
    }

    public void setAvai_number(int avai_number) {
        this.avai_number = avai_number;
    }

    public int getFree() {
        return free;
    }

    public void setFree(int free) {
        this.free = free;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public double getCoordinate1() {
        return coordinate1;
    }

    public void setCoordinate1(double coordinate1) {
        this.coordinate1 = coordinate1;
    }

    public double getCoordinate2() {
        return coordinate2;
    }

    public void setCoordinate2(double coordinate2) {
        this.coordinate2 = coordinate2;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getLast_update() {
        return last_update;
    }

    public void setLast_update(String last_update) {
        this.last_update = last_update;
    }
}
