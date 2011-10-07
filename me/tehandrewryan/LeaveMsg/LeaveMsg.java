package me.tehandrewryan.LeaveMsg;

import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class LeaveMsg extends JavaPlugin {
	public static LeaveMsg plugin;
	public final Logger logger = Logger.getLogger("Minecraft");
    private final BasicPlayerListener playerListener = new BasicPlayerListener(this);
	static String message;

	
	@Override
	public void onDisable() {
		PluginDescriptionFile pdfFile = this.getDescription();
		this.logger.info(pdfFile.getName() + " version " + pdfFile.getVersion() + " killed itself in anger.");
	}
	
	@Override
	public void onEnable() {
		PluginDescriptionFile pdfFile = this.getDescription();
		this.logger.info(pdfFile.getName() + " version " + pdfFile.getVersion() + " is enabled.");
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvent(Event.Type.PLAYER_KICK, this.playerListener, Event.Priority.Normal, this);
		
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String cmdLabel, String[] args) {
		ChatColor RED = ChatColor.RED;
		ChatColor YELLOW = ChatColor.YELLOW;
		ChatColor GREEN = ChatColor.GREEN;
			if (cmd.getName().equalsIgnoreCase("quit")) {
				if(args.length==0) {
					sender.sendMessage(RED + "Please specify a reason.");
					return false;				
				} else {
					String leaveMessage = "";
					for (int i =0;i<args.length;i++) {
						leaveMessage = leaveMessage + args[i] + " ";
					}
						if (sender instanceof Player) {
							getServer().broadcastMessage(YELLOW + sender.getName() + (" left the game: ") + GREEN + leaveMessage);
							((Player) sender).kickPlayer(("You left: " + leaveMessage));
							System.out.println(sender.getName() + (" lost connection: disconnect.quitting"));
							
					}
				}

			}	
		return true;
	}

}
