package es.ies.puerto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:cxf-service.xml")
public class XenoWikiApplication {

	public static void main(String[] args) {
		SpringApplication.run(XenoWikiApplication.class, args);
	}

}
