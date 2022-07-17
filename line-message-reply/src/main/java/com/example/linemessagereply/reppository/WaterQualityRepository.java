package com.example.linemessagereply.reppository;

import com.example.linemessagereply.entity.WaterQuality;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface WaterQualityRepository extends MongoRepository<WaterQuality, String> {

//    @Query("{'NO': {'$gte': ?0}}")
//    List<WaterQuality> findAll();

}
