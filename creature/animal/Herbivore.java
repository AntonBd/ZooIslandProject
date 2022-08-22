package creature.animal;

import creature.Creature;
import creature.plant.Plant;
import zooisland.Island;

import java.util.ArrayList;

//Возвращает знак "-" в случае, если растение съедено полностью
public abstract class Herbivore extends Animal {

    public Herbivore(int x, int y) {
        super(x, y);
    }

    @Override
    public String eat(Creature possibleFood, ArrayList<Creature> innerList) {

        String result = "";

        if (possibleFood instanceof Plant) {
            double remains = possibleFood.getWeight() - this.getFullness();

            if (remains >= 0) {
                this.setWeight(this.getWeight() + this.getFullness());
                possibleFood.setWeight(possibleFood.getWeight() - this.getFullness());
                Island.setPlantsEaten(Island.getPlantsEaten() + this.getFullness());
            }
            else {
                this.setWeight(this.getWeight() + possibleFood.getWeight());
                Island.setPlantsEaten(Island.getPlantsEaten() + possibleFood.getWeight());
                possibleFood.die(innerList);
                result = "-";
            }
        }
        return result;
    }
}
