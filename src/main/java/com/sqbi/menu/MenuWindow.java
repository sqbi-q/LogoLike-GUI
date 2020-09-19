package com.sqbi.menu;

import javax.swing.JFrame;

import java.awt.Component;
import java.awt.GridLayout;
import javax.swing.JPanel;

public class MenuWindow {
	public JFrame frame;
	public JPanel panel;
	
	public MenuWindow(){
		InitWindow();
	}
	
	public void InitWindow() {
		frame = new JFrame("Dialog box");
		frame.setResizable(false);
		
		frame.setSize(800, 420);
		frame.getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
		
		panel = new JPanel();
	}
	
	public void viewFrame() {
		frame.getContentPane().add(panel);
		frame.setVisible(true);
	}
	
	public void addComponent(Component comp) {
		panel.add(comp);
	}
	
	public void setTitle(String title) {
		frame.setTitle(title);
	}
}
