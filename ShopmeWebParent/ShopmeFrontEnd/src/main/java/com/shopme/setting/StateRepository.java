package com.shopme.setting;

import java.util.List;

import com.shopme.common.entity.Country;
import com.shopme.common.entity.State;

import org.springframework.data.repository.CrudRepository;

public interface StateRepository extends CrudRepository<State, Integer> {
    public List<State> findByCountryOrderByNameAsc(Country country);
}
