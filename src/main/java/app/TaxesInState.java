package app;

public class TaxesInState {
    private String name;
    private  double baseTax;
    private  double maxLocalSurtax;
    private String groceries;
    private String preparedFood;
    private String prescriptionDrug;
    private String nonPrescriptionDrug;
    private String clothing;
    private String intangibles;

    public TaxesInState(String name, double baseTax, double maxLocalSurtax, String groceries, String preparedFood, String prescriptionDrug, String nonPrescriptionDrug, String clothing, String intangibles) {
        this.name = name;
        this.baseTax = baseTax;
        this.maxLocalSurtax = maxLocalSurtax;
        this.groceries = groceries;
        this.preparedFood = preparedFood;
        this.prescriptionDrug = prescriptionDrug;
        this.nonPrescriptionDrug = nonPrescriptionDrug;
        this.clothing = clothing;
        this.intangibles = intangibles;
    }

    public String getName() {
        return name;
    }

    public double getBaseTaxValue() {
        return baseTax;
    }

    public String getTaxForCategory(String Category) throws NoSuchFieldException, IllegalAccessException {
        return (String) this.getClass().getDeclaredField(Category).get(this);
    }
}

