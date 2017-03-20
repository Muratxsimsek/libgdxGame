package com.samegame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by murat.simsek on 2/23/2017.
 */
public class GameManager {

    private Element[][] elementList;
    private GameScreen gameScreen;
    private static final int GRID_CELL = 128;
    private static final int WORLD_WIDTH = GRID_CELL*((int)(Gdx.graphics.getWidth()*0.45)/GRID_CELL);
    private static final int WORLD_HEIGHT = GRID_CELL*((int)(Gdx.graphics.getHeight()*0.45)/GRID_CELL);
    private List<Element> sameElementList = new ArrayList<Element>();

    GameManager(){
        initialize();
    }

    void initialize(){

        this.elementList = this.createElements();
        this.gameScreen = createGameScreen(this.elementList);
        this.gameScreen.setGRID_CELL(this.GRID_CELL);
        this.gameScreen.setWORLD_WIDTH(this.WORLD_WIDTH);
        this.gameScreen.setWORLD_HEIGHT(this.WORLD_HEIGHT);

        this.gameScreen.setBackgroundTexture(new Texture(Gdx.files.internal("bg.png")), this.WORLD_WIDTH, this.WORLD_HEIGHT);

    }



    private GameScreen createGameScreen(Element[][] elementList){
        return new GameScreen(elementList);
    }

   private void getAllSameElements(Element element){

       if(!sameElementList.contains(element)) {
           sameElementList.add(element);
           element.setIsSelected(true);

           for (Element sameElement : getSameElements(element)) {
               getAllSameElements(sameElement);
           }
       }

   }

   private  List<Element> getSameElements(Element element){
       List<Element> foundElements = new ArrayList<Element>();
       int i=element.getI();
       int j=element.getJ();
       int ii,jj;

       //up
       ii=i-1;
       jj=j;
       if(ii>=0 && this.elementList[ii][jj]!=null && this.elementList[ii][jj].getFileHandle() == element.getFileHandle()){
           foundElements.add(this.elementList[ii][jj]);
       }
       //down
       ii=i+1;
       jj=j;
       if(ii<WORLD_HEIGHT/GRID_CELL && this.elementList[ii][jj]!=null && this.elementList[ii][jj].getFileHandle() == element.getFileHandle()){
           foundElements.add(this.elementList[ii][jj]);
       }
       //right
       ii=i;
       jj=j+1;
       if(jj<WORLD_WIDTH/GRID_CELL && this.elementList[ii][jj]!=null && this.elementList[ii][jj].getFileHandle() == element.getFileHandle()){
           foundElements.add(this.elementList[ii][jj]);
       }
       //left
       ii=i;
       jj=j-1;
       if(jj>=0 && this.elementList[ii][jj]!=null && this.elementList[ii][jj].getFileHandle() == element.getFileHandle()){
           foundElements.add(this.elementList[ii][jj]);
       }


       return foundElements;
   }


    public Element[][] getElementList() {
        return elementList;
    }


    public void setElementList(Element[][] elementList) {
        this.elementList = elementList;
    }

    public GameScreen getGameScreen() {
        return gameScreen;
    }

    public void setGameScreen(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
    }

    private Element[][] createElements(){
        Element[][] elementList= new Element[WORLD_HEIGHT/GRID_CELL][WORLD_WIDTH/GRID_CELL];
//        int x=0,y;
        for (int j = 0; j < WORLD_WIDTH/GRID_CELL; j++) {
//            y=WORLD_HEIGHT-GRID_CELL;
            for (int i = 0; i < WORLD_HEIGHT/GRID_CELL; i++) {
                elementList[i][j] = new Element(new Texture(Enumarations.ElementFile.getRandom().getValue()));
                elementList[i][j].setPosition(i, j);
//                y-=GRID_CELL;

                elementList[i][j].addListener(new ElementGestureListener(this));
                //elementList[i][j].addAction(new BlinkAction(0.3f));
                elementList[i][j].setI(i);
                elementList[i][j].setJ(j);

            }
//            x+=GRID_CELL;
        }

        return elementList;
    }

    private void shiftColumnToLeft(){
        List<Integer> newColumns = new ArrayList<Integer>();

        for(int j=0;j<WORLD_WIDTH/GRID_CELL;j++ ){
            if(this.elementList[WORLD_HEIGHT/GRID_CELL-1][j]!=null){
                newColumns.add(j);
//                for(int jj=j+1;jj<WORLD_WIDTH/GRID_CELL;jj++){
//
//                    for(int i=0;i<WORLD_HEIGHT/GRID_CELL;i++){
//
//                        updateElementPosition(this.elementList[i][jj],i,j);
//                    }
//
////                    clearColumn(jj);
            }
        }

        int columnNumber=0;
        for(Integer j : newColumns){

            for(int i=0;i<WORLD_HEIGHT/GRID_CELL;i++){
                this.elementList[i][columnNumber]=this.elementList[i][j];
                updateElementPosition(this.elementList[i][j],i,columnNumber);
            }
            columnNumber++;
        }

        for(int j=columnNumber;j<WORLD_WIDTH/GRID_CELL;j++){
            clearColumn(j);
        }

    }

