package gameabstract;
/*
 * Author: Aaron Nech
 * Project: SJGF
 * Description: LibGDX bridge for abstract gameinput engine
 * 
 */
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;


public class GDXGameInput extends GameInput implements InputProcessor {
	private int cW, cH;
	private static final int TAP_PADDING = 8;
	
	//click watching variables
	private boolean touchDown;
	private float tx, ty;
	
	//constructs a new gameinput, setting LibGDX's default processor to this
	public GDXGameInput() {
		super();
		Gdx.input.setInputProcessor(this);
	}
	//sets the conversion factor for touch events so they report correctly
	public void setTouchContext(int width, int height) {
		cW = width;
		cH = height;
	}
	public boolean tap(int screenX, int screenY, int pointer, int button) {
		int[] passed = {(int) (screenX * ((float) cW / Gdx.graphics.getWidth())),
				(int) (screenY * ((float) cH / Gdx.graphics.getHeight())), pointer, button};
		add(new InputEvent("tap", passed));
		return true;
	}
	
	//bridge methods to libgdx input processor
	//all methods create a new input event to feed to the processor queue.
	public boolean keyDown(int keycode) {
		add(new InputEvent("keyDown", keycode));
		return true;
	}
	public boolean keyUp(int keycode) {
		add(new InputEvent("keyUp", keycode));
		return true;
	}
	public boolean keyTyped(char character) {
		add(new InputEvent("keyTyped", character));
		return true;
	}
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		int[] passed = {(int) (screenX * ((float) cW / Gdx.graphics.getWidth())),
				(int) (screenY * ((float) cH / Gdx.graphics.getHeight())), pointer, button};
		add(new InputEvent("touchDown", passed));
		tx = screenX;
		ty = screenY;
		touchDown = true;
		return true;
	}
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		int[] passed = {(int) (screenX * ((float) cW / Gdx.graphics.getWidth())),
				(int) (screenY * ((float) cH / Gdx.graphics.getHeight())), pointer, button};
		add(new InputEvent("touchUp", passed));
		if(touchDown) {
			if(tx <= screenX + TAP_PADDING && tx >= screenX - TAP_PADDING &&
					ty <= screenY + TAP_PADDING && ty >= screenY - TAP_PADDING)
				tap(screenX, screenY, pointer, button);
			touchDown = false;
		}
		return true;
	}
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		int[] passed = {(int) (screenX * ((float) cW / Gdx.graphics.getWidth())),
				(int) (screenY * ((float) cH / Gdx.graphics.getHeight())), pointer};
		add(new InputEvent("touchDragged", passed));
		return true;
	}
	public boolean mouseMoved(int screenX, int screenY) {
		int[] passed = {(int) (screenX * ((float) cW / Gdx.graphics.getWidth())),
				(int) (screenY * ((float) cH / Gdx.graphics.getHeight()))};
		add(new InputEvent("mouseMoved", passed));
		return true;
	}
	public boolean scrolled(int amount) {
		add(new InputEvent("scrolled", amount));
		return true;
	}

}
