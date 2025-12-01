package com.vishal.bfh_qualifier_java.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.vishal.bfh_qualifier_java.dto.FinalQueryRequest;
import com.vishal.bfh_qualifier_java.dto.GenerateWebhookRequest;
import com.vishal.bfh_qualifier_java.dto.GenerateWebhookResponse;

@Service
public class WebhookService {

    @Autowired
    private RestTemplate restTemplate;

    private static final String GENERATE_WEBHOOK_URL =
            "https://bfhldevapigw.healthrx.co.in/hiring/generateWebhook/JAVA";

    public void executeFlow() {

        GenerateWebhookRequest requestBody =
        new GenerateWebhookRequest("Vedavyas Vishal Kodandapani", "22BCE2287", "vedavyaskv05@gmail.com");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<GenerateWebhookRequest> httpEntity =
                new HttpEntity<>(requestBody, headers);

        ResponseEntity<GenerateWebhookResponse> response =
                restTemplate.postForEntity(GENERATE_WEBHOOK_URL, httpEntity, GenerateWebhookResponse.class);

        GenerateWebhookResponse generateResponse = response.getBody();

        System.out.println("WEBHOOK: " + generateResponse.getWebhook());
        System.out.println("Token: " + generateResponse.getAccessToken());

        String finalQuery = "SELECT d.department_name, p.amount AS salary, CONCAT(e.first_name, ' ', e.last_name) AS employee_name, FLOOR(DATEDIFF(CURRENT_DATE, e.dob) / 365) AS age FROM payments p JOIN employee e ON p.emp_id = e.emp_id JOIN department d ON e.department = d.department_id WHERE EXTRACT(DAY FROM p.payment_time) != 1 AND p.amount = (SELECT MAX(p2.amount) FROM payments p2 JOIN employee e2 ON p2.emp_id = e2.emp_id WHERE e2.department = e.department AND EXTRACT(DAY FROM p2.payment_time) != 1);";

        sendFinalQuery(generateResponse.getWebhook(), generateResponse.getAccessToken(), finalQuery);
    }

    private void sendFinalQuery(String webhookUrl, String accessToken, String finalQuery) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", accessToken);

        FinalQueryRequest body = new FinalQueryRequest(finalQuery);
        HttpEntity<FinalQueryRequest> entity = new HttpEntity<>(body, headers);

        ResponseEntity<String> response =
                restTemplate.postForEntity(webhookUrl, entity, String.class);

        System.out.println("Final Response: " + response.getBody());
    }
}