    private void print(){
        int x=0;
        for(int i=0;i<WORLD_HEIGHT/GRID_CELL;i++){
            for(int j=0;j<WORLD_WIDTH/GRID_CELL;j++){
                x=0;
                if(this.elementList[i][j]!=null && this.elementList[i][j].getFileHandle() == Enumarations.ElementFile.ORANGE.getValue()){
                    x=1;
                }
                if(this.elementList[i][j]!=null && this.elementList[i][j].getFileHandle() == Enumarations.ElementFile.RED.getValue()){
                    x=2;
                }
                if(this.elementList[i][j]!=null && this.elementList[i][j].getFileHandle() == Enumarations.ElementFile.YELLOW.getValue()){
                    x=3;
                }
                System.out.print(x);
            }
            System.out.println();
        }
        System.out.println();
    }


    private  void clearColumn(int columnNumber){
        for(int i=WORLD_HEIGHT/GRID_CELL-1;i>=0;i--){
            if(this.elementList[i][columnNumber]!=null) {
                clearElement(i,columnNumber);
            }
        }
    }

    private  void clearElement(int i,int j){

        if(this.elementList[i][j]!=null) {
//            ((Actor) this.elementList[i][j]).remove();// =null;
//            this.elementList[i][j].setVisible(false);
            this.elementList[i][j] = null;
        }

    }

    private void getDownColumn(int columnNumber){

        List<Integer> newColumns = new ArrayList<Integer>();


        for(int i=WORLD_HEIGHT/GRID_CELL-1;i>=0;i--){

            if(this.elementList[i][columnNumber]!=null){
                newColumns.add(i);
            }
        }



        int i=WORLD_HEIGHT/GRID_CELL-1;
        for(Integer ii : newColumns){
            this.elementList[i][columnNumber]=this.elementList[ii][columnNumber];
            updateElementPosition(this.elementList[ii][columnNumber],i,columnNumber);
            i--;
        }

        for(int ii=i;ii>=0;ii--){
            clearElement(ii, columnNumber);
        }


    }

    private void updateElementPosition(Element element,int i,int j){
        if(element!=null){
            element.setPosition(i,j);
        }
    }


    private void clearSameElementList(){
        for(Element sameElement : this.sameElementList){
            sameElement.clearActions();
            sameElement.setVisible(true);
            sameElement.setIsSelected(false);
        }
        sameElementList.clear();
    }

    public void setTapped(Element element){

        //sameElementList.add(element);
        //getSameElements(element);

//        if(!element.isSelected()){
            clearSameElementList();
            getAllSameElements(element);

//            if(this.sameElementList.size()>1) {
//                for (Element sameElement : this.sameElementList) {
//                    sameElement.addAction(new BlinkAction(0.1f));
//                }
//            }
//        }
//        else{
            if(this.sameElementList.size()>1) {
                for (Element sameElement : this.sameElementList) {

                    ((Actor) this.elementList[sameElement.getI()][sameElement.getJ()]).remove();// =null;
                    this.elementList[sameElement.getI()][sameElement.getJ()] = null;

                }
                print();
                for(Element sameElement : this.sameElementList){
                    getDownColumn(sameElement.getJ());
                }
                print();
                shiftColumnToLeft();
                print();
            }


//        }
////        Skin uiSkin = new Skin();
//        //Skin skin = new Skin(Gdx.files.internal("uiskin.json"));
//        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("uiskin.json"));
//        Skin skin = new Skin(atlas);
//        new Dialog("Some Dialog", skin, "dialog") {
//            protected void result (Object object) {
//                System.out.println("Chosen: " + object);
//            }
//        }.text("Are you enjoying this demo?").button("Yes", true).button("No", false).key(Input.Keys.ENTER, true)
//                .key(Input.Keys.ESCAPE, false).show(this.getGameScreen().getStage());


    }



    public static int getWorldWidth() {
        return WORLD_WIDTH;
    }

    public static int getWorldHeight() {
        return WORLD_HEIGHT;
    }

    public static int getGridCell() {
        return GRID_CELL;
    }
}
