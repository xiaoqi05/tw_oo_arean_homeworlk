package oo;

import oo.player.NormalPlayer;
import oo.player.Player;

import java.util.ArrayList;

import static java.lang.String.format;

public class Game {
    private ArrayList<String> gameRecorder = new ArrayList<String>();
    private NormalPlayer player1;
    private NormalPlayer player2;

    public Game(NormalPlayer player1, NormalPlayer player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    private ConsolePrinter consolePrinter;

    public Game(ConsolePrinter consolePrinter) {
        this.consolePrinter = consolePrinter;
    }

    public void fight(Player firstPlayer, Player secondPlayer) {
        Player attacker = firstPlayer;
        Player victim = secondPlayer;
        Player loser = attacker;

        while (attacker.isAlive()) {
            consolePrinter.print(format("%s\n", attacker.attack(victim)));
            loser = victim;
            victim = attacker;
            attacker = loser;
        }

        consolePrinter.print(format("%s被打败了", loser.getName()));
    }

    public void startfightGame() {

        while (player1.isAlive() && player2.isAlive()) {
            //攻击前判断自己有没有延时等伤害，若有，先掉血
            gameRecorder.add(player1.carryOutPoisonAttack());

            if (!player1.whetherChangeAttacker()) {
                boolean attackValid = true;
                player1.commonAttack(player2);
//                gameRecorder.add(player1.carryWeaponExtraEffect(player2));
                gameRecorder.add(getCommonAttackOutput(player1, player2, attackValid));
            }

            NormalPlayer temp = player1;
            player1 = player2;
            player2 = temp;
        }


    }

    private String getCommonAttackOutput(NormalPlayer player1, NormalPlayer player2, boolean attackValid) {
        String output = "";
        if (attackValid) {
            output += getCommonAttackProcessOutput(player1, player2);
            output += getCommonAttackResultString(player1, player2);
        } else {
            output += player1.getName() + "靠近了" + player2.getName() + "\n";
        }

        return output;
    }

    private int getAttackedBlood(NormalPlayer player1, NormalPlayer player2) {
        int attackedBlood;
        if (player2.hasArmor()) {
            attackedBlood = player1.getAttack() - player2.getDefense();
        } else {
            attackedBlood = player1.getAttack();
        }

        if (player1.hasWeapon() && player1.getWeapon().get().hasAttr() && player1.getWeapon().get().getWeaponAttributes().getType().equals("tripleDamage")) {
            attackedBlood *= 3;
        }
        return attackedBlood;
    }

    private String getCommonAttackResultString(NormalPlayer player1, NormalPlayer player2) {
        int attackedBlood = getAttackedBlood(player1, player2);
        String output = "";
        output += player2.getName() + "受到了" + attackedBlood + "点伤害,";
        if (player2.isPoisoning()) {
            output += player2.getName() + player2.getWeaponAttributes().getResult() + "了,";
        }
        output += player2.getName() + "剩余生命：" + player2.getBlood() + "\n";
        return output;
    }

    private String getCommonAttackProcessOutput(NormalPlayer player1, NormalPlayer player2) {
        String output = "";
        if (player1.hasWeapon()) {
            output += player1.getJob() + player1.getName() + "用" + player1.getWeapon().get().getName() + "攻击了" + player2.getJob() + player2.getName() + ",";
        } else {
            output += player1.getJob() + player1.getName() + "攻击了" + player2.getJob() + player2.getName() + ",";
        }

        if (player1.hasWeapon() && player1.getWeapon().get().hasAttr() && player1.getWeapon().get().getWeaponAttributes().getType().equals("tripleDamage")) {
            output += player1.getName() + "发动了" + player1.getWeapon().get().getWeaponAttributes().getName() + ",";
        }
        return output;
    }

    public String displayGameRecorder() {
        return "" + gameRecorder;
    }

    public String displayGameResult() {
        String gameResult = "";
        if (player1.isDead()) {
            gameResult = player1.getName() + "被打败了";
        } else {
            gameResult = player2.getName() + "被打败了";
        }

        return gameResult;
    }
}
