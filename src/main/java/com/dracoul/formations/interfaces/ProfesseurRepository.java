package com.dracoul.formations.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dracoul.formations.entity.Professeur;

@Repository
public interface ProfesseurRepository extends JpaRepository<Professeur, Long> {
}

