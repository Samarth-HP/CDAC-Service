package com.samagra.cdac;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.Response;

@SpringBootApplication
@RestController
public class CdacApplication {

    @Value("username") String username;
    @Value("senderId") String senderId;
    @Value("password") String password;
    @Value("secureKey") String secureKey;

    @Autowired
    SMSServices smsServices;

    @GetMapping("/")
    ResponseEntity<String> test() {
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @GetMapping("/send_unicode_sms")
    ResponseEntity<String> sendUnicodeSms(@RequestParam String message, @RequestParam String mobileNumber, @RequestParam String templateid) {
        try {
            String resp = smsServices.sendUnicodeSMS(username, password, message, senderId, mobileNumber, secureKey, templateid);
            return new ResponseEntity<>(resp, HttpStatus.OK);
        }catch (Exception e){
            System.out.println("Exception: " + e);
            return new ResponseEntity<>("ERROR", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/send_bulk_sms")
    ResponseEntity<String> sendBulkSMS(@RequestParam String message, @RequestParam String mobileNumber, @RequestParam String templateid){
        try {
            String resp = smsServices.sendBulkSMS(username, password, message, senderId, mobileNumber, secureKey, templateid);
            return new ResponseEntity<>(resp, HttpStatus.OK);
        }catch (Exception e){
            System.out.println("Exception: " + e);
            return new ResponseEntity<>("ERROR", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/send_single_sms")
    ResponseEntity<String> sendSingleSms(@RequestParam String message, @RequestParam String mobileNumber, @RequestParam String templateid){
        try {
            String resp = smsServices.sendSingleSMS(username, password, message, senderId, mobileNumber, secureKey, templateid);
            return new ResponseEntity<>(resp, HttpStatus.OK);
        }catch (Exception e){
            System.out.println("Exception: " + e);
            return new ResponseEntity<>("ERROR", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/send_otp_sms")
    ResponseEntity<String> sendOtpSms(@RequestParam String message, @RequestParam String mobileNumber, @RequestParam String templateid) {
        try {
            String resp = smsServices.sendOtpSMS(username, password, message, senderId, mobileNumber, secureKey, templateid);
            return new ResponseEntity<>(resp, HttpStatus.OK);
        }catch (Exception e){
            System.out.println("Exception: " + e);
            return new ResponseEntity<>("ERROR", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/send_single_unicode_sms")
    ResponseEntity<String> sendUnicodeOtpSMS(@RequestParam String message, @RequestParam String mobileNumber, @RequestParam String templateid) {
        try {
            String resp = smsServices.sendUnicodeOtpSMS(username, password, message, senderId, mobileNumber, secureKey, templateid);
            return new ResponseEntity<>(resp, HttpStatus.OK);
        }catch (Exception e){
            System.out.println("Exception: " + e);
            return new ResponseEntity<>("ERROR", HttpStatus.NOT_FOUND);
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(CdacApplication.class, args);
    }

}
