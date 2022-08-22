package creature.animal.predator;

import creature.animal.Predator;

public class Monkey extends Predator {
    public Monkey(int x, int y) {
        super(x, y);
        setId(3);
        setSign("\uD83D\uDC12");
        setWeight(30);
        setInitialWeight(getWeight());
        setDist(3);
        setFullness(6);
    }
}
