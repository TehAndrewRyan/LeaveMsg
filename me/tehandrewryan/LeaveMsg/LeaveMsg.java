package me.tehandrewryan.LeaveMsg;

import java.io.File;
import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.config.Configuration;

public class LeaveMsg extends JavaPlugin {
	public static LeaveMsg plugin;
	public final Logger logger = Logger.getLogger("Minecraft");
    private final BasicPlayerListener playerListener = new BasicPlayerListener(this);
	static String message;
	protected static Configuration CONFIG;

	
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
		
		CONFIG = new Configuration(new File(this.getDataFolder(), "config.yml"));
		CONFIG.load();
		if(CONFIG.getProperty("leavemessage") == null) CONFIG.setProperty("leavemessage", " left the game: ");
		CONFIG.save();

	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String cmdLabel, String[] args) {
		ChatColor RED = ChatColor.RED;
		ChatColor YELLOW = ChatColor.YELLOW;
		ChatColor GREEN = ChatColor.GREEN;
		Player player = null;
		if (sender instanceof Player) {
			player = (Player)sender;
		}
			if (cmd.getName().equalsIgnoreCase("quit")) {
				if (player == null) {
					System.out.println("Only players can use this command.");
				} else 
				if(args.length==0) {
					sender.sendMessage(RED + "Please specify a reason.");
					return false;				
				} else {
					String leaveMessage = "";
					for (int i =0;i<args.length;i++) {
						leaveMessage = leaveMessage + args[i] + " ";
					}
						if (sender instanceof Player) {
							getServer().broadcastMessage(YELLOW + sender.getName() + CONFIG.getString("leavemessage") + GREEN + leaveMessage);
							((Player) sender).kickPlayer(("You left: " + leaveMessage));
							
					}
				}

			}	
		return true;
	}

}
