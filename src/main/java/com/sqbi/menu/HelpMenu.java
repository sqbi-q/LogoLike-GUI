package com.sqbi.menu;

import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedHashMap;


public class HelpMenu extends MenuClass{
		
	public HelpMenu(int lang_nr) {
		lang = new Lang();
		lang.setLang(lang_nr);
		MenuItems = new LinkedHashMap<String, JMenuItem>();
		
		InitMenu();
		InitItems();
		AddActionListeners();
		AddItems();
	}
	
	protected void InitMenu() {
		Menu = new JMenu(lang.getName("Help"));
	}
	
	protected void InitItems() {
		MenuItems.put("Documentation", new JMenuItem(lang.getName("Documentation")));
		MenuItems.put("About", new JMenuItem(lang.getName("About")));
	}
	
	private void AddActionListeners() {
		MenuItems.get("Documentation").addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//Documentation selected
			}
		});
		MenuItems.get("About").addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				MenuWindow dialog = new MenuWindow();
				
				dialog.frame.setResizable(false);
				dialog.frame.setAlwaysOnTop(true);
				dialog.frame.setTitle("About");
				dialog.frame.setBounds(100, 100, 450, 500);
				dialog.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				
				JPanel panel = new JPanel();
			    
				URL url = getClass().getResource("/assets/logolike/icon/krab.png");
				dialog.frame.setIconImage(new ImageIcon(url).getImage());
				ImageIcon icon = getScaledImage(url, 200, 200);
				panel.setLayout(new GridLayout(0, 1, 0, 0));
				JLabel label1 = new JLabel(
		                icon,
		                JLabel.CENTER);
				//Set the position of the text, relative to the icon:
				//label1.setVerticalTextPosition(JLabel.BOTTOM);
				//label1.setHorizontalTextPosition(JLabel.CENTER);
				
				panel.add(label1);
				
				JLabel lblNewLabel = new JLabel("LogoLike");
				lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 30));
				
				JTextPane txtpnOpensource = new JTextPane();
				txtpnOpensource.setFont(new Font("Dialog", Font.PLAIN, 18));
				txtpnOpensource.setText("Open source integrated development environment for LogoLike language.");
				
				SimpleAttributeSet sa = new SimpleAttributeSet();
				StyleConstants.setAlignment(sa, StyleConstants.ALIGN_CENTER);

				txtpnOpensource.getStyledDocument().setParagraphAttributes(0, 0, sa, false);
				
				txtpnOpensource.setEditable(false);
				txtpnOpensource.setOpaque(false);
				
				JTextPane txtpnAutorSqbi = new JTextPane();
				txtpnAutorSqbi.setText("Author: Sqbi-q\n");
				txtpnAutorSqbi.setOpaque(false);
				txtpnAutorSqbi.setFont(new Font("Dialog", Font.BOLD, 13));
				txtpnAutorSqbi.setEditable(false);
				
				GroupLayout groupLayout = new GroupLayout(dialog.frame.getContentPane());
				groupLayout.setHorizontalGroup(
					groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(127)
									.addComponent(panel, GroupLayout.PREFERRED_SIZE, 193, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(83)
									.addComponent(txtpnOpensource, GroupLayout.PREFERRED_SIZE, 280, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(148)
									.addComponent(lblNewLabel))
								.addGroup(groupLayout.createSequentialGroup()
									.addContainerGap()
									.addComponent(txtpnAutorSqbi, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
							.addContainerGap(83, Short.MAX_VALUE))
				);
				groupLayout.setVerticalGroup(
					groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblNewLabel)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtpnOpensource, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 111, Short.MAX_VALUE)
							.addComponent(txtpnAutorSqbi, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
				);
				dialog.frame.getContentPane().setLayout(groupLayout);
				
				dialog.viewFrame();
			}
		});
	}
	
	private ImageIcon getScaledImage(URL url, int w, int h){
	    BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g2 = resizedImg.createGraphics();
	    try {
	    	Image srcImg = ImageIO.read(url);
	    	//g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		    g2.drawImage(srcImg, 0, 0, w, h, null);
		    g2.dispose();
	    }catch(IOException ex) {}

	    /*g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g2.drawImage(srcImg, 0, 0, w, h, null);
	    g2.dispose();*/

	    return new ImageIcon((Image)resizedImg);
	}

}
