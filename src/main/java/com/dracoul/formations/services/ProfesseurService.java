package com.dracoul.formations.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dracoul.formations.entity.Professeur;
import com.dracoul.formations.interfaces.ProfesseurRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProfesseurService {
    @Autowired
    private ProfesseurRepository repository;

    public List<Professeur> getAllProfesseurs() {
        return repository.findAll();
    }

    public Optional<Professeur> getProfesseurById(Long id) {
        return repository.findById(id);
    }

    public Professeur saveProfesseur(Professeur professeur) {
        return repository.save(professeur);
    }

    public void deleteProfesseur(Long id) {
        repository.deleteById(id);
    }
}

