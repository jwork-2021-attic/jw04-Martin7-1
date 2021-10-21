package com.anish.calabashbros;

import com.anish.maze.generator.Maze;

public class World {

    public static final int WIDTH = 40;
    public static final int HEIGHT = 40;

    private Tile<Thing>[][] tiles;

    @SuppressWarnings("unchecked")
    public World(Maze maze) {
        // 将maze放到tiles里面
        tiles = new Tile[WIDTH][HEIGHT];
        
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                tiles[i][j] = new Tile<>(i, j);
                if (maze.isRoad(i, j)) {
                    tiles[i][j].setThing(new Floor(this));
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    public World() {

        if (tiles == null) {
            tiles = new Tile[WIDTH][HEIGHT];
        }

        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                tiles[i][j] = new Tile<>(i, j);
                tiles[i][j].setThing(new Floor(this));
            }
        }
    }

    public Thing get(int x, int y) {
        return this.tiles[x][y].getThing();
    }

    public void put(Thing t, int x, int y) {
        this.tiles[x][y].setThing(t);
    }

}
