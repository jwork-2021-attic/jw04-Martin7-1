package com.anish.maze.generator;

import com.anish.calabashbros.World;

public class Maze {

    private int[][] tiles;

    public Maze(int[][] tiles) {
        this.tiles = tiles;
    }

    public int[][] getMaze() {
        return this.tiles;
    }

    /**
     * 返回迷宫的某一个位置是不是路
     * @param xIndex x坐标
     * @param yIndex y坐标
     * @return true if the element is road, false otherwise.
     */
    public boolean isRoad(int xIndex, int yIndex) {
        if (xIndex >= World.WIDTH || yIndex >= World.HEIGHT) {
            return false;
        }
        
        if (tiles[xIndex][yIndex] == 1) {
            return true;
        }

        return false;
    }

    public int getDim() {
        return this.tiles.length;
    }
    
}
