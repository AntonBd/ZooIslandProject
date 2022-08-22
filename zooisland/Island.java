package zooisland;

import creature.Creature;
import creature.animal.*;
import creature.animal.herbivore.*;
import creature.animal.predator.*;
import creature.plant.*;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.lang.reflect.*;

public class Island {

    private static int islandX = 150;
    private static int islandY = 30;

    private static String[][] field = new String[islandX][islandY];                      //Массив острова для вывода на экран
    private static ArrayList<Creature> lifeList = new ArrayList<>();                     //Лист со всеми объектами
    private static ArrayList<Creature>[][] island = new ArrayList[islandY][islandX];     //Массив с листами внутри


    private static int population = 0;
    private static int stepsPerDay = 0;
    private static String dayEvents = "";
    private static double plantsEaten = 0;
    private static int herbivoreEaten = 0;


    //Добавление существ в lifeList в начале симуляции
    public static void addCreatures() {
        int countCreaters = (islandX * islandY) / population;                           //Количество объектов на острове

        for (int i = 0; i < countCreaters; i++) {
            int type = ThreadLocalRandom.current().nextInt(1,21);           //Выбор типа объекта
            int newX = ThreadLocalRandom.current().nextInt(0, islandX -1);
            int newY = ThreadLocalRandom.current().nextInt(0, islandY -1);

            Class aClass = switch (type) {
                case 1 -> Sheep.class;
                case 2 -> Cactus.class;
                case 3 -> Wolf.class;
                case 4 -> Herb.class;
                case 5 -> Tiger.class;
                case 6 -> Camal.class;
                case 7 -> Elephant.class;
                case 8 -> Horse.class;
                case 9 -> Bear.class;
                case 10 -> Cow.class;
                case 11 -> Oak.class;
                case 12 -> Monkey.class;
                case 13 -> Turtle.class;
                case 14 -> Koala.class;
                case 15 -> Sunflower.class;
                case 16 -> Boa.class;
                case 17 -> Corn.class;
                case 18 -> Rabbit.class;
                case 19 -> Penguin.class;
                case 20 -> Pig.class;
                default -> null;
            };
            makeNewCreature(aClass, newX, newY);
        }
    }

