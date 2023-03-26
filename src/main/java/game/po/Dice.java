package game.po;

public class Dice {

	private int name;
	private int value;

	public Dice(int name, int value) {
		this.name = name;
		this.value = value;
	}

	public int getName() {
		return name;
	}

	public void setName(int name) {
		this.name = name;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

}
