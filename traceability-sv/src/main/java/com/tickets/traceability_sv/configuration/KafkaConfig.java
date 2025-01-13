package com.tickets.traceability_sv.configuration;

import com.tickets.traceability_sv.event.RequirementTraceabilityEvent;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConfig {

    @Value("${KAFKA_URL}")
    private String kafkaURL;

    @Bean
    public ConsumerFactory<String, RequirementTraceabilityEvent> consumerFactory() {
        JsonDeserializer<RequirementTraceabilityEvent> deserializer = new JsonDeserializer<>(RequirementTraceabilityEvent.class);
        deserializer.addTrustedPackages("*"); // Confiar en todos los paquetes

        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaURL); // Dirección de Kafka
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "traceability-sv");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), deserializer);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, RequirementTraceabilityEvent> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, RequirementTraceabilityEvent> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }
}
