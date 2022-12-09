package edu.bu.minigeni;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static edu.bu.minigeni.utility.Utility2.getPretrained;

@SpringBootApplication
public class MiniGeniApplication {
	public static void main(String[] args) {
//		OpenCV.loadShared();
		getPretrained();
		System.out.println("Starting Spring...");
		SpringApplication.run(MiniGeniApplication.class, args);
	}
}
