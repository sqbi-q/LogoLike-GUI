package com.sqbi;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JLabel;
//import javax.swing.JMenuBar;
//import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.imageio.ImageIO;
//import javax.swing.JInternalFrame;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JComponent;

//import javax.swing.JList;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.List;
import java.awt.Rectangle;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.net.URL;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JTree;
import javax.swing.SpinnerListModel;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.sqbi.menu.DraggableObject;
import com.sqbi.menu.PropertySpinner;
import com.sqbi.menu.PropertySpinner;
import java.awt.FlowLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.event.ChangeListener;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JSpinner;

public class MainApplication {
	
	private JFrame frame;
	private MainMenu menu;
	private JTextField textField;
	private static JPanel treePanel;
	private static JTree tree;
	//display
	private static JPanel displayPanel;
	private static Gson gson;
	private static JsonObject project_setting;
	//display object
	private static ArrayList<DraggableObject> DraggableObjectArray = new ArrayList<DraggableObject>();
	
	//property
	private static JPanel propertyPanel;
	public static ArrayList<PropertySpinner> propertySpinner = new ArrayList<PropertySpinner>();
	
	private static JSplitPane splitPane_3;
	
	public static String project_path_str;
	private JTextField txtTest;
	
	public MainApplication(){
		menu = new MainMenu();
		gson = new Gson();
		
		InitWindow();
	}
	
	public void InitWindow() {
		frame = new JFrame("LogoLike IDE");
		
		URL url = getClass().getResource("/assets/logolike/icon/krab.png");
		frame.setIconImage(new ImageIcon(url).getImage());
		
		frame.setSize(1240, 800);

		frame.setJMenuBar(menu.MenuBar);
		frame.setMinimumSize(new Dimension(1240, 800));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane.setResizeWeight(0.7);
		frame.getContentPane().add(splitPane);
		
		JPanel panel = new JPanel();
		splitPane.setRightComponent(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		JSplitPane splitPane_2 = new JSplitPane();
		splitPane_2.setEnabled(false);
		splitPane_2.setResizeWeight(0.8);
		splitPane_2.setOrientation(JSplitPane.VERTICAL_SPLIT);
		panel.add(splitPane_2);
		
		JPanel panel_4 = new JPanel();
		splitPane_2.setRightComponent(panel_4);
		panel_4.setLayout(new GridLayout(1, 0, 0, 0));
		
		//INPUT
		textField = new JTextField();
		panel_4.add(textField);
		textField.setColumns(10);
		
		JPanel panel_5 = new JPanel();
		splitPane_2.setLeftComponent(panel_5);
		panel_5.setLayout(new GridLayout(1, 0, 0, 0));
		
		//OUTPUT
		JTextPane textPane = new JTextPane();
		textPane.setEditable(false);
		panel_5.add(textPane);
		
		JPanel panel_1 = new JPanel();
		splitPane.setLeftComponent(panel_1);
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));
		
		JSplitPane splitPane_1 = new JSplitPane();
		splitPane_1.setResizeWeight(0.2);
		panel_1.add(splitPane_1);
		
		
		//DISPLAY
		
		displayPanel = new JPanel();
		displayPanel.setLayout(null);
		displayPanel.addComponentListener(new ComponentAdapter() {
		    public void componentResized(ComponentEvent componentEvent) {
		    	resizeDisplay();
		    }
		});
		splitPane_1.setRightComponent(displayPanel);
		
		splitPane_3 = new JSplitPane();
		splitPane_3.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane_3.setResizeWeight(0.7);
		splitPane_1.setLeftComponent(splitPane_3);
		
		treePanel = new JPanel();
		splitPane_3.setLeftComponent(treePanel);
		treePanel.setLayout(new GridLayout(1, 0, 0, 0));
		
		/*propertySpinner.add(new PropertySpinner("Pos. X", "PosX", 0, 1));
		propertySpinner.add(new PropertySpinner("Pos. Y", "PosY", 0, 1));
		propertySpinner.add(new PropertySpinner("Size X", "SizeX", 0, 1));
		propertySpinner.add(new PropertySpinner("Size Y", "SizeY", 0, 1));*/
		
		
		propertyPanel = new JPanel();
		propertyPanel.setLayout(new BoxLayout(propertyPanel, BoxLayout.Y_AXIS));
		for (PropertySpinner ps : propertySpinner) {
			propertyPanel.add(ps);
		}
		splitPane_3.setRightComponent(propertyPanel);
		
