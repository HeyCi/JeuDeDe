package jeuDe;

public class GobeletBean {
	private DeBean d1;
	private DeBean d2;

	public GobeletBean(boolean truque) {
		if (truque == true) {
			d1 = new DePipeBean();
		} else {
			d1 = new DeBean();
		}
		d2 = new DeBean();
	}

	public int getScoreDes() {
		int somme = d1.getValue() + d2.getValue();
		return somme;
	}

	public void lancer() {
		d1.lancer();
		d2.lancer();
	}

	public void test() {
		int prout = 0;
		for (int i = 0; i <= 30; i++) {
			lancer();
			if (d1.getValue() == 6) {
				prout++;
			}
		}
		System.out.println(prout);
	}

	// ---------------
	// Getter Setter
	// ---------------

	public DeBean getD1() {
		return d1;
	}

	public void setD1(DeBean d1) {
		this.d1 = d1;
	}

	public DeBean getD2() {
		return d2;
	}

	public void setD2(DeBean d2) {
		this.d2 = d2;
	}
}