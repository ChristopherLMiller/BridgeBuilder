package com.moosemanstudios.BridgeBuilder;

import net.gravitydevelopment.updater.Updater;
import org.bukkit.plugin.java.JavaPlugin;
import org.mcstats.Metrics;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

public class BridgeBuilder extends JavaPlugin {
	private Logger log = Logger.getLogger("Minecraft");
	private String prefix = "[BridgeBuilder] ";

	private Boolean debug;

	public Boolean updaterEnabled, updaterAuto, updaterNotify;

	public int curseID = 0;

	@Override
	public void onEnable() {
		// load config
		loadConfig();

		// check upater settings
		if (updaterEnabled) {
			if (updaterAuto) {
				Updater updater = new Updater(this, curseID, this.getFile(), Updater.UpdateType.DEFAULT, true);
				if (updater.getResult() == Updater.UpdateResult.SUCCESS)
					log.info(prefix + "Update downloaded sucesfully, restart server to apply update");
			}
			if (updaterNotify) {
				this.getServer().getPluginManager().registerEvents(new UpdateListener(this), this);
			}
		}

		// Register the command executor
		getCommand("bridge").setExecutor(new BridgeBuilderCommandExecutor(this));

		// Enable metrics
		try {
			Metrics metrics = new Metrics(this);
			metrics.start();
		} catch (IOException e) {
			log.warning("Unable to initialize metrics");
		}

		// lastly
		log.info(prefix + "has been enabled.");
	}

	@Override
	public void onDisable() {

	}

	public String getPrefix() { return this.prefix; }

	public Boolean isDebugging() { return this.debug; }

	public File getFileFolder() { return this.getFile(); }

	public void loadConfig() {
		reloadConfig();

		// misc settings
		if(!getConfig().contains("misc.debug")) getConfig().set("misc.debug", true);

		// updater settings
		if (!getConfig().contains("updater.enabled")) getConfig().set("updater.enabled", true);
		if (!getConfig().contains("updater.auto")) getConfig().set("updater.auto", true);
		if (!getConfig().contains("updater.notify")) getConfig().set("updater.notify", true);

		saveConfig();

		debug = getConfig().getBoolean("misc.debug");
		if (debug)
			log.info(prefix + "Debugging enabled");

		updaterEnabled = getConfig().getBoolean("updater.enabled");
		updaterAuto = getConfig().getBoolean("updater.auto");
		updaterNotify = getConfig().getBoolean("updater.notify");

		if (debug && updaterEnabled)
			if (updaterAuto)
				log.info(prefix + "Auto updating plugin");
			if (updaterNotify)
				log.info(prefix + "Notifying admins on join");
	}



}
