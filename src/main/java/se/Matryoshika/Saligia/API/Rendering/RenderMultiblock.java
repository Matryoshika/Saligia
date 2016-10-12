/**
 * 
 */
package se.Matryoshika.Saligia.API.Rendering;

import org.lwjgl.opengl.GL11;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import se.Matryoshika.Saligia.API.Rituals.RitualRegistry;
import se.Matryoshika.Saligia.Content.Tiles.TileRitual;

/**
 * This class was created by Matryoshika Oct 1, 2016
 * Property of Matryoshika. 
 * Part of the Saligia mod.
 * May be viewed for educational purposes.
 */
@SideOnly(Side.CLIENT)
public class RenderMultiblock{
	

	public static void renderAt(TileRitual te, double x, double y, double z) {

		
		//Get the multiblock in an array of Object arrays from the registry, which consists of x, y, z, Block
		Object[][] MULTIBLOCK = RitualRegistry.getMultiblock(te.renderMultiBlockKey);
		if(MULTIBLOCK == null)
			return;
		
		//If this tile isn't the one that was whacked with the Ritual Activator, return
		if(!te.isClicked)
			return;
			
		GlStateManager.pushAttrib();
		GlStateManager.pushMatrix();

		GlStateManager.translate(x, y, z);
    	for(Object[] object : MULTIBLOCK){
 	    
    		int dx = (Integer)object[0];
			int dy = (Integer)object[1];
			int dz = (Integer)object[2];
			Block block =  (Block)  object[3];
			
			GlStateManager.translate(0.5, 0.5, 0.5);
			GlStateManager.enableBlend();
			//Render missing blocks as ghost-blocks
			GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE_MINUS_SRC_COLOR);
			if(te.getWorld().isAirBlock(te.getPos().add(dx, dy, dz)))
				renderMultiblock(te, dx, dy, dz, block);
			//return to normal blend value
			GL11.glBlendFunc(GL11.GL_ONE_MINUS_SRC_COLOR, GL11.GL_ONE);
			
			GlStateManager.disableBlend();
			GlStateManager.translate(-0.5, -0.5, -0.5);
			
    	}
    	
		
		GlStateManager.popMatrix();
		GlStateManager.popAttrib();
		
	}
	
	private static void renderMultiblock(TileRitual te, int dx, int dy, int dz, Block block){
    	
		GlStateManager.pushMatrix();
		//Spin me right round
    	long angle = (System.currentTimeMillis() / 40) % 360;
    	
    	//offset the rendered block from the Tile, using the Multiblock's registered x, y & z
    	GlStateManager.translate(dx, dy, dz);
    	
    	GlStateManager.scale(0.5, 0.5, 0.5);
    	GlStateManager.rotate(angle, 0, 1, 0);
    	
    	//Render the block in the world
    	Minecraft.getMinecraft().getRenderItem().renderItem(new ItemStack(block), ItemCameraTransforms.TransformType.NONE);
    	
    	//Un-rotate the rotation
    	GlStateManager.rotate(-angle, 0, 1, 0);
    	GlStateManager.scale(2, 2, 2);
    	
    	GlStateManager.translate(-dx, -dy, -dz);
    	GlStateManager.popMatrix();
        
	}

}
