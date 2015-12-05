package oo.armor;

public class Armor {
    private String name;
    private int defenseForce;

    public Armor(int defenseForce, String name) {
        this.defenseForce = defenseForce;
        this.name = name;
    }

    public Armor() {
    }

    public int getDefenseForce() {
        return defenseForce;
    }

    public void setDefenseForce(int defenseForce) {
        this.defenseForce = defenseForce;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
