package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.entity.Ship_Details;

@Repository
public interface ShipDetails_Repository extends JpaRepository<Ship_Details, String> {

}
