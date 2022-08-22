package creature.animal.herbivore;

import creature.animal.Herbivore;

public class Elephant extends Herbivore {

    public Elephant(int x, int y) {
        super(x, y);
        setId(1);
        setSign("\uD83D\uDC18");
        setWeight(1500);
        setInitialWeight(getWeight());
        setDist(4);
        setFullness(100);
    }
}
