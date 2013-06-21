package simpleui;

import gameabstract.InputEvent;
import gameabstract.InputHandler;
import gameabstract.Renderer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class SimpleUIManager implements InputHandler, Iterable<SimpleUIComponent> {
	private List<SimpleUIComponent> components;

	public SimpleUIManager() {
		components = new ArrayList<SimpleUIComponent>();
	}
	public void input(InputEvent e) {
		for(SimpleUIComponent component : components) {
			if(component.visible())
				component.react(e);
		}
		postReact();
	}
	protected void postReact() {
		checkRemoves();
	}
	protected void preReact() {}
	private void checkRemoves() {
		Iterator<SimpleUIComponent> i = components.iterator();
		while(i.hasNext()) {
			if(i.next().markedDelete())
				i.remove();
		}
	}
	public void renderComponents(Renderer r) {
		for(SimpleUIComponent component : components) {
			if(component.visible())
				r.render(component);
		}
	}	
	public void addComponent(SimpleUIComponent c) {
		components.add(c);
	}
	public void addComponent(SimpleUIComponent c, SimpleUIListener listener,
			SimpleEventType action, String methodName) {
		c.subscribe(listener, action, methodName);
		addComponent(c);
	}
	public void addAllComponents(Collection<? extends SimpleUIComponent> c) {
		components.addAll(c);
	}
	public SimpleUIComponent getComponent(String name) {
		for(SimpleUIComponent component : components) {
			if(component.getName().equals(name))
				return component;
		}
		return null;
	}
	public void removeComponent(SimpleUIComponent c) {
		components.remove(c);
	}
	
	public void removeComponent(String name) {
		Iterator<SimpleUIComponent> i = components.iterator();
		while(i.hasNext()) {
			if(i.next().getName().equals(name))
				i.remove();
		}
	}
	
	public void clearComponents() {
		components.clear();
	}
	public int count() {
		return components.size();
	}
	public void subscribe(String name, SimpleUIListener listener,
			SimpleEventType action, String methodName) {
		for(SimpleUIComponent c : components) {
			if(c.getName().equals(name))
				c.subscribe(listener, action, methodName);
		}
	}
	public void subscribeToAll(SimpleUIListener listener,
			SimpleEventType action, String methodName) {
		for(SimpleUIComponent c : components) {
			c.subscribe(listener, action, methodName);
		}
	}
	public void clearSubscriptions() {
		for(SimpleUIComponent c : components) {
			c.clearSubscriptions();
		}
	}
	public Iterator<SimpleUIComponent> iterator() {
		return components.iterator();
	}
}
