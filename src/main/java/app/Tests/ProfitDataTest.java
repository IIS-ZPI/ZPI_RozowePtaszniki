package app.Tests;

import app.ProfitData;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProfitDataTest {
    @Test
    void getNameOfState() {
        ProfitData profitdata = new ProfitData("Alaska", 10.5, 90.20, 0.05);
        assertEquals(profitdata.getNameOfState(), "Alaska");

    }

    @Test
    void setNameOfState() {
        String nameOfState = "Alaska";
        ProfitData profitdata = new ProfitData(nameOfState, 10.5, 90.20, 0.05);
        assertEquals(profitdata.getNameOfState(), nameOfState);
    }

    @Test
    void getPriceWithoutTaxes() {
        ProfitData profitdata = new ProfitData("Alaska", 10.5, 90.20, 0.05);
        assertEquals(profitdata.getPriceWithoutTaxes(), 10.5);
    }

    @Test
    void setPriceWithoutTaxes() {
        double priceWithoutTaxes = 10.5;
        ProfitData profitdata = new ProfitData("Alaska", priceWithoutTaxes, 90.20, 0.05);
        assertEquals(profitdata.getPriceWithoutTaxes(), priceWithoutTaxes);
    }

    @Test
    void getProfit() {
        ProfitData profitdata = new ProfitData("Alaska", 10.5, 90.20, 0.05);
        assertEquals(profitdata.getProfit(), 90.20);
    }

    @Test
    void setProfit() {
        double profit = 90.20;
        ProfitData profitdata = new ProfitData("Alaska", 10.5, profit, 0.05);
        assertEquals(profitdata.getProfit(), profit);
    }


}