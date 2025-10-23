package org.example.appreservation.Entités;

import jakarta.persistence.*;
import org.example.appreservation.enums.RoleUtilisateur;

import java.util.List;

@Entity
@Table(name = "utilisateurs")
public class Utilisateur {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String nomUtilisateur;
        private String email;

        private String password;

        @Enumerated(EnumType.STRING)
        private RoleUtilisateur role;



        // Constructeurs
        public Utilisateur() {}

        public Utilisateur(String nomUtilisateur, String email, String password, RoleUtilisateur role) {
                this.nomUtilisateur = nomUtilisateur;
                this.email = email;
                this.password = password;
                this.role = role;
        }

        // Getters et Setters
        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }

        public String getNomUtilisateur() { return nomUtilisateur; }
        public void setNomUtilisateur(String nomUtilisateur) { this.nomUtilisateur = nomUtilisateur; }

        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }

        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }

        public RoleUtilisateur getRole() { return role; }
        public void setRole(RoleUtilisateur role) { this.role = role; }


}
