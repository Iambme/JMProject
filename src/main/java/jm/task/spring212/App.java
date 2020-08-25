package jm.task.spring212;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        /*
1. Прочитайте про виды бинов.
2. Создайте POJO-класс Cat.
3. В классе AppConfig, по аналогии, создайте бин с именем “cat”.
4. Настройте этот бин так, чтобы он создавался новым при каждом запросе.
5. В классе App, по аналогии, вызовите еще раз бин HelloWorld, затем 2 раза вызовите бин cat. Сравните 2 пары бинов по ссылке и выведите результат в консоль.
 Для пары HelloWorld должно вернуться true, для cat - false. Так вы можете увидеть результат того, как работает наш контейнер.
6. Раскоментируйте тестовый класс и проверьте своё решение.
         */
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        HelloWorld bean = (HelloWorld) applicationContext.getBean("helloworld");
        HelloWorld bean1 = (HelloWorld) applicationContext.getBean("helloworld");
        Cat cat = (Cat) applicationContext.getBean("cat");
        Cat cat1 = (Cat) applicationContext.getBean("cat");
        System.out.println(bean.getMessage());
        System.out.println(bean==bean1);
        System.out.println(cat==cat1);
    }
}