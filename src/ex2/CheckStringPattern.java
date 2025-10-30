package ex2;

import java.util.function.Predicate;

public class CheckStringPattern {
    public static void main(String[] args) {
        Predicate<String> startsWithJorN = s -> s.startsWith("J") || s.startsWith("N");
        Predicate<String> endsWithA = s -> s.endsWith("A");
        Predicate<String> combinedCheck = startsWithJorN.and(endsWithA);

        System.out.println(combinedCheck.test("JohA"));
        System.out.println(combinedCheck.test("NohA"));
        System.out.println(combinedCheck.test("Johna"));
        System.out.println(combinedCheck.test("Nohna"));
    }
}
