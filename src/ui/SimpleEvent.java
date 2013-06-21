package simpleui;

public class  SimpleEvent {
	private SimpleUIComponent origin;
	private SimpleEventType actionType;
	private Object message;
	
	public SimpleEvent(SimpleUIComponent o, SimpleEventType a, Object m) {
		origin = o;
		actionType = a;
		message = m;
	}
	
	public SimpleUIComponent getComponent() {
		return origin;
	}
	public SimpleEventType getAction() {
		return actionType;
	}
	public Object getMessage() {
		return message;
	}
}
