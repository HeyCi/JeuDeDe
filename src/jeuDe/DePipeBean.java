package jeuDe;

public class DePipeBean extends DeBean {
	@Override
	public void lancer() {
		double proba = Math.random();
		if (proba <= 0.5) {
			value = 6;
		} else {
			super.lancer();
		}
	}
}