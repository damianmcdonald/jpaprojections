package com.github.damianmcdonald.jpaprojections.model;

import org.hibernate.envers.NotAudited;

import javax.persistence.*;

@Entity
@Table(name = "TEAM_COLOUR")
public class TeamColour {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY,
            cascade = { CascadeType.PERSIST,
                    CascadeType.MERGE })
    @JoinColumn(name = "TEAM_ID")
    @NotAudited
    private Team team;

    @Column(name = "NAME")
    private String name;

    @Column(name = "HTMLCODE")
    private String htmlCode;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHtmlCode() {
        return htmlCode;
    }

    public void setHtmlCode(String htmlCode) {
        this.htmlCode = htmlCode;
    }
}
