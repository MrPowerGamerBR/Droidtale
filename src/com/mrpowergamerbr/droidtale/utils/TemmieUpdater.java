package com.mrpowergamerbr.droidtale.utils;

import java.net.URL;
import java.util.Scanner;

import com.mrpowergamerbr.droidtale.Droidtale;

public class TemmieUpdater {
	/*
	 * TemmieUpdater - Fancy Updater
	 * 
	 * Created by MrPowerGamerBR 2016
	 */

	public TemmieUpdater() {
		startUpdater();
	}

	public void startUpdater() {
		Thread t = new Thread(new Runnable() {
			public void run() {
				try {
					Scanner updater = new Scanner((new URL("http://mrpowergamerbr.com/updater?name=" + Droidtale.name)).openStream());

					boolean update = false;
					String newVersion = "";
					while (updater.hasNextLine()) {
						String line = updater.nextLine();
						if (line.equals(Droidtale.version)) {
							break;
						} else {
							update = true;
							newVersion = line;
						}
					}
					updater.close();

					if (update) {
					} else {
					}
				} catch (Exception e) {
					
				}
			}
		});
		t.start();
	}
}
