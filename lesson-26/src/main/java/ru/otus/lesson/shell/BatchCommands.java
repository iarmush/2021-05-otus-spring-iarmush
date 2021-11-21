package ru.otus.lesson.shell;

import static ru.otus.lesson.config.JobConfig.MIGRATE_BOOKS_JOB;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobInstance;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
@RequiredArgsConstructor
public class BatchCommands {

    private final Job bookMigrationJob;
    private final JobLauncher jobLauncher;
    private final JobOperator jobOperator;
    private final JobExplorer jobExplorer;

    @ShellMethod(value = "startMigrationJobWithJobLauncher", key = "launch")
    public void startMigrationJobWithJobLauncher() throws Exception {
        JobExecution execution = jobLauncher.run(bookMigrationJob, new JobParametersBuilder()
            .toJobParameters());
        System.out.println(execution);
    }

    @ShellMethod(value = "startMigrationJobWithJobOperator", key = "operate")
    public void startMigrationJobWithJobOperator() throws Exception {
        Long executionId = jobOperator.start(MIGRATE_BOOKS_JOB, String.valueOf(System.currentTimeMillis()));
        System.out.println(jobOperator.getSummary(executionId));
    }

    @ShellMethod(value = "restartMigrationJobWithJobOperator", key = "restart")
    public void restartMigrationJobWithJobOperator() throws Exception {
        JobInstance instance = jobExplorer.getLastJobInstance(MIGRATE_BOOKS_JOB);
        if (instance != null) {
            Long executionId = jobOperator.restart(instance.getInstanceId());
            System.out.println(jobOperator.getSummary(executionId));
        }
    }

    @ShellMethod(value = "showJobStatus", key = "status")
    public void showJobStatus() {
        JobInstance lastJobInstance = jobExplorer.getLastJobInstance(MIGRATE_BOOKS_JOB);
        if (lastJobInstance != null) {
            List<JobExecution> jobExecutions = jobExplorer.getJobExecutions(lastJobInstance);
            jobExecutions.forEach(jobExecution -> System.out.println(jobExecution.getStatus()));
        } else {
            System.out.println("Jobs didn't execute yet");
        }
    }
}
