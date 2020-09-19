package com.sqbi.menu;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JTextPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Color;
import javax.swing.JTextArea;

public class test {

	private JFrame frmAbout;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					test window = new test();
					window.frmAbout.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the application.
	 */
	public test() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAbout = new JFrame();
		frmAbout.setResizable(false);
		frmAbout.setAlwaysOnTop(true);
		frmAbout.setTitle("About");
		frmAbout.setBounds(100, 100, 450, 500);
		frmAbout.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
	    
		URL url = getClass().getResource("/assets/logolike/icon/krab.png");
		frmAbout.setIconImage(new ImageIcon(url).getImage());
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
		
		GroupLayout groupLayout = new GroupLayout(frmAbout.getContentPane());
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
		frmAbout.getContentPane().setLayout(groupLayout);
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
