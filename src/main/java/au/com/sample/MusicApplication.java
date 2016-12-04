package au.com.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class MusicApplication {
	
	public static void main(String[] args) {
        SpringApplication.run(MusicApplication.class, args);
    }

}
