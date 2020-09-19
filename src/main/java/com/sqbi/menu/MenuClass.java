package com.sqbi.menu;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.LinkedHashMap;

public abstract class MenuClass extends JMenu{
	public JMenu Menu;
	protected Lang lang;
	protected LinkedHashMap<String, JMenuItem> MenuItems;
	
	protected abstract void InitMenu();
	protected abstract void InitItems();
	
	protected void AddItems() {
		for (JMenuItem i : MenuItems.values()) {
			Menu.add(i);
		}
	}
}
