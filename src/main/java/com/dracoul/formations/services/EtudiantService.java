package com.dracoul.formations.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dracoul.formations.entity.Classe;
import com.dracoul.formations.entity.Etudiant;
import com.dracoul.formations.execeptions.NotFoundException;
import com.dracoul.formations.interfaces.ClasseRepository;
import com.dracoul.formations.interfaces.EtudiantRepository;


import java.util.List;
import java.util.Optional;

@Service
public class EtudiantService {
    @Autowired
    private EtudiantRepository repository;

    @Autowired
    private ClasseRepository classeRepository; // Injectez la classe repository

    public List<Etudiant> getAllEtudiants() {
        return repository.findAll();
    }

    public Optional<Etudiant> getEtudiantById(Long id) {
        return repository.findById(id);
    }

    public Etudiant saveEtudiant(Etudiant etudiant) {
        // Vérifiez si la classe existe avant d'associer l'étudiant à la classe
        if (etudiant.getClasse() != null && etudiant.getClasse().getId() != null) {
            Classe classe = classeRepository.findById(etudiant.getClasse().getId())
                    .orElseThrow(() -> new NotFoundException("Classe non trouvée avec l'ID : " + etudiant.getClasse().getId()));
            etudiant.setClasse(classe);
        }

        return repository.save(etudiant);
    }

    public void deleteEtudiant(Long id) {
        repository.deleteById(id);
    }
}
