package app;

public class InterCosts {
    private String name;
    private String curr;
    private double transportFee;
    private double importTarifCons;
    private double importTarifOther;

    public InterCosts(String name, String curr, double transportFee, double importTarifCons, double importTarifOther) {
        this.name = name;
        this.curr = curr;
        this.transportFee = transportFee;
        this.importTarifCons = importTarifCons;
        this.importTarifOther = importTarifOther;
    }
}
