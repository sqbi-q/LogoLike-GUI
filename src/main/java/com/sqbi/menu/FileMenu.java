package com.sqbi.menu;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileFilter;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.sqbi.MainApplication;

public class FileMenu extends MenuClass{
	
	public FileMenu(int lang_nr) {
		lang = new Lang();
		lang.setLang(lang_nr);
		MenuItems = new LinkedHashMap<String, JMenuItem>();

		InitMenu();
		InitItems();
		AddActionListeners();
		AddItems();
	}

	protected void InitMenu() {
		Menu = new JMenu(lang.getName("File"));
	}

	protected void InitItems() {
		MenuItems.put("New", new JMenuItem(lang.getName("New")));
		MenuItems.put("Open", new JMenuItem(lang.getName("Open")));
		
		MenuItems.put("Save", new JMenuItem(lang.getName("Save")));

		MenuItems.put("Import", new JMenuItem(lang.getName("Import")));
		MenuItems.put("Export", new JMenuItem(lang.getName("Export")));
	}

	private void AddActionListeners() {
		MenuItems.get("New").addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
			    JFileChooser c = new JFileChooser();
			    c.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			    c.setCurrentDirectory(new File("."/*"."new File(".").getCanonicalPath()*/));
			    int rVal = c.showDialog(c, lang.getName("Create_proj_directory"));
			    if (rVal == JFileChooser.APPROVE_OPTION) {
			    	String project_path = c.getCurrentDirectory().toString()+System.getProperty("file.separator")+c.getSelectedFile().getName();
			    	int inputValue = JOptionPane.showConfirmDialog(null,
			    	        String.format(lang.getName("is_corr_path"), project_path),
			    	        lang.getName("Create_proj_directory"),
			    	        JOptionPane.YES_NO_OPTION);

			    	if(inputValue == 0) {
			    		boolean created = new File(project_path).mkdir();
			    		if(created) {
			    			//dir created
			    			File main_project = new File(project_path, "main_project.lglk");
			    			try {
			    				FileWriter fr = new FileWriter(main_project);

			    				String tmp_insert =
			    						"#Main project file, declare objects\n"
			    						+ "<canvas>\n"
			    						+ "\t{level: 0}\n"
			    						+ "\t<object>\n"
			    						+ "\t\t{draw_line: 1, visible: 1, position: CENTER}\n"
			    						+ "\t</object>\n"
			    						+ "</canvas>";
				    			fr.write(tmp_insert);

				    			fr.close();
			    			} catch(IOException er) {
			    				//show error
			    			}
			    		}
			    		else {
			    			//error
			    			JOptionPane.showMessageDialog(null, lang.getName("couldnt_create_dir"));
			    		}
			    	}
			    }
			    /*if (rVal == JFileChooser.CANCEL_OPTION) {
			    	//filename.setText("You pressed cancel");
			    	//dir.setText("");
			    }*/
			}
		});
		MenuItems.get("Open").addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser c = new JFileChooser();
			    c.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int rVal = c.showDialog(c, lang.getName("Open"));
				if (rVal == JFileChooser.APPROVE_OPTION) {

					//LOAD PROJECT
					
					String project_path_str = c.getSelectedFile().getAbsolutePath();
					
					File f = new File(project_path_str+"/setting.json");
					if(f.exists() && !f.isDirectory()) { 
						MainApplication.project_path_str = project_path_str;
						MainApplication.setupProject();
					}else {
						//invalid selection
						JOptionPane.showMessageDialog(null, lang.getName("couldnt_open_project"));
					}

				}
			  	if (rVal == JFileChooser.CANCEL_OPTION) {
				    //filename.setText("You pressed cancel");
				    //dir.setText("");
			  	}
			}
		});
		MenuItems.get("Save").addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainApplication.saveProject();	
			}
		});
	}
}
