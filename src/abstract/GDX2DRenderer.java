package gameabstract;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

//LibGDX 2D implementation of the abstract Renderer
//Uses orthographic camera techniques to render 2 dimensional games
public abstract class GDX2DRenderer implements Renderer {
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private TextureAtlas atlas;
	private ShapeRenderer shape;
	private BitmapFont debug;
	
	//creates a new 2d renderer with a default texture atlas
	public GDX2DRenderer(TextureAtlas a, int w, int h) {
		this(w, h);
		atlas = a;
	}
	
	//creates a new 2d renderer that does not have a texture atlas
	public GDX2DRenderer(int w, int h) {
		camera = new OrthographicCamera();
		setCameraViewPort(w, h);
		debug = new BitmapFont();
		shape = new ShapeRenderer();
	}
	
	//sets the camera viewport to a given width and height
	public void setCameraViewPort(int w, int h) {
		camera.setToOrtho(true, w, h);
	}
	
	//returns the SpriteBatch libGDX object for rendering
	public SpriteBatch batch() {
		return batch;
	}
	
	//returns the ShapeRenderer libGDX object for rendering
	public ShapeRenderer shape() {
		return shape;
	}
	
	//returns the Camera libGDX object
	public Camera camera() {
		return camera;
	}
	
	//clears the screen with black
	public void cls() {
		Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
	}
	
	//called to render something renderable
	abstract public void render(Renderable e);
	//called at the beginning of every render pass
	abstract public void begin();
	//called at the end of every render pass
	abstract public void end();
	
}
