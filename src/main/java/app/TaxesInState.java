package app;

public class TaxesInState {
    private String name;
    private double baseTax;
    private double maxLocalSurtax;
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
        this.nonPrescriptionDrug = nonPrescriptionDrug;
        this.prescriptionDrug = prescriptionDrug;
        this.clothing = clothing;
        this.preparedFood = preparedFood;
        this.intangibles = intangibles;
    }

    public String getName() {
        return name;
    }

    public double getBaseTaxValue() {
        return baseTax;
    }

    public String getTaxForCategory(String category) throws NoSuchFieldException, IllegalAccessException {
        switch (category){
            case "Groceries":
                return groceries;
            case "Non-prescription-drug":
                return nonPrescriptionDrug;
            case "Non-prescription-drugs":
                return nonPrescriptionDrug;
            case "Prescription-drug":
                return prescriptionDrug;
            case "Clothing":
                return clothing;
            case "Prepared-food":
                return preparedFood;
            case "Intangibles":
                return intangibles;
            default:
                return null;
        }
    }
}

