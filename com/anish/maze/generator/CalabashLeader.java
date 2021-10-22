package com.anish.maze.generator;

import com.anish.calabashbros.Calabash;
import com.anish.calabashbros.World;

/**
 * dfs自动引导葫芦娃走向终点
 */
public class CalabashLeader {

    Maze maze;
    Calabash bro;
    World world;

    public CalabashLeader(Maze maze, Calabash bro, World world) {
        this.maze = maze;
        this.bro = bro;
        this.world = world;
    }

    public void startLead() {
        dfsMaze(bro.getX(), bro.getY());
    }

    /**
     * dfs解决迷宫问题
     * @param xPos
     * @param yPos
     */
    private void dfsMaze(int xPos, int yPos) {
        int[][] intMaze = changeMaze(maze);
        // 1表示通路，0表示死路
        int dim = intMaze.length;

        if (xPos < 0 || yPos < 0) {
            return;
        }
        if (xPos > dim - 1 || yPos > dim - 1 || intMaze[xPos][yPos] == 0) {
            // 无法前进的道路
            return;
        }


    }

    private int[][] changeMaze(Maze maze) {
        int[][] newMaze = new int[maze.getDim()][maze.getDim()];

        for (int i = 0; i < newMaze.length; i++) {
            for (int j = 0; j < newMaze.length; j++) {
                newMaze[i][j] = maze.isRoad(i, j) ? 1 : 0;
            }
        }

        return newMaze;
    }
    
}
