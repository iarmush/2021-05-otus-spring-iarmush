package ru.otus.lesson;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.JobRepositoryTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.lesson.config.JobConfig;
import ru.otus.lesson.service.BookService;

@SpringBootTest
@SpringBatchTest
public class ImportLibraryJobTest {

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    @Autowired
    private JobRepositoryTestUtils jobRepositoryTestUtils;

    @Autowired
    private BookService bookService;

    @BeforeEach
    public void setUp() {
        jobRepositoryTestUtils.removeJobExecutions();
    }

    @Test
    public void testJob() throws Exception {
        var job = jobLauncherTestUtils.getJob();

        assertThat(job).isNotNull()
            .extracting(Job::getName)
            .isEqualTo(JobConfig.MIGRATE_BOOKS_JOB);

        var jobExecution = jobLauncherTestUtils.launchJob();

        assertThat(jobExecution.getExitStatus()).isEqualByComparingTo(ExitStatus.COMPLETED);
        assertThat(bookService.getAll()).hasSize(3);
    }
}
