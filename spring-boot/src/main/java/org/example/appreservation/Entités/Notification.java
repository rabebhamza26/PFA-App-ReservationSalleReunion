package org.example.appreservation.Entités;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "notifications")

public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String message;
    private LocalDateTime dateEnvoi;
    @ManyToOne
    @JoinColumn(name = "utilisateur_id")
    private Utilisateur destinataire;
    // Constructeurs, getters et setters
    public Notification() {
    }
    public Notification(String message, Utilisateur destinataire) {
        this.message = message;
        this.destinataire = destinataire;
        this.dateEnvoi = LocalDateTime.now();
    }
    // Getters et setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getDateEnvoi() {
        return dateEnvoi;
    }

    public void setDateEnvoi(LocalDateTime dateEnvoi) {
        this.dateEnvoi = dateEnvoi;
    }

    public Utilisateur getDestinataire() {
        return destinataire;
    }

    public void setDestinataire(Utilisateur destinataire) {
        this.destinataire = destinataire;
    }
}









