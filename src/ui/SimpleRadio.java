package simpleui;

import gameabstract.InputEvent;

public class SimpleRadio extends SimpleToggle {
	private SimpleRadioGroup group;
	
	public SimpleRadio(String n, float x, float y, float width,
			float height) {
		super(n, x, y, width, height);
	}
	
	public boolean react(InputEvent e) {
		boolean clicked = super.react(e);
		if(clicked) {
			group.select(this);
			SimpleEvent event = new SimpleEvent(this, SimpleEventType.SELECT, getMessage());
			fireEvent(event);
		}
		return clicked;
	}
	public void setGroup(SimpleRadioGroup simpleRadioGroup) {
		group = simpleRadioGroup;
	}
	
}
