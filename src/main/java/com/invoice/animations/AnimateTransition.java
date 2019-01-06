package com.invoice.animations;

import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;



/*
 *Author: Shavar Litchmore
 * Date: 01-02-19
 * This class will be used to preload a new scene
 * and also will be responsible to perform an animation when
 * changing the scene.
 *
 */
public class AnimateTransition extends Parent {
    private int w;
    private int h;
    private Rectangle bg;

    public AnimateTransition(int w, int h) {
        this.w = w;
        this.h = h;
        bg = new Rectangle(w, h, Color.color(0.2, 0.2, 0.2, 0.75));

        getChildren().add(bg);
    }

    public void animate() {


    }
}
