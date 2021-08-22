package com.example.ticketadmin.bundle;

public class BookingBundle {

    String user_name, seats, booking_date, travel_date, total_cost, bus_number, selectedIdes;
    String source, destination, arrival_time, depertar_time, busCatagory;
    String vehicleType, trainTicketCabinIndex;

    public BookingBundle() {

    }

    public BookingBundle(String user_name, String seats, String booking_date, String travel_date, String total_cost, String bus_number, String source, String destination, String arrival_time, String depertar_time, String selectedIdes, String vehicleType, String trainTicketCabinIndex, String busCatagory) {
        this.user_name = user_name;
        this.seats = seats;
        this.booking_date = booking_date;
        this.travel_date = travel_date;
        this.total_cost = total_cost;
        this.bus_number = bus_number;
        this.source = source;
        this.destination = destination;
        this.arrival_time = arrival_time;
        this.depertar_time = depertar_time;
        this.selectedIdes=selectedIdes;
        this.vehicleType = vehicleType;
        this.trainTicketCabinIndex = trainTicketCabinIndex;
        this.busCatagory = busCatagory;

        //this.selectedIdes="7,8,9";

    }

    public String getBusCatagory() {
        return busCatagory;
    }

    public String getUser_name() {
        return user_name;
    }

    public String getSeats() {
        return seats;
    }

    public String getBooking_date() {
        return booking_date;
    }

    public String getTravel_date() {
        return travel_date;
    }

    public String getTotal_cost() {
        return total_cost;
    }

    public String getBus_number() {
        return bus_number;
    }

    public String getSource() {
        return source;
    }

    public String getSelectedIdes() {
        return selectedIdes;
    }

    public String getDestination() {
        return destination;
    }

    public String getArrival_time() {
        return arrival_time;
    }

    public String getDepertar_time() {
        return depertar_time;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public String getTrainTicketCabinIndex() {
        return trainTicketCabinIndex;
    }

}
