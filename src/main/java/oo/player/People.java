package oo.player;

import oo.armor.Armor;
import oo.weapon.Weapon;

import java.util.Optional;

public interface People {
    boolean hasWeapon();

    boolean hasArmor();

    int getAttack();

    int getDefense();

    void addDefense();

    String getWeaponExtEff();

    Optional<Weapon> getWeapon();

    Optional<Armor> getArmor();

    void dropBlood(NormalPlayer player);

}
