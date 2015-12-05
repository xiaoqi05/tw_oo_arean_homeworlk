package oo;

import oo.player.Player;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class PlayerTest {
    @Test
    public void shouldCreatePlayerSuccessfully() {
        Player player = new Player("王二麻子", 100, 10);

        assertThat(player.getName(), is("王二麻子"));
        assertThat(player.getBlood(), is(100));
        assertThat(player.getDamage(), is(10));
    }

    @Test
    public void shouldCanAttackOtherPlayer() {
        Player attacker = new Player("张三", 100, 10);
        Player victim = new Player("李四", 80, 20);

        attacker.attack(victim);

        assertThat(victim.getBlood(), is(80 - attacker.getDamage()));
    }

    @Test
    public void shouldReturnWhoAttackVictimAndHowMuchVictimBleedAndHowMuchBloodLeft() {
        Player attacker = new Player("张三", 100, 10);
        Player victim = new Player("李四", 80, 20);

        assertThat(attacker.attack(victim), is("张三攻击了李四，李四受到了10点伤害，李四剩余生命：70"));
    }

    @Test
    public void shouldAliveWhenBloodIs0() {
        Player survivor = new Player("王二麻子", 0, 0);

        assertThat(survivor.isAlive(), is(true));
    }

    @Test
    public void shouldAliveWhenBloodGreatThan0() {
        Player survivor = new Player("王二麻子", 1, 0);

        assertThat(survivor.isAlive(), is(true));
    }

    @Test
    public void shouldDeadWhenBloodLessThan0() {
        Player dead = new Player("王二麻子", -1, 0);

        assertThat(dead.isAlive(), is(false));
    }
}