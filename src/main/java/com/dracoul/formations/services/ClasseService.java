package com.dracoul.formations.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dracoul.formations.entity.Classe;
import com.dracoul.formations.interfaces.ClasseRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ClasseService {
    @Autowired
    private ClasseRepository repository;

    public List<Classe> getAllClasses() {
        return repository.findAll();
    }

    public Optional<Classe> getClasseById(Long id) {
        return repository.findById(id);
    }

    public Classe saveClasse(Classe classe) {
        return repository.save(classe);
    }

    public void deleteClasse(Long id) {
        repository.deleteById(id);
    }
}
