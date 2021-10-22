package com.anish.calabashbros;

import java.awt.Color;

public class Floor extends Thing {

    public Floor(World world, boolean isReach) {
        super(Color.gray, (char) 250, world);
        if (isReach) {
            setColor(Color.yellow);
        }
    }

}
