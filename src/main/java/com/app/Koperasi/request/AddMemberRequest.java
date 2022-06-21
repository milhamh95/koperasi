package com.app.Koperasi.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class AddMemberRequest {
    @NotEmpty(message = "name should not be empty")
    @Size(min = 3,message = "name should be at least 3 chars")
    private String name;

    @NotEmpty(message = "address should not be empty")
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
