package ex7;

import java.util.*;
import java.util.stream.Collectors;

class Phone {
    private String number;
    private PhoneType type;

    public Phone(String number, PhoneType type) {
        this.number = number;
        this.type = type;
    }

    public PhoneType getType() {
        return type;
    }

    public String getNumber() {
        return number;
    }
}

enum PhoneType {
    MOBILE, LANDLINE
}

class Client {
    private int id;
    private String name;
    private int age;
    private List<Phone> phones;

    public Client(int id, String name, int age, List<Phone> phones) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.phones = phones;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public List<Phone> getPhones() {
        return phones;
    }
}

public class ClientProcessor {
    public static void main(String[] args) {
        List<Client> clients = Arrays.asList(
                new Client(1, "Петя", 25, Arrays.asList(
                        new Phone("79609990010", PhoneType.MOBILE),
                        new Phone("79609990012", PhoneType.LANDLINE)
                )),
                new Client(2, "Даша", 22, Arrays.asList(
                        new Phone("79609991010", PhoneType.MOBILE)
                )),
                new Client(3, "Филипп", 30, Arrays.asList(
                        new Phone("75602991010", PhoneType.LANDLINE)
                ))
        );

        Optional<Client> youngestClient = clients.stream()
                .filter(client -> client.getPhones().stream()
                        .anyMatch(phone -> phone.getType() == PhoneType.MOBILE))
                .min(Comparator.comparingInt(Client::getAge));

        youngestClient.ifPresent(client ->
                System.out.println("Самый молодой клиент: " + client.getName() + ", возраст: " + client.getAge()));
    }
}