package com.anish.screen;

import java.awt.event.KeyEvent;

import com.anish.calabashbros.Calabash;
import com.anish.calabashbros.World;
import com.anish.maze.generator.MazeGenerator;
import com.anish.maze.generator.Maze;
import com.anish.maze.generator.CalabashLeader;
import com.anish.calabashbros.Floor;
import com.anish.calabashbros.God;

import asciiPanel.AsciiPanel;

public class WorldScreen implements Screen {

    private World world;
    private Maze maze;
    private Calabash calabash;
    private int countStep;
    private God god;
    private CalabashLeader leader;
    private boolean isNeedLead;

    public WorldScreen(boolean isNeedLead) {
        // boolean判断是否是自动引导
        this.isNeedLead = isNeedLead;
        MazeGenerator mazeGenerator = new MazeGenerator(World.WIDTH);
        mazeGenerator.generateMaze();
        this.maze = new Maze(mazeGenerator.getMaze());

        world = new World(this.maze);
        god = God.getGod(this.world);
        countStep = 0;

        // 获得葫芦娃
        // 放置葫芦娃到世界中
        calabash = god.createCalabashBro();
        god.setPositions(calabash, 0, 0);
        if (isNeedLead) {
            leader = new CalabashLeader(this.maze, this.calabash, this.world);
        }
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
        if (this.leader != null) {
            leader.startLead();
            while (!leader.getExit()) {
                leader.execute();
                countStep++;
            }
            
            return new WinScreen(countStep);
        }
        int keyCode = key.getKeyCode();
        int x = calabash.getX();
        int y = calabash.getY();
        Boolean moveSuccessfully = false;
        int curX = x,curY = y;
        if (x == World.WIDTH - 1 && y == World.HEIGHT - 1) {
            return new WinScreen(countStep);
        }
        switch(keyCode){
            case KeyEvent.VK_LEFT:
                if (maze.isRoad(curX - 1, curY)) {
                    moveSuccessfully = calabash.moveTo(x-1,y);
                    curX = x - 1;
                }
                break;
            case KeyEvent.VK_RIGHT:
                if (maze.isRoad(curX + 1, curY)) {
                    moveSuccessfully = calabash.moveTo(x+1, y);
                    curX = x + 1;
                }
                break;
            case KeyEvent.VK_UP:
                if (maze.isRoad(curX, curY - 1)) {
                    moveSuccessfully = calabash.moveTo(x, y-1);
                    curY = y - 1;
                }
                break;
            case KeyEvent.VK_DOWN:
                if (maze.isRoad(curX, curY + 1)) {
                    moveSuccessfully = calabash.moveTo(x, y+1);
                    curY = y + 1;
                }
                break;
            default:
        }

        if(moveSuccessfully){
            world.put(new Floor(world, true), x, y);
            countStep++;
        }
        
        return this;
    }

}
