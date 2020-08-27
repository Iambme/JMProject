package jm.task.spring213.app.config;

import jm.task.spring213.app.model.Dog;

import jm.task.spring213.app.model.Timer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;


@Configuration
@ComponentScan(basePackages = "jm.task.spring213.app")
public class AppConfig {

    @Bean(name="dog")
    public Dog getDog() {
        Dog dog = new Dog();
        dog.setName("Sobaken");
        return dog;
    }
    @Bean(name = "timer")
    public Timer getTimer() {
        return new Timer();
    }
}
