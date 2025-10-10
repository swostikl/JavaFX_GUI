package entity;

import jakarta.persistence.*;

@Entity
@Table(name = "CURRENCY")
public class Currency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 3)
    private String abbreviation;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private double rateTOUSD;

    public Currency() {
    }

    public Currency(String abbreviation, String name, double rateTOUSD) {
        this.abbreviation = abbreviation;
        this.name = name;
        this.rateTOUSD = rateTOUSD;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRateTOUSD() {
        return rateTOUSD;
    }

    public void setRateTOUSD(double rateTOUSD) {
        this.rateTOUSD = rateTOUSD;
    }

    @Override
    public String toString() {
        return abbreviation + " - " + name;
    }
}
