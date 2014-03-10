package com.moosemanstudios.BridgeBuilder;

import net.gravitydevelopment.updater.Updater;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class UpdateListener implements Listener {
	BridgeBuilder plugin;

	UpdateListener(BridgeBuilder plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		//
		// Note - this listener is never registered if the updater is disabled
		//
		if (event.getPlayer().hasPermission("bridgebuilder.update")) {
			Updater updater = new Updater(plugin, plugin.curseID, plugin.getFileFolder(), Updater.UpdateType.NO_DOWNLOAD, false);

			if (updater.getResult() == Updater.UpdateResult.UPDATE_AVAILABLE) {
				event.getPlayer().sendMessage(ChatColor.AQUA + "An update is available for BridgeBuilder: " + updater.getLatestName());
				event.getPlayer().sendMessage(ChatColor.AQUA + "Type " + ChatColor.WHITE + "/bridge update" + ChatColor.AQUA + " to update");
			}
		}
	}

}
