package org.example.appreservation.Services;

import org.example.appreservation.Entités.Notification;
import org.example.appreservation.Entités.Reservation;
import org.example.appreservation.Entités.Utilisateur;
import org.example.appreservation.Repositories.NotificationRepository;
import org.example.appreservation.Repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {
    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

    public Notification getNotificationById(Long id) {
        return notificationRepository.findById(id).orElse(null);
    }

    public Notification createNotification(Notification notification) {
        // Assure que la date est bien initialisée si elle est null
        if (notification.getDateEnvoi() == null) {
            notification.setDateEnvoi(LocalDateTime.now());
        }
        return notificationRepository.save(notification);
    }

    public Notification updateNotification(Long id, Notification updatedNotification) {
        Optional<Notification> existing = notificationRepository.findById(id);
        if (existing.isPresent()) {
            Notification notif = existing.get();
            notif.setMessage(updatedNotification.getMessage());
            notif.setDateEnvoi(updatedNotification.getDateEnvoi());
            notif.setDestinataire(updatedNotification.getDestinataire());
            return notificationRepository.save(notif);
        }
        return null;
    }

    public void deleteNotification(Long id) {
        notificationRepository.deleteById(id);
    }

    public void envoyerNotification(Utilisateur destinataire, String message) {
        Notification notification = new Notification(message, destinataire);
        notification.setDateEnvoi(LocalDateTime.now());
        notificationRepository.save(notification);


    }
}