package com.seiken.interest;

import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerMoveEvent;

public class InterestPlayer extends PlayerListener {

    private final Interest plugin;

    public InterestPlayer( final Interest plugin )
    {
        this.plugin = plugin;
    }
    
    @Override
    public void onPlayerJoin( PlayerEvent event )
    {
        plugin.updateCurrent( event.getPlayer() );
    }
    
    @Override
    public void onPlayerQuit( PlayerEvent event )
    {
    	plugin.removeCurrent( event.getPlayer() );
    }

    
    
    @Override
    public void onPlayerMove( PlayerMoveEvent event )
    {
    	plugin.updateCurrent( event.getPlayer() );
    }

}