package com.example.admin.bundle;

import java.util.ArrayList;
import java.util.List;

public class TrainBundle {

    String destination, counter1_name, counter2_name, counter3_name, travles_name, bogy_number, counter_1_ticket_price, counter_2_ticket_price, counter_3_ticket_price, train_type;
    String date, time;
    List<String> seatPlane = new ArrayList<>();
    String counter1_arrival_time, counter2_arrival_time,counter3_arrival_time, destination_arrival_time;


    public TrainBundle() {


    }

    public TrainBundle(String travles_name, String bogy_number, String counter_1_ticket_price, String counter_2_ticket_price, String counter_3_ticket_price, String counter1_name, String counter2_name, String counter3_name, String destination, String train_type, List<String> seatPlane, String counter1_arrival_time, String counter2_arrival_time, String counter3_arrival_time, String destination_arrival_time) {

        this.travles_name = travles_name;
        this.bogy_number = bogy_number;
        this.counter_1_ticket_price = counter_1_ticket_price;
        this.counter_2_ticket_price = counter_2_ticket_price;
        this.counter_3_ticket_price = counter_3_ticket_price;
        this.counter1_name =counter1_name;
        this.counter2_name =counter2_name;
        this.counter3_name =counter3_name;
        this.destination =destination;
        this.train_type=train_type;
        this.seatPlane = seatPlane;
        this.counter1_arrival_time = counter1_arrival_time;
        this.counter2_arrival_time = counter2_arrival_time;
        this.counter3_arrival_time = counter3_arrival_time;
        this.destination_arrival_time = destination_arrival_time;

    }


    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public List<String> getSeatPlane() {
        return seatPlane;
    }

    public String getTravles_name() {
        return travles_name;
    }

    public String getDestination() {
        return destination;
    }


    public String getbogy_number() {
        return bogy_number;
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

    public String getTrain_type() {
        return train_type;
    }
}
