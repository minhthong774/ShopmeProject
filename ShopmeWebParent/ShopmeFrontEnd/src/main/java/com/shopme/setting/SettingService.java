package com.shopme.setting;

import java.util.List;

import com.shopme.common.entity.Setting;
import com.shopme.common.entity.SettingCategory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SettingService {
    @Autowired
    private SettingRepository repo;


    public List<Setting> getGeneralSettings(){
        return repo.findByTwocategories(SettingCategory.GENERAL, SettingCategory.CURRENCY);
    }

}
