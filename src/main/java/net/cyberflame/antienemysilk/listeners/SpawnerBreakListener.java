package net.cyberflame.antienemysilk.listeners;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import com.massivecraft.factions.Board;
import com.massivecraft.factions.FLocation;
import com.massivecraft.factions.FPlayer;
import com.massivecraft.factions.FPlayers;
import com.massivecraft.factions.Faction;


public class SpawnerBreakListener implements Listener
{
	@EventHandler
	public void onSpawnerBreak(BlockBreakEvent e) 
	{
		@SuppressWarnings("unused")
		Faction faction = null;
		Player p = e.getPlayer();
		FPlayer fp = FPlayers.getInstance().getByPlayer(p);
		FLocation fLoc = new FLocation(p.getLocation());
		faction = Board.getInstance().getFactionAt(fLoc);
		Block b = e.getBlock();
		
		fp.checkIfNearbyEnemies();
		if (fp.isInOwnTerritory() & b.getType() == Material.MOB_SPAWNER && fp.hasEnemiesNearby())
			e.setCancelled(true);
	}
}
