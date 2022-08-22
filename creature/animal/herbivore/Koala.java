package creature.animal.herbivore;

import creature.animal.Herbivore;

public class Koala extends Herbivore {
    public Koala(int x, int y) {
        super(x, y);
        setId(7);
        setSign("\uD83D\uDC28");
        setWeight(15);
        setInitialWeight(getWeight());
        setDist(2);
        setFullness(4);
    }
}
