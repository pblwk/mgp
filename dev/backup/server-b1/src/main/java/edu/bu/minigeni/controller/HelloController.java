//package edu.bu.minigeni.controller;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//@Controller
//@CrossOrigin(origins = "*", allowedHeaders = "*")
//public class HelloController {
//    @GetMapping("/hello")
//    @ResponseBody
//    public String index() {
//        return "Greetings from Spring Boot!";
//    }
//
//    @PostMapping("/upload")
//    @ResponseBody
//    public String handleFileUpload( @RequestParam("file") MultipartFile file ) {
//
//        String fileName = file.getOriginalFilename();
//        System.out.print(fileName);
//
//
//
//
//        return "Successful uploaded";
//
//    }
//}