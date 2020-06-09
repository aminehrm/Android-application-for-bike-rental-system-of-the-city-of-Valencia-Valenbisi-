package com.amine.hrimech.uv.es;

public class report {
    private int id;
    private int stationId;
    private String name;
    private String description;
    private String status;
    private String type;

    public report(int id, int stationId, String name, String description, String status, String type) {
        this.id = id;
        this.stationId = stationId;
        this.name = name;
        this.description = description;
        this.status = status;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStationId() {
        return stationId;
    }

    public void setStationId(int stationId) {
        this.stationId = stationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
