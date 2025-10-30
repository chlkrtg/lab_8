package ex5;

import java.util.function.Supplier;
import java.util.Random;

public class NumberGenerator {
    public static void main(String[] args) {
        Supplier<Integer> randomSupplier = () -> new Random().nextInt(11);

        System.out.println(randomSupplier.get());
        System.out.println(randomSupplier.get());
        System.out.println(randomSupplier.get());
    }
}