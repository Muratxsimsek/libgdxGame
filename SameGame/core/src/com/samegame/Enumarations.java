package com.samegame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import java.security.SecureRandom;
import java.util.Random;

/**
 * Created by murat.simsek on 2/22/2017.
 */
public class Enumarations {

    public enum ElementFile {
//        BLUE(Gdx.files.internal("Circle_Blue.png")),
//        GREEN(Gdx.files.internal("Circle_Green.png")),
        ORANGE(Gdx.files.internal("Circle_Orange.png")),
        RED(Gdx.files.internal("Circle_Red.png")),
//        GRAY(Gdx.files.internal("Circle_Grey.png")),
        YELLOW(Gdx.files.internal("Circle_Yellow.png"));

        private FileHandle value;

        private ElementFile(FileHandle value) {
            this.value = value;
        }
        public static ElementFile getRandom() {
            Random rand = new SecureRandom();
            return values()[rand.nextInt(values().length)];
        }
        public FileHandle getValue() {
            return value;
        }
    }
}
