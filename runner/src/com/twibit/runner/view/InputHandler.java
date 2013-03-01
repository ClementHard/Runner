package com.twibit.runner.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.twibit.runner.model.IWorld;

/**
 * Handle the inputs of the game.
 */
public class InputHandler implements InputProcessor {

	private IWorld world;

	public InputHandler(IWorld world) {
		this.world = world;
		this.world.setInput(this);
	}

	public static final int UP = 0;
	public static final int DOWN = 1;
	public static final int LEFT = 2;
	public static final int RIGHT = 3;

	public static final int JUMP = 4;
	public static final int SHOOT = 5;

	public static final int ESCAPE = 6;

	public boolean[] buttons = new boolean[64];
	public boolean[] oldButtons = new boolean[64];

	public void set(int key, boolean down) {
		int button = -1;

		if (key == Keys.DPAD_UP)
			button = UP;
		if (key == Keys.DPAD_LEFT)
			button = LEFT;
		if (key == Keys.DPAD_DOWN)
			button = DOWN;
		if (key == Keys.DPAD_RIGHT)
			button = RIGHT;

		if (key == Keys.Y)
			button = JUMP;
		if (key == Keys.Z)
			button = JUMP;
		if (key == Keys.X)
			button = SHOOT;
		if (key == Keys.C)
			button = JUMP;
		if (key == Keys.A)
			button = JUMP;
		if (key == Keys.S)
			button = SHOOT;
		if (key == Keys.D)
			button = JUMP;

		if (key == Keys.ESCAPE || key == Keys.MENU)
			button = ESCAPE;

		if (button >= 0) {
			buttons[button] = down;
		}
	}

	public void releaseAllKeys() {
		for (int i = 0; i < buttons.length; i++) {
			if (i == UP || i == DOWN)
				continue;
			buttons[i] = false;
		}
	}

	@Override
	public boolean keyDown(int keycode) {
		set(keycode, true);
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		set(keycode, false);
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		x = (int) (x / (float) Gdx.graphics.getWidth() * 320);
		if (x > 160 - 32 && x < 160) {
			set(Keys.DPAD_UP, !buttons[UP]);
			if (buttons[UP])
				buttons[DOWN] = false;
		}
		if (x > 160 && x < 160 + 32) {
			set(Keys.DPAD_DOWN, !buttons[DOWN]);
			if (buttons[DOWN])
				buttons[UP] = false;
		}
		System.out.println("buttons: " + buttons[UP] + ", " + buttons[DOWN]);
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
