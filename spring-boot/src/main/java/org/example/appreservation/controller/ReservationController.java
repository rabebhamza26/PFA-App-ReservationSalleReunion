package org.example.appreservation.controller;

import org.example.appreservation.Entités.Reservation;
import org.example.appreservation.Entités.Utilisateur;
import org.example.appreservation.Services.ReservationService;
import org.example.appreservation.enums.StatutReservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @GetMapping
    public List<Reservation> getAllReservations() {
        return reservationService.getAllReservations();
    }

    @GetMapping("/{id}")
    public Reservation getReservationById(@PathVariable Long id) {
        return reservationService.getReservationById(id);
    }



    @PostMapping("/post/create")
    public ResponseEntity<?> createReservation(@RequestBody Reservation reservation) {
        System.out.println(reservation.toString());
        try{

            reservation.setStatut(StatutReservation.EN_ATTENTE);
            Reservation saved = reservationService.createReservation(reservation);
            if (saved != null) {
                return ResponseEntity.ok(saved);
            } else {
                return ResponseEntity
                        .status(HttpStatus.CONFLICT)
                        .body("Salle non disponible à ce créneau.");
            }
        } catch (Exception e) {
            System.out.println("---------------------"+e);
        }
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body("Salle non disponible à ce créneau.");
    }



    @PutMapping("/{id}")
    public Reservation updateReservation(@PathVariable Long id, @RequestBody Reservation updated) {
        return reservationService.updateReservation(id, updated);
    }

    @DeleteMapping("/{id}")
    public void deleteReservation(@PathVariable Long id) {
        reservationService.deleteReservation(id);
    }

    @PutMapping("/annuler/{id}")
    public void annulerReservation(@PathVariable Long id) {

        reservationService.annulerReservation(id);
    }

    @PutMapping("/confirmer/{id}")
    public void confirmerReservation(@PathVariable Long id) {
        reservationService.confirmerReservation(id);
    }


}