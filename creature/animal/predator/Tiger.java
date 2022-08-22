package creature.animal.predator;

import creature.animal.Predator;

public class Tiger extends Predator {


    public Tiger(int x, int y) {
        super(x, y);
        setId(1);
        setSign("\uD83D\uDC2F");
        setWeight(400);
        setInitialWeight(getWeight());
        setDist(4);
        setFullness(30);

    }
}
