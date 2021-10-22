package com.anish.maze.generator;

import com.anish.calabashbros.Calabash;
import com.anish.calabashbros.World;
import com.anish.calabashbros.Floor;

import java.util.LinkedList;

/**
 * dfs自动引导葫芦娃走向终点
 */
public class CalabashLeader {

    private Maze maze;
    private int[][] visited;
    private Calabash bro;
    private World world;
    private LinkedList<Link> plans;
    private LinkedList<Link> temp;
    private boolean findSolution = false;

    public CalabashLeader(Maze maze, Calabash bro, World world) {
        this.maze = maze;
        this.bro = bro;
        this.world = world;
        visited = new int[maze.getDim()][maze.getDim()];
        plans = new LinkedList<>();
        temp = new LinkedList<>();
    }

    public void startLead() {
        generateDfsPlan();
    }

    private void generateDfsPlan() {
        findSolution = false;
        dfs(bro.getX(), bro.getY());
    }

    @SuppressWarnings("unchecked")
    private void dfs(int curX,int curY){
        if(curX == World.WIDTH-1 && curY == World.HEIGHT - 1){
            if(!findSolution){
                plans = (LinkedList<Link>) temp.clone();
                findSolution = true;
            }
            return;
        }

        if (curX <= 0 || curX >= World.WIDTH || curY <= 0 || curY >= World.HEIGHT) {
            return;
        }

        if (!maze.isRoad(curX, curY)) {
            return;
        }

        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};
        for(int i = 0; i < 4; i++) {
            int x = curX + dx[i];
            int y = curY + dy[i];

            if (x <= 0 || x >= World.WIDTH || y <= 0 || y >= World.HEIGHT) {
                return;
            }
    
            if (!maze.isRoad(x, y)) {
                return;
            } 

            if(visited[x][y] == 0) {
                Link link = new Link(new Node(curX, curY), new Node(x,y));
                temp.add(link);
                visited[x][y] = 1;
                dfs(x, y);
                visited[x][y] = 0;
                temp.remove(link);
            }
        }
    }

    /**
     * 执行移动
     */
    public boolean execute() {
        Link curLink = plans.poll();
        Node dest = curLink.getDest();
        Node src = curLink.getSrc();

        this.bro.moveTo(dest.x, dest.y);
        world.put(new Floor(world, true), src.x, src.y);

        if (dest.x == World.WIDTH - 1 && dest.y == World.HEIGHT - 1) {
            return true;
        }

        return false;
    }
    
}
