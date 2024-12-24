package com.arielZarate.demoUnitTest.controller;



import com.arielZarate.demoUnitTest.models.Country;
import com.arielZarate.demoUnitTest.models.CountryResponse;
import com.arielZarate.demoUnitTest.repositories.CountryRepository;
import com.arielZarate.demoUnitTest.util.DiferenciaEntreFechas;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.Period;
import java.util.Optional;

/**
 * Author: ArielZarate
 */
@RestController()
public class IndependencyController {

    CountryResponse countryResponse;
    Optional<Country> country;
    CountryRepository countryRepository;
    DiferenciaEntreFechas diferenciaEntreFechas;

    public IndependencyController(CountryRepository countryRepository,DiferenciaEntreFechas diferenciaEntreFechas) {
        this.countryRepository = countryRepository;
        this.diferenciaEntreFechas = diferenciaEntreFechas;
    }

    @GetMapping(path = "/country/{countryId}")
    public ResponseEntity<CountryResponse> getCountryDetails(@PathVariable("countryId") String countryId) {
        country = Optional.of(new Country());
        countryResponse = new CountryResponse();

        country = Optional.ofNullable(countryRepository.findCountryByIsoCode(countryId.toUpperCase()));

        if (country.isPresent()) {
            Period period = diferenciaEntreFechas.calculateYearsOfIndependency(country.get().getCountryIdependenceDate());
            // System.out.println("mostrando period"+ period.getMonths());
           // System.out.println("mostrando period"+ period.getDays());
            countryResponse.setCountryName(country.get().getCountryName());
            countryResponse.setCapitalName(country.get().getCountryCapital());
            countryResponse.setIndependenceDate(country.get().getCountryIdependenceDate());
            countryResponse.setDayssOfIndependency(period.getDays());
            countryResponse.setMonthsOfIndependency(period.getMonths());
            countryResponse.setYearsOfIndependency(period.getYears());

            return ResponseEntity.ok(countryResponse);
        }
        else{
            // Si el país no se encuentra, devolver un 404 Not Found
            return ResponseEntity.notFound().build();  // Devuelve 404 Not Found
        }

    }
}