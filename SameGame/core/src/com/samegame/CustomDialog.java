/*
package com.samegame;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

*/
/**
 * Created by murat.simsek on 2/26/2017.
 *//*

public class CustomDialog extends Dialog {

    public CustomDialog (String title) {
        super(title, Asset);
        initialize();
    }

    private void initialize() {
        padTop(60); // set padding on top of the dialog title
        getButtonTable().defaults().height(60); // set buttons height
        setModal(true);
        setMovable(false);
        setResizable(false);
    }

    @Override
    public CustomDialog text(String text) {
        super.text(new Label(text, Assets.skin, "medium-green"));
        return this;
    }

    */
/**
     * Adds a text button to the button table.
     * @param listener the input listener that will be attached to the button.
     *//*

    public CustomDialog button(String buttonText, InputListener listener) {
        TextButton button = new TextButton(buttonText, Assets.skin);
        button.addListener(listener);
        button(button);
        return this;
    }

    @Override
    public float getPrefWidth() {
        // force dialog width
        return 480f;
    }

    @Override
    public float getPrefHeight() {
        // force dialog height
        return 240f;
    }
}*/
