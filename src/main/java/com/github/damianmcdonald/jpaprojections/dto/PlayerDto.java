package com.github.damianmcdonald.jpaprojections.dto;

public class PlayerDto {

    private final String firstName;
    private final String surname;
    private final String position;
    private final int rating;
    private final int netSalary;
    private final int charitableContribution;

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

    public String getSurname() {
        return surname;
    }

    public String getPosition() {
        return position;
    }

    public int getRating() {
        return rating;
    }

    public int getNetSalary() {
        return netSalary;
    }

    public int getCharitableContribution() {
        return charitableContribution;
    }

}