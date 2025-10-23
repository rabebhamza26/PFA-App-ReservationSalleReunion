package org.example.appreservation.Repositories;

import org.example.appreservation.Entités.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
    Optional<Utilisateur> findByEmailAndPassword(String email, String password);
    Optional<Utilisateur> findByEmail(String email);
}