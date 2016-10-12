/**
 * 
 */
package se.Matryoshika.Saligia.Content.Tiles.Utility;

import net.minecraft.block.Block;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import se.Matryoshika.Saligia.Content.Blocks.Utility.UtilityBlock;
import se.Matryoshika.Saligia.Content.Tiles.CustomTileClass;

/**
 * This class was created by Matryoshika Oct 10, 2016
 * Property of Matryoshika. 
 * Part of the Saligia mod.
 * May be viewed for educational purposes.
 */
public class TileBlockPlacer extends CustomTileClass implements ITickable{
	
	public static int TIMER = 0;
	public static final int LIMIT = 2 * 20;
	private final int RADII = 5;
	private final int HEIGHT = 2;
	
	private boolean hasEditedBlockColour = false;
	
	public TileBlockPlacer(){
		this.name = "blockplacer";
	}
	@Override
	public void update() {
		if(worldObj.isRemote)
			return;
		
		TIMER++;
		if(TIMER >= LIMIT){
			TIMER = 0;
			activate();
		}
	}
	
	@Override
	public double[] colourScheme() {
		return new double[]{0, 80, 80};
	}
	
	private void activate(){
		
	}

}
