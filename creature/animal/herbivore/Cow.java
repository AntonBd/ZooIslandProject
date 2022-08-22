package creature.animal.herbivore;

import creature.animal.Herbivore;

public class Cow extends Herbivore {
    public Cow(int x, int y) {
        super(x, y);
        setId(4);
        setSign("\uD83D\uDC2E");
        setWeight(250);
        setInitialWeight(getWeight());
        setDist(2);
        setFullness(60);
    }
}
