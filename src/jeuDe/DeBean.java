package jeuDe;

public class DeBean {
	protected int value;

	public DeBean(int value) {
		this.value = value;
	}

	public DeBean() {
		this(1);
	}

	public void lancer() {
		value = (int) (1 + Math.random() * 6);
	}

	public void test() {
		int prout = 0;
		for (int i = 0; i <= 30; i++) {
			lancer();
			if (value == 6) {
				prout++;
			}
		}
		System.out.println(prout);
	}

	// ---------------
	// Getter Setter
	// ---------------

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
}