package com.github.damianmcdonald.jpaprojections.model;

import javax.persistence.*;

@Entity
@Table(name = "PLAYER")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY,
            cascade = { CascadeType.PERSIST,
                    CascadeType.MERGE })
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    @Column(name = "FIRSTNAME")
    private String firstName;

    @Column(name = "SURNAME")
    private String surname;

    @Column(name = "POSITION")
    private String position;

    @Column(name = "RATING")
    private int rating;

    @OneToOne(mappedBy = "player", fetch = FetchType.LAZY)
    private Salary salary;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
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

    public Salary getSalary() {
        return salary;
    }

    public void setSalary(Salary salary) {
        this.salary = salary;
    }
}
