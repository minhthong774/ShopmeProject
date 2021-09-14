package com.shopme.admin.setting;

import java.util.List;

import com.shopme.common.entity.Currency;

import org.springframework.data.repository.CrudRepository;

public interface CurrencyRepository extends CrudRepository <Currency, Integer> {
    
    public List<Currency> findAllByOrderByNameAsc();
}
