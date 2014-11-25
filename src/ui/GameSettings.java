package ui;

import java.awt.*;

import javax.swing.*;

import util.Config;

public class GameSettings extends JFrame{

	JSpinner screenHeightSpinner = new JSpinner(new SpinnerNumberModel(Config.screenHeight, 600, 1000, 10));
	JSpinner screenWidthSpinner = new JSpinner(new SpinnerNumberModel(Config.screenWidth, 600, 2000, 10));
	JSpinner topBarSpinner = new JSpinner(new SpinnerNumberModel(Config.topBarHeight, 40, 100, 10));
	JSpinner animFrameSpinner = new JSpinner(new SpinnerNumberModel(Config.animationFrameCount, 5, 20, 1));
	
	public GameSettings(){
		setTitle("Settings");
		Container cp = getContentPane();
				
		cp.setLayout(new BorderLayout());
		
		JPanel optionPane = new JPanel(new GridLayout(4, 2));
		
		optionPane.add(new JLabel("Screen Height     "));
		optionPane.add(screenHeightSpinner);
		
		optionPane.add(new JLabel("Screen Width     "));
		optionPane.add(screenWidthSpinner);

		JLabel tbh = new JLabel("Top Bar Height      ");
		tbh.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		optionPane.add(new JLabel("Top Bar Height      "));
		optionPane.add(topBarSpinner);

		optionPane.add(new JLabel("Animation Frame Count      "));
		optionPane.add(animFrameSpinner);
		
		cp.add(optionPane, BorderLayout.CENTER);
		
		
		JPanel actionPane = new JPanel(new FlowLayout());
		
		JButton resetHighScore = new JButton("Reset High Score");
		actionPane.add(resetHighScore);
		
		JButton applyButton = new JButton("Apply");
		actionPane.add(applyButton);
		
		JButton cancelButton = new JButton("Cancel");
		actionPane.add(cancelButton);
		
		cp.add(actionPane, BorderLayout.SOUTH);
		
//		if(isValidChoice())
		
		setLocationByPlatform(true);
		setVisible(true);
		pack();
	}
	
	private boolean isValidChoice(){
		boolean cond1 = (Integer)(screenHeightSpinner.getValue()) < (Integer)(screenWidthSpinner.getValue());
		return cond1;
	}
	
	public static void main(String[] args) {
		new GameSettings();
	}
}
