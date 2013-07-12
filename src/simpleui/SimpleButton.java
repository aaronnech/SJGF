package simpleui;

import gameabstract.InputEvent;
/*
 * Author: Aaron Nech
 * Project: SJGF
 * Description: This class represents a button
 * 
 */
public class SimpleButton extends SimpleUIComponent {
	
	//Constructs a button from a given name, location, and size
	public SimpleButton(String n, float x, float y, float width, float height) {
		super(n, x, y, width, height);
	}
	
	//called by the UI manager when a input event is detected
	public boolean react(InputEvent e) {
		if(e.getType().equals("tap") && 
				inButton(((int[]) e.getData())[0], ((int[]) e.getData())[1])) {
			SimpleEvent event = new SimpleEvent(this, SimpleEventType.TOUCH, getMessage());
			fireEvent(event);
			return true;
		}
		return false;
	}
	
	//Bounding box test to see if a location is within the button
	private boolean inButton(int tx, int ty) {
		return tx >= x && ty >= y &&
				tx <= x + width && ty <= y + height;
	}
}
