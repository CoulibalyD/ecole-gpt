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

import com.dracoul.formations.entity.Classe;
import com.dracoul.formations.execeptions.NotFoundException;
import com.dracoul.formations.services.ClasseService;

@RestController
@RequestMapping("/api/classes")
public class ClasseController {
    @Autowired
    private ClasseService service;

    @GetMapping
    public List<Classe> getAllClasses() {
        return service.getAllClasses();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Classe> getClasseById(@PathVariable Long id) {
        Classe classe = service.getClasseById(id).orElse(null);
        return classe != null ? ResponseEntity.ok(classe) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> saveClasse(@RequestBody Classe classe) {
        try {
            Classe savedClasse = service.saveClasse(classe);
            return new ResponseEntity<>(savedClasse, HttpStatus.CREATED);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteClasse(@PathVariable Long id) {
        try {
            service.deleteClasse(id);
            return ResponseEntity.ok("Classe supprimée avec succès");
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
