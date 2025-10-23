package org.example.appreservation.controller;

import org.example.appreservation.Entités.LoginRequest;
import org.example.appreservation.Entités.Utilisateur;
import org.example.appreservation.Repositories.UtilisateurRepository;
import org.example.appreservation.Services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/utilisateurs")
public class UtilisateurController {

    @Autowired
    private UtilisateurService utilisateurService;
    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Utilisateur utilisateur) {
        // Rechercher l'utilisateur par email
        Optional<Utilisateur> existingUser = utilisateurRepository.findByEmail(utilisateur.getEmail());

        // Vérifie si l'utilisateur est présent
        if (!existingUser.isPresent()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseMessage("Utilisateur non trouvé"));
        }

        Utilisateur user = existingUser.get(); // Récupère l'utilisateur de l'Optional

        // Vérifie si le mot de passe correspond
        if (!user.getPassword().equals(utilisateur.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseMessage("Mot de passe incorrect"));
        }

        return ResponseEntity.ok(new ResponseMessage("Connexion réussie"));
    }

    public class ResponseMessage {
        private String message;

        public ResponseMessage(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }








    // Récupérer tous les utilisateurs
    @GetMapping("/getAll")
    public List<Utilisateur> getAllUtilisateurs() {
        return utilisateurService.getAllUtilisateurs();
    }

    // Récupérer un utilisateur par son ID
    @GetMapping("get/{id}")
    public Utilisateur getUtilisateurById(@PathVariable Long id) {
        return utilisateurService.getUtilisateurById(id);
    }

    // Créer un nouvel utilisateur
    @PostMapping("post/creat")
    public Utilisateur createUtilisateur(@RequestBody Utilisateur utilisateur) {
        return utilisateurService.saveUtilisateur(utilisateur);
    }

    // Mettre à jour un utilisateur
    @PutMapping("update/{id}")
    public Utilisateur updateUtilisateur(@PathVariable Long id, @RequestBody Utilisateur utilisateurDetails) {
        return utilisateurService.updateUtilisateur(id, utilisateurDetails);
    }

    // Supprimer un utilisateur
    @DeleteMapping("delete/{id}")
    public void deleteUtilisateur(@PathVariable Long id) {
        utilisateurService.deleteUtilisateur(id);
    }


}
