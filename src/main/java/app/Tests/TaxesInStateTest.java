package app.Tests;

import app.TaxesInState;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaxesInStateTest {

    TaxesInState taxesInState = new TaxesInState("Alabama",4,13.5,"4","4","0","4","4","4",0.04);
    TaxesInState taxesInState1 = new TaxesInState("Alaska",0,0,"0","0","0","0","0","0",0.04);
    TaxesInState taxesInState2 = new TaxesInState("Arizona",5.5,10.725,"0","0","0","0","0","0",0.04);
    TaxesInState taxesInState3 = new TaxesInState("Arkansas",6.5,11.625,"0","0","0","0","0","0",0.04);
    TaxesInState taxesInState4 = new TaxesInState("California",7.25,10.5,"0","0","0","0","0","0",0.04);
    TaxesInState taxesInState5 = new TaxesInState("Colorado",2.9,10.0,"0","0","0","0","0","0",0.04);
    TaxesInState taxesInState6 = new TaxesInState("Connecticut",6.35,6.35,"0","0","0","0","0","0",0.04);
    TaxesInState taxesInState7 = new TaxesInState("Delaware",0,0,"0","0","0","0","0","0",0.04);
    TaxesInState taxesInState8 = new TaxesInState("District of Columbia",5.75,5.75,"0","0","0","0","0","0",0.04);
    TaxesInState taxesInState9 = new TaxesInState("Florida",6,6.5,"0","0","0","0","0","0",0.04);

    @Test
    void getNameAlaska() {
        TaxesInState taxesInState = new TaxesInState("Alaska",0,0,"0","0","0","0","0","0",0.04);
        assertEquals(taxesInState.getName(), "Alaska");
    }

    @Test
    void getNameArizona() {
        TaxesInState taxesInState = new TaxesInState("Arizona",5.5,10.725,"0","0","0","0","0","0",0.04);
        assertEquals(taxesInState.getName(), "Arizona");
    }

    @Test
    void getNameArkansas() {
        TaxesInState taxesInState = new TaxesInState("Arkansas",6.5,11.625,"0","0","0","0","0","0",0.04);
        assertEquals(taxesInState.getName(), "Arkansas");
    }

    @Test
    void getNameColorado() {
        TaxesInState taxesInState = new TaxesInState("Colorado",2.9,10.0,"0","0","0","0","0","0",0.04);
        assertEquals(taxesInState.getName(), "Colorado");
    }

    @Test
    void getNameConnecticut() {
        TaxesInState taxesInState = new TaxesInState("Connecticut",6.35,6.35,"0","0","0","0","0","0",0.04);
        assertEquals(taxesInState.getName(), "Connecticut");
    }

    @Test
    void getNameDelaware() {
        TaxesInState taxesInState = new TaxesInState("Delaware",0,0,"0","0","0","0","0","0",0.04);
        assertEquals(taxesInState.getName(), "Delaware");
    }

    @Test
    void getNameDistrictOfColumbia() {
        TaxesInState taxesInState = new TaxesInState("District of Columbia",5.75,5.75,"0","0","0","0","0","0",0.04);
        assertEquals(taxesInState.getName(), "District of Columbia");
    }

    @Test
    void getNameFlorida() {
        TaxesInState taxesInState = new TaxesInState("Florida",6,6.5,"0","0","0","0","0","0",0.04);
        assertEquals(taxesInState.getName(), "Florida");
    }
    @Test
    void getLogisticCostAlaska() {
        TaxesInState taxesInState = new TaxesInState("Alaska",0,0,"0","0","0","0","0","0",0.04);
        assertEquals(taxesInState.getLogisticCost(), 0.04);
    }
    @Test
    void getLogisticCostArkansas() {
        TaxesInState taxesInState = new TaxesInState("Arkansas",6.5,11.625,"0","0","0","0","0","0",0.04);
        assertEquals(taxesInState.getLogisticCost(), 0.04);
    }
    @Test
    void getLogisticCostArizona() {
        TaxesInState taxesInState = new TaxesInState("Arizona",0,7,"0","0","0","0","0","0",0.04);
        assertEquals(taxesInState.getLogisticCost(), 0.04);
    }

    @Test
    void getLogisticCostColorado() {
        TaxesInState taxesInState = new TaxesInState("Colorado",2.9,10.0,"0","0","0","0","0","0",0.04);
        assertEquals(taxesInState.getLogisticCost(), 0.04);
    }

    @Test
    void getLogisticCostCalifornia() {
        TaxesInState taxesInState = new TaxesInState("California",7.25,10.5,"0","7.25","0","7.25","7.25","0", 0.17);
        assertEquals(taxesInState.getLogisticCost(), 0.17);
    }

    @Test
    void getLogisticCostDelaware() {
        TaxesInState taxesInState = new TaxesInState("Delaware",0,0,"0","0","0","0","0","0",0.04);
        assertEquals(taxesInState.getLogisticCost(), 0.04);
    }

    @Test
    void getLogisticCostConnecticut() {
        TaxesInState taxesInState = new TaxesInState("Connecticut",6.35,6.35,"0","0","0","0","0","0",0.04);
        assertEquals(taxesInState.getLogisticCost(), 0.04);
    }

    @Test
    void getLogisticCostFlorida() {
        TaxesInState taxesInState = new TaxesInState("Florida",6,6.5,"0","0","0","0","0","0",0.04);
        assertEquals(taxesInState.getLogisticCost(), 0.04);
    }

    @Test
    void getLogisticCostDistrictOfColumbia() {
        TaxesInState taxesInState = new TaxesInState("District of Columbia",5.75,5.75,"0","0","0","0","0","0",0.04);
        assertEquals(taxesInState.getLogisticCost(), 0.04);
    }
    @Test
    void getNameAlabama() {
        TaxesInState taxesInState = new TaxesInState("Alabama",4,13.5,"4","4","0","4","4","4",0.04);
        assertEquals(taxesInState.getName(), "Alabama");
    }
    @Test
    void getBaseTaxValueAlabama() {
        TaxesInState taxesInState = new TaxesInState("Alabama",4,13.5,"4","4","0","4","4","4",0.04);
        assertEquals(taxesInState.getBaseTaxValue(), 4);
    }
    @Test
    void getNameCalifornia() {
        TaxesInState taxesInState = new TaxesInState("California",7.25,10.5,"0","7.25","0","7.25","7.25","0", 0.17);
        assertEquals(taxesInState.getName(), "California");
    }
    @Test
    void getBaseTaxValueCalifornia() {
        TaxesInState taxesInState = new TaxesInState("California",7.25,10.5,"0","7.25","0","7.25","7.25","0", 0.17);
        assertEquals(taxesInState.getBaseTaxValue(), 7.25);
    }

}