package me.hybridplague.checkjob;

import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.permission.Permission;

public class CheckJob extends JavaPlugin {

	private static Permission perms = null;
	private static Chat chat = null;
	@Override
	public void onEnable() {
		
		setupPermissions();
		setupChat();
		
		this.getCommand("job").setExecutor(new JobCommand(this));
		this.getCommand("checkjob").setExecutor(new JobCommand(this));
		
		this.saveDefaultConfig();
	}
	
    public static Permission getPermissions() {
        return perms;
    }
    
    public static Chat getChat() {
    	return chat;
    }
	
    private boolean setupPermissions() {
        RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
        perms = rsp.getProvider();
        return perms != null;
    }
	
    private boolean setupChat() {
        RegisteredServiceProvider<Chat> rsp = getServer().getServicesManager().getRegistration(Chat.class);
        chat = rsp.getProvider();
        return chat != null;
    }
    
}
