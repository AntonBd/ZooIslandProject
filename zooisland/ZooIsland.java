package zooisland;

import java.util.Scanner;
import java.util.concurrent.*;

public class ZooIsland extends Island {

    private static int day = 1;
    private static int countDays;
    private static int needDrawIsland;
    private static ScheduledExecutorService executorService;

    public static void main(String[] args) throws InterruptedException {

        greet();
        setParameters();
        addCreatures();
        createIsland();
        addToCells();

        executorService = Executors.newScheduledThreadPool(2);
        executorService.scheduleAtFixedRate(new AnimalsThread(), 0, 3, TimeUnit.SECONDS);
        executorService.scheduleAtFixedRate(new PlantsThread(), 0, 3, TimeUnit.SECONDS);
    }

    public static class AnimalsThread implements Runnable {
        @Override
        public void run() {
            if (needDrawIsland == 1) {

                drawField();
            }
            moveAnimals();
            printStatistic();
            interraction();

            day++;
            if (day == countDays+1) {
                executorService.shutdown();
                System.out.println("Выполнение симуляции завершено.");
            }
        }
    }

    public static class PlantsThread implements Runnable {
        @Override
        public void run() {
            growPlants();
        }
    }

    public static void greet() {
        System.out.println("""                           
                Университет JavaRush
                Итоговая работа к модулю 2: Java Core
                Проект \u0094Симулятор острова, населенного животными\u0093
                -----------------------------------------------------------              
                """);
    }

    public static void setParameters() {
        System.out.println("Укажите параметры симуляции:");
        System.out.print("Длина острова в диапазоне от 20 до 150 км: ");
        setIslandX(enterNumber(20, 150));
        System.out.print("Ширина острова в диапазоне до 10 до 30 км: ");
        setIslandY(enterNumber(10, 30));
        System.out.print("Количество тактов симуляции в диапазоне от 1 до 100 дней: ");
        countDays = enterNumber(1, 100);
        System.out.print("Режим вывода информации: 1 - отрисовка острова и статистика, 2 - только статистика: ");
        needDrawIsland = enterNumber(1,2);
        System.out.print("Населенность острова в диапазоне от 3 (высокая) до 10 (низкая): ");
        setPopulation(enterNumber(3, 10));
        System.out.println("-----------------------------------------------------------");
    }

    public static int enterNumber(int valueFrom, int valueTo) {
        int number;

        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                number = Integer.parseInt(scanner.nextLine());
                if (number >= valueFrom && number <= valueTo) {
                    break;
                }
                else {
                    System.out.print("Значение должно быть в диапазоне от " + valueFrom + " до " + valueTo + ": ");
                }
            }
            catch (NumberFormatException e) {
                System.out.print("Необходимо указать число: ");
            }
        }
        return number;
    }

    public static int getDay() {
        return day;
    }
}
