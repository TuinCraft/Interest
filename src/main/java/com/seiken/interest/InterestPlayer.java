package com.seiken.interest;

import org.bukkit.event.player.*;

public class InterestPlayer extends PlayerListener {

    private final Interest plugin;

    public InterestPlayer( final Interest plugin )
    {
        this.plugin = plugin;
    }
    
    @Override
    public void onPlayerJoin(PlayerJoinEvent event) {
        plugin.updateCurrent(event.getPlayer());
    }

    @Override
    public void onPlayerQuit( PlayerQuitEvent event )
    {
    	plugin.removeCurrent( event.getPlayer() );
    }

    
    
    @Override
    public void onPlayerMove( PlayerMoveEvent event )
    {
    	plugin.updateCurrent( event.getPlayer() );
    }

}