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

import com.dracoul.formations.entity.Cours;
import com.dracoul.formations.execeptions.NotFoundException;
import com.dracoul.formations.services.CoursService;

@RestController
@RequestMapping("/api/cours")
public class CoursController {
    @Autowired
    private CoursService service;

    @GetMapping
    public List<Cours> getAllCours() {
        return service.getAllCours();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cours> getCoursById(@PathVariable Long id) {
        Cours cours = service.getCoursById(id).orElse(null);
        return cours != null ? ResponseEntity.ok(cours) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> saveCours(@RequestBody Cours cours) {
        try {
            Cours savedCours = service.saveCours(cours);
            return new ResponseEntity<>(savedCours, HttpStatus.CREATED);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCours(@PathVariable Long id) {
        try {
            service.deleteCours(id);
            return ResponseEntity.ok("Cours supprimé avec succès");
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
