package com.wyattgahm.safetp;

import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.*;
import cn.nukkit.event.player.PlayerTeleportEvent.TeleportCause;
import cn.nukkit.level.Location;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.TextFormat;
import cn.nukkit.Player;

public class SafeTP extends PluginBase implements Listener {
	
	@Override
	public void onLoad() {
		this.getLogger().info(TextFormat.WHITE + "SafeTP Loaded");
	}

	@Override
	public void onEnable() {
		this.getLogger().info(TextFormat.DARK_GREEN + "SafeTP Enabled");
		this.getServer().getPluginManager().registerEvents(this, this);
	}

	@Override
	public void onDisable() {
		this.getLogger().info(TextFormat.DARK_RED + "SafeTP Disabled");
	}
	
	@EventHandler
	private void playerMoved(PlayerMoveEvent e) {
		if(e.getPlayer().getPosition().y < 0) {
			findSafeBlock(e.getPlayer());
			this.getLogger().info(TextFormat.DARK_GREEN + "SafeTP Has Saved " + e.getPlayer().getName() + "! (hopefully)");
		}
    }
	
	private void findSafeBlock(Player p){
		p.noDamageTicks = 30; //1.5 seconds of safety
		int x = (int)p.getPosition().x;
	    int z = (int)p.getPosition().z;
		p.teleport(new Location(x,p.getLevel().getHighestBlockAt(x,z) + 1,z,p.getLocation().getYaw(),p.getLocation().getPitch(),p.getLocation().getLevel()), TeleportCause.PLUGIN );
	}
	
}
