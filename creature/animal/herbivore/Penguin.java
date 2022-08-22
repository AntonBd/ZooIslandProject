package creature.animal.herbivore;

import creature.animal.Herbivore;

public class Penguin extends Herbivore {
    public Penguin(int x, int y) {
        super(x, y);
        setId(9);
        setSign("\uD83D\uDC27");
        setWeight(8);
        setInitialWeight(getWeight());
        setDist(2);
        setFullness(3);
    }
}
