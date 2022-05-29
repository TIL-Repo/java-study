package reflection._3_inspecting_constructors;

import reflection._2_inspecting_java_classes.getting_ready.Animal;

public class Bird extends Animal {

	private boolean walks;

	public Bird() {
		super("bird");
	}

	public Bird(String name) {
		super(name);
	}

	public Bird(String name, boolean walks) {
		super(name);
		setWalks(walks);
	}

	public void setWalks(boolean walks) {
		this.walks = walks;
	}

	public boolean walks() { return walks; }

	@Override
	public String eats() {
		return null;
	}

	@Override
	protected String getSound() {
		return null;
	}
}
