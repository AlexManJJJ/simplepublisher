package com.example.demo.model;

import java.util.Set;

public class Publication {

    private String message;

    private Set<Destination> destinations;

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public Set<Destination> getDestinations() {
        return destinations;
    }

    public void setDestinations(Set<Destination> destinations) {
        this.destinations = destinations;
    }

    @Override
    public String toString() {
        return "Publication{" +
                "message='" + message + '\'' +
                ", destinations=" + destinations +
                '}';
    }

}
