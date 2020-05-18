package app.Tests;

import app.ProfitData;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProfitCalculatorTest {

    @Test
    void calculate() {
        String NameOfState = "Alabama";
        double baseTax = 0;
        double startingPrice = 4.99;
        double finalPrice = 10;
        double finalPriceWithoutTaxes = Math.round(finalPrice / (1.0 + baseTax/100.0) * 100.0) / 100.0;

        ProfitData p1 = new ProfitData("Alabama", 10, 5.01);

        assertEquals(p1.getNameOfState(), "Alabama");
        assertEquals(p1.getPriceWithoutTaxes(), finalPriceWithoutTaxes);
        assertEquals(p1.getProfit(), (finalPriceWithoutTaxes-startingPrice));


    }

    @Test
    void calculate1() {
        String NameOfState = "California";
        double baseTax = 0;
        double startingPrice = 4.99;
        double finalPrice = 50;
        double finalPriceWithoutTaxes = Math.round(finalPrice / (1.0 + baseTax/100.0) * 100.0) / 100.0;

        ProfitData p1 = new ProfitData("California", 50,45.01);

        assertEquals(p1.getNameOfState(), "California");
        assertEquals(p1.getPriceWithoutTaxes(), finalPriceWithoutTaxes);
        assertEquals(p1.getProfit(), (finalPriceWithoutTaxes-startingPrice));


    }

    @Test
    void calculate2() {
        String NameOfState = "Alabama";
        double baseTax = 4;
        double startingPrice = 0.2;
        double finalPrice = 8;
        double finalPriceWithoutTaxes = Math.round(finalPrice / (1.0 + baseTax/100.0) * 100.0) / 100.0;

        ProfitData p1 = new ProfitData("Alabama", 7.69,7.49);

        assertEquals(p1.getNameOfState(), "Alabama");
        assertEquals(p1.getPriceWithoutTaxes(), finalPriceWithoutTaxes);
        assertEquals(p1.getProfit(), (finalPriceWithoutTaxes-startingPrice));


    }

    @Test
    void calculate3() {
        String NameOfState = "Alabama";
        double baseTax = 0;
        double startingPrice = 4.99;
        double finalPrice = 10;
        double finalPriceWithoutTaxes = Math.round(finalPrice / (1.0 + baseTax/100.0) * 100.0) / 100.0;

        ProfitData p1 = new ProfitData("Alabama", 10, 5.01);

        assertEquals(p1.getNameOfState(), "Alabama");
        assertEquals(p1.getPriceWithoutTaxes(), finalPriceWithoutTaxes);
        assertEquals(p1.getProfit(), (finalPriceWithoutTaxes-startingPrice));


    }
}