package com.samagra.cdac;

import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import com.google.gson.Gson;
import java.util.HashMap;

@SpringBootApplication
@RestController
public class CdacApplication {

    @Value("${username}") String username;
    @Value("${senderId}") String senderId;
    @Value("${password}") String password;
    @Value("${secureKey}") String secureKey;

    @Autowired
    SMSService smsService;

    @GetMapping("/")
    ResponseEntity<String> test() {
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @GetMapping("/send_unicode_sms")
    ResponseEntity<String> sendUnicodeSms(@RequestParam String message, @RequestParam String mobileNumber, @RequestParam String templateid) {
        try {
            String resp = smsService.sendUnicodeSMS(username, password, message, senderId, mobileNumber, secureKey, templateid);
            return new ResponseEntity<>(resp, HttpStatus.OK);
        }catch (Exception e){
            System.out.println("Exception: " + e);
            return new ResponseEntity<>("ERROR", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/send_bulk_sms")
    ResponseEntity<String> sendBulkSMS(@RequestParam String message, @RequestParam String mobileNumber, @RequestParam String templateid){
        try {
            String resp = smsService.sendBulkSMS(username, password, message, senderId, mobileNumber, secureKey, templateid);
            return new ResponseEntity<>(resp, HttpStatus.OK);
        }catch (Exception e){
            System.out.println("Exception: " + e);
            return new ResponseEntity<>("ERROR", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/send_single_sms")
    ResponseEntity<String> sendSingleSms(@RequestParam String message, @RequestParam String mobileNumber, @RequestParam String templateid){
        try {
            String resp = smsService.sendSingleSMS(username, password, message, senderId, mobileNumber, secureKey, templateid);
            return new ResponseEntity<>(resp, HttpStatus.OK);
        }catch (Exception e){
            System.out.println("Exception: " + e);
            return new ResponseEntity<>("ERROR", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/send_otp_sms")
    ResponseEntity<String> sendOtpSms(@RequestParam String message, @RequestParam String mobileNumber, @RequestParam String templateid) {
        try {
            String resp = smsService.sendOtpSMS(username, password, message, senderId, mobileNumber, secureKey, templateid);
            return new ResponseEntity<>(resp, HttpStatus.OK);
        }catch (Exception e){
            System.out.println("Exception: " + e);
            return new ResponseEntity<>("ERROR", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/send_single_unicode_sms")
    ResponseEntity<String> sendUnicodeOtpSMS(@RequestParam String message, @RequestParam String mobileNumber, @RequestParam String templateid) {
        try {
            String resp = smsService.sendUnicodeOtpSMS(username, password, message, senderId, mobileNumber, secureKey, templateid);
            return new ResponseEntity<>(resp, HttpStatus.OK);
        }catch (Exception e){
            System.out.println("Exception: " + e);
            return new ResponseEntity<>("ERROR", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/send_from_csv")
    ResponseEntity<String> sendFromCsv(@RequestParam String message, @RequestParam("file") MultipartFile file, @RequestParam String templateid) {
        try {
            if (file.isEmpty()) {
                return new ResponseEntity<>("ERROR UPLOADING CSV", HttpStatus.BAD_REQUEST);
            } else {
                HashMap<String, String> respMap = new HashMap<>();
                Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();
                try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        String resp = smsService.sendUnicodeOtpSMS(username, password, message, senderId, line, secureKey, templateid);
                        respMap.put(line, resp);
                    }
                } catch (Exception ex) {
                    return new ResponseEntity<>("ERROR PROCESSING CSV", HttpStatus.BAD_REQUEST);
                }
//                String resp = smsService.sendUnicodeOtpSMS(username, password, message, senderId, mobileNumber, secureKey, templateid);
                return new ResponseEntity<>(gson.toJson(respMap), HttpStatus.OK);
            }
        }catch (Exception e){
            System.out.println("Exception: " + e);
            return new ResponseEntity<>("ERROR", HttpStatus.NOT_FOUND);
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(CdacApplication.class, args);
    }

}
