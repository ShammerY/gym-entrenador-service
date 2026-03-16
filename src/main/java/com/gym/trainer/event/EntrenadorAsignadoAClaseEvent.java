package com.gym.trainer.event;

import java.time.Instant;

public record  EntrenadorAsignadoAClaseEvent(
        Long claseId,
        Long entrenadorId,
        Instant occurredAt
) {}
