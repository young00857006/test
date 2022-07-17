package com.example.linemessagereply.Service;

import com.example.linemessagereply.entity.WaterQuality;
import com.example.linemessagereply.reppository.WaterQualityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WaterQualityService {

    @Autowired
    private WaterQualityRepository repository;

    public String getWaterQualities() {
        String message = "";

        List<WaterQuality> list = repository.findAll();
        for(WaterQuality i: list){
            message += i.getNO() + i.getTDSvalue() + i.getDATETIME() + "\n";
        }
        return message;
    }
}
