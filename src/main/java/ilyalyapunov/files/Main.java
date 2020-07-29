package ilyalyapunov.files;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.FileNotFoundException;

// Задание:
//
// Product: id, Наименование, Код, Производитель, Цена, Количество на складе.
//        Создать массив объектов. Вывести:
//
//        список всех записей;
//        список товаров заданного производителя;
//        список товаров заданного производителя, цена которых не превосходит заданную;
//        список товаров, которых нет на складе;
//        список всех записей, отсортированный по производителю

public class Main {
    public static void main(String[] args) throws FileNotFoundException{
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        Reader reader = context.getBean("reader", Reader.class);

        String filename = "input.txt"; //Правильный файл - input.txt
        reader.read(filename);

        reader.getAllRecords();
        reader.getMissingProducts();
        reader.getRecordsByPublisher("Apple");
        reader.getRecordsByPublisherAndPrice("Samsung", 50000);
        reader.getSortedByPublisher();
    }
}
