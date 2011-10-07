package me.tehandrewryan.LeaveMsg;

import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerListener;

public class BasicPlayerListener extends PlayerListener {
	public static LeaveMsg plugin;
	public BasicPlayerListener(LeaveMsg instance) {
		plugin = instance;
	}
	public void onPlayerKick(PlayerKickEvent event) {
		event.setLeaveMessage(null);
	}

}
