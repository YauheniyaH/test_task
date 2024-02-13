package es.epam.recordReader.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class TemporalRecord implements Serializable {
    private float actualValue;
    private long observationDate;
    private long asOfDate;

    public TemporalRecord (){
        this.actualValue = Float.parseFloat(null);
        this.observationDate = Long.parseLong(null);
        this.asOfDate = Long.parseLong(null);

    }

    public TemporalRecord(float value, long observationDate, long asOfDate) {
        this.actualValue = value;
        this.observationDate = observationDate;
        this.asOfDate = asOfDate;
    }

    public float getActualValue() {
        return actualValue;
    }

    public void setActualValue(float actualValue) {
        this.actualValue = actualValue;
    }

    public long getObservationDate() {
        return observationDate;
    }

    public void setObservationDate(long observationDate) {
        this.observationDate = observationDate;
    }

    public long getAsOfDate() {
        return asOfDate;
    }

    public void setAsOfDate(long asOfDate) {
        this.asOfDate = asOfDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TemporalRecord that)) return false;
        return Float.compare(that.actualValue, actualValue) == 0 && Objects.equals(observationDate, that.observationDate) && Objects.equals(asOfDate, that.asOfDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(actualValue, observationDate, asOfDate);
    }

    @Override
    public String toString() {
        return "{" +
                "actualValue=" + actualValue +
                ", observationDate=" + observationDate +
                ", asOfDate=" + asOfDate +
                '}';
    }
}
