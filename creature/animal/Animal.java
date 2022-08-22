package creature.animal;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import creature.Creature;
import zooisland.Island;

import static zooisland.Island.*;

public abstract class Animal extends Creature {

    private int dist;
    private int fullness;

    public Animal(int x, int y) {
        super(x, y);
    }

    public void move() {

        int direction = ThreadLocalRandom.current().nextInt(1,5);       //Направление движения
        int steps = ThreadLocalRandom.current().nextInt(1, dist+1);     //Количество шагов

        if (direction == 1) {
            if (getX() + steps < Island.getIslandX())   setX(getX() + steps);
            else                                        setX(getX() - steps);
        }
        if (direction == 2) {
            if (getX() - steps >= 0)                    setX(getX() - steps);
            else                                        setX(getX() + steps);
        }
        if (direction == 3) {
            if (getY() + steps < Island.getIslandY())   setY(getY() + steps);
            else                                        setY(getY() - steps);
        }
        if (direction == 4) {
            if (getY() - steps >= 0)                    setY(getY() - steps);
            else                                        setY(getY() + steps);
        }

        setStepsPerDay(getStepsPerDay() + steps);                                   //Информация в статистику
    }

    public abstract String eat(Creature possibleFood, ArrayList<Creature> innerList);

    public void reproduce(Class<? extends Animal> aClass, int x, int y) {
        Island.makeNewCreature(aClass, x, y);
    }

    public int getDist() {
        return dist;
    }

    public void setDist(int dist) {
        this.dist = dist;
    }

    public int getFullness() {
        return fullness;
    }

    public void setFullness(int fullness) {
        this.fullness = fullness;
    }
}