package ru.otus.lesson.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.scheduling.PollerMetadata;
import ru.otus.lesson.service.EstimationService;

@Configuration
@EnableIntegration
public class IntegrationEstimation {

    @Bean
    public QueueChannel estimationChannel() {
        return MessageChannels.queue(10).get();
    }

    @Bean
    public PublishSubscribeChannel resultDataChannel() {
        return MessageChannels.publishSubscribe().get();
    }

    @Bean(name = PollerMetadata.DEFAULT_POLLER)
    public PollerMetadata poller() {
        return Pollers.fixedRate(100).maxMessagesPerPoll(5).get();
    }

    @Bean
    public IntegrationFlow estimationFlow(EstimationService estimationService) {
        return IntegrationFlows.from("estimationChannel")
            .split()
            .handle(estimationService, "processEstimation")
            .aggregate()
            .channel(resultDataChannel())
            .get();
    }
}
