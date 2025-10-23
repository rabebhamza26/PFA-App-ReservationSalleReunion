package org.example.appreservation.Repositories;

import org.example.appreservation.Entités.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface NotificationRepository extends JpaRepository<Notification, Long> {

}
