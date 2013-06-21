package simpleui;

import gameabstract.InputEvent;

public class SimpleToggle extends SimpleButton {
	private boolean toggleState;
	
	public SimpleToggle(String n, float x, float y, float width, float height) {
		super(n, x, y, width, height);
		toggleState = false;
	}
	public boolean react(InputEvent e) {
		boolean clicked = super.react(e);
		if(clicked) {
			SimpleEventType type = SimpleEventType.TOGGLE_FALSE;
			if(toggleState)
				type = SimpleEventType.TOGGLE_TRUE;
			SimpleEvent event = new SimpleEvent(this, type, getMessage());
			fireEvent(event);
		}
		return clicked;
	}
	public void toggle() {
		toggleState = !toggleState;
	}
	public void set(boolean value) {
		toggleState = value;
	}
	public boolean state() {
		return toggleState;
	}
}
