package oo;

import oo.player.Player;
import oo.weapon.Weapon;

public class FightPlayer extends Player {
    private TypeEnum typeEnum;
    private oo.weapon.Weapon Weapon;


    public FightPlayer(String name, int blood, int damage,TypeEnum typeEnum) {
        super(name, blood, damage);
        this.typeEnum = typeEnum;
    }

    public void setWeapon(Weapon Weapon){
        if (this.typeEnum!=TypeEnum.normal){
            this.Weapon = Weapon;
        }

    }


}
