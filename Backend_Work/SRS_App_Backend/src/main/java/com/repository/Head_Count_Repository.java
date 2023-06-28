package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.entity.Head_Count;

@Repository
public interface Head_Count_Repository extends JpaRepository<Head_Count, Integer> {

}
