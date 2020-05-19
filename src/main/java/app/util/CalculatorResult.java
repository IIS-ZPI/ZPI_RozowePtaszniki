package app.util;

import app.ProfitData;
import com.google.gson.Gson;
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
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
