package com.gym.trainer.controller;

import com.gym.trainer.model.Entrenador;
import com.gym.trainer.service.EntrenadorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/entrenadores")
@Tag(name = "Entrenadores", description = "API para gestión de entrenadores")
@SecurityRequirement(name = "bearer-jwt")
public class EntrenadorController {

    private final EntrenadorService entrenadorService;

    public EntrenadorController(EntrenadorService entrenadorService) {
        this.entrenadorService = entrenadorService;
    }

    @Operation(summary = "Crear entrenador", description = "Crea un nuevo entrenador")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Entrenador creado"),
        @ApiResponse(responseCode = "400", description = "Solicitud inválida"),
        @ApiResponse(responseCode = "401", description = "No autenticado"),
        @ApiResponse(responseCode = "403", description = "No autorizado")
    })
    @PostMapping
    public Entrenador crear(@RequestBody Entrenador entrenador) {
        return entrenadorService.crearEntrenador(entrenador);
    }

    @Operation(summary = "Listar entrenadores", description = "Retorna todos los entrenadores")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Listado obtenido"),
        @ApiResponse(responseCode = "401", description = "No autenticado"),
        @ApiResponse(responseCode = "403", description = "No autorizado")
    })
    @GetMapping
    public List<Entrenador> listar() {
        return entrenadorService.obtenerTodos();
    }

    @Operation(summary = "Obtener entrenador por ID", description = "Retorna un entrenador según su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Entrenador encontrado"),
        @ApiResponse(responseCode = "404", description = "Entrenador no encontrado"),
        @ApiResponse(responseCode = "401", description = "No autenticado"),
        @ApiResponse(responseCode = "403", description = "No autorizado")
    })
    @GetMapping("/{id}")
    public Entrenador obtener(@Parameter(description = "ID del entrenador", required = true) @PathVariable Long id) {
        return entrenadorService.obtenerPorId(id);
    }

    @Operation(summary = "Actualizar entrenador", description = "Actualiza un entrenador existente por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Entrenador actualizado"),
        @ApiResponse(responseCode = "404", description = "Entrenador no encontrado"),
        @ApiResponse(responseCode = "400", description = "Solicitud inválida"),
        @ApiResponse(responseCode = "401", description = "No autenticado"),
        @ApiResponse(responseCode = "403", description = "No autorizado")
    })
    @PutMapping("/{id}")
    public Entrenador actualizar(
            @Parameter(description = "ID del entrenador", required = true) @PathVariable Long id,
            @RequestBody Entrenador entrenador
    ) {
        return entrenadorService.actualizarEntrenador(id, entrenador);
    }

    @Operation(summary = "Eliminar entrenador", description = "Elimina un entrenador por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Entrenador eliminado"),
        @ApiResponse(responseCode = "404", description = "Entrenador no encontrado"),
        @ApiResponse(responseCode = "401", description = "No autenticado"),
        @ApiResponse(responseCode = "403", description = "No autorizado")
    })
    @DeleteMapping("/{id}")
    public void eliminar(@Parameter(description = "ID del entrenador", required = true) @PathVariable Long id) {
        entrenadorService.eliminarEntrenador(id);
    }
}