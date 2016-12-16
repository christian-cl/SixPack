package es.uma.lcc.neo.cintrano.shortestpath.model;

/**
 * Created by christian on 15/12/16.
 */
public class Weight {
    private double value;
    private String type;

    public Weight(double value, String type) {
        this.value = value;
        this.type = type;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "(" + type + ":" + value + ")";
    }
}
