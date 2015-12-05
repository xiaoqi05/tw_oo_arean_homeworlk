package oo;

import oo.armor.Armor;
import oo.player.NormalPlayer;
import oo.player.Player;
import oo.player.Solider;
import oo.weapon.Weapon;
import oo.weapon.WeaponAttributes;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.inOrder;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@RunWith(MockitoJUnitRunner.class)
public class GameTest {
    private Game game;
    @Mock
    private ConsolePrinter consolePrinter;
    InOrder inOrder;

    @Before
    public void setUp() {
        inOrder = inOrder(consolePrinter);
        game = new Game(consolePrinter);
    }

    @Test
    public void shouldSecondPlayerLoseWhenFirstPlayerIsPowerful() {
        Player firstPlayer = new Player("张三", 10, 10);
        Player secondPlayer = new Player("李四", 9, 10);

        game.fight(firstPlayer, secondPlayer);

        inOrder.verify(consolePrinter, times(1)).print("张三攻击了李四，李四受到了10点伤害，李四剩余生命：-1\n");
        inOrder.verify(consolePrinter, times(1)).print("李四被打败了");
    }

    @Test
    public void shouldFirstPlayerLoseWhenSecondPlayerIsPowerful() {
        Player firstPlayer = new Player("张三", 10, 8);
        Player secondPlayer = new Player("李四", 20, 9);

        game.fight(firstPlayer, secondPlayer);

        inOrder.verify(consolePrinter, times(1)).print("张三攻击了李四，李四受到了8点伤害，李四剩余生命：12\n");
        inOrder.verify(consolePrinter, times(1)).print("李四攻击了张三，张三受到了9点伤害，张三剩余生命：1\n");
        inOrder.verify(consolePrinter, times(1)).print("张三攻击了李四，李四受到了8点伤害，李四剩余生命：4\n");
        inOrder.verify(consolePrinter, times(1)).print("李四攻击了张三，张三受到了9点伤害，张三剩余生命：-8\n");
        inOrder.verify(consolePrinter, times(1)).print("张三被打败了");
    }





    /*
    *
    *     Toxicity("毒性", "damageDelay", "中毒", 2, 1),
    Fire("火焰", "damageDelay", "着火", 2, 1),
    Icy("冰冻", "eachTwoRoundNoAttack", "冻僵", 2, 2),
    Faint("击晕", "twoRoundNoAttack", "晕倒", 2, 2),
    WholeAttack("全力一击", "tripleDamage", "被击倒", 2, 1);
    *
    *
    *
    * */

    @Test
    public void shouldPrintCorrectGameProcessWhenWeaponHasToxicityDamage() {
        WeaponAttributes weaponFeature = WeaponAttributes.Toxicity;
        Weapon weapon = new Weapon(1,"优质毒剑", weaponFeature);
        Armor armor = new Armor(1,"护具");
        NormalPlayer player1 = new NormalPlayer("张三", 10, 8);
        NormalPlayer player2 = new Solider("李四", 20, 2, weapon, armor);

        game = new Game(player1, player2);
        game.startfightGame();
        String gameProcess = game.displayGameRecorder();
        assertThat(gameProcess, is("普通人张三攻击了战士李四,李四受到了7点伤害,李四剩余生命：13\n" +
                "战士李四用优质毒剑攻击了普通人张三,张三受到了3点伤害,张三中毒了,张三剩余生命：7\n" +
                "张三受到2点毒性伤害,张三剩余生命：5\n" +
                "普通人张三攻击了战士李四,李四受到了7点伤害,李四剩余生命：6\n" +
                "战士李四用优质毒剑攻击了普通人张三,张三受到了3点伤害,张三中毒了,张三剩余生命：2\n" +
                "张三受到2点毒性伤害,张三剩余生命：0\n"));
        String gameResult = game.displayGameResult();
        assertThat(gameResult, is("张三被打败了"));

    }


    @Test
    public void shouldPrintCorrectGameProcessWhenWeaponHasFaintDamage() {
        WeaponAttributes weaponFeature = WeaponAttributes.Faint;
        Weapon weapon = new Weapon(2,"晕锤", weaponFeature);
        Armor armor = new Armor(1,"护具");
        NormalPlayer player1 = new NormalPlayer("张三",17,5);
        NormalPlayer player2 = new Solider("李四", 13, 2, weapon, armor);

        game = new Game(player1, player2);
        game.startfightGame();
        String gameProcess = game.displayGameRecorder();
        assertThat(gameProcess, is("普通人张三攻击了战士李四,李四受到了4点伤害,李四剩余生命：9\n" +
                "战士李四用晕锤攻击了普通人张三,张三受到了4点伤害,张三晕倒了,张三剩余生命：13\n" +
                "张三晕倒了,无法攻击,眩晕还剩1轮\n" + "" +
                "战士李四用晕锤攻击了普通人张三,张三受到了4点伤害,张三晕倒了,张三剩余生命：9\n" +
                "张三晕倒了,无法攻击,眩晕还剩0轮\n" +
                "战士李四用晕锤攻击了普通人张三,张三受到了4点伤害,张三晕倒了,张三剩余生命：5\n" +
                "普通人张三攻击了战士李四,李四受到了4点伤害,李四剩余生命：5\n" +
                "战士李四用晕锤攻击了普通人张三,张三受到了4点伤害,张三晕倒了,张三剩余生命：1\n" +
                "张三晕倒了,无法攻击,眩晕还剩1轮\n" +
                "战士李四用晕锤攻击了普通人张三,张三受到了4点伤害,张三晕倒了,张三剩余生命：-3\n"));
        String gameResult = game.displayGameResult();
        assertThat(gameResult, is("张三被打败了"));

    }


















}