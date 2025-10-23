package org.example.appreservation.Services;

import org.example.appreservation.Entités.Reservation;
import org.example.appreservation.Entités.Salle;
import org.example.appreservation.Repositories.ReservationRepository;
import org.example.appreservation.Repositories.SalleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class SalleService {
    @Autowired
    private SalleRepository salleRepository;

    public List<Salle> getAllSalles() {
        return salleRepository.findAll();
    }

    public Salle getSalleById(Long id) {
        return salleRepository.findById(id).orElse(null);
    }

    public Salle saveSalle(Salle salle) {
        return salleRepository.save(salle);
    }

    public Salle updateSalle(Long id, Salle updatedSalle) {
        Optional<Salle> optionalSalle = salleRepository.findById(id);
        if (optionalSalle.isPresent()) {
            Salle existing = optionalSalle.get();
            existing.setNom(updatedSalle.getNom());
            existing.setCapacite(updatedSalle.getCapacite());
            existing.setEquipements(updatedSalle.getEquipements());
            return salleRepository.save(existing);
        }
        return null;
    }

    public void deleteSalle(Long id) {
        salleRepository.deleteById(id);
    }

}



