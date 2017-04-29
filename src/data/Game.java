package data;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.CardLayout;

public class Game extends JFrame {
	
	private JGamePanel gamePane;
	
	private ArrayList<DynamicAnimator> dynamicThreadPool;
	private ArrayList<Thread> dynamicThreadController;
	
	private InputController inputC;
	
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Game frame = new Game();
					frame.setUndecorated(true);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Game() {
		setTitle("Chad");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 891, 544);
		gamePane = new JGamePanel();
		gamePane.setBackground(Color.WHITE);
		gamePane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(gamePane);
		gamePane.setLayout(new CardLayout(0, 0));
		
		
		String imgPath = ("resources" + File.separator + "gfx" + File.separator);
		
		dynamicThreadPool = new ArrayList<DynamicAnimator>();
		dynamicThreadController = new ArrayList<Thread>();
		
		inputC = new InputController(gamePane, dynamicThreadPool);
		
		gamePane.addStaticObject(imgPath + "crate.png" , 5, 50);
		gamePane.addDynamicObject(imgPath + "chadPistols.png" , 205, 250 ,imgPath + "chadShootDown.png",imgPath + "chadShootDown2.png",imgPath + "chadShootDown1.png", dynamicThreadPool, dynamicThreadController);
		
		
	}

}
