package com.aninfo.model;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Employee {
    @JsonProperty("legajo")
    private Long idNumber;

    @JsonProperty("Nombre")
    private String name;

    @JsonProperty("Apellido")
    private String lastName;

    public String toString()
    {
        return "Empleado{" +
                "legajo=" + idNumber+
                ", nombre='" + name + '\'' +
                ", apellido='" + lastName + '\'' +
                '}';

    }

    public Employee(Integer id){
    }

    public Long getIdNumber() {
        return this.idNumber;
    }
}