    //Создание нового объекта, класс которого заранее не известен
    public static void makeNewCreature(Class<? extends Creature> aClass, int x, int y) {
        try {
            Constructor<? extends Creature> unitConstructor = aClass.getConstructor(int.class, int.class);
            Creature unit = unitConstructor.newInstance(x,y);
            lifeList.add(unit);
        }
        catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    //Создание острова: помещаем лист в каждую ячейку массива
    public static void createIsland() {
        for (int i = 0; i < islandY; i++) {
            for(int j = 0; j < islandX; j++) {
                island[i][j] = new ArrayList<>();
            }
        }
    }

    //Добавление существ из lifeList в листы внутри ячеек поля
    public static void addToCells() {
        for (Creature unit : lifeList) {
            ArrayList<Creature> innerList = island[unit.getY()][unit.getX()];   //Ссылка на внутринний лист ячейки
            if (innerList.size() == 0) {
                innerList.add(unit);
            }
        }
    }

    //Отрисовка острова
    public static void drawField(){

        //Присваивание каждой ячейке "не занятого" значения
        for (int i = 0; i < islandY; i++) {
            for (int j = 0; j < islandX; j++) {
                field[j][i] = "\u0F1A";
            }
        }

        //Добавление объектов на поле
        for (Creature toField : lifeList) {
            field[toField.getX()][toField.getY()] = toField.getSign();           //Присваивание ячейке символа персонажа
        }

        //Вывод поля на экран
        System.out.println("\nДень " + ZooIsland.getDay() +":");
        for (int i = 0; i < islandY; i++) {
            for (int j = 0; j < islandX; j++) {
                System.out.print(field[j][i]);
            }
            System.out.println();
        }
    }

    //Получение животными новых координат
    public static void moveAnimals() {
        for (int i = 0; i < lifeList.size(); i++) {
            ArrayList<Creature> innerList;                          //Внутренний массив ячейки
            Creature unit = lifeList.get(i);                        //Текущий объект из list

            if (unit instanceof Animal) {
                innerList = island[unit.getY()][unit.getX()];       //Ссылка на лист в ячейке до перемещения
                innerList.remove(unit);                             //Удаление объекта из листа

                ((Animal)unit).move();                              //Задание объекту новых координат

                innerList = island[unit.getY()][unit.getX()];       //Ссылка на лист в ячейке с новыми координатами
                innerList.add(unit);                                //Запись объекта в лист новой ячейки


                unit.setWeight(unit.getWeight() - (unit.getWeight()/25));       //Потеря веса при перемещении
                if (unit.getWeight() < unit.getInitialWeight() /2) {            //Если вес меньше половины от исходного
                    unit.die(innerList);                                        //Животное умирает
                    dayEvents = dayEvents + "y" + unit.getY() + "_" + "x" + unit.getX() + " " +
                            unit.getSign() + " \u279D \uD83D\uDC80 | ";         //Добавление информации в События дня
                }
            }
        }
    }

    //Взаимодействие объектов
    public static void interraction() {
        for (int i = 0; i < islandY; i++) {
            for (int j = 0; j < islandX; j++) {
                ArrayList<Creature> innerList = island[i][j];           //Получение ссылки на лист внутри ячейки
                int innerSize = innerList.size();

                if (innerSize > 1) {
                    boolean eventToStatistic = false;                           //Добавить ли событие в вывод
                    String interractionResult = "";                             //Результат взаимодействия объектов

                    Animal last = (Animal)innerList.get(innerSize-1);           //Ссылка на последний объект в листе
                    Creature prev = innerList.get(innerSize-2);                 //Ссылка на предпоследний объект

                    if (last.getClass().equals(prev.getClass())) {              //Если классы объектов равны
                        Class<? extends Animal> aClass = last.getClass();       //Получение класса этого объекта

                        last.reproduce(aClass, last.getX(), last.getY());       //Рождение нового животного

                        eventToStatistic = true;
                        interractionResult = "+";                               //Символ рождения в События дня
                    }
                    else {
                        String hunterResult = last.eat(prev, innerList);        //Попытка съесть объект
                        if(!hunterResult.equals("")) {
                            eventToStatistic = true;
                            interractionResult = hunterResult;
                        }
                    }
                    if (eventToStatistic) {                                     //Добавить информацию в События дня
                        dayEvents = dayEvents + "y" + last.getY() + "_" + "x" + last.getX() + " " +
                                last.getSign() + " \u279D " + prev.getSign() + " : " +
                                interractionResult + prev.getSign() + " | ";
                    }
                }
            }
        }
    }

    //Рост растений
    public static void growPlants() {
        for (Creature creature : lifeList) {
            if (creature instanceof Plant) {
                ((Plant) creature).grow();
            }
        }
    }

    //Вывод статистики
    public static void printStatistic() {
        System.out.print("События дня "+ ZooIsland.getDay() + ": " + dayEvents);
        System.out.println("\nСтатистика: Количество объектов: " + lifeList.size() + " | " +
                "Пройденое расстояние: " + stepsPerDay + " км" + " | Вес съеденных растений: " +
                (int)plantsEaten + " кг, животных: " + herbivoreEaten + " кг |");
        System.out.println();

            dayEvents = "";
            stepsPerDay = 0;
            plantsEaten = 0;
            herbivoreEaten = 0;
    }

    public static int getIslandX() {
        return islandX;
    }

    public static void setIslandX(int x) { islandX = x; }

    public static int getIslandY() {
        return islandY;
    }

    public static void setIslandY(int y) {
        islandY = y;
    }

    public static void setPopulation(int population) { Island.population = population; }

    public static ArrayList<Creature> getLifeList() {
        return lifeList;
    }

    public static void setStepsPerDay(int stepsPerDay) {
        Island.stepsPerDay = stepsPerDay;
    }

    public static int getStepsPerDay() {
        return stepsPerDay;
    }

    public static double getPlantsEaten() { return plantsEaten; }

    public static void setPlantsEaten(double plantsEaten) { Island.plantsEaten = plantsEaten; }

    public static int getHerbivoreEaten() {
        return herbivoreEaten;
    }

    public static void setHerbivoreEaten(int herbivoreEaten) {
        Island.herbivoreEaten = herbivoreEaten;
    }
}