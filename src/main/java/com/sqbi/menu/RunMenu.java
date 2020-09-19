package com.sqbi.menu;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class RunMenu extends MenuClass{
	
	public RunMenu(int lang_nr) {
		lang = new Lang();
		lang.setLang(lang_nr);
		MenuItems = new LinkedHashMap<String, JMenuItem>();
		
		InitMenu();
		InitItems();
		AddActionListeners();
		AddItems();
	}
	
	protected void InitMenu() {
		Menu = new JMenu(lang.getName("Run"));
	}
	
	protected void InitItems() {
		MenuItems.put("Run", new JMenuItem(lang.getName("Run")));
		MenuItems.put("Build", new JMenuItem(lang.getName("Build")));
	}
	
	private void AddActionListeners() {
		MenuItems.get("Run").addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//Run selected
			}
		});
		MenuItems.get("Build").addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//Build selected
			}
		});
	}
}
