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
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class Droidtale {

	private JFrame frmDroidtale;
	String path = "";
	private JTextField textField;
	private JLabel lblInvalidFolderdatawin;
	private JLabel label;
	private JButton btnCreateUndertaleApk;

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
		frmDroidtale.setBounds(100, 100, 450, 387);
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

		btnCreateUndertaleApk = new JButton("Create Undertale APK");
		btnCreateUndertaleApk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Action");

				try {
					String[] fileArray = { "assets/game.droid" };
					deleteZipEntry(new File("C:\\Users\\User\\Desktop\\UndertaleWrapper.apk"), fileArray);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				ZipFile zipFile = null;
				try {
					zipFile = new ZipFile("C:\\Users\\User\\Desktop\\UndertaleWrapper.apk");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				Enumeration<? extends ZipEntry> entries = zipFile.entries();

				while(entries.hasMoreElements()){
					ZipEntry entry = entries.nextElement();
					System.out.println(entry.getName());
					// InputStream stream = zipFile.getInputStream(entry);
				}
				
				try {
					zipFile.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				Path myFilePath = Paths.get(path + File.separator + "data.win");

				Path zipFilePath = Paths.get("C:\\Users\\User\\Desktop\\UndertaleWrapper.apk");
				try( FileSystem fs = FileSystems.newFileSystem(zipFilePath, null) ){
					Path fileInsideZipPath = fs.getPath("/assets/game.droid");
					Files.copy(myFilePath, fileInsideZipPath);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnCreateUndertaleApk.setEnabled(false);
		btnCreateUndertaleApk.setBounds(114, 304, 217, 25);
		frmDroidtale.getContentPane().add(btnCreateUndertaleApk);
	}

	public Droidtale getMe() {
		return this;
	}

	public void editPath(File file) {
		path = file.toString();
		textField.setText(file.toString());

		if (isAValidUndertaleFolder(file)) {
			lblInvalidFolderdatawin.setText("Valid Undertale Installation!");
			btnCreateUndertaleApk.setEnabled(true);
		} else {
			lblInvalidFolderdatawin = new JLabel("Invalid Folder! \"data.win\" missing!");
			btnCreateUndertaleApk.setEnabled(false);
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

	public static void deleteZipEntry(File zipFile,
			String[] files) throws IOException {
		// get a temp file
		File tempFile = File.createTempFile(zipFile.getName(), null);
		// delete it, otherwise you cannot rename your existing zip to it.
		tempFile.delete();
		tempFile.deleteOnExit();
		boolean renameOk=zipFile.renameTo(tempFile);
		if (!renameOk)
		{
			throw new RuntimeException("could not rename the file "+zipFile.getAbsolutePath()+" to "+tempFile.getAbsolutePath());
		}
		byte[] buf = new byte[1024];

		ZipInputStream zin = new ZipInputStream(new FileInputStream(tempFile));
		ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(zipFile));

		ZipEntry entry = zin.getNextEntry();
		while (entry != null) {
			String name = entry.getName();
			boolean toBeDeleted = false;
			for (String f : files) {
				if (f.equals(name)) {
					toBeDeleted = true;
					break;
				}
			}
			if (!toBeDeleted) {
				// Add ZIP entry to output stream.
				zout.putNextEntry(new ZipEntry(name));
				// Transfer bytes from the ZIP file to the output file
				int len;
				while ((len = zin.read(buf)) > 0) {
					zout.write(buf, 0, len);
				}
			}
			entry = zin.getNextEntry();
		}
		// Close the streams        
		zin.close();
		// Compress the files
		// Complete the ZIP file
		zout.close();
		tempFile.delete();
	}
}
