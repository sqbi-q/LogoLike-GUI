package com.sqbi.menu;

import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

public class PropertySpinner extends JPanel{
	private JLabel spinnerLabel;
	public String spinnerName;
	public JSpinner spinner;
	
	public PropertySpinner(String spinner_name, String spinnerID) {
		spinnerLabel = new JLabel(spinner_name);
		this.add(spinnerLabel);
		
		spinnerName = spinnerID;
	}
	
	public PropertySpinner(String spinner_name, String spinnerID, int min, int value, int max, int step) {
		spinnerLabel = new JLabel(spinner_name);
		this.add(spinnerLabel);
		
		spinnerName = spinnerID;
		
		_endconstr(value, min, max, step);
	}
	public PropertySpinner(String spinner_name, String spinnerID, int value, int step) {
		spinnerLabel = new JLabel(spinner_name);
		this.add(spinnerLabel);
		
		spinnerName = spinnerID;
		
		_endconstr(value, Integer.MIN_VALUE, Integer.MAX_VALUE, step);
	}
	
	//Adding spinner to panel
	private void _endconstr(int m_value, int m_min, int m_max, int step) {
		SpinnerNumberModel model = new SpinnerNumberModel(m_value, m_min, m_max, step);
		spinner = new JSpinner(model);
		spinner.setName(spinnerName);
		this.add(spinner);
	}
}
