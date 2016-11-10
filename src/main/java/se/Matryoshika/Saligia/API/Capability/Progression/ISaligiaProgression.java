/**
 * 
 */
package se.Matryoshika.Saligia.API.Capability.Progression;

import java.util.HashMap;

import net.minecraft.entity.player.EntityPlayer;
import se.Matryoshika.Saligia.API.Progression;

/**
 * This class was created by Matryoshika Nov 2, 2016
 * Property of Matryoshika. 
 * Part of the Saligia mod.
 * May be viewed for educational purposes.
 */
public interface ISaligiaProgression {
	
	/**
	 * The player that this instance of the capability is attached to.
	 */
	public EntityPlayer getPlayer();
	
	/**
	 * Gets the key's corresponding progression for this player.
	 */
	public boolean getPlayerProgression(EntityPlayer player, String key);
	
	/**
	 * Sets the key's corresponding progression for this player.
	 */
	public void setPlayerProgression(EntityPlayer player, String key, boolean done);
	
	/**
	 * Takes all the progress from player #1, and copies it to player #2.
	 * Mainly used to sustain progress between deaths/cross-dimensional 
	 * teleportation (Usually End-Overworld etc)
	 * <br>
	 * Can of course be used to copy between two different players.
	 */
	public void copyPlayerProgression(EntityPlayer playerOne, EntityPlayer playerTwo);

}
