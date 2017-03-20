package com.samegame.util;

import com.samegame.Screen.AbstractScreen;
import com.samegame.Screen.GameScreen;
import com.samegame.Screen.LevelSelectScreen;
import com.samegame.Screen.MainMenuScreen;
import com.samegame.Screen.GameScreen;

public enum ScreenEnum {
	
	MAIN_MENU {
		public AbstractScreen getScreen(Object... params) {
			return new MainMenuScreen();
		}
	},
	
	LEVEL_SELECT {
		public AbstractScreen getScreen(Object... params) {
			return new LevelSelectScreen();
		}
	},
	
	GAME {
		public AbstractScreen getScreen(Object... params) {
			return new GameScreen((Integer) params[0]);
		}
	};
	
	public abstract AbstractScreen getScreen(Object... params);
}
