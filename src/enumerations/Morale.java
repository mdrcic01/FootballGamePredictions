package enumerations;

public enum Morale {
    LOW(0.6),
    MID(0.8),
    HIGH(1.0);

    private Double value;
    Morale(Double value) {
        this.value = value;
    }

    public Double getValue() {
        return value;
    }
}
