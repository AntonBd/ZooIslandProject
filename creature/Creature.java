package creature;

import zooisland.Island;

import java.util.ArrayList;

public abstract class Creature {
    private int x;
    private int y;

    private int id;
    private String sign;
    private double weight;
    private double initialWeight;

    public Creature(int x, int y) {
        this.x = x;
        this.y = y;
    }

    //Смерть существа: удаление объекта из листа со всеми объектами (lifeList) и из листа ячейки (innerList)
    public void die(ArrayList<Creature> innerList) {
        innerList.remove(this);
        Island.getLifeList().remove(this);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getInitialWeight() {
        return initialWeight;
    }

    public void setInitialWeight(double initialWeight) {
        this.initialWeight = initialWeight;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
