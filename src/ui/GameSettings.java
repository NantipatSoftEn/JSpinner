/**
 * JSpinner: 2110215 PROG METH PROJECT
 * @author Thanawit Prasongpongchai 5631045321
 * @author Phatrasek Jirabovonvisut 5630469621
 */

package ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import control.ScreenState;
import util.Config;

public class GameSettings extends JFrame{

	private JSpinner screenHeightSpinner;
	private JSpinner screenWidthSpinner;
	
	public static GameSettings settingsFrame = new GameSettings();
	
	private GameSettings(){
		setTitle("Settings");
		Container cp = getContentPane();
				
		cp.setLayout(new BorderLayout());
		
		JPanel optionPane = new JPanel(new GridLayout(2, 1));
		
		screenWidthSpinner = addSpinnerWithLabelTo(optionPane, "Screen Width     ", Config.screenWidth, 750, 2000, 10);
		screenHeightSpinner = addSpinnerWithLabelTo(optionPane, "Screen Height     ", Config.screenHeight, 600, 5000, 10);
//		animFrameSpinner = addSpinnerWithLabelTo(optionPane, "Animation Frame Count      ", Config.animationFrameCount, 5, 20, 1);		
		
		JPanel actionPane = new JPanel(new FlowLayout());
		
		addButtonTo(actionPane, "Reset High Score", new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "Reset High Score");
			}
		});
		
		JButton okButton = addButtonTo(actionPane, "OK", new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(isValidOption()){
					Config.screenWidth = (Integer)(screenWidthSpinner.getValue());
					Config.screenHeight = (Integer)(screenHeightSpinner.getValue());
//					Config.animationFrameCount = (Integer)(animFrameSpinner.getValue());
					settingsFrame.setVisible(false);
					ScreenState.presentScreen = ScreenState.REFRESH_TITLE;
				}
			}	
		});
		okButton.requestFocus();
		
		addButtonTo(actionPane, "Cancel", new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				settingsFrame.setVisible(false);
			}
		});
		
		cp.add(optionPane, BorderLayout.CENTER);
		cp.add(actionPane, BorderLayout.SOUTH);
				
		setPreferredSize(new Dimension(300, 180));
		setLocationByPlatform(true);
		pack();
	}
	
	private boolean isValidOption(){
		boolean cond1 = (Integer)(screenHeightSpinner.getValue()) <= (Integer)(screenWidthSpinner.getValue());
		return cond1;
	}
	
	private JSpinner addSpinnerWithLabelTo(Container pane, String label, int spinnerVal, int spinnerMin, int spinnerMax, int spinnerStep){
		JPanel panel = new JPanel(new FlowLayout());
		panel.add(new JLabel(label));
		JSpinner spinner = new JSpinner(new SpinnerNumberModel(spinnerVal, spinnerMin, spinnerMax, spinnerStep));
		panel.add(spinner);
		panel.setBorder(BorderFactory.createEmptyBorder(10, 15, 15, 15));
		pane.add(panel);
		return spinner;
	}
	
	private JButton addButtonTo(Container pane, String label, ActionListener al){
		JButton button = new JButton(label);
		button.addActionListener(al);
		pane.add(button);
		return button;
	}
	
//	public static void main(String[] args) {
//		settingsFrame.setVisible(true);
//		settingsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//	}
}
