package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SamolotyRepository extends JpaRepository<Samolot, Long> {

    List<Samolot> findAllByModel(String model);

    List<Samolot> findAllByLotnisko(String lotnisko);


}
