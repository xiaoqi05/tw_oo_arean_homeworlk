package oo.weapon;

public enum WeaponAttributes {
    Toxicity("毒性", "damageDelay", "中毒", 2, 1),
    Fire("火焰", "damageDelay", "着火", 2, 1),
    Icy("冰冻", "eachTwoRoundNoAttack", "冻僵", 2, 2),
    Faint("击晕", "twoRoundNoAttack", "晕倒", 2, 2),
    WholeAttack("全力一击", "tripleDamage", "被击倒", 2, 1);

    private String name;
    private String type;
    private String result;
    private int round;
    private int attackPower;




    WeaponAttributes(String name, String type, String result, int attackPower, int round) {
        this.name = name;
        this.attackPower = attackPower;
        this.result = result;
        this.round = round;
        this.type = type;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public void setAttackPower(int attackPower) {
        this.attackPower = attackPower;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
