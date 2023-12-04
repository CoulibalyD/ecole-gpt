package com.dracoul.formations.controlleurs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dracoul.formations.entity.Etudiant;
import com.dracoul.formations.execeptions.NotFoundException;
import com.dracoul.formations.services.EtudiantService;

@RestController
@RequestMapping("/api/etudiants")
public class EtudiantController {
    @Autowired
    private EtudiantService service;

    @GetMapping
    public List<Etudiant> getAllEtudiants() {
        return service.getAllEtudiants();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Etudiant> getEtudiantById(@PathVariable Long id) {
        Etudiant etudiant = service.getEtudiantById(id).orElse(null);
        return etudiant != null ? ResponseEntity.ok(etudiant) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> saveEtudiant(@RequestBody Etudiant etudiant) {
        try {
            Etudiant savedEtudiant = service.saveEtudiant(etudiant);
            return new ResponseEntity<>(savedEtudiant, HttpStatus.CREATED);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEtudiant(@PathVariable Long id) {
        try {
            service.deleteEtudiant(id);
            return ResponseEntity.ok("Etudiant supprimé avec succès");
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}

