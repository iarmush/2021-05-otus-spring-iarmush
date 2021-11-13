package ru.otus.lesson.events;

import org.bson.Document;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterDeleteEvent;
import org.springframework.stereotype.Component;
import ru.otus.lesson.repository.GenreRepository;
import ru.otus.lesson.domain.Genre;


@Component
public class MongoGenreCascadeDeleteEventsListener extends AbstractMongoEventListener<Genre> {

    private final GenreRepository genreRepository;

    public MongoGenreCascadeDeleteEventsListener(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public void onAfterDelete(AfterDeleteEvent<Genre> event) {
        super.onAfterDelete(event);
        Document source = event.getSource();
        String id = source.get("_id").toString();
        genreRepository.removeById(id);
    }
}
