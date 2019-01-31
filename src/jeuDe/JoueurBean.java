package jeuDe;

public class JoueurBean {
	private int scorePartie = 0;
	private String nom;
	private GobeletBean gobelet;
	private boolean tricheur = false;

	public JoueurBean(String nom) {
		this.nom = nom;
		gobelet = new GobeletBean(tricheur);
	}

	public void lancer() {
		gobelet.lancer();
	}

	public void ajouter1point() {
		scorePartie++;
	}

	// ---------------
	// Getter Setter
	// ---------------

	public int getScorePartie() {
		return scorePartie;
	}

	public void setScorePartie(int scorePartie) {
		this.scorePartie = scorePartie;
	}

	public GobeletBean getGobelet() {
		return gobelet;
	}

	public void setGobelet(GobeletBean gobeletBean) {
		gobelet = gobeletBean;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public boolean isTricheur() {
		return tricheur;
	}

	public void setTricheur(boolean tricheur) {
		this.tricheur = tricheur;
	}
}
