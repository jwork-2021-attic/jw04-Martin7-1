package com.anish.monsters;

import java.lang.reflect.Array;
import java.util.Random;
import java.awt.Color;

public class God {

    World world;

    private God(World world) {
        this.world = world;
    }

    public static God getGod(World world) {
        return new God(world);
    }

    /**
     * 上帝创建妖精（不用泛型是因为不会用泛型创建二维数组呜呜呜
     * @param row 妖精矩阵的行数
     * @param column 妖精矩阵的列数
     * @param world 妖精所在的世界
     * @return 创建好的妖精矩阵
     */
    public Monster[][] createMonsters(int row, int column, World world) {
        // 创造一个row行column列的生物矩阵
        // 赋予这个生物矩阵
        Monster[][] monsters = new Monster[row][column];
        Random random = new Random();
        int Rvalue = 0;
        int Gvalue = 0;
        int Bvalue = 0;
        int curIndex = 0;
        while (curIndex < 64) {
            Rvalue = random.nextInt(256);
            Gvalue = random.nextInt(256);
            Bvalue = random.nextInt(256);
            monsters[Math.floorDiv(curIndex, column)][curIndex % column] = new Monster(new Color(Rvalue, Gvalue, Bvalue), world);
        }

        return monsters;
    }

    /**
     * 设置生物的坐标
     * @param <T> 某一种生物
     * @param creatures 某一种生物的矩阵
     */
    public <T extends Creature> void setPositions(T[][] creatures) {
        // 设置monster在世界中的位置
        // 我们将64个怪物每一行放在13-27之间，两个怪物之间相隔的距离为2
        // 将64个怪物放在3-17之间
        int curX = 13;
        int curY = 3;
        for (int i = 0; i < creatures.length; i++) {
            curX = 13;
            for (int j = 0; j < creatures[0].length; j++) {
                this.world.put(creatures[i][j], curX, curY);
                curX += 2;
            }
            curY += 2;
        }
    }
    
}
