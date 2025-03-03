package com.example.demo;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BackendThreadService {

    @Autowired
    private SendMessageRepository sendMessageRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Scheduled(fixedRate = 1000)
    public void processSmsRequests() {
        List<SendMessage> newRequests = sendMessageRepository.findByStatus("NEW");

        for (SendMessage request : newRequests) {
       
            request.setStatus("INPROCESS");
            sendMessageRepository.save(request);

            // Simulate calling the Telecom API
            String url = "http://localhost:<port>/telco/smsc?account_id=" + request.getAccountId() +
                         "&mobile=" + request.getMobile() +
                         "&message=" + request.getMessage();
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, null, String.class);

            // Update status and response
            request.setStatus("SENT");
            request.setTelcoResponse(response.getBody());
            request.setSentTs(new Timestamp(System.currentTimeMillis()));
            sendMessageRepository.save(request);
        }
    }
}
