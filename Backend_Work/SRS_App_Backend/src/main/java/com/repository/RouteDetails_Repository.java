package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.entity.Route_Details;

@Repository
public interface RouteDetails_Repository extends JpaRepository<Route_Details, String> {

}
