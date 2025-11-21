package ex3;

import java.util.function.Consumer;

class HeavyBox {
    private double weight;

    public HeavyBox(double weight) {
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }
}

public class Box {
    public static void main(String[] args) {
        Consumer<HeavyBox> shipment = box -> System.out.println("Отгрузили ящик с весом " + box.getWeight());
        Consumer<HeavyBox> send = box -> System.out.println("Отправили ящик с весом " + box.getWeight());
        Consumer<HeavyBox> combined = shipment.andThen(send);

        HeavyBox box = new HeavyBox(20.5);
        combined.accept(box);
    }
}
