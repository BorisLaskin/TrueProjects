package View;

import model.Toy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleView extends AbstractView {
    private List<Toy> lottery = new ArrayList<>();

    @Override
    public void printLotteryResults() {
        for (Toy toy : lottery) {
            System.out.println(toy.toString());
        }

    }

    @Override
    public void writeResults() {

    }

    @Override
    public void getResults(List<Toy> list) {
        this.lottery = list;
    }

    @Override
    public int getCountToPrise() {
        System.out.println("Сколько игрушек желаете разыграть?");
        Scanner cin = new Scanner(System.in);
        if (cin.hasNextInt()) {
            int temp = cin.nextInt();
            if (temp < 0) {
                System.out.println("Отрицательные число игрушек нельзя разыгарть, будет разыграно 20 шт.");
                temp = 20;
            }
            return temp;
        }
        return 20;
    }


}

