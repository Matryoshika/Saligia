/**
 * 
 */
package se.Matryoshika.Saligia.Rendering.Tiles;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import se.Matryoshika.Saligia.Content.Tiles.TileGhastSpawner;

/**
 * This class was created by Matryoshika Oct 23, 2016
 * Property of Matryoshika. 
 * Part of the Saligia mod.
 * May be viewed for educational purposes.
 */
@SideOnly(Side.CLIENT)
public class TileGhastSpawnerTESR extends TileEntitySpecialRenderer<TileGhastSpawner>{
	static Entity entity = null;
	
	
	public void renderTileEntityAt(TileGhastSpawner te, double x, double y, double z, float partialTicks, int destroyStage){
        GlStateManager.pushMatrix();
        GlStateManager.translate((float)x + 0.5F, (float)y, (float)z + 0.5F);
        renderMob(te, x, y, z, partialTicks);
        GlStateManager.popMatrix();
    }

    /**
     * Render the mob inside the mob spawner.
     */
    public static void renderMob(TileGhastSpawner te, double posX, double posY, double posZ, float partialTicks){


    	if(entity == null)
    		entity = new EntityGhast(te.getWorld());
    	
        if (entity != null){
            GlStateManager.pushMatrix();
            GlStateManager.translate(0.0F, 0.4F, 0.0F);
            GlStateManager.rotate((System.currentTimeMillis() / 60) % 360, 0.0F, 1.0F, 0.0F);
            GlStateManager.translate(0.0F, 0.15F, 0.0F);
            GlStateManager.rotate(-30.0F, 1.0F, 0.0F, 0.0F);
            GlStateManager.scale(0.09, 0.09, 0.09);
            entity.setLocationAndAngles(posX, posY, posZ, 0.0F, 0.0F);
            Minecraft.getMinecraft().getRenderManager().doRenderEntity(entity, 0.0D, 0.0D, 0.0D, 0.0F, partialTicks, false);
            GlStateManager.popMatrix();
        }
    }

}
