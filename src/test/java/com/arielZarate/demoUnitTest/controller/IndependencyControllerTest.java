package com.arielZarate.demoUnitTest.controller;

import com.arielZarate.demoUnitTest.models.Country;
import com.arielZarate.demoUnitTest.models.CountryResponse;
import com.arielZarate.demoUnitTest.repositories.CountryRepository;
import com.arielZarate.demoUnitTest.util.DiferenciaEntreFechas;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.time.Period;
import java.util.Objects;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class IndependencyControllerTest {

    private IndependencyController independencyController;

    @Mock
    private CountryRepository countryRepository;

    @Mock
    private DiferenciaEntreFechas diferenciaEntreFechas;


    @BeforeEach
    public void setup() {

        //TODO NO ES NECESARIO DADO A QUE MOCKITO INYECTA SOLO POR DEFECTO
        this.independencyController = new IndependencyController(countryRepository, diferenciaEntreFechas);
    }


    @DisplayName("Debe devolver los detalles de un país con independencia correcta")
    @Test
    public void  testGetCountryDetails_whenCountryExists() {

        //GIVEN  Se configura el país mockeado y el comportamiento esperado
        //        Country mockCountry = new Country();
        Country mockCountry = new Country();
        mockCountry.setCountryId(1L);
        mockCountry.setIsoCode("DO");
        mockCountry.setCountryName("República Dominicana");
        mockCountry.setCountryCapital("Santo Domingo");
        mockCountry.setCountryIdependenceDate("23/11/1986");


        //=======WHEN========
        // Definir lo que debe hacer el mock de countryRepository cuando se le pase "DO"
        when(countryRepository.findCountryByIsoCode("DO")).thenReturn(mockCountry);

        //definimos que deberia hacer cuando llame al metodo calculateYear..
        Period mockPeriod = Period.ofYears(38);//esto es lo que va devolver siempre :period

        //cuando le pasemos cualquier string devolvera un period o mockPeriod
       // when(diferenciaEntreFechas.calculateYearsOfIndependency(anyString())).thenReturn(mockPeriod);
        Period mockkPeriod = diferenciaEntreFechas.calculateYearsOfIndependency(mockCountry.getCountryIdependenceDate());
        when(diferenciaEntreFechas.calculateYearsOfIndependency(mockCountry.getCountryIdependenceDate())).thenReturn(mockPeriod);


     //   System.out.println("Periodo completo: " + mockkPeriod); // Agrega esta línea para ver todo el periodo


        //invoco al metodo getCountryDetails y verifico las repuestas
        ResponseEntity<CountryResponse> response = this.independencyController.getCountryDetails("DO");
        //=======================


        //THEN
        assertNotNull(mockCountry,"no debe ser null");
       // assertTrue(1L,mockCountry.get);
        assertNotNull(response, "la respuesta no debe ser null");
        assertEquals(38, Objects.requireNonNull(response.getBody()).getYearsOfIndependency(), "El metodo debe devolver 38 que son los años de independencia");
        assertEquals(mockCountry.getCountryCapital(), response.getBody().getCapitalName());
        assertEquals(mockCountry.getCountryName(), response.getBody().getCountryName(), "el pais debe ser el mismo ");
        assertEquals(mockCountry.getCountryIdependenceDate(), response.getBody().getIndependenceDate());
        assertEquals(0,response.getBody().getMonthsOfIndependency());
        assertEquals(0,response.getBody().getDayssOfIndependency());


    }


    @Test
    public void testGetCountryDetails_whenCountryDoesNotExist() {


        Country mockCountry = new Country();
        mockCountry.setCountryId(1L);
        mockCountry.setIsoCode("DO");
        mockCountry.setCountryName("República Dominicana");
        mockCountry.setCountryCapital("Santo Domingo");
        mockCountry.setCountryIdependenceDate("23/11/1986");


        // Configurar lo que el repositorio debe devolver cuando el país no se encuentra
        when(countryRepository.findCountryByIsoCode("DO")).thenReturn(null);

        // Llamar al método
        ResponseEntity<CountryResponse> response = independencyController.getCountryDetails("DO");

        // Verificar que la respuesta es la esperada (aunque no se encuentra el país)
        assertEquals(404, response.getStatusCodeValue());  // Verificamos que el estado sea 404
        assertNull(response.getBody());
    }




}