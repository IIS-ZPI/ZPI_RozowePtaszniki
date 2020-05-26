package app;

import com.google.gson.annotations.SerializedName;

public class ProfitData {
    private String nameOfState;
    private double profit;
    private double priceWithoutTaxes;

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

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

    public ProfitData(String nameOfState, double priceWithoutTaxes, double profit) {
        this.nameOfState = nameOfState;
        this.priceWithoutTaxes = priceWithoutTaxes;
        this.profit = profit;
    }

    @Override
    public String toString() {
        return  '\"' + nameOfState + "\":{" +
                "\"zysk\":" + profit +
                ",\"cena bez podatku\":" + priceWithoutTaxes +
                "},";
    }
}
