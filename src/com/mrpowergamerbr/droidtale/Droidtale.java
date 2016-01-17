package com.mrpowergamerbr.droidtale;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import javax.swing.JFileChooser;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

public class Droidtale {

	private JFrame frmDroidtale;
	String path = "";
	private JTextField textField;
	private JLabel lblInvalidFolderdatawin;
	private JLabel label;
	private JButton btnCreateUndertaleApk;
	private JProgressBar progressBar;

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
			@Override
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
		frmDroidtale.setBounds(100, 100, 450, 209);
		frmDroidtale.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmDroidtale.getContentPane().setLayout(null);

		JButton btnSetUndertaleFolder = new JButton("Set Undertale Folder");
		btnSetUndertaleFolder.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser j = new JFileChooser();
				j.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				Integer opt = j.showSaveDialog(frmDroidtale);
				if(opt == JFileChooser.APPROVE_OPTION) {
					editPath(j.getSelectedFile().getAbsoluteFile());
				}
			}
		});
		btnSetUndertaleFolder.setBounds(12, 13, 162, 25);
		frmDroidtale.getContentPane().add(btnSetUndertaleFolder);

		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(180, 14, 240, 22);
		frmDroidtale.getContentPane().add(textField);
		textField.setColumns(10);

		lblInvalidFolderdatawin = new JLabel("Invalid Folder! \"data.win\" missing!");
		lblInvalidFolderdatawin.setBounds(12, 51, 408, 16);
		frmDroidtale.getContentPane().add(lblInvalidFolderdatawin);

		label = new JLabel("");
		label.setBounds(12, 69, 203, 16);
		frmDroidtale.getContentPane().add(label);

		btnCreateUndertaleApk = new JButton("Create Undertale APK");
		btnCreateUndertaleApk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Action");
				playSound("flowey.wav");

				try {
					String[] fileArray = { "assets/game.droid" };
					deleteZipEntry(new File("./UndertaleWrapper.apk"), fileArray);
					progressBar.setValue(25);

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				ZipFile zipFile = null;
				try {
					zipFile = new ZipFile("./UndertaleWrapper.apk");
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

				Path zipFilePath = Paths.get("./UndertaleWrapper.apk");
				try( FileSystem fs = FileSystems.newFileSystem(zipFilePath, null) ){
					Path fileInsideZipPath = fs.getPath("/assets/game.droid");
					Files.copy(myFilePath, fileInsideZipPath);
					progressBar.setValue(25);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				/*
				 * aapt
				 */
				Runtime commandPrompt = Runtime.getRuntime();
				try {           
					progressBar.setValue(50);
				    Process aapt = commandPrompt.exec("aapt add -f -v UndertaleWrapper.apk assets/credits.txt assets/mus_a2.ogg assets/mus_alphysfix.ogg assets/mus_amalgam.ogg assets/mus_ambientwater.ogg assets/mus_anothermedium.ogg assets/mus_bad.ogg assets/mus_barrier.ogg assets/mus_battle1.ogg assets/mus_battle2.ogg assets/mus_bergentruckung.ogg assets/mus_bgflameA.ogg assets/mus_birdnoise.ogg assets/mus_birdsong.ogg assets/mus_boss1.ogg assets/mus_cast_1.ogg assets/mus_cast_2.ogg assets/mus_cast_3.ogg assets/mus_cast_4.ogg assets/mus_cast_5.ogg assets/mus_cast_6.ogg assets/mus_cast_7.ogg assets/mus_chokedup.ogg assets/mus_churchbell.ogg assets/mus_computer.ogg assets/mus_confession.ogg assets/mus_coolbeat.ogg assets/mus_core.ogg assets/mus_coretransition.ogg assets/mus_core_ambience.ogg assets/mus_creepy_ambience.ogg assets/mus_crickets.ogg assets/mus_cymbal.ogg assets/mus_dance_of_dog.ogg assets/mus_date.ogg assets/mus_date_fight.ogg assets/mus_date_tense.ogg assets/mus_deeploop2.ogg assets/mus_disturbing.ogg assets/mus_dogappear.ogg assets/mus_dogmeander.ogg assets/mus_dogroom.ogg assets/mus_dogsong.ogg assets/mus_dontgiveup.ogg assets/mus_doorclose.ogg assets/mus_dooropen.ogg assets/mus_drone.ogg assets/mus_dummybattle.ogg assets/mus_dununnn.ogg assets/mus_elevator.ogg assets/mus_elevator_last.ogg assets/mus_endarea_parta.ogg assets/mus_endarea_partb.ogg assets/mus_endingexcerpt1.ogg assets/mus_endingexcerpt2.ogg assets/mus_express_myself.ogg assets/mus_fallendown2.ogg assets/mus_fearsting.ogg assets/mus_flowey.ogg assets/mus_f_6s_1.ogg assets/mus_f_6s_2.ogg assets/mus_f_6s_3.ogg assets/mus_f_6s_4.ogg assets/mus_f_6s_5.ogg assets/mus_f_6s_6.ogg assets/mus_f_alarm.ogg assets/mus_f_destroyed.ogg assets/mus_f_destroyed2.ogg assets/mus_f_destroyed3.ogg assets/mus_f_finale_1.ogg assets/mus_f_finale_1_l.ogg assets/mus_f_finale_2.ogg assets/mus_f_finale_3.ogg assets/mus_f_intro.ogg assets/mus_f_newlaugh.ogg assets/mus_f_newlaugh_low.ogg assets/mus_f_part1.ogg assets/mus_f_part2.ogg assets/mus_f_part3.ogg assets/mus_f_saved.ogg assets/mus_f_wind1.ogg assets/mus_f_wind2.ogg assets/mus_gameover.ogg assets/mus_ghostbattle.ogg assets/mus_harpnoise.ogg assets/mus_hereweare.ogg assets/mus_hotel.ogg assets/mus_hotel_battle.ogg assets/mus_house1.ogg assets/mus_house2.ogg assets/mus_intronoise.ogg assets/mus_kingdescription.ogg assets/mus_lab.ogg assets/mus_leave.ogg assets/mus_menu0.ogg assets/mus_menu1.ogg assets/mus_menu2.ogg assets/mus_menu3.ogg assets/mus_menu4.ogg assets/mus_menu5.ogg assets/mus_menu6.ogg assets/mus_mettafly.ogg assets/mus_mettatonbattle.ogg assets/mus_mettaton_ex.ogg assets/mus_mettaton_neo.ogg assets/mus_mettaton_pretransform.ogg assets/mus_mettmusical1.ogg assets/mus_mettmusical2.ogg assets/mus_mettmusical3.ogg assets/mus_mettmusical4.ogg assets/mus_mettsad.ogg assets/mus_mett_applause.ogg assets/mus_mett_cheer.ogg assets/mus_mode.ogg assets/mus_mtgameshow.ogg assets/mus_muscle.ogg assets/mus_musicbox.ogg assets/mus_myemeow.ogg assets/mus_mysteriousroom2.ogg assets/mus_mystery.ogg assets/mus_napstachords.ogg assets/mus_napstahouse.ogg assets/mus_news.ogg assets/mus_news_battle.ogg assets/mus_ohyes.ogg assets/mus_oogloop.ogg assets/mus_operatile.ogg assets/mus_options_fall.ogg assets/mus_options_summer.ogg assets/mus_options_winter.ogg assets/mus_papyrus.ogg assets/mus_papyrusboss.ogg assets/mus_piano.ogg assets/mus_prebattle1.ogg assets/mus_predummy.ogg assets/mus_race.ogg assets/mus_rain.ogg assets/mus_rain_deep.ogg assets/mus_repeat_1.ogg assets/mus_repeat_2.ogg assets/mus_reunited.ogg assets/mus_rimshot.ogg assets/mus_ruins.ogg assets/mus_ruinspiano.ogg assets/mus_sansdate.ogg assets/mus_sfx_a_grab.ogg assets/mus_sfx_chainsaw.ogg assets/mus_sfx_hypergoner_charge.ogg assets/mus_sfx_hypergoner_laugh.ogg assets/mus_sfx_rainbowbeam_hold.ogg assets/mus_shop.ogg assets/mus_sigh_of_dog.ogg assets/mus_silence.ogg assets/mus_smallshock.ogg assets/mus_smile.ogg assets/mus_snoresymphony.ogg assets/mus_snowwalk.ogg assets/mus_snowy.ogg assets/mus_spider.ogg assets/mus_spoopy.ogg assets/mus_spoopy_holiday.ogg assets/mus_spoopy_wave.ogg assets/mus_star.ogg assets/mus_sticksnap.ogg assets/mus_story.ogg assets/mus_story_stuck.ogg assets/mus_st_happytown.ogg assets/mus_st_him.ogg assets/mus_st_meatfactory.ogg assets/mus_st_troubledingle.ogg assets/mus_temshop.ogg assets/mus_temvillage.ogg assets/mus_tension.ogg assets/mus_tone2.ogg assets/mus_tone3.ogg assets/mus_toomuch.ogg assets/mus_toriel.ogg assets/mus_town.ogg assets/mus_tv.ogg assets/mus_undyneboss.ogg assets/mus_undynefast.ogg assets/mus_undynepiano.ogg assets/mus_undynescary.ogg assets/mus_undynetheme.ogg assets/mus_undynetruetheme.ogg assets/mus_vsasgore.ogg assets/mus_waterfall.ogg assets/mus_waterquiet.ogg assets/mus_wawa.ogg assets/mus_whoopee.ogg assets/mus_wind.ogg assets/mus_woofenstein.ogg assets/mus_woofenstein_loop.ogg assets/mus_wrongnumbersong.ogg assets/mus_wrongworld.ogg assets/mus_xpart.ogg assets/mus_xpart_2.ogg assets/mus_xpart_a.ogg assets/mus_xpart_b.ogg assets/mus_xpart_back.ogg assets/mus_x_undyne.ogg assets/mus_x_undyne_pre.ogg assets/mus_yourbestfriend_3.ogg assets/mus_zzz_c.ogg assets/mus_zzz_c2.ogg assets/mus_zz_megalovania.ogg assets/mus_z_ending.ogg assets/snd_ballchime.ogg assets/snd_bombfall.ogg assets/snd_bombsplosion.ogg assets/snd_buzzing.ogg assets/snd_curtgunshot.ogg assets/snd_fall2.ogg assets/snd_flameloop.ogg assets/snd_heavydamage.ogg assets/snd_mushroomdance.ogg");
				    aapt.waitFor();
				    progressBar.setValue(75);
				    Process apkSigner = commandPrompt.exec("java -jar lib.jar -w testkey.x509.pem testkey.pk8 UndertaleWrapper.apk UndertaleWrapper_Signed.apk");
				    apkSigner.waitFor();
				    /*
				     * TODO: Push the APK to the device if it has debugging enabled.
				     */
				} catch (IOException|InterruptedException e) {
					/*
					 * TODO: Better exception handling?
					 */
				}
				progressBar.setValue(100);
			}
		});
		btnCreateUndertaleApk.setEnabled(false);
		btnCreateUndertaleApk.setBounds(117, 88, 217, 25);
		frmDroidtale.getContentPane().add(btnCreateUndertaleApk);

		progressBar = new JProgressBar();
		progressBar.setBounds(12, 126, 408, 25);
		frmDroidtale.getContentPane().add(progressBar);
	}

	public Droidtale getMe() {
		return this;
	}

	public void editPath(File file) {
		path = file.toString();
		textField.setText(file.toString());

		UndertaleFolderValue uvf = isAValidUndertaleFolder(file);
		if (uvf.equals(UndertaleFolderValue.VALID_FOLDER)) {
			lblInvalidFolderdatawin.setText("Valid Undertale Installation!");
			btnCreateUndertaleApk.setEnabled(true);
			return;
		} else if (uvf.equals(UndertaleFolderValue.STEAM_VERSION)) {
			lblInvalidFolderdatawin.setText("Steam Version is Unsupported! Please open the UNDERTALE.exe in 7zip to extract it!");
			btnCreateUndertaleApk.setEnabled(false);
			return;
		} else {
			lblInvalidFolderdatawin.setText("Invalid Folder! \"data.win\" missing!");
			btnCreateUndertaleApk.setEnabled(false);
			return;
		}
	}

	public UndertaleFolderValue isAValidUndertaleFolder(final File folder) {
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
					return UndertaleFolderValue.VALID_FOLDER;
				}
				if (fileEntry.getName().equalsIgnoreCase("UNDERTALE.exe")) {
					System.out.println("Steam");
					return UndertaleFolderValue.STEAM_VERSION;
				}
			}
		}
		return UndertaleFolderValue.NOT_FOUND;
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
			throw new RuntimeException("could not rename the file " + zipFile.getAbsolutePath() + " to " + tempFile.getAbsolutePath());
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

	public void playSound(final String url) {
		//read audio data from whatever source (file/classloader/etc.)
		InputStream audioSrc = Droidtale.class.getResourceAsStream("/com/mrpowergamerbr/Droidtale/" + url);
		//add buffer for mark/reset support
		InputStream bufferedIn = new BufferedInputStream(audioSrc);
		try {
			AudioInputStream audioStream = AudioSystem.getAudioInputStream(bufferedIn);
			
			Clip clip = AudioSystem.getClip();
			
			clip.open(audioStream);
			
			clip.start();
			
			
		} catch (UnsupportedAudioFileException | IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
