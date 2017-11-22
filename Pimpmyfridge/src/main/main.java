package main;

import java.awt.EventQueue;

import View.FrameView;
import Model.Model;

public class main {
	/*
	  Launch the application
	 */

	public static void main(String[] args) {
		Model m = new Model();
		FrameView obs = new FrameView();
		m.addObserver(obs);
		m.setMesures(10, 30, 60, 5);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameView window = new FrameView();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
