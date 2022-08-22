package creature.animal.predator;

import creature.animal.Predator;

public class Bear extends Predator {

    public Bear(int x, int y) {
        super(x, y);
        setId(2);
        setSign("\uD83D\uDC3B");
        setWeight(300);
        setInitialWeight(getWeight());
        setDist(3);
        setFullness(20);

    }
}
