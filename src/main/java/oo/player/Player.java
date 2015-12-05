package oo.player;

import static java.lang.String.format;

public class Player {
    private String name;
    private int blood;
    private int damage;

    public Player(String name, int blood, int damage) {
        this.name = name;
        this.blood = blood;
        this.damage = damage;

    }

    public String getName() {
        return name;
    }

    public int getBlood() {
        return blood;
    }

    public int getDamage() {
        return damage;
    }

    public String attack(Player victim) {
        return format("%s攻击了%s，%s", name, victim.name, victim.beAttacked(damage));
    }

    private String beAttacked(int damage) {
        blood -= damage;
        return format("%s受到了%d点伤害，%s剩余生命：%d",
                name, damage, name, blood);
    }

    public boolean isAlive() {
        return blood >= 0;
    }
}
