package com.example.demo;

import com.example.demo.Artiste;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ArtisteRepository extends CrudRepository <Artiste, Long>{
    List<Artiste> findByName(String name);

    Artiste findOne(long artisteId);
}
