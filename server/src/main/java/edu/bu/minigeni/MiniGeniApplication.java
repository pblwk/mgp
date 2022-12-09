package edu.bu.minigeni;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static edu.bu.minigeni.utility.Utility.getPretrained;


/**
 * Entrance of the server program
 */
@SpringBootApplication
public class MiniGeniApplication {
    /**
     * the entrance of the program
     */
    public static void main(String[] args) {
        //preload the model
        getPretrained();
        System.out.println("Starting Spring...");

        //start the server
        SpringApplication.run(MiniGeniApplication.class, args);
    }
}


