package oo.player;

import oo.armor.Armor;
import oo.weapon.Weapon;
import oo.weapon.WeaponAttributes;

import java.util.Optional;

public class NormalPlayer implements People {
    private String name;
    public String job;
    private int blood;
    public int defenseForce;
    private int attackPower;
    private boolean posionFlag = false;
    private boolean changeAttacker = false;
    private WeaponAttributes weaponAttributes;

    public NormalPlayer(String name,int blood,int attackPower) {
        this.job = "普通人";
        this.attackPower = attackPower;
        this.defenseForce = 0;
        this.blood = blood;
        this.name = name;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public void setAttackPower(int attackPower) {
        this.attackPower = attackPower;
    }

    public int getBlood() {
        return blood;
    }

    public void setBlood(int blood) {
        this.blood = blood;
    }

    public boolean isChangeAttacker() {
        return changeAttacker;
    }

    public void setChangeAttacker(boolean changeAttacker) {
        this.changeAttacker = changeAttacker;
    }

    public int getDefenseForce() {
        return defenseForce;
    }

    public void setDefenseForce(int defenseForce) {
        this.defenseForce = defenseForce;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPosionFlag() {
        return posionFlag;
    }

    public void setPosionFlag(boolean posionFlag) {
        this.posionFlag = posionFlag;
    }

    public WeaponAttributes getWeaponAttributes() {
        return weaponAttributes;
    }

    public void setWeaponAttributes(WeaponAttributes weaponAttributes) {
        this.weaponAttributes = weaponAttributes;
    }

    @Override
    public boolean hasWeapon() {
        return false;
    }

    @Override
    public boolean hasArmor() {
        return false;
    }

    @Override
    public int getAttack() {
        return this.attackPower;
    }

    @Override
    public int getDefense() {
        return this.defenseForce;
    }

    @Override
    public void addDefense() {
        this.defenseForce++;
    }

    @Override
    public String getWeaponExtEff() {
        return "";
    }

    @Override
    public Optional<Weapon> getWeapon() {
        return null;
    }

    @Override
    public Optional<Armor> getArmor() {
        return null;
    }

    @Override
    public void dropBlood(NormalPlayer player) {
        int blood;
        if (player instanceof Solider) {
            blood = this.attackPower - player.getDefense();
        } else {
            blood = this.attackPower;
        }
        player.blood -= blood;
    }

    public boolean isAlive() {
        return this.blood > 0;
    }

    public boolean isDead() {
        return this.blood < 0;
    }


    public void commonAttack(NormalPlayer normalPlayer) {
        //
        dropBlood(normalPlayer);
        carryWeaponExtFff(normalPlayer);
        makePoisoned(normalPlayer);

    }

    private void makePoisoned(NormalPlayer normalPlayer) {
        if (hasWeapon() && getWeapon().get().hasAttr() && isPoisoning()) {
            normalPlayer.posionFlag = true;
            normalPlayer.weaponAttributes = this.getWeapon().get().getWeaponAttributes();
        }
    }

    public boolean isPoisoning() {
        return this.posionFlag;
    }



    private void carryWeaponExtFff(NormalPlayer normalPlayer) {
        if (getWeaponExtEff().equals("doubleAttack")) {
            dropBlood(normalPlayer);
        } else if (getWeaponExtEff().equals("extraDefense")) {
            addDefense();
        }

    }

    public String poisonSelfAttack() {
        String poisonOutput = "";

        WeaponAttributes poisonState = getWeaponAttributes();
        if (poisonState.getType().equals("damageDelay")) {
            poisonOutput = damageDelaySituation();
        } else if (poisonState.getType().equals("eachTwoRoundNoAttack")) {
            poisonOutput = eachTwoRoundNoAttackSituation();
        } else if (poisonState.getType().equals("twoRoundNoAttack")) {
            poisonOutput = twoRoundNoAttackSituation();
        }

        return poisonOutput;
    }

    private String twoRoundNoAttackSituation() {
        WeaponAttributes poisonState = getWeaponAttributes();
        String poisonOutput = "";

        if (poisonState.getRound() > 0) {
            poisonOutput += getName() + "晕倒了,无法攻击,眩晕还剩" + (poisonState.getRound() - 1) + "轮\n";
            changeAttacker = true;
            poisonState.setRound(poisonState.getRound() - 1);
        } else {
            poisonState.setRound(2);
            changeAttacker = false;
        }
        return poisonOutput;
    }

    private String eachTwoRoundNoAttackSituation() {
        WeaponAttributes poisonState = getWeaponAttributes();
        String poisonOutput = "";

        if (poisonState.getRound() <= 0) {
            poisonOutput += getName() + "冻得直哆嗦，没有击中\n";
            changeAttacker = true;
            poisonState.setRound(2);
        } else {
            poisonState.setRound(poisonState.getRound() - 1);
            changeAttacker = false;
        }
        return poisonOutput;
    }

    private String damageDelaySituation() {
        WeaponAttributes poisonState = getWeaponAttributes();

        String poisonOutput = "";
        poisonAttack();
        if (isDead()) {
            changeAttacker = true;
        }
        poisonOutput += getName() + "受到" + poisonState.getAttackPower() + "点" + poisonState.getName() + "伤害,";
        poisonOutput += getName() + "剩余生命：" + getBlood() + "\n";
        return poisonOutput;
    }

    public void poisonAttack() {
        this.blood -= this.weaponAttributes.getAttackPower();
    }


    public boolean whetherChangeAttacker() {
        return changeAttacker;
    }

    public String carryOutPoisonAttack() {
        changeAttacker = false;
        String gameProcess = "";
        if (isPoisoning()) {
            gameProcess += poisonSelfAttack();
            changeAttacker = whetherChangeAttacker();
        }
        return gameProcess;
    }


}
