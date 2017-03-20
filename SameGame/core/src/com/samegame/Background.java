package com.samegame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 * Created by murat.simsek on 2/23/2017.
 */
public class Background extends Actor {

    private Image backgroundImage;

    public Background(Image backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
    }
}