package net.cyberflame.antienemysilk.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.massivecraft.factions.Board;
import com.massivecraft.factions.FLocation;
import com.massivecraft.factions.FPlayer;
import com.massivecraft.factions.FPlayers;
import com.massivecraft.factions.Faction;
import de.dustplanet.silkspawners.events.SilkSpawnersSpawnerBreakEvent;


public class SpawnerBreakListener implements Listener
{
	@EventHandler
	public void onSpawnerBreak(SilkSpawnersSpawnerBreakEvent e)
	{
		Player p = e.getPlayer();
		FPlayer fp = FPlayers.getInstance().getByPlayer(p);
		fp.checkIfNearbyEnemies();
		p.sendMessage(p.getName() + fp.getName() + fp + p + fp.isInOwnTerritory() + fp.hasEnemiesNearby());
		if (fp.isInOwnTerritory() && fp.hasEnemiesNearby())
			e.setCancelled(true);
	}
}
