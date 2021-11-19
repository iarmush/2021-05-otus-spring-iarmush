package ru.otus.lesson.events;

import org.bson.Document;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterDeleteEvent;
import org.springframework.stereotype.Component;
import ru.otus.lesson.repository.AuthorRepository;
import ru.otus.lesson.domain.Author;


@Component
public class MongoAuthorCascadeDeleteEventsListener extends AbstractMongoEventListener<Author> {

    private final AuthorRepository authorRepository;

    public MongoAuthorCascadeDeleteEventsListener(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public void onAfterDelete(AfterDeleteEvent<Author> event) {
        super.onAfterDelete(event);
        Document source = event.getSource();
        String id = source.get("_id").toString();
        authorRepository.deleteById(id);
    }
}
