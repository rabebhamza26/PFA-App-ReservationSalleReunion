package org.example.appreservation.Repositories;

import org.example.appreservation.Entités.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository


public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findBySalleIdAndDateDebutBetween(Long salleId, LocalDateTime start, LocalDateTime end);

}



