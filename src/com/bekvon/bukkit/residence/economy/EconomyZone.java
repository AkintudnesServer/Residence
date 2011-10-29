/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bekvon.bukkit.residence.economy;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Material;
import org.bukkit.util.config.ConfigurationNode;

import com.bekvon.bukkit.residence.protection.FlagPermissions;

/**
 *
 * @author Administrator
 */
public class EconomyZone {

    protected String zoneName;
    protected String description;
    protected int distance;
    protected double buyFactor;
    protected double leaseFactor;
    protected int areaFactor;

    public EconomyZone(String name)
    {
        zoneName = name;
    }
    
    public EconomyZone(String name, ConfigurationNode node)
    {
        this(name);
        this.parseZone(node);
    }

    public String getName()
    {
        return zoneName;
    }

    public String getDescription()
    {
        return description;
    }

    public int getDistance()
    {
        return distance;
    }

    public double getBuyFactor()
    {
        return buyFactor;
    }

    public double getLeaseFactor()
    {
        return leaseFactor;
    }

    public int getAreaFactor()
    {
        return areaFactor;
    }

    private void parseZone(ConfigurationNode zone) {
        if(zone == null)
            return;
        description = zone.getString("Description", "");
        distance = zone.getInt("Distance", 0);
        buyFactor = zone.getDouble("BuyFactor",1.0);
        leaseFactor = zone.getDouble("LeaseFactor",1.0);
        areaFactor = zone.getInt("AreaFactor",1);
    }
}
