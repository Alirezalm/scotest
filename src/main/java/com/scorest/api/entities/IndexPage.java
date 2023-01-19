package com.scorest.api.entities;


public class IndexPage {
    private final String status;

    public IndexPage(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "IndexPage{" +
                "status='" + status + '\'' +
                '}';
    }

}
