package edu.bu.minigeni.controller;

import edu.bu.minigeni.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ImageController {
    @Autowired
    ImageService imageService;

    @GetMapping("/hello")
    @ResponseBody
    public String index() {
        return "Greetings from ImageController!";
    }

    @PostMapping("/upload")
    @ResponseBody
    public String handleFileUpload( @RequestParam("file") MultipartFile mpFile ) {

//        String fileName = mpFile.getOriginalFilename();
//        System.out.print(fileName);
        return imageService.recognize(mpFile);
//        return "Successful uploaded";

    }
}
