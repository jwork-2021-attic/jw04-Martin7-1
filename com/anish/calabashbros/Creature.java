package com.anish.calabashbros;

import java.awt.Color;

public class Creature extends Thing {

    Creature(Color color, char glyph, World world) {
        super(color, glyph, world);
    }

    /**
     * 移动某个生物，如果移动成功则返回true
     * @param xPos x坐标
     * @param yPos y坐标
     * @return true if move successfully, false otherwise.
     */
    public boolean moveTo(int xPos, int yPos) {
        this.world.put(this, xPos, yPos);
        if (this.getX() != xPos || this.getY() != yPos) {
            return false;
        }

        return true;
    }

}
