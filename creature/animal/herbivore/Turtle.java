package creature.animal.herbivore;

import creature.animal.Herbivore;

public class Turtle extends Herbivore {
    public Turtle(int x, int y) {
        super(x, y);
        setId(6);
        setSign("\uD83D\uDC22");
        setWeight(8);
        setInitialWeight(getWeight());
        setDist(1);
        setFullness(2);
    }
}
