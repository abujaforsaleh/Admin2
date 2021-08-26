package com.example.admin.bundle;

public class BusBundle {

    String destination, counter1_name, counter2_name, counter3_name, travles_name, bus_number, counter_1_ticket_price, counter_2_ticket_price, counter_3_ticket_price, total_seat, bus_type;
    String availableSeat, date, time, seatPlane;
    String counter1_arrival_time, counter2_arrival_time,counter3_arrival_time, destination_arrival_time;
    public BusBundle() {

    }

    public BusBundle(String travles_name, String bus_number, String counter_1_ticket_price, String counter_2_ticket_price, String counter_3_ticket_price, String total_seat, String counter1_name, String counter2_name, String counter3_name, String destination, String bus_type, String seatPlane, String counter1_arrival_time, String counter2_arrival_time, String counter3_arrival_time, String destination_arrival_time) {
        this.travles_name = travles_name;
        this.bus_number = bus_number;
        this.counter_1_ticket_price = counter_1_ticket_price;
        this.counter_2_ticket_price = counter_2_ticket_price;
        this.counter_3_ticket_price = counter_3_ticket_price;
        this.total_seat = total_seat;
        this.counter1_name =counter1_name;
        this.counter2_name =counter2_name;
        this.counter3_name =counter3_name;
        this.availableSeat =total_seat;
        this.destination =destination;
        this.bus_type=bus_type;
        this.seatPlane = seatPlane;
        this.counter1_arrival_time = counter1_arrival_time;
        this.counter2_arrival_time = counter2_arrival_time;
        this.counter3_arrival_time = counter3_arrival_time;
        this.destination_arrival_time = destination_arrival_time;

    }


    public String getAvailableSeat() {
        return availableSeat;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getSeatPlane() {
        return seatPlane;
    }

    public String getTravles_name() {
        return travles_name;
    }

    public String getDestination() {
        return destination;
    }


    public String getBus_type() {
        return bus_type;
    }

    public String getBus_number() {
        return bus_number;
    }

    public String getCounter_1_ticket_price() {
        return counter_1_ticket_price;
    }

    public String getCounter_2_ticket_price() {
        return counter_2_ticket_price;
    }

    public String getCounter_3_ticket_price() {
        return counter_3_ticket_price;
    }

    public String getTotal_seat() {
        return total_seat;
    }

    public String getCounter1_name() {
        return counter1_name;
    }

    public String getCounter2_name() {
        return counter2_name;
    }

    public String getCounter3_name() {
        return counter3_name;
    }

    public String getCounter1_arrival_time() {
        return counter1_arrival_time;
    }

    public String getCounter2_arrival_time() {
        return counter2_arrival_time;
    }

    public String getCounter3_arrival_time() {
        return counter3_arrival_time;
    }

    public String getDestination_arrival_time() {
        return destination_arrival_time;
    }
}
