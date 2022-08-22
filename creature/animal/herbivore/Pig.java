package creature.animal.herbivore;

import creature.animal.Herbivore;

public class Pig extends Herbivore {
    public Pig(int x, int y) {
        super(x, y);
        setId(5);
        setSign("\uD83D\uDC37");
        setWeight(70);
        setInitialWeight(getWeight());
        setDist(2);
        setFullness(10);
    }
}
