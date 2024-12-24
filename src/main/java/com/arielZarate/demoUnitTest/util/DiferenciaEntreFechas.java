package com.arielZarate.demoUnitTest.util;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

@Component
public class DiferenciaEntreFechas {

    public Period calculateYearsOfIndependency(String independenceDay) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        LocalDate localDate = LocalDate.parse(independenceDay, formatter);
        LocalDate today = LocalDate.now();
        //return la fecha entre el perido y hoy
        return Period.between(localDate, today);
    }
}