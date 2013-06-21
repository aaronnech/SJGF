package simpleui;

import gameabstract.InputEvent;


public class SimpleButton extends SimpleUIComponent {
	
	public SimpleButton(String n, float x, float y, float width, float height) {
		super(n, x, y, width, height);
	}
	
	public boolean react(InputEvent e) {
		if(e.getType().equals("tap") && 
				inButton(((int[]) e.getData())[0], ((int[]) e.getData())[1])) {
			SimpleEvent event = new SimpleEvent(this, SimpleEventType.TOUCH, getMessage());
			fireEvent(event);
			return true;
		}
		return false;
	}
	
	private boolean inButton(int tx, int ty) {
		return tx >= x && ty >= y &&
				tx <= x + width && ty <= y + height;
	}
}
