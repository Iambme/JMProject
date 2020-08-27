package jm.task.spring213.app;

import jm.task.spring213.app.config.AppConfig;
import jm.task.spring213.app.model.AnimalsCage;
import jm.task.spring213.app.model.Dog;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {

    public static void main(String[] args) {
        /*
1) Создайте бин Dog, унаследуйте его от Animal. Создайте на основе этого класса компонент.
2) Запустите приложение и проверьте, что было выброшено исключение NoUniqueBeanDefinitionException.
Это произошло из-за того, что существует 2 бина с типом Animal.
3) Прочитайте о связывании бинов по имени и свяжите AnimalCage c бином Dog через интерфейс Animal.
4) На основе класса Timer создайте бин. Свяжите с AnimalCage.
 Проверьте, что при выполнении метода main время, которое пишет таймер, одно и тоже.
5) Раскоментировать тест из заготовки и проверить своё решение.
         */
        ApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(AppConfig.class);
        Dog dog = (Dog) applicationContext.getBean("dog");
        for (int i = 0; i < 5; i++) {
            AnimalsCage bean =
                    applicationContext.getBean(AnimalsCage.class);
            bean.whatAnimalSay();
        }

    }

}
