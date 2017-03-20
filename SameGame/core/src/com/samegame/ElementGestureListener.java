package com.samegame;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;

/**
 * Created by murat.simsek on 2/23/2017.
 */
public class ElementGestureListener extends ActorGestureListener {

    GameManager gameManager;

    @Override
    public void tap(InputEvent event, float x, float y, int count, int button) {
        super.tap(event, x, y, count, button);

        System.out.println("tap Down");
        //this.element.setVisible(false);
        ;
        gameManager.setTapped((Element) getTouchDownTarget());

//
//        if(this.element.getActions().size==0){
//            this.element.addAction(new BlinkAction(0.3f));
//        }

    }

    public ElementGestureListener(GameManager gameManager) {
        this.gameManager = gameManager;
    }


}
