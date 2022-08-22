package creature.animal.herbivore;

import creature.animal.Herbivore;

public class Sheep extends Herbivore {

    public Sheep(int x, int y) {
        super(x, y);
        setId(0);
        setSign("\uD83D\uDC11");
        setWeight(90);
        setInitialWeight(getWeight());
        setDist(2);
        setFullness(10);
    }
}
