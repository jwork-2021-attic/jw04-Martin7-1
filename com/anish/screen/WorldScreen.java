package com.anish.screen;

import java.awt.event.KeyEvent;

import com.anish.monsters.God;
import com.anish.monsters.QuickSorter;
import com.anish.monsters.Monster;
import com.anish.monsters.World;

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

        this.monsters = god.createMonsters(8, 8);
        god.setPositions(monsters);

        QuickSorter<Monster> quickSorter = new QuickSorter<>(Monster.class);
        quickSorter.load(monsters);
        quickSorter.sort();

        sortSteps = this.parsePlan(quickSorter.getPlan());
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
