package me.mrpowergamerbr.Droidtale;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JCheckBox;
import javax.swing.JFileChooser;

import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Path;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class Droidtale {

	private JFrame frmDroidtale;
	String path = "";
	private JTextField textField;
	private JLabel lblInvalidFolderdatawin;
	private JLabel label;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			// Set Look and Feel
			UIManager.setLookAndFeel(
					UIManager.getSystemLookAndFeelClassName());
		} 
		catch (UnsupportedLookAndFeelException e) {
			// handle exception
		}
		catch (ClassNotFoundException e) {
			// handle exception
		}
		catch (InstantiationException e) {
			// handle exception
		}
		catch (IllegalAccessException e) {
			// handle exception
		}

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Droidtale window = new Droidtale();
					window.frmDroidtale.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Droidtale() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmDroidtale = new JFrame();
		frmDroidtale.setTitle("Droidtale");
		frmDroidtale.setBounds(100, 100, 450, 332);
		frmDroidtale.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmDroidtale.getContentPane().setLayout(null);

		JCheckBox chckbxSteamVersion = new JCheckBox("Steam Version");
		chckbxSteamVersion.setBounds(157, 9, 113, 43);
		frmDroidtale.getContentPane().add(chckbxSteamVersion);

		JButton btnSetUndertaleFolder = new JButton("Set Undertale Folder");
		btnSetUndertaleFolder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser j = new JFileChooser();
				j.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				Integer opt = j.showSaveDialog(frmDroidtale);
				if(opt == JFileChooser.APPROVE_OPTION) {
					editPath(j.getSelectedFile().getAbsoluteFile());
				}
			}
		});
		btnSetUndertaleFolder.setBounds(12, 187, 162, 25);
		frmDroidtale.getContentPane().add(btnSetUndertaleFolder);

		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(180, 188, 240, 22);
		frmDroidtale.getContentPane().add(textField);
		textField.setColumns(10);
		
		lblInvalidFolderdatawin = new JLabel("Invalid Folder! \"data.win\" missing!");
		lblInvalidFolderdatawin.setBounds(12, 225, 203, 16);
		frmDroidtale.getContentPane().add(lblInvalidFolderdatawin);
		
		label = new JLabel("");
		label.setBounds(12, 242, 203, 16);
		frmDroidtale.getContentPane().add(label);
	}

	public Droidtale getMe() {
		return this;
	}
	
	public void editPath(File file) {
		path = file.toString();
		textField.setText(file.toString());
		
		if (isAValidUndertaleFolder(file)) {
			lblInvalidFolderdatawin.setText("Valid Undertale Installation!");
		} else {
			lblInvalidFolderdatawin = new JLabel("Invalid Folder! \"data.win\" missing!");
		}
	}
	
	public boolean isAValidUndertaleFolder(final File folder) {
	    for (final File fileEntry : folder.listFiles()) {
	        if (fileEntry.isDirectory()) {
	            
	        } else {
	            if (fileEntry.getName().equalsIgnoreCase("codex.ini")) {
	            	/*
	            	 * Support Undertale :)
	            	 */
	            	label.setText("Please buy Undertale :)");
	            }
	            if (fileEntry.getName().equalsIgnoreCase("data.win")) {
	            	return true;
	            }
	        }
	    }
	    return false;
	}
}
