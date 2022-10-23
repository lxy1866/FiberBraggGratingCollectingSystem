package top.kaluna.pipelineMonitor.domain;


import java.util.Date;

public class VariantPoint {

    private int id;

    private Date date;

    private double value;

    public VariantPoint() {
    }

    public VariantPoint(Date date, double value) {
        this.date = date;
        this.value = value;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
