package com.github.damianmcdonald.jpaprojections.dto;

public class PlayerDto {

    private String firstName;
    private String surname;
    private String position;
    private int rating;
    private int netSalary;
    private int charitableContribution;

    public PlayerDto(final String firstName, final String surname, final String position, final int rating,
                     final int netSalary, final int charitableContribution) {
        this.firstName = firstName;
        this.surname = surname;
        this.position = position;
        this.rating = rating;
        this.netSalary = netSalary;
        this.charitableContribution = charitableContribution;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getNetSalary() {
        return netSalary;
    }

    public void setNetSalary(int netSalary) {
        this.netSalary = netSalary;
    }

    public int getCharitableContribution() {
        return charitableContribution;
    }

    public void setCharitableContribution(int charitableContribution) {
        this.charitableContribution = charitableContribution;
    }
}
