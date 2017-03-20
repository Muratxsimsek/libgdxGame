package com.samegame;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.FileTextureData;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;


/**
 * Created by murat.simsek on 2/22/2017.
 */
public class Element extends Image{

    private FileHandle fileHandle;

    private int i,j;

    private boolean isSelected;




    Element(Texture texture) {
        super(texture);
        fileHandle = ((FileTextureData)texture.getTextureData()).getFileHandle();


    }

    @Override
    public void setPosition(float i, float j) {
        super.setPosition(j*GameManager.getGridCell(), GameManager.getWorldHeight()-GameManager.getGridCell()-i*GameManager.getGridCell());
        setI((int) i);
        setJ((int) j);
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setIsSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    public FileHandle getFileHandle() {
        return fileHandle;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public int getJ() {
        return j;
    }

    public void setJ(int j) {
        this.j = j;
    }

    @Override
    public boolean addListener(EventListener listener) {
        return super.addListener(listener);
    }

    @Override
    public void addAction(Action action) {
        super.clearActions();
        super.addAction(action);
    }


}
