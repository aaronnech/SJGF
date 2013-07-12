package simpleui;

import gameabstract.InputEvent;

import java.util.ArrayList;
import java.util.List;

public class SimpleRadioGroup extends SimpleUIComponent {
	private SimpleRadio selected;
	private List<SimpleRadio> members;
	
	public SimpleRadioGroup() {
		selected = null;
		members = new ArrayList<SimpleRadio>();
	}
	
	public void add(SimpleRadio button) {
		button.setGroup(this);
		members.add(button);
	}
	public void add(SimpleRadio button, SimpleUIListener listener,
			SimpleEventType action, String methodName) {
		button.subscribe(listener, action, methodName);
		button.setGroup(this);
		members.add(button);
	}
	public void select(SimpleRadio radio) {
		if(members.contains(radio)) {
			selected = radio;
			for(SimpleRadio button : members)
				button.set(false);
			selected.set(true);
		}
	}
	public void select(String name) {
		for(SimpleRadio button : members) {
			if(button.name.equals(name)) {
				selected = button;
				for(SimpleRadio others : members)
					others.set(false);
				selected.set(true);
			}
		}
	}
	public boolean react(InputEvent event) {
		boolean hitOne = false;
		for(SimpleRadio r : members) {
			if(r.react(event))
				hitOne = true;
		}
		return hitOne;
	}

	public List<SimpleRadio> getRadios() {
		return members;
	}
	
}
