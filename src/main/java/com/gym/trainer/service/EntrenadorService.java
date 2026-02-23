package com.gym.trainer.service;

import com.gym.trainer.model.Entrenador;
import com.gym.trainer.repository.EntrenadorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntrenadorService {

    private final EntrenadorRepository entrenadorRepository;

    public EntrenadorService(EntrenadorRepository entrenadorRepository) {
        this.entrenadorRepository = entrenadorRepository;
    }

    public Entrenador crearEntrenador(Entrenador entrenador) {
        return entrenadorRepository.save(entrenador);
    }

    public List<Entrenador> obtenerTodos() {
        return entrenadorRepository.findAll();
    }

    public Entrenador obtenerPorId(Long id) {
        return entrenadorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Entrenador no encontrado"));
    }

    public Entrenador actualizarEntrenador(Long id, Entrenador datosActualizados) {
        Entrenador entrenador = obtenerPorId(id);

        entrenador.setNombre(datosActualizados.getNombre());
        entrenador.setEspecialidad(datosActualizados.getEspecialidad());

        return entrenadorRepository.save(entrenador);
    }

    public void eliminarEntrenador(Long id) {
        entrenadorRepository.deleteById(id);
    }
}