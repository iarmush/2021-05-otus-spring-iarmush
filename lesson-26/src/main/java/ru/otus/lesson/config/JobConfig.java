package ru.otus.lesson.config;

import java.util.HashMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.MongoItemReader;
import org.springframework.batch.item.data.builder.MongoItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.data.mongodb.core.MongoOperations;
import ru.otus.lesson.domain.mongo.Book;
import ru.otus.lesson.service.BookService;
import ru.otus.lesson.service.BookTransformer;

@Configuration
@Slf4j
public class JobConfig {

    private static final int CHUNK_SIZE = 5;
    public static final String MIGRATE_BOOKS_JOB = "migrateBooksJob";

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @StepScope
    @Bean
    public MongoItemReader<Book> mongoBookReader(MongoOperations mongoOperations) {
        return new MongoItemReaderBuilder<Book>()
            .name("mongoBookReader")
            .template(mongoOperations)
            .jsonQuery("{}")
            .targetType(Book.class)
            .sorts(new HashMap<>())
            .build();
    }

    @StepScope
    @Bean
    public ItemProcessor<Book, ru.otus.lesson.domain.jpa.Book> bookTransform() {
        return BookTransformer::transform;
    }

    @StepScope
    @Bean
    public ItemWriter<ru.otus.lesson.domain.jpa.Book> itemWriter(BookService bookService) {
        return books -> books.forEach(bookService::saveBook);
    }

    @Bean
    public Job migrateBookJob(Step transformBookStep) {
        return jobBuilderFactory.get(MIGRATE_BOOKS_JOB)
            .incrementer(new RunIdIncrementer())
            .flow(transformBookStep)
            .end()
            .build();
    }

    @Bean
    public Step transformBookStep(@Qualifier("mongoBookReader") ItemReader<Book> reader, ItemWriter<ru.otus.lesson.domain.jpa.Book> writer,
        @Qualifier("bookTransform") ItemProcessor<Book, ru.otus.lesson.domain.jpa.Book> itemProcessor) {
        return stepBuilderFactory.get("transformBookStep")
            .<Book, ru.otus.lesson.domain.jpa.Book>chunk(CHUNK_SIZE)
            .reader(reader)
            .processor(itemProcessor)
            .writer(writer)
            .taskExecutor(new SimpleAsyncTaskExecutor())
            .build();
    }
}
