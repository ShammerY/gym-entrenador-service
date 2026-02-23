package com.gym.trainer.config;

import com.gym.trainer.model.Entrenador;
import com.gym.trainer.repository.EntrenadorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final EntrenadorRepository entrenadorRepository;

    public DataLoader(EntrenadorRepository entrenadorRepository) {
        this.entrenadorRepository = entrenadorRepository;
    }

    @Override
    public void run(String... args) {

        if (entrenadorRepository.count() == 0) {

            System.out.println("Cargando entrenadores iniciales...");

            entrenadorRepository.save(
                    new Entrenador("Carlos Rodríguez", "Crossfit")
            );

            entrenadorRepository.save(
                    new Entrenador("Mariana López", "Yoga")
            );

            entrenadorRepository.save(
                    new Entrenador("Andrés Martínez", "Musculación")
            );

            entrenadorRepository.save(
                    new Entrenador("Laura Gómez", "Pilates")
            );

            entrenadorRepository.save(
                    new Entrenador("Sebastián Torres", "Entrenamiento Funcional")
            );

            System.out.println("Entrenadores cargados correctamente.");
        }
    }
}