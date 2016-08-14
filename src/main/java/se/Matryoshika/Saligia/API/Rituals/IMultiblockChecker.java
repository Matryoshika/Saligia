/**
 * 
 */
package se.Matryoshika.Saligia.API.Rituals;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * This class was created by Matryoshika Aug 14, 2016
 * Property of Matryoshika. 
 * Part of the Saligia mod.
 * May be viewed for educational purposes.
 */
public interface IMultiblockChecker {
	
	public boolean isMultiblockIntact(World world, Object[][] multiblock, BlockPos pos);

}
