package creature.animal.herbivore;

import creature.animal.Herbivore;

public class Horse extends Herbivore {
    public Horse(int x, int y) {
        super(x, y);
        setId(3);
        setSign("\uD83D\uDC0E");
        setWeight(250);
        setInitialWeight(getWeight());
        setDist(3);
        setFullness(80);
    }
}
