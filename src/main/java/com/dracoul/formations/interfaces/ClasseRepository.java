package com.dracoul.formations.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dracoul.formations.entity.Classe;

@Repository
public interface ClasseRepository extends JpaRepository<Classe, Long> {
}

