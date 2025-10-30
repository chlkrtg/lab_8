package ex1;

import java.util.function.Predicate;

public class CheckString {
    public static void main(String[] args) {
        //a. Создать лямбда выражение, которое возвращает значение true,если строка не null, используя функциональный интерфейс Predicate.
        Predicate<String> notNull = s -> s != null;

        //b. Создать лямбда выражение, которое проверяет, что строка не пуста, используя функциональный интерфейс Predicate.
        Predicate<String> notEmpty = s -> !s.isEmpty();

        //c. Написать программу проверяющую, что строка не null и не пуста, используя метод and() функционального интерфейса Predicate
        Predicate<String> notNullAndNotEmpty = notNull.and(notEmpty);

        System.out.println(notNullAndNotEmpty.test("Hi!")); //true
        System.out.println(notNullAndNotEmpty.test("")); //false
        System.out.println(notNullAndNotEmpty.test(null)); //false
    }
}
