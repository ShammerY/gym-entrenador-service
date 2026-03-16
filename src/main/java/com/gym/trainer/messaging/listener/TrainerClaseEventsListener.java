package com.gym.trainer.messaging.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.gym.trainer.config.RabbitMQConfig;
import com.gym.trainer.event.EntrenadorAsignadoAClaseEvent;

@Service
public class TrainerClaseEventsListener {

    private static final Logger log = LoggerFactory.getLogger(TrainerClaseEventsListener.class);

    @RabbitListener(queues = RabbitMQConfig.QUEUE_TRAINER)
    public void onEntrenadorAsignadoAClase(EntrenadorAsignadoAClaseEvent event) {
        log.info("Evento recibido: entrenador {} asignado a clase {} (occurredAt={})",
                event.entrenadorId(), event.claseId(), event.occurredAt());
    }
}
