package ilyalyapunov.files;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode

public class Product {
        private int id, code, price, quantity;  
        private String name, publisher;

        public String toString(){
            return String.format("|%2d| %15s| %6d| %8s| %6d| %6d|",
                                    this.id, this.name, this.code, this.publisher, this.price, this.quantity);
        }

        public static String DrawTableHeading(){
            return "|Id|        Название|    Код|    Фирма|   Цена| Кол-во|"
                    +"\n"+"+--+----------------+-------+---------+-------+-------+";}

        public static String DrawLine(){
            return "+--+----------------+-------+---------+-------+-------+"; }
}
