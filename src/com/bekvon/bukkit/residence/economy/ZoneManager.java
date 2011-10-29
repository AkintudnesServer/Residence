/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bekvon.bukkit.residence.economy;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.util.config.Configuration;

import com.bekvon.bukkit.residence.Residence;
import com.bekvon.bukkit.residence.protection.ClaimedResidence;

/**
 *
 * @author Administrator
 */
public class ZoneManager {
    protected List<EconomyZone> zones;

    public ZoneManager(Configuration config)
    {
        zones = new ArrayList<EconomyZone>();
        this.readZones(config);
    }
    
    public void printZoneInfo(Location loc, Player player) {
        EconomyZone zone = getZone(loc);
        if (zone != null) {
            player.sendMessage("§eZone: §a" + zone.getDescription());
        }
    }

    public EconomyZone getZone(Location loc) {
        int distX = loc.getBlockX();
        int distZ = loc.getBlockZ();
        int distXC = (distX < 0) ? 1 - (distX + 1) / 16 : distX / 16; 
        int distZC = (distZ < 0) ? 1 - (distZ + 1) / 16 : distZ / 16; 
        int distC = Math.max(distXC, distZC);
        EconomyZone theZone = null;
        int zoneDistC = -1;
        for (EconomyZone zone : zones) {
            if ((zone.getDistance() <= distC) && (zone.getDistance() > zoneDistC)) {
                zoneDistC = zone.getDistance();
                theZone = zone;
            }
        }
        return theZone;
    }

    private void readZones(Configuration config) {
        List<String> keys = config.getKeys("Zones");
        if (keys != null) {
            for (String key : keys) {
                try {
                    EconomyZone zone = new EconomyZone(key,config.getNode("Zones." + key));
                    zones.add(zone);
                    //System.out.println("Debug: read zone " + key + " description: " + zone.getDescription() + " distance: " + zone.getDistance() + " buyfactor:" + zone.getBuyFactor() + " leasefactor:" + zone.getLeaseFactor());
                } catch (Exception ex) {
                    System.out.println("Failed to load zone:" + key);
                }
            }
        }
    }
}
