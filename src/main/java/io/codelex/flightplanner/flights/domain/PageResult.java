package io.codelex.flightplanner.flights.domain;

import java.util.ArrayList;
import java.util.List;

public class PageResult<Flight> {
    private int page;
    private int totalItems;
    private List<Flight> items;

    public PageResult() {
        this.page = 0;
        this.totalItems = 0;
        this.items = new ArrayList<>();
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }

    public List<Flight> getItems() {
        return items;
    }

    public void setItems(List<Flight> items) {
        this.items = items;
    }

    public void addItem(Flight flight) {
        items.add(flight);
        totalItems++;
    }

}
