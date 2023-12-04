package com.dracoul.formations.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dracoul.formations.entity.Cours;
import com.dracoul.formations.entity.Professeur;
import com.dracoul.formations.execeptions.NotFoundException;
import com.dracoul.formations.interfaces.CoursRepository;
import com.dracoul.formations.interfaces.ProfesseurRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CoursService {
    @Autowired
    private CoursRepository repository;

    @Autowired
    private ProfesseurRepository professeurRepository; // Injectez la classe repository

    public List<Cours> getAllCours() {
        return repository.findAll();
    }

    public Optional<Cours> getCoursById(Long id) {
        return repository.findById(id);
    }

    public Cours saveCours(Cours cours) {
        // Vérifiez si le professeur existe avant d'associer le cours au professeur
        if (cours.getProfesseur() != null && cours.getProfesseur().getId() != null) {
            Professeur professeur = professeurRepository.findById(cours.getProfesseur().getId())
                    .orElseThrow(() -> new NotFoundException("Professeur non trouvé avec l'ID : " + cours.getProfesseur().getId()));
            cours.setProfesseur(professeur);
        }

        return repository.save(cours);
    }

    public void deleteCours(Long id) {
        repository.deleteById(id);
    }
}
