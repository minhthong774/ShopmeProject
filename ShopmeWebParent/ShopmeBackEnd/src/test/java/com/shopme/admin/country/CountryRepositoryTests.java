package com.shopme.admin.country;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import com.shopme.admin.Country.CountryRepository;
import com.shopme.common.entity.Country;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class CountryRepositoryTests {
    
    @Autowired
    CountryRepository countryRepo;

    @Test
    public void testCreateCountry(){
        Country country = new Country("China", "CN");
        Country countrySaved = countryRepo.save(country);
        assertThat(countrySaved).isNotNull();
        assertThat(country.getId()).isGreaterThan(0);
    }

    @Test
    public void testCreateManyCountry(){
        Country country = new Country("Republic of India", "IN");
        Country country1 = new Country("United States", "US");
        Country country2 = new Country("United Kingdom", "UK");
        Country country3 = new Country("Vietnam", "VN");
        countryRepo.saveAll(List.of(country, country1, country2, country3));
        Iterable<Country> listCountries = countryRepo.findAll();
        assertThat(listCountries).size().isEqualTo(4);
    }

    @Test
    public void testListCountries(){
        List<Country> listCountries = countryRepo.findALLByOrderByNameAsc();
        listCountries.forEach(System.out::println);
    }

    @Test
    public void testUpdateCountry(){
        Country usa = countryRepo.findById(1).get();
        usa.setCode("USA");
        usa.setName("United States");
        assertThat(countryRepo.save(usa)).isNotNull();
    }

    @Test
    public void testDeteteCountry(){
        Country usa = countryRepo.findById(1).get();
        countryRepo.delete(usa);
        Optional<Country> deleted = countryRepo.findById(1);
        assertThat(!deleted.isPresent());
    }
}
