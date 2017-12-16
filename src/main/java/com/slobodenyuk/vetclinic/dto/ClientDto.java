package com.slobodenyuk.vetclinic.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonAutoDetect(
        fieldVisibility = com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE,
        getterVisibility = com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE,
        setterVisibility = com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClientDto {
    @JsonProperty
    private Long id;

    @JsonProperty
    private String name;

    @JsonProperty
    private String phone;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
