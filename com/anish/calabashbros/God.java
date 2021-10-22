package com.anish.calabashbros;

import java.util.Random;
import java.awt.Color;
import java.lang.reflect.InvocationTargetException;

public class God {

    World world;

    private God(World world) {
        this.world = world;
    }

    public static God getGod(World world) {
        return new God(world);
    }

    /**
     * 生物的创建
     * @param <T> 某种生物的类别（class）
     * @param clazz 某种生物的类别（class）
     * @return 某种生物
     * @throws InstantiationException 
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     */
    public <T extends Creature> T createCreature(Class<T> clazz) throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        // 创建生物
        T creature;
        Color color = getRandomColor();

        // 泛型类的实例化？
        creature = clazz.getDeclaredConstructor().newInstance(color, this.world);
        // creature = new T(new Color(Rvalue, Gvalue, Bvalue), this.world);

        return creature;
    }

    public Calabash createCalabashBro() {
        Color color = getRandomColor();
        Calabash bro = new Calabash(color, this.world);

        return bro;
    }

    private Color getRandomColor() {
        Random random = new Random();
        int Rvalue = random.nextInt(256);
        int Gvalue = random.nextInt(256);
        int Bvalue = random.nextInt(256);

        return new Color(Rvalue, Gvalue, Bvalue);
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
        int curY = 2;
        for (int i = 0; i < creatures.length; i++) {
            curX = 13;
            for (int j = 0; j < creatures[0].length; j++) {
                this.world.put(creatures[i][j], curX, curY);
                curX += 2;
            }
            curY += 2;
        }
    }

    public <T extends Creature> void setPositions(T[][] creatures, int xPos, int yPos, int gap) {
        int curX = xPos;
        int curY = yPos;

        for (int i = 0; i < creatures.length; i++) {
            curX = xPos;
            for (int j = 0; j < creatures[0].length; j++) {
                this.world.put(creatures[i][j], curX, curY);
                curX += gap;
            }
            curY += gap;
        }
    } 

    public <T extends Creature> void setPositions(T creature, int xPos, int yPos) {
        this.world.put(creature, xPos, yPos);
    }
    
}

