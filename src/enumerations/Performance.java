package enumerations;

public enum Performance {
    LOW(0.6), MID(0.8), HIGH(1.0);

    private Double value;
    Performance(Double performance) {
        this.value = performance;
    }

    public Double getValue() {
        return value;
    }
}
