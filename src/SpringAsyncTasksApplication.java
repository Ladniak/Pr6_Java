import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@SpringBootApplication
@EnableScheduling
public class SpringAsyncTasksApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringAsyncTasksApplication.class, args);
    }

    // Щоденне виконання завдання о 10:00
    @Scheduled(cron = "0 0 10 * * ?")
    public void dailyTask() {
        System.out.println("Щоденне завдання виконано: " + java.time.LocalDateTime.now());
    }

    // Запуск задачі кожні 3 секунди з початковою затримкою 2 секунди
    @Scheduled(initialDelay = 2000, fixedRate = 3000)
    public void periodicTask() {
        System.out.println("Періодичне завдання виконується: " + java.time.LocalDateTime.now());
    }

    // Налаштування планувальника для асинхронних задач
    @Bean
    public ThreadPoolTaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(10);
        scheduler.setThreadNamePrefix("TaskScheduler-");
        return scheduler;
    }
}