package com.aninfo.model;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Client{
    @JsonProperty("id")
    private Long id;

    @JsonProperty("razon social")
    private String business_name;

    @JsonProperty("CUIT")
    private String cuit;

    public String toString()
    {
        return "Cliente{" +
                "id=" + id+
                ", razon social='" + business_name + '\'' +
                ", CUIT='" + cuit + '\'' +
                '}';

    }

    public Long getId(){ return id;}

}