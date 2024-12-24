package com.arielZarate.demoUnitTest.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Period;

import static org.junit.jupiter.api.Assertions.*;

class DiferenciaEntreFechasTest {


    private DiferenciaEntreFechas diferenciaEntreFechas;



    @BeforeEach
    public void setup(){
        this.diferenciaEntreFechas=new DiferenciaEntreFechas();//inicializo  la isntancia
    }


    @Test
    void calculateYearsOfIndependency() {

        Period p=diferenciaEntreFechas.calculateYearsOfIndependency("09/07/1816");

       System.out.println("año "+ p.getYears());
        System.out.println("meses "+ p.getMonths());
        System.out.println("dias "+ p.getDays());




        assertNotNull(p,"no debe ser null");
        assertEquals(208,p.getYears(),"El numero debe ser igual a 208");

        assertTrue(p.getYears()>0,"El numero de años debe ser positivo");
        assertEquals(5,p.getMonths());
        assertEquals(15,p.getDays());
    }
}