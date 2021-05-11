package net.cyberflame.antienemysilk;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import net.cyberflame.antienemysilk.listeners.SpawnerBreakListener;



public class AntiEnemySilkPlugin extends JavaPlugin
{
	@Override
	public void onEnable()
	{
		Bukkit.getPluginManager().registerEvents(new SpawnerBreakListener(), this);
		System.out.println("AntiEnemySilk enabled!");
	}
	
}
