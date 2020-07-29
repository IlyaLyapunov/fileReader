package ilyalyapunov.files;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

@Component("reader")
public class Reader{        //Все методы на получение выборки возвращают кол-во записей

    private Product[] products;

    public Reader() {
        //Конструктор по умолчанию
        this.products = new Product[0];
    }

    public void read(String filename) throws FileNotFoundException {
        //Чтение файла, формирование массива объектов Product
        File file = new File(filename);
        Scanner in = new Scanner(file);
        int n = in.nextInt();
        products = new Product[n];
        for (int i = 0; i < n; i++) {
            int id = in.nextInt();
            in.nextLine();
            String name = in.nextLine();
            int code = in.nextInt();
            in.nextLine();
            String publisher = in.nextLine();
            int price = in.nextInt();
            int quantity = in.nextInt();
            products[i] = new Product(id, code, price, quantity, name, publisher);
        }
    }

    //Получить все записи из файла
    public int getAllRecords() {
        for (int i = 0; i < products.length; i++) {
            System.out.println(products[i].toString());
        }
        return products.length;
    }

    //Получить записи о товарах данного производителя
    public int getRecordsByPublisher(String publisher) {
        int count = 0;
        for (int i = 0; i < products.length; i++) {
            if (publisher.compareTo(products[i].getPublisher()) == 0) {
                System.out.println(products[i].toString());
                count++;
            }
        }
        return count;
    }

    //Получить записи о товарах данного производителя, цена которых не превосходит заданную
    public int getRecordsByPublisherAndPrice(String publisher, int price) {
        int count = 0;
        for (int i = 0; i < products.length; i++) {
            if ((publisher.compareTo(products[i].getPublisher()) == 0) && (price >= products[i].getPrice())) {
                System.out.println(products[i].toString());
                count++;
            }
        }
        return count;
    }

    //Получить записи об отсутствующих товарах
    public int getMissingProducts() {
        int count = 0;
        for (int i = 0; i < products.length; i++) {
            if (products[i].getQuantity() == 0) {
                System.out.println(products[i].toString());
                count++;
            }
        }
        return count;
    }

    //Получить записи, отсортированные по производителю
    public int getSortedByPublisher() {
        int i = 0;
        int count = 1;
        while (i < products.length - 1) {
            for (int j = i; j < products.length - 1; j++) {
                if ((products[i].getPublisher().compareTo(products[j].getPublisher()) == 0) && (i != j)) {
                    Product change = products[i + count];
                    products[i + count] = products[j];
                    products[j] = change;
                    count++;
                }
            }
            i += count;
            count = 1;
        }
        for (int j = 0; j < products.length; j++) {
            System.out.println(products[j].toString());
        }
        return products.length;
    }
}

