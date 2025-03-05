package com.ifba.ms_user.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ifba.ms_user.models.Occupation;

@Repository
public interface OccupationRepository extends JpaRepository<Occupation, Long> {
    
}
