package com.anish.screen;

import java.awt.Color;
import java.awt.event.KeyEvent;

import com.anish.calabashbros.BubbleSorter;
import com.anish.calabashbros.Calabash;
import com.anish.calabashbros.World;
import com.anish.mazegenerator.MazeGenerator;

import asciiPanel.AsciiPanel;

public class WorldScreen implements Screen {

    private World world;
    private int[][] maze;
    private Calabash calabash;

    public WorldScreen() {
        world = new World();
        MazeGenerator mazeGenerator = new MazeGenerator(World.WIDTH);
        mazeGenerator.generateMaze();
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
        int x = bro.getX();
        int y = bro.getY();
        Boolean moveSuccessfully = false;
        Boolean reachExit = false;
        int curX = x,curY = y;
        switch(keyCode){
            case KeyEvent.VK_LEFT:
                moveSuccessfully = bro.moveTo(x-1,y);
                curX = x - 1;
                autoMove = false;
                break;
            case KeyEvent.VK_RIGHT:
                moveSuccessfully = bro.moveTo(x+1, y);
                curX = x + 1;
                autoMove = false;
                break;
            case KeyEvent.VK_UP:
                moveSuccessfully = bro.moveTo(x, y-1);
                curY = y - 1;
                autoMove = false;
                break;
            case KeyEvent.VK_DOWN:
                moveSuccessfully = bro.moveTo(x, y+1);
                curY = y + 1;
                autoMove = false;
                break;   
            case KeyEvent.VK_ENTER:
                if(autoMove == false){
                    autoMove = true;
                    autoRouter.startAutoDrive();
                }
                reachExit = autoRouter.execute();
                counter++;
        }
        if(moveSuccessfully){
            world.put(new Floor(world),x,y);
            counter++;
        }
        if(curX == World.WIDTH && curY == World.HEIGHT)reachExit = true;
        if(reachExit){
            return new WinScreen(counter);
        }
        return this;
    }

}
