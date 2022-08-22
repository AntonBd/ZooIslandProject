package creature.animal.herbivore;

import creature.animal.Herbivore;

public class Camal extends Herbivore {
    public Camal(int x, int y) {
        super(x, y);
        setId(2);
        setSign("\uD83D\uDC2B");
        setWeight(400);
        setInitialWeight(getWeight());
        setDist(3);
        setFullness(70);
    }
}
