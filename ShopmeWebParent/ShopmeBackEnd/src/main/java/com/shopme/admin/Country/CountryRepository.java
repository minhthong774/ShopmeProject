package com.shopme.admin.Country;

import java.util.List;

import com.shopme.common.entity.Country;

import org.springframework.data.repository.CrudRepository;

public interface CountryRepository extends CrudRepository<Country, Integer> {
    public List<Country> findALLByOrderByNameAsc();
}
