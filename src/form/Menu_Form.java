/*
 * Menu_Form.java
 *
 * Created on __DATE__, __TIME__
 */

package form;

import java.awt.*;
import java.awt.event.*;
import java.net.URL;

import javax.swing.*;

import com.data.MImages;

import uc.panel.*;

/**
 *
 * @author  __USER__
 */
public class Menu_Form extends javax.swing.JFrame {

	private Menu_UC jMenu;
	private SelectLevel_UC jSelectLevel;
	private Game_UC jGame;
	Pause_UC jPause;
	private Success_UC jResult;

	/** Creates new form Menu_Form */
	public Menu_Form() {
		initComponents();
		//super();
		//URL imageURL = this.getClass().getResource("/images/cursor1.png/");
		MImages.srcPath = MImages.getPath();
		ImageIcon imageicon = new ImageIcon(MImages.srcPath + "/data/" + "/images/cursor1.png/");
		Image image = imageicon.getImage();

		Cursor cursor = Toolkit.getDefaultToolkit().createCustomCursor(image,
				new Point(0, 0), "myCursor");

		this.setCursor(cursor);

		//imageURL = this.getClass().getResource("/images/birds/bird1_1.png/");
		imageicon = new ImageIcon(MImages.srcPath + "/data/" + "/images/birds/bird1_1.png/");
		image = imageicon.getImage();
	
		this.setIconImage(image);
		this.setTitle("Angry Bird");

		this.setName("Main");

		jMenu = new Menu_UC();
		jSelectLevel = new SelectLevel_UC();

		jMenu.setBounds(0, 0, main_jPanel.getWidth(), main_jPanel.getHeight());

		jPause = new Pause_UC();
		jPause.setBounds(0, 0, main_jPanel.getWidth(), main_jPanel.getHeight());

		jGame = new Game_UC();
		jGame.setBounds(0, 0, main_jPanel.getWidth(), main_jPanel.getHeight());

		jResult = new Success_UC();
		jResult
				.setBounds(0, 0, main_jPanel.getWidth(), main_jPanel
						.getHeight());

		

		jSelectLevel.setBounds(0, 0, main_jPanel.getWidth(), main_jPanel
				.getHeight());
		//main_jPanel.setLayout(null);
		main_jPanel.add(jMenu);

		jMenu.quit_jButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				menuQuit(evt);
			}
		});

		jMenu.startGame_jButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				menuStart(evt);
			}
		});

		this.jSelectLevel.back_jButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				levelBack(evt);
			}
		});

		jSelectLevel.jLevel1Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				levelSelect(evt);
			}
		});

		jSelectLevel.jLevel2Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				levelSelect(evt);
			}
		});

		jSelectLevel.jLevel3Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				levelSelect(evt);
			}
		});

	}

	/** This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		main_jPanel = new javax.swing.JPanel();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
		addComponentListener(new java.awt.event.ComponentAdapter() {
			public void componentShown(java.awt.event.ComponentEvent evt) {
				formComponentShown(evt);
			}
		});

		main_jPanel.setBorder(javax.swing.BorderFactory
				.createLineBorder(new java.awt.Color(0, 0, 0)));

		javax.swing.GroupLayout main_jPanelLayout = new javax.swing.GroupLayout(
				main_jPanel);
		main_jPanel.setLayout(main_jPanelLayout);
		main_jPanelLayout.setHorizontalGroup(main_jPanelLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 1278, Short.MAX_VALUE));
		main_jPanelLayout.setVerticalGroup(main_jPanelLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 718, Short.MAX_VALUE));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addComponent(
				main_jPanel, javax.swing.GroupLayout.DEFAULT_SIZE,
				javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		layout.setVerticalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addComponent(
				main_jPanel, javax.swing.GroupLayout.DEFAULT_SIZE,
				javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

		pack();
	}// </editor-fold>
	//GEN-END:initComponents

	private void formComponentShown(java.awt.event.ComponentEvent evt) {
		// TODO add your handling code here:
		
		com.data.MImages.loadImage();
		com.data.MImages.loadLevel();
		
		Image img = MImages.bird[0][0];
		
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new Menu_Form().setVisible(true);
			}
		});
	}

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	public javax.swing.JPanel main_jPanel;

	// End of variables declaration//GEN-END:variables

	private void menuStart(ActionEvent e) {

		main_jPanel.remove(jMenu);
		//main_jPanel.repaint();
		//jMenu.

		main_jPanel.add(jSelectLevel);

		this.validate();
		main_jPanel.repaint();

	}

	private void levelBack(ActionEvent e) {
		main_jPanel.remove(jSelectLevel);
		main_jPanel.add(jMenu);
		this.validate();
		main_jPanel.repaint();

	}

	private void menuQuit(ActionEvent e) {
		this.dispose();
		System.exit(0);
	}

	public void levelSelect(ActionEvent e) {

		jGame.selectLevel(((JButton) e.getSource()).getName());
		main_jPanel.remove(jSelectLevel);
		main_jPanel.add(jGame);
		this.validate();
		main_jPanel.repaint();

	}

	private void gameStart(ActionEvent e) {
		jGame.reStart();
	}

	public void gamePuase() {
		//gameFinish();

		jGame.stop();
		main_jPanel.remove(jGame);
		main_jPanel.add(jPause);
		this.validate();
		main_jPanel.repaint();

	}

	public void gameReStart(JPanel jpanel) {
		main_jPanel.remove(jpanel);
		jGame.pause_jButton.setVisible(true);
		jGame.start_jButton.setVisible(true);
		main_jPanel.add(jGame);
		jGame.reStart();
		this.validate();
		main_jPanel.repaint();
	}

	public void returnSelectLevel(JPanel jpanel) {
		main_jPanel.remove(jpanel);
		main_jPanel.remove(jGame);
		jGame.exit();
		main_jPanel.add(this.jSelectLevel);
		this.validate();
		main_jPanel.repaint();
	}

	public void pauseMenu(JPanel jpanel) {
		main_jPanel.remove(jpanel);
		main_jPanel.remove(jGame);
		jGame.exit();
		main_jPanel.add(this.jMenu);
		this.validate();
		main_jPanel.repaint();
	}

	public void gameFinish(boolean isWin, int score) {
		jGame.stop();

		jGame.start_jButton.setVisible(false);
		main_jPanel.remove(jGame);
		main_jPanel.add(jResult);
		jResult.win(isWin, jGame.getLevel(), score);
		this.validate();
		main_jPanel.repaint();
	}

	public void nextLevel(JPanel jpanel) {
		if (jGame.nextLevel()) {
			jGame.pause_jButton.setVisible(true);
			jGame.start_jButton.setVisible(true);
			main_jPanel.remove(jpanel);
			main_jPanel.add(jGame);
			//jGame.reStart();
			this.validate();
			main_jPanel.repaint();
		}
	}

	public void pauseCont(JPanel jpanel) {
		main_jPanel.remove(jpanel);
		main_jPanel.add(jGame);
		jGame.gameCont();
		this.validate();
		main_jPanel.repaint();
	}

	public void sayout() {

		System.out.println("333");
	}

}