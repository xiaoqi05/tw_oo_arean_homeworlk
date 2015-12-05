package oo.weapon;

public class Weapon {
    private String name;
    private int baseAttackPower;
    private WeaponAttributes weaponAttributes;

    public Weapon() {
    }

    public Weapon(String name, int baseAttackPower) {
        this.name = name;
        this.baseAttackPower = baseAttackPower;
    }

    public Weapon(int baseAttackPower, String name, WeaponAttributes weaponAttributes) {
        this.baseAttackPower = baseAttackPower;
        this.name = name;
        this.weaponAttributes = weaponAttributes;
    }

    public int getBaseAttackPower() {
        return baseAttackPower;
    }

    public void setBaseAttackPower(int baseAttackPower) {
        this.baseAttackPower = baseAttackPower;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public WeaponAttributes getWeaponAttributes() {
        return weaponAttributes;
    }

    public void setWeaponAttributes(WeaponAttributes weaponAttributes) {
        this.weaponAttributes = weaponAttributes;
    }

    public boolean hasAttr(){
        return weaponAttributes !=null;
    }

}
