package com.samegame;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.actions.TemporalAction;

public class BlinkAction extends TemporalAction
{
    private Color baseColor;
    private Color blinkColor;
    private boolean done;
    private float interval;
    private float timeLeftInInterval;
    private boolean isShowingBase;


    public BlinkAction(float interval)
    {
       this.interval=interval;
    }

    @Override
    public boolean act(float delta)
    {
        super.act(delta);

        timeLeftInInterval -= delta;
        if(timeLeftInInterval <= 0)
        {
            if(getActor().isVisible()){
                getActor().setVisible(false);
            }
            else{
                getActor().setVisible(true);
            }

            timeLeftInInterval = interval;

        }

        return false;
    }

    @Override
    protected void update(float percent)
    {
        if(percent >= 1)
        {
            this.done = true;
        }

    }

}