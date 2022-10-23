package top.kaluna.pipelineMonitor.domain;

import java.util.Date;

public class QueryResulyRow {

    private Date date;

    private Double twelve_construction_value;

    private Double twentytwo_construction_value;

    private Double twelve_one_operation_value;

    private Double twelve_two_operation_value;

    private Double twentytwo_operation_value;

    public QueryResulyRow() {
    }

    public QueryResulyRow(Date date, double twelve_construction_value, double twentytwo_construction_value, double twelve_one_operation_value, double twelve_two_operation_value, double twentytwo_operation_value) {
        this.date = date;
        this.twelve_construction_value = twelve_construction_value;
        this.twentytwo_construction_value = twentytwo_construction_value;
        this.twelve_one_operation_value = twelve_one_operation_value;
        this.twelve_two_operation_value = twelve_two_operation_value;
        this.twentytwo_operation_value = twentytwo_operation_value;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getTwelve_construction_value() {
        return twelve_construction_value;
    }

    public void setTwelve_construction_value(double twelve_construction_value) {
        this.twelve_construction_value = twelve_construction_value;
    }

    public Double getTwentytwo_construction_value() {
        return twentytwo_construction_value;
    }

    public void setTwentytwo_construction_value(double twentytwo_construction_value) {
        this.twentytwo_construction_value = twentytwo_construction_value;
    }

    public Double getTwelve_one_operation_value() {
        return twelve_one_operation_value;
    }

    public void setTwelve_one_operation_value(double twelve_one_operation_value) {
        this.twelve_one_operation_value = twelve_one_operation_value;
    }

    public Double getTwelve_two_operation_value() {
        return twelve_two_operation_value;
    }

    public void setTwelve_two_operation_value(double twelve_two_operation_value) {
        this.twelve_two_operation_value = twelve_two_operation_value;
    }

    public Double getTwentytwo_operation_value() {
        return twentytwo_operation_value;
    }

    public void setTwentytwo_operation_value(double twentytwo_operation_value) {
        this.twentytwo_operation_value = twentytwo_operation_value;
    }
}
