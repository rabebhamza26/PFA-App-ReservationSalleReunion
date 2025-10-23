package org.example.appreservation.Entités;

import jakarta.persistence.*;
import org.example.appreservation.enums.StatutReservation;

import java.time.LocalDateTime;

@Entity
@Table(name = "reservations")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;

    @Enumerated(EnumType.STRING)
    private StatutReservation statut;

    @ManyToOne
    @JoinColumn(name = "utilisateur_id")
    private Utilisateur utilisateur;

    @ManyToOne
    @JoinColumn(name = "salle_id")
    private Salle salle;

    public Reservation() {
        this.statut = StatutReservation.EN_ATTENTE;
    }

    public Reservation(LocalDateTime dateDebut, LocalDateTime dateFin, Utilisateur utilisateur, Salle salle) {
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.utilisateur = utilisateur;
        this.salle = salle;
        this.statut = StatutReservation.EN_ATTENTE;
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDateTime getDateDebut() { return dateDebut; }
    public void setDateDebut(LocalDateTime dateDebut) { this.dateDebut = dateDebut; }

    public LocalDateTime getDateFin() { return dateFin; }
    public void setDateFin(LocalDateTime dateFin) { this.dateFin = dateFin; }

    public StatutReservation getStatut() { return statut; }
    public void setStatut(StatutReservation statut) { this.statut = statut; }

    public Utilisateur getUtilisateur() { return utilisateur; }
    public void setUtilisateur(Utilisateur utilisateur) { this.utilisateur = utilisateur; }

    public Salle getSalle() { return salle; }
    public void setSalle(Salle salle) { this.salle = salle; }

}