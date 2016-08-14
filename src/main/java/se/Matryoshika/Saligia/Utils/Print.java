/**
 * 
 */
package se.Matryoshika.Saligia.Utils;

import net.minecraft.util.math.BlockPos;

/**
 * This class was created by Matryoshika Aug 13, 2016
 * Property of Matryoshika. 
 * Part of the Saligia mod.
 * May be viewed for educational purposes.
 */
public class Print {
	
	public static String printXYZ(int x, int y, int z){
		return x+", "+y+", "+z;
	}
	
	public static String printPos(BlockPos pos){
		return pos.getX()+", "+pos.getY()+", "+pos.getZ();
	}

}
