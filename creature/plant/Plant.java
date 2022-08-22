package creature.plant;

import creature.Creature;

public abstract class Plant extends Creature {

    public Plant(int x, int y) {
        super(x, y);
    }

    //Увеличение массы растения
    public void grow() {
        this.setWeight(this.getWeight() + (this.getWeight() * 0.05));
    }
}
