package app;

public class ProfitData {



    private String nameOfState;
    private double priceWithoutTaxes;
    private String category;
    private double profit;

    public String getNameOfState() {
        return nameOfState;
    }

    public void setNameOfState(String nameOfState) {
        this.nameOfState = nameOfState;
    }

    public double getPriceWithoutTaxes() {
        return priceWithoutTaxes;
    }

    public void setPriceWithoutTaxes(double priceWithoutTaxes) {
        this.priceWithoutTaxes = priceWithoutTaxes;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

    public ProfitData(String nameOfState, double priceWithoutTaxes, String category, double profit) {
        this.nameOfState = nameOfState;
        this.priceWithoutTaxes = priceWithoutTaxes;
        this.category = category;
        this.profit = profit;
    }
}
