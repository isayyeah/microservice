package com.example.voiture;

import com.example.voiture.entity.Client;
import com.example.voiture.entity.Voiture;
import com.example.voiture.repository.VoitureRepo;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@SpringBootApplication
public class VoitureApplication {

    private final VoitureRepo voitureRepository;
    private final ClientService clientService;

    public VoitureApplication(VoitureRepo voitureRepository, ClientService clientService) {
        this.voitureRepository = voitureRepository;
        this.clientService = clientService;
    }

    public static void main(String[] args) {
        SpringApplication.run(VoitureApplication.class, args);
    }

    @FeignClient(name = "SERVICE-CLIENT")
    interface ClientService {
        @GetMapping(path = "/clients/{id}")
        Client clientById(@PathVariable Long id);
    }

    @Transactional
    @Bean
    CommandLineRunner initializeDatabase(VoitureRepo voitureRepository, ClientService clientService) {
        return args -> {
            Client c1 = clientService.clientById(2L);
            Client c2 = clientService.clientById(1L);
            System.out.println("Id est :" + c1.getId());
            System.out.println("Nom est :" + c1.getNom());
            voitureRepository.save(new Voiture(null, "Mercedes", "A 55 67348", "e65", 12L));
            voitureRepository.save(new Voiture(null, "audi", "A 55 27834", "Q5", 2L));
            voitureRepository.save(new Voiture(null, "ford", "A 55 7856", "focus", 2.5L));
        };
    }
}
