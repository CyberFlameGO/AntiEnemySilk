package net.cyberflame.antienemysilk.listeners;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import com.massivecraft.factions.FPlayer;
import com.massivecraft.factions.FPlayers;
import com.massivecraft.factions.struct.Relation;


public class SpawnerBreakListener implements Listener
{
	public boolean enemiesNearby = false;
	@EventHandler
	public void onSpawnerBreak(BlockBreakEvent e) 
	{
		Player p = e.getPlayer();
		FPlayer fp = FPlayers.getInstance().getByPlayer(p);
		Block b = e.getBlock();
		
		if (p == null) return;
	    if (p.hasPermission("aes.bypassnearbyenemycheck")) return;
	    int radius = 50;
	    for (@SuppressWarnings("unused") Entity ent : p.getNearbyEntities(radius, 255, radius)) 
	    {
	        if (e instanceof Player) 
	        {
	            Player eplayer = (((Player) e).getPlayer());
	            if (eplayer == null) continue;
	            if (eplayer.hasMetadata("NPC")) continue;
	            FPlayer efplayer = FPlayers.getInstance().getByPlayer(eplayer);
	            if (efplayer == null) continue;
	            if (!p.canSee(eplayer) || efplayer.isVanished()) continue;
	            if (fp.getRelationTo(efplayer).equals(Relation.ENEMY) && !efplayer.isStealthEnabled()) 
	            	enemiesNearby = true;
	    		if (fp.isInOwnTerritory() & b.getType() == Material.MOB_SPAWNER && enemiesNearby == true)
	    			e.setCancelled(true);
	        }
	    }
	}
}

