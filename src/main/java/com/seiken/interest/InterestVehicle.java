package com.seiken.interest;

import org.bukkit.entity.Player;
import org.bukkit.event.vehicle.VehicleListener;
import org.bukkit.event.vehicle.VehicleMoveEvent;

public class InterestVehicle extends VehicleListener {

    private final Interest plugin;

    public InterestVehicle( final Interest plugin )
    {
        this.plugin = plugin;
    }
    
    @Override
    public void onVehicleMove( VehicleMoveEvent event )
    {
    	if ( event.getVehicle().getPassenger() instanceof Player )
    		plugin.updateCurrent( ( Player )event.getVehicle().getPassenger() );
    }
    
}