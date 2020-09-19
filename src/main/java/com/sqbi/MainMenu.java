package com.sqbi;

import javax.swing.JMenuBar;

import com.sqbi.menu.FileMenu;
import com.sqbi.menu.HelpMenu;
import com.sqbi.menu.RunMenu;

public class MainMenu {
	public JMenuBar MenuBar;
	
	public MainMenu(){
		MenuBar = new JMenuBar();
		
		MenuBar.add(new FileMenu(1).Menu);
		MenuBar.add(new RunMenu(1).Menu);
		MenuBar.add(new HelpMenu(1).Menu);
	}
}
