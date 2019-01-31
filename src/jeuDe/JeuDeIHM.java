package jeuDe;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class JeuDeIHM {

	private JFrame frame;
	private JTextField textFieldScoreJ1;
	private JTextField textFieldScoreJ2;
	private JTextField textFieldDe2;
	private JTextField textFieldDe1;

	private PartieBean pb;
	private JLabel lblTourValue;
	private JLabel lblGagnant;
	private JButton btnLancerJ2;
	private JButton btnLancerJ1;
	public GobeletBean gobeletTricheur = new GobeletBean(true);
	public GobeletBean gobeletHonnete = new GobeletBean(false);
	private JCheckBox chckbxJ2Tricheur;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					JeuDeIHM window = new JeuDeIHM();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public JeuDeIHM() {
		pb = new PartieBean("toto", "max");
		pb.getJ1().setGobelet(gobeletHonnete);
		pb.getJ2().setGobelet(gobeletHonnete);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.PINK);
		frame.setBounds(100, 100, 557, 281);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblJoueur1 = new JLabel("" + pb.getJ1().getNom());
		lblJoueur1.setForeground(Color.BLUE);
		lblJoueur1.setBounds(25, 36, 62, 14);
		frame.getContentPane().add(lblJoueur1);

		JLabel lblJoueur2 = new JLabel("" + pb.getJ2().getNom());
		lblJoueur2.setForeground(Color.RED);
		lblJoueur2.setBounds(459, 36, 61, 14);
		frame.getContentPane().add(lblJoueur2);

		JLabel lblTour = new JLabel("Tour : ");
		lblTour.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTour.setBounds(223, 34, 46, 14);
		frame.getContentPane().add(lblTour);

		JLabel lblScoreJ1 = new JLabel("Score :");
		lblScoreJ1.setForeground(Color.BLUE);
		lblScoreJ1.setBounds(10, 61, 46, 14);
		frame.getContentPane().add(lblScoreJ1);

		JLabel lblScoreJ2 = new JLabel("Score :");
		lblScoreJ2.setForeground(Color.RED);
		lblScoreJ2.setBounds(442, 61, 46, 14);
		frame.getContentPane().add(lblScoreJ2);

		textFieldScoreJ1 = new JTextField("0");
		textFieldScoreJ1.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldScoreJ1.setForeground(Color.BLUE);
		textFieldScoreJ1.setBounds(61, 58, 38, 20);
		frame.getContentPane().add(textFieldScoreJ1);
		textFieldScoreJ1.setColumns(10);

		textFieldScoreJ2 = new JTextField("0");
		textFieldScoreJ2.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldScoreJ2.setForeground(Color.RED);
		textFieldScoreJ2.setColumns(10);
		textFieldScoreJ2.setBounds(493, 58, 38, 20);
		frame.getContentPane().add(textFieldScoreJ2);

		btnLancerJ1 = new JButton("Lancer");
		btnLancerJ1.setBackground(Color.WHITE);
		btnLancerJ1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (pb.getJoueurCourant() == pb.getJ1()) {
					pb.getJ1().lancer();
					textFieldDe1.setText("" + pb.getJ1().getGobelet().getD1().getValue());
					textFieldDe2.setText("" + pb.getJ1().getGobelet().getD2().getValue());
					if (pb.getJ1().getGobelet().getScoreDes() >= PartieBean.SCORE_A_ATTEINDRE) {
						pb.getJ1().ajouter1point();
						textFieldScoreJ1.setText("" + pb.getJ1().getScorePartie());
					}
					pb.echangerJoueurCourant();
					btnLancerJ1.setEnabled(false);
					btnLancerJ2.setEnabled(true);
				}
			}
		});
		btnLancerJ1.setForeground(Color.BLUE);
		btnLancerJ1.setBounds(10, 86, 89, 23);
		frame.getContentPane().add(btnLancerJ1);

		btnLancerJ2 = new JButton("Lancer");
		btnLancerJ2.setBackground(Color.PINK);
		btnLancerJ2.setEnabled(false);
		btnLancerJ2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (pb.getJoueurCourant() == pb.getJ2()) {
					pb.getJ2().lancer();
					textFieldDe1.setText("" + pb.getJ2().getGobelet().getD1().getValue());
					textFieldDe2.setText("" + pb.getJ2().getGobelet().getD2().getValue());
					if (pb.getJ2().getGobelet().getScoreDes() >= PartieBean.SCORE_A_ATTEINDRE) {
						pb.getJ2().ajouter1point();
						textFieldScoreJ2.setText("" + pb.getJ2().getScorePartie());
					}
					pb.setTour(pb.getTour() + 1);
					if (pb.getTour() == PartieBean.NB_LANCER) {
						btnLancerJ2.setEnabled(false);
						btnLancerJ1.setEnabled(false);
						if (pb.getJ1().getScorePartie() > pb.getJ2().getScorePartie()) {
							lblGagnant.setForeground(Color.BLUE);
							lblGagnant.setText("Le joueur " + pb.getJ1().getNom() + " a gagné !");
						} else if (pb.getJ1().getScorePartie() < pb.getJ2().getScorePartie()) {
							lblGagnant.setForeground(Color.RED);
							lblGagnant.setText("Le joueur " + pb.getJ2().getNom() + " a gagné !");
						} else if (pb.getJ1().getScorePartie() == pb.getJ2().getScorePartie()) {
							lblGagnant.setForeground(Color.BLACK);
							lblGagnant.setText("Les joueurs sont à égalité !");
						}
					} else {
						lblTourValue.setText("" + (pb.getTour() + 1));
						btnLancerJ2.setEnabled(false);
						btnLancerJ1.setEnabled(true);
					}
					pb.echangerJoueurCourant();
				}
			}
		});
		btnLancerJ2.setForeground(Color.RED);
		btnLancerJ2.setBounds(442, 86, 89, 23);
		frame.getContentPane().add(btnLancerJ2);

		JLabel lblDe1 = new JLabel("DE 1");
		lblDe1.setForeground(Color.BLACK);
		lblDe1.setBounds(197, 90, 46, 14);
		frame.getContentPane().add(lblDe1);

		JLabel lblDe2 = new JLabel("DE 2");
		lblDe2.setForeground(Color.BLACK);
		lblDe2.setBounds(302, 90, 46, 14);
		frame.getContentPane().add(lblDe2);

		textFieldDe2 = new JTextField();
		textFieldDe2.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldDe2.setForeground(Color.BLACK);
		textFieldDe2.setColumns(10);
		textFieldDe2.setBounds(285, 108, 63, 53);
		frame.getContentPane().add(textFieldDe2);

		textFieldDe1 = new JTextField();
		textFieldDe1.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldDe1.setForeground(Color.BLACK);
		textFieldDe1.setColumns(10);
		textFieldDe1.setBounds(180, 108, 63, 53);
		frame.getContentPane().add(textFieldDe1);

		JButton btnRestart = new JButton("Restart");
		btnRestart.setBackground(Color.WHITE);
		btnRestart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				lblGagnant.setText("");
				pb.setTour(0);
				lblTourValue.setText("" + (pb.getTour() + 1));
				pb.getJ1().setScorePartie(0);
				textFieldScoreJ1.setText("0");
				pb.getJ2().setScorePartie(0);
				textFieldScoreJ2.setText("0");
				textFieldDe1.setText("");
				textFieldDe2.setText("");
				btnLancerJ1.setEnabled(true);
				pb.setJoueurCourant(pb.getJ1());
				btnLancerJ2.setEnabled(false);
				pb.getJ1().setGobelet(gobeletHonnete);
				pb.getJ2().setGobelet(gobeletHonnete);
			}
		});
		btnRestart.setBounds(223, 209, 89, 23);
		frame.getContentPane().add(btnRestart);

		lblTourValue = new JLabel("1");
		lblTourValue.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTourValue.setBounds(292, 34, 20, 14);
		frame.getContentPane().add(lblTourValue);

		lblGagnant = new JLabel("");
		lblGagnant.setHorizontalAlignment(SwingConstants.CENTER);
		lblGagnant.setBounds(69, 172, 401, 26);
		frame.getContentPane().add(lblGagnant);

		JCheckBox chckbxJ1Tricheur = new JCheckBox("tricheur");
		chckbxJ1Tricheur.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0) {
				if (pb.getJ1().getGobelet() == gobeletTricheur) {
					pb.getJ1().setGobelet(gobeletHonnete);
				} else if (pb.getJ1().getGobelet() == gobeletHonnete) {
					pb.getJ1().setGobelet(gobeletTricheur);
				}
			}
		});
		chckbxJ1Tricheur.setForeground(Color.BLUE);
		chckbxJ1Tricheur.setBackground(Color.PINK);
		chckbxJ1Tricheur.setBounds(10, 116, 89, 23);
		frame.getContentPane().add(chckbxJ1Tricheur);

		chckbxJ2Tricheur = new JCheckBox("tricheur");
		chckbxJ2Tricheur.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				if (pb.getJ2().getGobelet() == gobeletTricheur) {
					pb.getJ2().setGobelet(gobeletHonnete);
				} else if (pb.getJ2().getGobelet() == gobeletHonnete) {
					pb.getJ2().setGobelet(gobeletTricheur);
				}
			}
		});
		chckbxJ2Tricheur.setForeground(Color.RED);
		chckbxJ2Tricheur.setBackground(Color.PINK);
		chckbxJ2Tricheur.setBounds(442, 116, 89, 23);
		frame.getContentPane().add(chckbxJ2Tricheur);
	}

	// ---------------
	// Getter Setter
	// ---------------

	public PartieBean getPartieBean() {
		return pb;
	}

	public void setPartieBean(PartieBean partieBean) {
		pb = partieBean;
	}
}
