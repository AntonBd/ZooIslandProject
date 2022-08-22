package creature.animal.herbivore;

import creature.animal.Herbivore;

public class Rabbit extends Herbivore {
    public Rabbit(int x, int y) {
        super(x, y);
        setId(8);
        setSign("\uD83D\uDC30");
        setWeight(8);
        setInitialWeight(getWeight());
        setDist(2);
        setFullness(2);
    }
}
