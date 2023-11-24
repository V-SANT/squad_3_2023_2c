package com.aninfo.model;

import javax.persistence.Id;
import java.util.ArrayList;

public class Employee {

    private String name;

    @Id
    private Long dni;
    private Long hourlyPay;

    Employee(String name, Long dni, Long hourlyPay) {
        this.name = name;
        this.dni = dni;
        this.hourlyPay = hourlyPay;
    }
}
