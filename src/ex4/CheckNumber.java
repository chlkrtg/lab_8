package ex4;

import java.util.function.Function;

public class CheckNumber {
    public static void main(String[] args) {
        Function<Integer, String> checkNumber = num -> {
            return (num > 0 ? "Положительное число" : (num < 0 ? "Отрицательное число" : "Ноль"));
        };

        System.out.println(checkNumber.apply(5)); //Положительное число
        System.out.println(checkNumber.apply(-5)); //Отрицательное число
        System.out.println(checkNumber.apply(0)); //Ноль
    }
}
