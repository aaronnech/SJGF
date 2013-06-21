package simpleui;

import gameabstract.InputEvent;
import gameabstract.Renderable;

import java.lang.reflect.Method;
import java.util.ArrayList;

public abstract class SimpleUIComponent implements Renderable {
	protected String name;
	protected float x, y;
	protected float width, height;
	protected boolean hide;
	
	private Object message;
	private boolean markDelete;
	
	private ArrayList<SimpleSubscription> subscriptions;
	
	public SimpleUIComponent(String name, float x, float y, float width,
			float height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.name = name;
		this.markDelete = false;
		subscriptions = new ArrayList<SimpleSubscription>();
	}
	
	public SimpleUIComponent() {
		subscriptions = new ArrayList<SimpleSubscription>();
	}
	
	abstract public boolean react(InputEvent event);
	public final String getName() {
		return name;
	}
	
	public final void setName(String name) {
		this.name = name;
	}
	public final float getX() {
		return x;
	}
	public final float getY() {
		return y;
	}
	public final void setX(float x) {
		this.x = x;
	}
	public final void setY(float y) {
		this.y = y;
	}
	public final float getWidth() {
		return width;
	}
	public final void setWidth(float width) {
		this.width = width;
	}
	public final float getHeight() {
		return height;
	}
	public final void setHeight(float height) {
		this.height = height;
	}
	public final void hide() {
		hide = true;
	}
	public final void show() {
		hide = false;
	}
	public final boolean visible() {
		return !hide;
	}
	public final void delete() {
		markDelete = true;
	}
	public final boolean markedDelete() {
		return markDelete;
	}
	public final void setMessage(Object o) {
		message = o;
	}
	public final Object getMessage() {
		return message;
	}
	public final void subscribe(SimpleUIListener listener, SimpleEventType action,
			String methodName) {
		try {
			Method method = listener.getClass().getMethod(methodName, SimpleEvent.class);
			subscriptions.add(new SimpleSubscription(method, action, listener));
		} catch (Exception e) {
			throw new IllegalArgumentException("Bad subscription! " + methodName);
		}
	}
	public final void clearSubscriptions() {
		subscriptions.clear();
	}
	protected final void fireEvent(SimpleEvent e) {
		for(SimpleSubscription subscription : subscriptions) {
			if(subscription.action == e.getAction()) {
				try {
					subscription.method.invoke(subscription.subscriber, e);
				} catch (Exception ex) {
				}
			}
		}
	}
}
