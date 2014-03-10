package com.moosemanstudios.BridgeBuilder;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * Created by Chris on 3/10/14.
 */
public class BridgeBuilderCommandExecutor implements CommandExecutor{

	private BridgeBuilder plugin;

	public BridgeBuilderCommandExecutor(BridgeBuilder plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		// TODO: annotated command manager here
		return false;
	}
}
