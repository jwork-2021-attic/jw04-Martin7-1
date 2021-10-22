package com.anish.screen;

import java.awt.Color;
import java.awt.event.KeyEvent;

import com.anish.calabashbros.Calabash;
import com.anish.calabashbros.World;
import com.anish.maze.generator.MazeGenerator;
import com.anish.maze.generator.Maze;
import com.anish.calabashbros.Floor;

import asciiPanel.AsciiPanel;

public class WorldScreen implements Screen {

    private World world;
    private Maze maze;
    private Calabash calabash;
    private int countStep;

    public WorldScreen() {
        MazeGenerator mazeGenerator = new MazeGenerator(World.WIDTH);
        mazeGenerator.generateMaze();
        this.maze = new Maze(mazeGenerator.getMaze());

        world = new World(this.maze);
        countStep = 0;

        // 获得葫芦娃
        // 放置葫芦娃到世界中
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
        boolean autoMove = false;
        int keyCode = key.getKeyCode();
        int x = calabash.getX();
        int y = calabash.getY();
        Boolean moveSuccessfully = false;
        Boolean reachExit = false;
        int curX = x,curY = y;
        switch(keyCode){
            case KeyEvent.VK_LEFT:
                moveSuccessfully = calabash.moveTo(x-1,y);
                curX = x - 1;
                autoMove = false;
                break;
            case KeyEvent.VK_RIGHT:
                moveSuccessfully = calabash.moveTo(x+1, y);
                curX = x + 1;
                autoMove = false;
                break;
            case KeyEvent.VK_UP:
                moveSuccessfully = calabash.moveTo(x, y-1);
                curY = y - 1;
                autoMove = false;
                break;
            case KeyEvent.VK_DOWN:
                moveSuccessfully = calabash.moveTo(x, y+1);
                curY = y + 1;
                autoMove = false;
                break;   
            case KeyEvent.VK_ENTER:
                // TODO
                // auto move 
                if(autoMove == false){
                    autoMove = true;
                    // autoRouter.startAutoDrive();
                }
                // reachExit = autoRouter.execute();
                countStep++;
        }
        if(moveSuccessfully){
            world.put(new Floor(world), x, y);
            countStep++;
        }
        if(curX == World.WIDTH && curY == World.HEIGHT)reachExit = true;
        if(reachExit){
            return new WinScreen(countStep);
        }
        return this;
    }

}
