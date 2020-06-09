package app.util;

import app.ProfitData;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CalculatorResult {
    private int id;
    @SerializedName("cena podstawowa")
    private double startingPrice;
    @SerializedName("pożądana cena")
    private double finalPrice;
    @SerializedName("stany")
    private ArrayList<ProfitData> states;

    public CalculatorResult(int id, double startingPrice, double finalPrice, ArrayList<ProfitData> states) {
        this.id = id;
        this.startingPrice = startingPrice;
        this.finalPrice = finalPrice;
        this.states = states;
    }

    public String getResult() {
        StringBuilder result = new StringBuilder();
        result.append("{" + "\"id\":").append(id).append(",");
        result.append("\"cena podstawowa\":").append(startingPrice).append(",");
        result.append("\"pożądana cena\":").append(finalPrice).append(",");
        for(ProfitData val : states) {
            result.append(val.toString());
        }
        result.deleteCharAt(result.lastIndexOf(","));
        result.append("}");
        return result.toString();
    }
}
