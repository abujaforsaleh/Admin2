package com.example.ticketadmin.bundle;

public class PlaneBundle {

    String agencyNameVariable, planeNumberVariable, SourceCounterTicketPriceVariable,    sourceAirportNameVariable,   DestinationAirportNameVariable,  seatPlan, sourceAirportTime,   destinationAirportTime;

    public PlaneBundle(){

    }

    public PlaneBundle(String agencyNameVariable, String planeNumberVariable, String sourceCounterTicketPriceVariable, String sourceAirportNameVariable, String destinationAirportNameVariable, String seatPlan, String sourceAirportTime, String destinationAirportTime) {
        this.agencyNameVariable = agencyNameVariable;
        this.planeNumberVariable = planeNumberVariable;
        this.SourceCounterTicketPriceVariable = sourceCounterTicketPriceVariable;
        this.sourceAirportNameVariable = sourceAirportNameVariable;
        this.DestinationAirportNameVariable = destinationAirportNameVariable;
        this.seatPlan = seatPlan;
        this.sourceAirportTime = sourceAirportTime;
        this.destinationAirportTime = destinationAirportTime;
    }


    public void setAgencyNameVariable(String agencyNameVariable) {
        this.agencyNameVariable = agencyNameVariable;
    }

    public void setPlaneNumberVariable(String planeNumberVariable) {
        this.planeNumberVariable = planeNumberVariable;
    }

    public void setSourceCounterTicketPriceVariable(String sourceCounterTicketPriceVariable) {
        SourceCounterTicketPriceVariable = sourceCounterTicketPriceVariable;
    }

    public void setSourceAirportNameVariable(String sourceAirportNameVariable) {
        this.sourceAirportNameVariable = sourceAirportNameVariable;
    }

    public void setDestinationAirportNameVariable(String destinationAirportNameVariable) {
        DestinationAirportNameVariable = destinationAirportNameVariable;
    }

    public void setSeatPlan(String seatPlan) {
        this.seatPlan = seatPlan;
    }

    public void setSourceAirportTime(String sourceAirportTime) {
        this.sourceAirportTime = sourceAirportTime;
    }

    public void setDestinationAirportTime(String destinationAirportTime) {
        this.destinationAirportTime = destinationAirportTime;
    }

    public String getAgencyNameVariable() {
        return agencyNameVariable;
    }

    public String getPlaneNumberVariable() {
        return planeNumberVariable;
    }

    public String getSourceCounterTicketPriceVariable() {
        return SourceCounterTicketPriceVariable;
    }

    public String getSourceAirportNameVariable() {
        return sourceAirportNameVariable;
    }

    public String getDestinationAirportNameVariable() {
        return DestinationAirportNameVariable;
    }

    public String getSeatPlan() {
        return seatPlan;
    }

    public String getSourceAirportTime() {
        return sourceAirportTime;
    }

    public String getDestinationAirportTime() {
        return destinationAirportTime;
    }
}
