package org.vamdc.validator.gui.settings;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import org.vamdc.validator.Setting;
import org.vamdc.validator.gui.mainframe.MainFrameController;

public class SettingsPanelController implements ActionListener{

	private MainFrameController main;
	private SettingsPanel panel;

	public SettingsPanelController(MainFrameController main,SettingsPanel panel){
		this.main=main;
		this.panel = panel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		if (command == SettingsPanel.CMD_SAVE){
			panel.saveSettings();
			panel.loadSettings();
			//reconfigure model
			try{
				main.reloadComponents();
			}catch(Exception ex){
				JOptionPane.showMessageDialog(panel,"Exception while applying new settings: "+ex.getMessage(), "Settings",JOptionPane.ERROR_MESSAGE);
				System.out.println("Exception while applying new settings: "+ex.getMessage());
			}
		}else if (command == SettingsPanel.CMD_RESET){
			//Reload settings (clear changes)
			if (JOptionPane.showConfirmDialog(panel, "Revert all changes?", "Settings", JOptionPane.YES_NO_OPTION)==JOptionPane.OK_OPTION){	
				panel.loadSettings();
			}
		}else if (command == SettingsPanel.CMD_DEFAULTS){
			//Reset settings to defaults
			if (JOptionPane.showConfirmDialog(panel, "Load defaults for all options?", "Settings", JOptionPane.YES_NO_OPTION)==JOptionPane.OK_OPTION){	

				Setting.reset();
				panel.loadSettings();
			}
		}


	}

}
