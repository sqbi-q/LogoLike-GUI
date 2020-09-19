package com.sqbi.menu;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileFilter;

import java.awt.Color;
import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.Reader;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
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
				JFrame parentFrame = new JFrame();
				FileDialog fd = new FileDialog(parentFrame, lang.getName("Create_proj_directory"), FileDialog.SAVE);
				
				fd.setVisible(true);
				String filename = fd.getFile();
				
				if (filename != null) {
					String dir = fd.getDirectory()+"/"+filename;
					boolean created = new File(dir).mkdir();
					//dir created
					if(created) {
		    			File main_project = new File(dir, "setting.json");
		    			try {
		    				FileWriter fw = new FileWriter(main_project);

		    				//write setting
		    				String default_setting =
		    					"{"
			    					+ "\"project-name\":"
			    						+ "\"Project\","
			    					+ "\"objects\":{"
			    						+ "\"default\":{"
			    							+ "\"origin-position\":[0,0],"
			    							+ "\"sprite\":\"/images/crab.bmp\","
			    							+ "\"sprite-size\":[32,32]"
			    						+ "}"
			    					+ "},"
			    					+ "\"background-color\":[0,0,0]"
		    					+ "}";
			    			fw.write(default_setting);
			    			
			    			//paste image
			    			
			    			String images_dir = dir + "/images";
			    			boolean images_dir_created = new File(images_dir).mkdir();
			    			Path default_sprite = 
			    					Paths.get(getClass().getResource("/assets/logolike/default_sprite/crab.bmp").toURI());
			    			//images dir created
			    			if(images_dir_created) {
			    				Files.copy(new FileInputStream(default_sprite.toFile()), 
			    						Paths.get(images_dir+"/crab.bmp"));
			    			}

			    			fw.close();
		    			} catch(IOException | URISyntaxException er) {
		    				//show error
		    			}
		    		}
		    		else {
		    			//error
		    			JOptionPane.showMessageDialog(null, lang.getName("couldnt_create_dir"));
		    		}
				}
			}
		});
		MenuItems.get("Open").addActionListener(new ActionListener() {
			//TODO JFileChooser to FileDialog
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
