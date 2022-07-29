package me.hybridplague.checkjob;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class JobCommand implements CommandExecutor {
	
	String pre;
	private CheckJob main;
	public JobCommand(CheckJob main) {
		
		this.main = main;
		pre = "&9&lCheckJob > &f";
	}
	
	private static final Pattern HEX_PATTERN = Pattern.compile("&(#\\w{6})");
	  
	  public static String colorize(String message) {
		    Matcher matcher = HEX_PATTERN.matcher(ChatColor.translateAlternateColorCodes('&', message));
		    StringBuffer buffer = new StringBuffer();

		    while (matcher.find()) {
		        matcher.appendReplacement(buffer, ChatColor.of(matcher.group(1)).toString());
		    }
		 
		    return matcher.appendTail(buffer).toString();
		}

	
	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			if (!sender.hasPermission("checkjob.use")) {
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', pre + "&c&lThis plugin is currently being worked on! We will announce when this is fixed, thank you for your patience."));
				return true;
			}
		}
		if (args.length == 0) {
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', pre + "/job <player>"));
			return true;
		} else if (args.length >= 1) {
			
			if (args[0].equalsIgnoreCase("reload")) {
				if (!sender.hasPermission("checkjob.reload")) {
					return true;
				} else {
					main.reloadConfig();
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', pre + "&aConfig reloaded!"));
					return true;
				}
			}
			
			OfflinePlayer t = Bukkit.getOfflinePlayer(args[0]);
			
			if (t.hasPlayedBefore()) {
				String[] groups = CheckJob.getPermissions().getPlayerGroups(null, t);
				List<String> g = new ArrayList<String>();
				List<String> cabinet = new ArrayList<String>();
				List<String> parliament = new ArrayList<String>();
				List<String> seniorGovernment = new ArrayList<String>();
				List<String> government = new ArrayList<String>();
				List<String> university = new ArrayList<String>();
				List<String> qualifications = new ArrayList<String>();
				
				for (int i = 0; i < groups.length; i++) {
					g.add(" " + groups[i]);
				}
				
				String result = String.join("&f, &e", g);
				result = " " + result.toLowerCase();
				
				for (String list : main.getConfig().getStringList("Cabinet")) {
					if ((result.toLowerCase()).contains(" " + list.toLowerCase())) {
						cabinet.add(colorize(CheckJob.getChat().getGroupPrefix(t.getName(), list)));
					}
				}
				for (String list : main.getConfig().getStringList("Parliament")) {
					if ((result.toLowerCase()).contains(" " + list.toLowerCase())) {
						parliament.add(colorize(CheckJob.getChat().getGroupPrefix(t.getName(), list)));
					}
				}
				for (String list : main.getConfig().getStringList("SeniorGovernment")) {
					if ((result.toLowerCase()).contains(" " + list.toLowerCase())) {
						seniorGovernment.add(colorize(CheckJob.getChat().getGroupPrefix(t.getName(), list)));
					}
				}
				for (String list : main.getConfig().getStringList("Government")) {
					if ((result.toLowerCase()).contains(" " + list.toLowerCase())) {
						government.add(colorize(CheckJob.getChat().getGroupPrefix(t.getName(), list)));
					}
				}
				for (String list : main.getConfig().getStringList("University")) {
					if ((result.toLowerCase()).contains(" " + list.toLowerCase())) {
						university.add(colorize(CheckJob.getChat().getGroupPrefix(t.getName(), list)));
					}
				}
				for (String list : main.getConfig().getStringList("Qualifications")) {
					if ((result.toLowerCase()).contains(" " + list.toLowerCase())) {
						qualifications.add(colorize(CheckJob.getChat().getGroupPrefix(t.getName(), list)));
					}
				}
				sender.sendMessage("");
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&d&lJobs of " + t.getName() + ":"));
				
				String cabResult = String.join("&f⼁ ", cabinet);
				if (cabinet.size() > 0) {
					sender.sendMessage("");
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&3&l--- Cabinet ---"));
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', cabResult));
				} else if (cabinet.size() == 0){
				}
				
				/*
				 * 
				 */
				String parResult = String.join("&f⼁ ", parliament);
				if (parliament.size() > 0) {
					sender.sendMessage("");
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&3&l--- Parliament ---"));
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', parResult));
				} else if (parliament.size() == 0){
				}
				
				/*
				 * 
				 */
				String srGovResult = String.join("&f⼁ ", seniorGovernment);
				if (seniorGovernment.size() > 0) {
					sender.sendMessage("");
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&3&l--- SeniorGovernment ---"));
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', srGovResult));
				} else if (seniorGovernment.size() == 0){
				}
				
				/*
				 * 
				 */
				String govResult = String.join("&f⼁ ", government);
				if (government.size() > 0) {
					sender.sendMessage("");
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&3&l--- Government ---"));
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', govResult));
				} else if (government.size() == 0){
				}
				
				/*
				 * 
				 */
				String uniResult = String.join("&f⼁ ", university);
				if (university.size() > 0) {
					sender.sendMessage("");
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&3&l--- University ---"));
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', uniResult));
				} else if (university.size() == 0){
				}
				
				/*
				 * 
				 */
				String qualResult = String.join("&f⼁ ", qualifications);
				if (qualifications.size() > 0) {
					sender.sendMessage("");
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&3&l--- Qualifications ---"));
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', qualResult));
				} else if (qualifications.size() == 0){
				}
				
				return true;
			} else {
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', pre + "Player not found: &e" + args[0]));
				return true;
			}
		}
		return false;
	}
	
	
}
