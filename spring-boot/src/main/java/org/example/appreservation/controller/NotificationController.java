package org.example.appreservation.controller;

import org.example.appreservation.Entités.Notification;
import org.example.appreservation.Repositories.NotificationRepository;
import org.example.appreservation.Services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")

public class NotificationController {
    @Autowired
    private NotificationService notificationService;

    // GET ALL
    @GetMapping
    public List<Notification> getAllNotifications() {
        return notificationService.getAllNotifications();
    }



    // GET BY ID
    @GetMapping("/{id}")
    public Notification getNotificationById(@PathVariable Long id) {
        return notificationService.getNotificationById(id);
    }


    // CREATE
    @PostMapping
    public Notification createNotification(@RequestBody Notification notification) {
        return notificationService.createNotification(notification);
    }

    // UPDATE
    @PutMapping("/{id}")
    public Notification updateNotification(@PathVariable Long id, @RequestBody Notification updatedNotification) {
        return notificationService.updateNotification(id, updatedNotification);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void deleteNotification(@PathVariable Long id) {
        notificationService.deleteNotification(id);
    }



}


