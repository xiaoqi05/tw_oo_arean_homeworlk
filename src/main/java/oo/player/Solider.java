package oo.player;


import oo.armor.Armor;
import oo.weapon.Weapon;

import java.util.Optional;

/**
 * 战士只能装备 中 武器
 */
public class Solider extends NormalPlayer {
    private Weapon weapon;
    private Armor armor;

    public Solider(String name, int blood, int attackPower,Weapon weapon,Armor armor) {
        super(name, blood, attackPower);
        this.job = "战士";
        this.weapon = weapon;
        this.armor = armor;
        this.defenseForce = this.armor.getDefenseForce();
    }

    /*public Solider(String name, int blood, int attack, Weapon weapon, Armor armor) {
        super(attack, blood, name);
        this.job = "战士";
        this.weapon = weapon;
        this.armor = armor;
        this.defenseForce = this.armor.getDefenseForce();
    }*/

    @Override
    public void dropBlood(NormalPlayer player2) {
        int droppedBlood;
        if (player2 instanceof Solider) {
            droppedBlood = this.getAttack() - player2.getDefense();
        } else {
            droppedBlood = this.getAttack();
        }

        if (weapon.hasAttr() && weapon.getWeaponAttributes().getType().equals("tripleDamage")) {
            droppedBlood *= 3;
        }

        player2.setBlood(player2.getBlood() - droppedBlood);
    }

    @Override
    public boolean hasWeapon() {
        return true;
    }

    @Override
    public boolean hasArmor() {
        return true;
    }

    @Override
    public Optional<Weapon> getWeapon() {
        return Optional.of(this.weapon);
    }

    @Override
    public Optional<Armor> getArmor() {
        return Optional.of(this.armor);
    }

    @Override
    public int getAttack() {
        return this.getWeapon().get().getBaseAttackPower() + super.getAttack();
    }

    @Override
    public int getDefense() {
        return getArmor().get().getDefenseForce();
    }

}