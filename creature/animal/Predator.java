package creature.animal;

import creature.Creature;
import zooisland.Island;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Predator extends Animal {

    public Predator(int x, int y) {
        super(x, y);
    }


//Возвращает знак результата охоты, который далее передается в Событие дня
@Override
public String eat(Creature possibleFood, ArrayList<Creature> innerList) {

    //Матрица с информацией о вероятности съедания хищником травоядного
    //[1-ая ячейка] - id травоядного, [2-ая ячейка] - id хищника
    //Хищники: 0 - волк, 1 - тигр, 2 - медведь, 3 - обезьяна, 4 - удав
    int[][] percentageTable = new int[10][5];
    percentageTable[0][0] = 80;     //0 - овца
    percentageTable[0][1] = 80;
    percentageTable[0][2] = 50;
    percentageTable[0][3] = 1;
    percentageTable[0][4] = 60;
    percentageTable[1][0] = 15;     //1 - слон
    percentageTable[1][1] = 50;
    percentageTable[1][2] = 30;
    percentageTable[1][3] = 1;
    percentageTable[1][4] = 1;
    percentageTable[2][0] = 25;     //2 - верблюд
    percentageTable[2][1] = 60;
    percentageTable[2][2] = 40;
    percentageTable[2][3] = 1;
    percentageTable[2][4] = 1;
    percentageTable[3][0] = 35;     //3 - лошадь
    percentageTable[3][1] = 50;
    percentageTable[3][2] = 50;
    percentageTable[3][3] = 1;
    percentageTable[3][4] = 1;
    percentageTable[4][0] = 50;     //4 - корова
    percentageTable[4][1] = 70;
    percentageTable[4][2] = 60;
    percentageTable[4][3] = 1;
    percentageTable[4][4] = 50;
    percentageTable[5][0] = 60;     //5 - свинья
    percentageTable[5][1] = 70;
    percentageTable[5][2] = 40;
    percentageTable[5][3] = 50;
    percentageTable[5][4] = 70;
    percentageTable[6][0] = 10;     //6 - черепаха
    percentageTable[6][1] = 10;
    percentageTable[6][2] = 5;
    percentageTable[6][3] = 90;
    percentageTable[6][4] = 5;
    percentageTable[7][0] = 10;     //7 - коала
    percentageTable[7][1] = 60;
    percentageTable[7][2] = 30;
    percentageTable[7][3] = 80;
    percentageTable[7][4] = 80;
    percentageTable[8][0] = 60;     //8 - кролик
    percentageTable[8][1] = 70;
    percentageTable[8][2] = 30;
    percentageTable[8][3] = 70;
    percentageTable[8][4] = 90;
    percentageTable[9][0] = 70;     //9 - пингвин
    percentageTable[9][1] = 80;
    percentageTable[9][2] = 70;
    percentageTable[9][3] = 10;
    percentageTable[9][4] = 30;

    if (possibleFood instanceof Herbivore) {
        int success = percentageTable[possibleFood.getId()][this.getId()];              //% успешной охоты из матрицы

        int hunterResult = ThreadLocalRandom.current().nextInt(1,101);
        if (hunterResult <= success) {                                                  //Успешная охота
            possibleFood.die(innerList);

            //Набор хищником веса
            double remains = possibleFood.getWeight() - this.getFullness();
            if (remains >= 0) {
                this.setWeight(this.getWeight() + this.getFullness());
                Island.setHerbivoreEaten(Island.getHerbivoreEaten() + this.getFullness());
            }
            else {
                this.setWeight(this.getWeight() + possibleFood.getWeight());
                Island.setHerbivoreEaten(Island.getHerbivoreEaten() + (int)possibleFood.getWeight());
            }
            return "-";                                                           //Знак успешной охоты в События дни
        }
        else {
            return "X";                                                           //Знак неуспешной охоты в События дни
        }
    }
    return "";
    }
}
