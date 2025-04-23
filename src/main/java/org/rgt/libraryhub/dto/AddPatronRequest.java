package org.rgt.libraryhub.dto;

public class AddPatronRequest {

    private Integer patronId;
    private String name;

    // Getters and Setters
    public Integer getPatronId() {
        return patronId;
    }

    public void setPatronId(Integer patronId) {
        this.patronId = patronId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
