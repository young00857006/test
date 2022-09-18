package com.example.linemessagereply.reppository;

import com.example.linemessagereply.entity.Member;
import com.example.linemessagereply.entity.WaterQuality;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface WaterQualityRepository extends MongoRepository<WaterQuality, String> {

}
