package org.example.appreservation.Services;

import org.example.appreservation.Entités.Utilisateur;
import org.example.appreservation.Repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UtilisateurService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    // Récupérer tous les utilisateurs
    public List<Utilisateur> getAllUtilisateurs() {
        return utilisateurRepository.findAll();
    }

    // Récupérer un utilisateur par son ID
    public Utilisateur getUtilisateurById(Long id) {
        return utilisateurRepository.findById(id).orElse(null);
    }

    // Enregistrer un utilisateur
    public Utilisateur saveUtilisateur(Utilisateur utilisateur) {
        return utilisateurRepository.save(utilisateur);
    }

    // Modifier un utilisateur
    public Utilisateur updateUtilisateur(Long id, Utilisateur utilisateurDetails) {
        Utilisateur utilisateur = getUtilisateurById(id);
        if (utilisateur != null) {
            utilisateur.setNomUtilisateur(utilisateurDetails.getNomUtilisateur());
            utilisateur.setEmail(utilisateurDetails.getEmail());
            utilisateur.setRole(utilisateurDetails.getRole());
            utilisateur.setPassword(utilisateurDetails.getPassword());

            return utilisateurRepository.save(utilisateur);
        }
        return null;  // Ou vous pouvez lancer une exception si nécessaire
    }

    // Supprimer un utilisateur
    public void deleteUtilisateur(Long id) {
        utilisateurRepository.deleteById(id);
    }
    public String login(String email, String password) {
        Optional<Utilisateur> utilisateurOpt = utilisateurRepository.findByEmail(email);

        if (utilisateurOpt.isPresent()) {
            Utilisateur utilisateur = utilisateurOpt.get();
            if (utilisateur.getPassword().equals(password)) {
                return "Connecté avec succès!";
            } else {
                return "Mot de passe incorrect!";
            }
        } else {
            return "Utilisateur non trouvé!";
        }
    }
}
