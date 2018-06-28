package com.github.damianmcdonald.jpaprojections.model;

import javax.persistence.*;

@Entity
@Table(name = "SALARY")
public class Salary {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PLAYER_ID")
    private Player player;

    @Column(name = "GROSS")
    private int gross;

    @Column(name = "NET")
    private int net;

    @Column(name = "MEDICAL_CONTRIBUTION")
    private int medicalContribution;

    @Column(name = "CHARITABLE_CONTRIBUTION")
    private int charitableContribution;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getGross() {
        return gross;
    }

    public void setGross(int gross) {
        this.gross = gross;
    }

    public int getNet() {
        return net;
    }

    public void setNet(int net) {
        this.net = net;
    }

    public int getMedicalContribution() {
        return medicalContribution;
    }

    public void setMedicalContribution(int medicalContribution) {
        this.medicalContribution = medicalContribution;
    }

    public int getCharitableContribution() {
        return charitableContribution;
    }

    public void setCharitableContribution(int charitableContribution) {
        this.charitableContribution = charitableContribution;
    }
}
