package com.anish.maze.generator;

public class Maze {

    private int[][] tiles;

    public Maze(int[][] tiles) {
        this.tiles = tiles;
    }

    public int[][] getMaze() {
        return this.tiles;
    }
    
}
