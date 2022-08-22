package creature.animal.predator;

import creature.animal.Predator;

public class Wolf extends Predator {

    public Wolf(int x, int y) {
        super(x, y);
        setId(0);
        setSign("\uD83D\uDC3A");
        setWeight(50);
        setInitialWeight(getWeight());
        setDist(3);
        setFullness(5);
    }
}
