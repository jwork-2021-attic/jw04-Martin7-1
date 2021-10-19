package com.anish.screen;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.Random;

import com.anish.monsters.BubbleSorter;
import com.anish.monsters.Calabash;
import com.anish.monsters.God;
import com.anish.monsters.SelectionSorter;
import com.anish.monsters.Monster;
import com.anish.monsters.World;
import com.anish.util.Dimension;

import asciiPanel.AsciiPanel;

public class WorldScreen implements Screen {

    private World world;
    // private Calabash[] bros;
    String[] sortSteps;
    // 世界需要一个神
    God god;
    private Monster[][] monsters;
    

    public WorldScreen() {
        world = new World();
        // 世界需要一个神
        god = God.getGod(this.world);

        this.monsters = god.createMonsters(8, 8, world);

        // bros = new Calabash[7];

        // bros[3] = new Calabash(new Color(204, 0, 0), 1, world);
        // bros[5] = new Calabash(new Color(255, 165, 0), 2, world);
        // bros[1] = new Calabash(new Color(252, 233, 79), 3, world);
        // bros[0] = new Calabash(new Color(78, 154, 6), 4, world);
        // bros[4] = new Calabash(new Color(50, 175, 255), 5, world);
        // bros[6] = new Calabash(new Color(114, 159, 207), 6, world);
        // bros[2] = new Calabash(new Color(173, 127, 168), 7, world);

        // world.put(bros[0], 10, 10);
        // world.put(bros[1], 12, 10);
        // world.put(bros[2], 14, 10);
        // world.put(bros[3], 16, 10);
        // world.put(bros[4], 18, 10);
        // world.put(bros[5], 20, 10);
        // world.put(bros[6], 22, 10);

        monsters = new Monster[8][8];
        god.setPositions(monsters);

        SelectionSorter<Monster> insertionSorter = new SelectionSorter<>(Monster.class);
        insertionSorter.load(monsters);
        insertionSorter.sort();

        // BubbleSorter<Calabash> b = new BubbleSorter<>();
        // b.load(bros);
        // b.sort();

        // sortSteps = this.parsePlan(b.getPlan());
        sortSteps = this.parsePlan(insertionSorter.getPlan());
    }

    private String[] parsePlan(String plan) {
        return plan.split("\n");
    }

    private void execute(Monster[][] monsters, String step) {
        String[] couple = step.split("<->");
        getMonsterByRank(monsters, Integer.parseInt(couple[0])).swap(getMonsterByRank(monsters, Integer.parseInt(couple[1])));
    }

    private Monster getMonsterByRank(Monster[][] monsters, int rank) {
        for (int i = 0; i < monsters.length; i++) {
            for (int j = 0; j < monsters[0].length; j++) {
                if (monsters[i][j].getRank() == rank) {
                    return monsters[i][j];
                }
            }
        }
        return null;
    }

    @Override
    public void displayOutput(AsciiPanel terminal) {

        for (int x = 0; x < World.WIDTH; x++) {
            for (int y = 0; y < World.HEIGHT; y++) {

                terminal.write(world.get(x, y).getGlyph(), x, y, world.get(x, y).getColor());

            }
        }
    }

    int i = 0;

    @Override
    public Screen respondToUserInput(KeyEvent key) {

        if (i < this.sortSteps.length) {
            this.execute(this.monsters, sortSteps[i]);
            i++;
        }

        return this;
    }

}
