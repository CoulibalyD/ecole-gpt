package com.dracoul.formations.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dracoul.formations.entity.Cours;

@Repository
public interface CoursRepository extends JpaRepository<Cours, Long> {
}
