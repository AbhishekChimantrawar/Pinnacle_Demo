package com.example.demo;




import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SendMessageRepository extends JpaRepository<SendMessage, Long> {

    // Custom query to find SendMessages by status
    List<SendMessage> findByStatus(String status);
}
