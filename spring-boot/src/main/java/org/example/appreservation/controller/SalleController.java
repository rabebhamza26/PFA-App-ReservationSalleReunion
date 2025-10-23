package org.example.appreservation.controller;

import org.example.appreservation.Entités.Salle;
import org.example.appreservation.Services.SalleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/salles")
public class SalleController {
    @Autowired
    private SalleService salleService;

    @GetMapping("/getAll")
    public List<Salle> getAllSalles() {
        return salleService.getAllSalles();
    }

    @GetMapping("/get/{id}")
    public Salle getSalleById(@PathVariable Long id) {
        return salleService.getSalleById(id);
    }

    @PostMapping("/post")
    public Salle createSalle(@RequestBody Salle salle) {
        return salleService.saveSalle(salle);
    }

    @PutMapping("/update/{id}")
    public Salle updateSalle(@PathVariable Long id, @RequestBody Salle salle) {
        return salleService.updateSalle(id, salle);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteSalle(@PathVariable Long id) {
        salleService.deleteSalle(id);
    }



}