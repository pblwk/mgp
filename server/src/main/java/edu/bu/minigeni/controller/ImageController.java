package edu.bu.minigeni.controller;

import edu.bu.minigeni.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * Controller that handles the requests of image recognition
 */
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

    /**
     * @param mpFile the image file
     * @return the result of recognition
     */
    @PutMapping("/upload")
    @ResponseBody
    public String handleFileUpload(@RequestParam("file") MultipartFile mpFile) {
        return imageService.recognize(mpFile);
    }
}
