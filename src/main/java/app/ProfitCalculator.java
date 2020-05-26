package app;
import app.util.CalculatorResult;
import com.google.gson.Gson;

import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProfitCalculator {

    private ArrayList<TaxesInState> taxesInStates;
    private int id;
    private double startingPrice;
    private double finalPrice;

    ProfitCalculator() {
        this.taxesInStates = new ArrayList<>();
        this.taxesInStates.add(new TaxesInState("Alabama",4,13.5,"4","4","0","4","4","4"));
        this.taxesInStates.add(new TaxesInState("California",7.25,10.5,"0","7.25","0","7.25","7.25","0"));
        this.id = 0;
    }

    /**
     * @return ProfitData ArrayList to json
     * @throws IllegalArgumentException
     */
    public String CalculateForAllStates(String id, String finalCost) throws IllegalArgumentException {
        this.id++;
        if(id == null || finalCost == null)
            throw new IllegalArgumentException();
        Product product = null;

        try {
            product = Database.getProductById(Integer.parseInt(id));
        } catch (URISyntaxException | SQLException e) {
            e.printStackTrace();
        }

        try {
            this.finalPrice = Double.parseDouble(finalCost);
        }catch (NumberFormatException e){
            throw new IllegalArgumentException();
        }

        ArrayList<ProfitData> listOfProfits = new ArrayList<>();
        for (TaxesInState val : this.taxesInStates) {
            double baseTax = 0;
            try {
                baseTax = parseValue(val.getTaxForCategory(product.getCategory()),baseTax,finalPrice);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
                throw new IllegalArgumentException();
            }

            double finalPriceWithoutTaxes = Math.round(this.finalPrice / (1.0 + baseTax/100.0) * 100.0) / 100.0;
            listOfProfits.add(new ProfitData(val.getName(),finalPriceWithoutTaxes, finalPriceWithoutTaxes - product.getPrice() ));
        }

        CalculatorResult result = new CalculatorResult(this.id,this.finalPrice,product.getPrice(),listOfProfits);
        return result.getResult();
    }

    private double parseValue(String val, double baseTax, double finalPrice) {
        if(val.contains("$")) {
            double limit = Double.parseDouble(val.substring(val.indexOf("$")+1));
            if(limit > finalPrice)
                baseTax = 0;
        }
        else {
            baseTax = Double.parseDouble(val);
        }
        return baseTax;
    }

    /**
     * @param request product, cost, category, final_cost, state
     * @return ProfitData to json
     * @throws IllegalArgumentException
     */
    /*
    public String CalculateForSingleStates(String request) throws IllegalArgumentException {
        if(request == null )
            throw new IllegalArgumentException();

        String[] values = request.split(" ");

        if(values.length < 5)
            throw new IllegalArgumentException();

        try {
            this.startingPrice = Double.parseDouble(values[1]);
            this.finalPrice = Double.parseDouble(values[3]);
        }catch (NumberFormatException e){
            throw new IllegalArgumentException();
        }

        //This should find tax values by state name in database but it is hardcoded for now
        TaxesInState tax = null;
        for(TaxesInState val : this.taxesInStates) {
            if(val.getName().equals(values[4])) {
                tax = val;
                break;
            }
        }

        double baseTax = 0;
        try {
            baseTax = parseValue(tax.getTaxForCategory(values[2]),baseTax,finalPrice);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            throw new IllegalArgumentException();
        }

        double finalPriceWithoutTaxes = Math.round(this.finalPrice / (1.0 + baseTax/100.0) * 100.0) / 100.0;

        Gson gson = new Gson();
        return gson.toJson(new ProfitData(tax.getName(),finalPriceWithoutTaxes, finalPriceWithoutTaxes - this.startingPrice ));
    }
     */



}
