package creature.animal.predator;

import creature.animal.Predator;

public class Boa extends Predator {
    public Boa(int x, int y) {
        super(x, y);
        setId(4);
        setSign("\uD83D\uDC0D");
        setWeight(60);
        setInitialWeight(getWeight());
        setDist(1);
        setFullness(15);
    }
}
