package com.sqbi.menu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.google.gson.JsonElement;
import com.sqbi.MainApplication;

public class DraggableObject{
	private JLabel label;
	public String name;
	private String spritePath;
	private JsonElement relativeSpritePath; 
	private Rectangle originRect;
	private Point movePoint;
	private ImageIcon spriteImg;
	private JPanel dstPanel;

	public DraggableObject(JPanel objectPanel, String objectName, Rectangle objectRect, String objectSpritePath,
			JsonElement relSpritePathStr){
		dstPanel = objectPanel;
		name = objectName;
		originRect = objectRect;
		spritePath = objectSpritePath;
		relativeSpritePath = relSpritePathStr;
		
		movePoint = new Point(0,0);
		
		spriteImg = getScaledImage(
        	new File(objectSpritePath),
        	originRect.width, originRect.height);
		
		label = new JLabel(spriteImg, JLabel.CENTER);
		label.setBounds(
				dstPanel.getWidth()/2-originRect.width/2 +originRect.x+movePoint.x, 
				dstPanel.getHeight()/2-originRect.height/2 +originRect.y+movePoint.y, 
				originRect.width, originRect.height);
		
		label.addMouseMotionListener(new MouseMotionAdapter() {

            @Override
            public void mouseDragged(MouseEvent me) {
            	MainApplication.propertySpinner.clear();
            	MainApplication.repaintPanels();
            	
                me.translatePoint(me.getComponent().getLocation().x, me.getComponent().getLocation().y);
                label.setBounds(me.getX(), me.getY(), originRect.width, originRect.height);
                movePoint.x = me.getX()-originRect.x -dstPanel.getWidth()/2-originRect.width/2;
                movePoint.y = me.getY()-originRect.y -dstPanel.getHeight()/2-originRect.height/2;
            }

        });
		
		label.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				ArrayList<PropertySpinner> ps = MainApplication.propertySpinner;
				ps.clear();
				ps.add(new PropertySpinner("Pos. X", "PosX", movePoint.x, 1));
				ps.add(new PropertySpinner("Pos. Y", "PosY", movePoint.y, 1));
				ps.add(new PropertySpinner("Size X", "SizeX", originRect.width, 1));
				ps.add(new PropertySpinner("Size Y", "SizeY", originRect.height, 1));
				ps.forEach(obj->{
					obj.spinner.addChangeListener(new ChangeListener() {
						
						@Override
						public void stateChanged(ChangeEvent e) {
							JSpinner e_spinner = (JSpinner) e.getSource();
							System.out.println(e_spinner.getName());
							
							switch(e_spinner.getName()) {
								case "PosX":
									movePoint.x = (int) e_spinner.getValue();
									break;
								case "PosY":
									movePoint.y = (int) e_spinner.getValue();
									break;
									
								case "SizeX":
									originRect.width = (int) e_spinner.getValue();
									break;
								case "SizeY":
									originRect.height = (int) e_spinner.getValue();
									break;
							}
							updateResize();
						}
					});
				});
				
				MainApplication.repaintPanels();
			}
			
		});
		
		dstPanel.add(label);

	}
	
	public Point getPosition() {
		return movePoint;
	}
	public Dimension getSize() {
		return originRect.getSize();
	}
	
	public JsonElement getRelativeSpritePath() {
		return relativeSpritePath;
	}
	
	public void updateResize() {
		label.setBounds(
				dstPanel.getWidth()/2-originRect.width/2 +originRect.x+movePoint.x, 
				dstPanel.getHeight()/2-originRect.height/2 +originRect.y+movePoint.y, 
				originRect.width, originRect.height);
	}
	
	
	private static ImageIcon getScaledImage(File file, int w, int h){
	    BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g2 = resizedImg.createGraphics();
	    try {
	    	Image srcImg = ImageIO.read(file);
		    g2.drawImage(srcImg, 0, 0, w, h, null);
		    g2.dispose();
	    }catch(IOException ex) {
	    	ex.printStackTrace();
	    }

	    return new ImageIcon((Image)resizedImg);
	}
	
}