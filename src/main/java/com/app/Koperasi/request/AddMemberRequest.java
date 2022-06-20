package com.app.Koperasi.request;

public class AddMemberRequest {
    private String name;
    private String address;

    public AddMemberRequest() {
    }

    public AddMemberRequest(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
