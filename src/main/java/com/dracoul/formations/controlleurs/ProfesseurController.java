package com.dracoul.formations.controlleurs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.dracoul.formations.entity.Professeur;
import com.dracoul.formations.services.ProfesseurService;

import java.util.List;

@RestController
@RequestMapping("/api/professeurs")
public class ProfesseurController {
    @Autowired
    private ProfesseurService service;

    @GetMapping
    public List<Professeur> getAllProfesseurs() {
        return service.getAllProfesseurs();
    }

    @GetMapping("/{id}")
    public Professeur getProfesseurById(@PathVariable Long id) {
        return service.getProfesseurById(id).orElse(null);
    }

    @PostMapping
    public Professeur saveProfesseur(@RequestBody Professeur professeur) {
        return service.saveProfesseur(professeur);
    }

    @DeleteMapping("/{id}")
    public void deleteProfesseur(@PathVariable Long id) {
        service.deleteProfesseur(id);
    }
}
