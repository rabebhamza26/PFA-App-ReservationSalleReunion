package org.example.appreservation.Services;

import jakarta.transaction.Transactional;
import org.example.appreservation.Entités.Reservation;
import org.example.appreservation.Entités.Salle;
import org.example.appreservation.Entités.Utilisateur;
import org.example.appreservation.Repositories.ReservationRepository;
import org.example.appreservation.Repositories.SalleRepository;
import org.example.appreservation.enums.StatutReservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service

public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private NotificationService notificationService;


    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public Reservation getReservationById(Long id) {
        return reservationRepository.findById(id).orElse(null);
    }

    public Reservation createReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }


    public Reservation updateReservation(Long id, Reservation updated) {
        Optional<Reservation> existingOpt = reservationRepository.findById(id);
        if (existingOpt.isPresent()) {
            Reservation existing = existingOpt.get();
            existing.setDateDebut(updated.getDateDebut());
            existing.setDateFin(updated.getDateFin());
            existing.setSalle(updated.getSalle());
            existing.setUtilisateur(updated.getUtilisateur());
            existing.setStatut(updated.getStatut());
            return reservationRepository.save(existing);
        }
        return null;
    }

    public void deleteReservation(Long id) {
        reservationRepository.deleteById(id);
    }

    public boolean isSalleDisponible(Long salleId, LocalDateTime start, LocalDateTime end) {
        List<Reservation> conflits = reservationRepository.findBySalleIdAndDateDebutBetween(
                salleId, start.minusMinutes(1), end.plusMinutes(1));
        return conflits.isEmpty();
    }

    /*public void annulerReservation(Long id) {
        reservationRepository.findById(id).ifPresent(reservation -> {
            reservation.setStatut(StatutReservation.ANNULEE);
            reservationRepository.save(reservation);
        });
    }*/
    public void annulerReservation(Long id) {
        reservationRepository.findById(id).ifPresent(reservation -> {
            reservation.setStatut(StatutReservation.ANNULEE);
            reservationRepository.save(reservation);

            // Envoyer une notification à l'utilisateur
            String message = "Votre réservation du " + reservation.getDateDebut() +
                    " au " + reservation.getDateFin() + " a été annulée.";
            notificationService.envoyerNotification(reservation.getUtilisateur(), message);
        });
    }


    /*public void confirmerReservation(Long id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Réservation introuvable"));
        reservation.setStatut(StatutReservation.CONFIRMEE);
        reservationRepository.save(reservation);
    }*/
    public void confirmerReservation(Long id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Réservation introuvable"));

        reservation.setStatut(StatutReservation.CONFIRMEE);
        reservationRepository.save(reservation);

        // Envoyer une notification à l'utilisateur
        String message = "Votre réservation du " + reservation.getDateDebut() +
                " au " + reservation.getDateFin() + " a été confirmée.";
        notificationService.envoyerNotification(reservation.getUtilisateur(), message);
    }


}