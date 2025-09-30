package entity;

public class Currency {
    private String abbreviation;
    private String name;
    private double rateTOUSD;

    public Currency(String abbreviation, String name, double rateTOUSD){
        this.abbreviation = abbreviation;
        this.name = name;
        this.rateTOUSD = rateTOUSD;
    }

    public String getAbbreviation(){
        return abbreviation;
    }
    public String getName(){
        return name;
    }
    public double getRateTOUSD(){
        return rateTOUSD;
    }

    public void setAbbreviation(String abbreviation){
        this.abbreviation = abbreviation;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setRateTOUSD(double rateTOUSD){
        this.rateTOUSD = rateTOUSD;
    }

    @Override
    public String toString() {
        return abbreviation + " - " + name;
    }
}