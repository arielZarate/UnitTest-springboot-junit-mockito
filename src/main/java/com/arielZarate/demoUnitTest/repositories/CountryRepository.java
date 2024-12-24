package com.arielZarate.demoUnitTest.repositories;


import com.arielZarate.demoUnitTest.models.Country;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends CrudRepository<Country, Long> {
    Country findCountryByIsoCode(String isoCode);
}
