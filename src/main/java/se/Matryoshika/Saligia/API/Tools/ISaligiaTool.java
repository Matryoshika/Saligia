/**
 * 
 */
package se.Matryoshika.Saligia.API.Tools;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

/**
 * This class was created by Matryoshika Oct 30, 2016
 * Property of Matryoshika. 
 * Part of the Saligia mod.
 * May be viewed for educational purposes.
 */
public interface ISaligiaTool{

	/**
	 * Saligia tiers are Vile(1), Corrupted(2), Infernal(3) & Sin(4)
	 * These should all implement higher chances, etc, depending on tier.
	 * Quite possible to return higher tiers, but please keep them uniform
	 * eg. no jumps like 4->12. 
	 * You can always return the value of an already existing tier for a "specialized"
	 * tool. Examples would be Chameleon tools in Forbidden Magic @ Thaumcraft.
	 */
	public int getToolTier();
	
	/**
	 * Edits the max durability of the tool.
	 * All Saligia tools use a base of 1024*1.5(Same as diamond)
	 * which are extended based on the tier.
	 * For infinite durability, see the {@link #infiniteDurability() infiniteDurability}
	 * boolean method.
	 */
	public int durabilityModifier();
	
	/**
	 * Simply a boolean method that is checked during initialization. If it returns true,
	 * the tool's durability should return -1 (infinite)
	 */
	public boolean infiniteDurability();
	
	/**
	 * Allows one to modify the speed of the tool during runtime.
	 * Axe, pick etc become slower the less durability they have.
	 */
	public int speedModifier();
	
	/**
	 * Changes the damage against foes of the tool during runtime.
	 * Axe, pick etc become duller the less durability they have.
	 */
	public int damageModifier();
	
}