		frame.setVisible(true);
	}
	

	private static DefaultMutableTreeNode noding(Path path, DefaultMutableTreeNode tmp) {	
		DefaultMutableTreeNode ret_node = new DefaultMutableTreeNode(path.getFileName().toString());

		if(tmp != null) ret_node.add(new DefaultMutableTreeNode(tmp));
		if(path.getParent() == null) return ret_node;
		
		return noding(path.getParent(), ret_node);
	}
	
	public static void setupProject() {
		try {
			if(project_path_str == null) return;
			Reader reader = Files.newBufferedReader(Paths.get(project_path_str+"/setting.json"));
			project_setting = gson.fromJson(reader, JsonObject.class);
			
			//SETUP TREE
			DefaultMutableTreeNode root = new DefaultMutableTreeNode(project_setting.get("project-name").getAsString());
	       
	        Path project_path = Paths.get(project_path_str);
	        Files.walk(project_path)
	        	.filter(Files::isRegularFile)
	        	.forEach(path->{
	        		Path relative = project_path.relativize(path);
	        		root.add(noding(relative, null));
	        	});
	        
	        tree = new JTree(root);
	        cleanPanels();
	        
	        
	        //
	        //SETUP DISPLAY
	        //colors
	        JsonArray bg_color = project_setting.getAsJsonArray("background-color");
	        
	        displayPanel.setBackground(new Color(
	        	bg_color.get(0).getAsInt(),
	        	bg_color.get(1).getAsInt(),
	        	bg_color.get(2).getAsInt()
	        ));
	        
	        
	        //objects
	        JsonObject objs = project_setting.getAsJsonObject("objects");
	        Set<String> set = objs.keySet();
	        for (String key : set) {
	        	
	        	JsonObject obj = objs.getAsJsonObject(key);
	            
	            JsonArray sprite_position = obj.getAsJsonArray("origin-position");
	            JsonElement rel_sprite_path_el = obj.get("sprite");
	            String rel_sprite_path_str = rel_sprite_path_el.getAsString();
	            JsonArray sprite_size = obj.getAsJsonArray("sprite-size");
	            String abs_sprite_path_str = project_path_str+rel_sprite_path_str;
	        	
	        	DraggableObject draggableObject = 
	        			new DraggableObject(
	        					displayPanel,
	        					key, 
	        					new Rectangle(
	        							sprite_position.get(0).getAsInt(), sprite_position.get(1).getAsInt(),
	        							sprite_size.get(0).getAsInt(), sprite_size.get(1).getAsInt()),
	        					abs_sprite_path_str, rel_sprite_path_el);
	        	
	        	DraggableObjectArray.add(draggableObject);
	        }
	        //
	        
	        
	        repaintPanels();
	        reader.close();
	        
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void saveProject() {	
		/*{
			"project-name":"tests",
			"objects":{
				"default":{
					"origin-position":[0, 0],
					"sprite":"/images/krab.bmp",
					"sprite-size":[32, 32]
				}
			},
			"background-color":[0, 0, 0]
		}*/
		for (DraggableObject obj : DraggableObjectArray) {
			JsonObject jsonObj = new JsonObject();

			JsonArray sprite_position = new JsonArray();
			sprite_position.add(obj.getPosition().x);
			sprite_position.add(obj.getPosition().y);
			jsonObj.add("origin-position", sprite_position);

			JsonElement relative_sprite_path = obj.getRelativeSpritePath();
			jsonObj.add("sprite", relative_sprite_path);
			
			JsonArray sprite_size = new JsonArray();
			sprite_size.add(obj.getSize().width);
			sprite_size.add(obj.getSize().height);
			jsonObj.add("sprite-size", sprite_size);
			
			try {
				Reader reader = Files.newBufferedReader(Paths.get(project_path_str+"/setting.json"));
				
				project_setting = gson.fromJson(reader, JsonObject.class);
				JsonObject objs = project_setting.getAsJsonObject("objects");

				objs.remove(obj.name);
				objs.add(obj.name, jsonObj);
				
				//System.out.println(project_setting);
				FileWriter json_fileWriter = new FileWriter(project_path_str+"/setting.json");
				gson.toJson(project_setting, json_fileWriter);
				json_fileWriter.close();
				
				reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private static void resizeDisplay() {
		for (DraggableObject obj : DraggableObjectArray) {
			obj.updateResize();
		}
	}
	
	private static ImageIcon getScaledImage(File file, int w, int h){
	    BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g2 = resizedImg.createGraphics();
	    try {
	    	Image srcImg = ImageIO.read(file);
		    g2.drawImage(srcImg, 0, 0, w, h, null);
		    g2.dispose();
	    }catch(IOException ex) {}

	    return new ImageIcon((Image)resizedImg);
	}
	
	private static void cleanPanels() {
		treePanel.removeAll();
		treePanel.add(tree);
		
		displayPanel.removeAll();
	}
	public static void repaintPanels() {
		/*treePanel.revalidate();
		treePanel.repaint();*/
		displayPanel.revalidate();
		displayPanel.repaint();
		
		propertyPanel.removeAll();
		for (PropertySpinner ps : propertySpinner) {
			propertyPanel.add(ps);
		}
		
		propertyPanel.revalidate();
		propertyPanel.repaint();
		splitPane_3.revalidate();
		splitPane_3.repaint();
		
	}
	
	public static void main(String[] args) {
		new MainApplication();
	}
}
