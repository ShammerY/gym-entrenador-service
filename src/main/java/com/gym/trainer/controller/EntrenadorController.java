package com.gym.trainer.controller;

import com.gym.trainer.model.Entrenador;
import com.gym.trainer.service.EntrenadorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/entrenadores")
public class EntrenadorController {

    private final EntrenadorService entrenadorService;

    public EntrenadorController(EntrenadorService entrenadorService) {
        this.entrenadorService = entrenadorService;
    }

    @PostMapping
    public Entrenador crear(@RequestBody Entrenador entrenador) {
        return entrenadorService.crearEntrenador(entrenador);
    }

    @GetMapping
    public List<Entrenador> listar() {
        return entrenadorService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public Entrenador obtener(@PathVariable Long id) {
        return entrenadorService.obtenerPorId(id);
    }

    @PutMapping("/{id}")
    public Entrenador actualizar(
            @PathVariable Long id,
            @RequestBody Entrenador entrenador
    ) {
        return entrenadorService.actualizarEntrenador(id, entrenador);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        entrenadorService.eliminarEntrenador(id);
    }
}