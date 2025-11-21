package ex6;

import java.util.*;
import java.util.stream.Collectors;
import java.util.function.Function;

public class StreamAPI {
    //a. метод, возвращающий среднее значение списка целых чисел
    public static OptionalDouble average(List<Integer> numbers) {
        return numbers.stream()
                .mapToInt(intValue -> intValue)
                .average();
    }

    //b. метод, приводящий все строки в списке в верхний регистр и добавляющий к ним префикс «new»
    public static List<String> toUpperCaseAndPrefix(List<String> strings) {
        return strings.stream()
                .map(s -> "new_" + s.toUpperCase())
                .collect(Collectors.toList());
    }

    //c. метод, возвращающий список квадратов всех встречающихся только один раз элементов списка
    public static List<Integer> squaresOfUnique(List<Integer> numbers) {
        Map<Integer, Long> counts = numbers.stream()
                .collect(Collectors.groupingBy(n -> n, Collectors.counting()));

        return counts.entrySet().stream()
                .filter(entry -> entry.getValue() == 1)
                .map(entry -> entry.getKey() * entry.getKey())
                .collect(Collectors.toList());
    }

    //d. метод, принимающий на вход коллекцию строк и возвращает все строки, начинающиеся с заданной буквы, отсортированные по алфавиту
    public static List<String> filterAndSort(Collection<String> strings, char startLetter) {
        return strings.stream()
                .filter(s -> s != null && !s.isEmpty() && s.charAt(0) == startLetter)
                .sorted()
                .collect(Collectors.toList());
    }

    //e. метод, принимающий на вход коллекцию и возвращающий ее последний элемент или кидающий исключение, если коллекция пуста
    public static <T> T getLastElement(Collection<T> collection) {
        if (collection.isEmpty()) {
            throw new NoSuchElementException("Коллекция пуста");
        }

        if (collection instanceof java.util.List) {
            java.util.List<T> list = (java.util.List<T>) collection;
            return list.get(list.size() - 1);
        } else {
            throw new UnsupportedOperationException("Этот метод поддерживает только List");
        }
    }

    //f. метод, принимающий на вход массив целых чисел, возвращающий сумму чётных чисел или 0, если чётных чисел нет
    public static int sumEvenNumbers(int[] numbers) {
        return Arrays.stream(numbers).filter(n -> n % 2 == 0).sum();
    }

    //g. метод, преобразовывающий все строки в списке в Map, где первый символ – ключ, оставшиеся – значение
    public static Map<Character, List<String>> stringsToMap(List<String> strings) {
        return strings.stream()
                .filter(s -> s != null && !s.isEmpty())
                .collect(Collectors.groupingBy(
                        s -> s.charAt(0),
                        Collectors.mapping(s -> s.substring(1), Collectors.toList())
                ));
    }

    public static void main(String[] args) {
        List<Integer> aNumbers = Arrays.asList(1, 2, 7, 8, 1, 5, 9, 6, 4, 1);
        int[] fNumbers = {1, 2, 7, 8, 1, 5, 9, 6, 4, 1};

        if (aNumbers.isEmpty()) {
            System.out.println("Список пуст; среднего нет.");
        } else {
            average(aNumbers).ifPresent(avg -> System.out.println("Среднее значение: " + avg));
            System.out.println(squaresOfUnique(aNumbers));
            System.out.println(sumEvenNumbers(fNumbers));
        }

        List<String> bStrings = Arrays.asList("hi", "my", "name", "is", "Java", "hello!");

        if (bStrings.isEmpty()) {
            System.out.println("Список пуст; нечего выводить");
        } else {
            System.out.println(toUpperCaseAndPrefix(bStrings));
            System.out.println(filterAndSort(bStrings, 'h'));
            System.out.println(getLastElement(bStrings));
            System.out.println(stringsToMap(bStrings));
        }
    }
}
