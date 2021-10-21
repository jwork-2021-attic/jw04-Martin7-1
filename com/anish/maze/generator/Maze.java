package com.anish.maze.generator;

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
        if (tiles[xIndex][yIndex] == 0) {
            return true;
        }

        return false;
    }
    
}
