package com.gym.trainer.messaging.listener;

import com.gym.trainer.config.RabbitMQConfig;
import com.gym.trainer.messaging.event.HorarioClaseCambiadoEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class HorarioClaseListener {

    private static final Logger log = LoggerFactory.getLogger(HorarioClaseListener.class);

    @RabbitListener(queues = RabbitMQConfig.QUEUE_ENTRENADORES_HORARIO)
    public void onHorarioClaseCambiado(HorarioClaseCambiadoEvent event) {
        log.info(
                "[Entrenadores] Horario clase cambiado: claseId={}, anterior={}, nuevo={}, occurredAt={}",
                event.getClaseId(),
                event.getHorarioAnterior(),
                event.getHorarioNuevo(),
                event.getOccurredAt()
        );
    }
}
