package ru.otus.lesson.config;

import javax.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.ConsumerEndpointSpec;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.jpa.dsl.Jpa;
import org.springframework.integration.jpa.support.PersistMode;
import ru.otus.lesson.domain.Task;

@Configuration
@EnableIntegration
@RequiredArgsConstructor
public class IntegrationTask {

    private final EntityManagerFactory entityManagerFactory;

    @Bean
    public QueueChannel taskChannel() {
        return MessageChannels.queue(10).get();
    }

    @Bean
    public IntegrationFlow outboundAdapterFlow() {
        return IntegrationFlows.from(taskChannel())
            .handle(Jpa.outboundAdapter(this.entityManagerFactory)
                    .entityClass(Task.class)
                    .persistMode(PersistMode.PERSIST),
                ConsumerEndpointSpec::transactional)
            .get();
    }
}
