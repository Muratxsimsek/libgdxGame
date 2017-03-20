package com.samegame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by murat.simsek on 2/22/2017.
 */
public class GameScreen extends ScreenAdapter {

    private Viewport viewport;
    private Camera camera;

    private ShapeRenderer shapeRenderer;
    private SpriteBatch batch;

    private Stage stage;
    private Texture backgroundTexture;
    Element[][] elementList;
    private int GRID_CELL;
    private int WORLD_WIDTH;
    private int WORLD_HEIGHT;
    private Image background;

    GameScreen(Element[][] elementList){

        this.elementList=elementList;
        shapeRenderer = new ShapeRenderer();

//        Gdx.gl.glClearColor(0, 0, 0, 1);
//        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

//        //batch = new SpriteBatch();
//
        camera = new OrthographicCamera(this.WORLD_WIDTH+100,
                this.WORLD_HEIGHT+100);
        camera.position.set(this.WORLD_WIDTH / 2, this.WORLD_HEIGHT / 2f, 0);
        camera.update();
        viewport = new FitViewport(this.WORLD_WIDTH, this.WORLD_HEIGHT, camera);
        stage = new Stage(new FitViewport(this.WORLD_WIDTH, this.WORLD_HEIGHT));

        if(background!=null)
            stage.addActor(background);

        Gdx.input.setInputProcessor(stage);


    }

    public Stage getStage() {
        return stage;
    }

    public int getGRID_CELL() {
        return GRID_CELL;
    }

    public void setGRID_CELL(int GRID_CELL) {
        this.GRID_CELL = GRID_CELL;
    }

    public int getWORLD_WIDTH() {
        return WORLD_WIDTH;
    }

    public void setWORLD_WIDTH(int WORLD_WIDTH) {
        this.WORLD_WIDTH = WORLD_WIDTH;
    }

    public int getWORLD_HEIGHT() {
        return WORLD_HEIGHT;
    }

    public void setWORLD_HEIGHT(int WORLD_HEIGHT) {
        this.WORLD_HEIGHT = WORLD_HEIGHT;
    }

    @Override
    public void show() {
//        Gdx.gl.glClearColor(0, 0, 0, 1);
//        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//        shapeRenderer = new ShapeRenderer();
        //batch = new SpriteBatch();

//        camera = new OrthographicCamera();
//
////        camera.update();
//        viewport = new FitViewport(this.WORLD_WIDTH, this.WORLD_HEIGHT, camera);
//        camera.position.set(this.WORLD_WIDTH / 2, this.WORLD_HEIGHT / 2f, 0);
       stage = new Stage(new FitViewport(this.WORLD_WIDTH, this.WORLD_HEIGHT));
        if(background!=null)
            stage.addActor(background);
       stage.draw();

        Gdx.input.setInputProcessor(stage);

        drawElements(this.elementList);
    }

    public void setBackgroundTexture(Texture backgroundTexture,int width,int height) {

        this.background = new Image(backgroundTexture);
        background.setBounds(0, 0, width, height);
//        if(stage==null)
//            stage = new Stage(new FitViewport(this.WORLD_WIDTH, this.WORLD_HEIGHT));
//
//        stage.addActor(background);
    }

    @Override
    public void render(float delta) {


        //stage.act(delta);

        stage.draw();
        drawGrid(this.GRID_CELL);

        stage.act(Gdx.graphics.getDeltaTime());

//        stage.getBatch().begin();
//        stage.getBatch().draw(backgroundTexture, 0, 0, this.WORLD_WIDTH, this.WORLD_HEIGHT);



    }


    private void drawGrid(int GRID_CELL) {



        shapeRenderer.setProjectionMatrix(stage.getBatch().getProjectionMatrix());
        shapeRenderer.setTransformMatrix(stage.getBatch().getTransformMatrix());
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);

        shapeRenderer.setColor(Color.BLACK);
        for (int x = 0; x < this.WORLD_WIDTH; x += GRID_CELL) {

            for (int y = 0; y < this.WORLD_HEIGHT; y += GRID_CELL) {

                shapeRenderer.rect(x, y, GRID_CELL, GRID_CELL);

            }

        }
        shapeRenderer.end();

    }



    private void drawElements(Element[][] elementList) {


        for (int j = 0; j < this.WORLD_WIDTH/this.GRID_CELL; j++) {

            for (int i = 0; i < this.WORLD_HEIGHT/this.GRID_CELL; i++) {
                if(elementList[i][j]!=null){
                    drawElement(elementList[i][j]);
                }


            }


        }

    }

    private void drawElement(Element element) {
//        stage.getBatch().setProjectionMatrix(camera.projection);
//        stage.getBatch().setTransformMatrix(camera.view);
//
//
//        stage.getBatch().begin();
        stage.addActor(element);
//        stage.getBatch().draw(element, element.getX(), element.getY());
//
//        stage.getBatch().end();
    }

}
