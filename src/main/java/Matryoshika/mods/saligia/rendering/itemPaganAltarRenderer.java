/**
 * This code was made by Matryoshika at 7:35:07 PM Apr 27, 2016.
 * This code is property of Matryoshika.
 * It may be modified for personal use, but may then not be distributed.
 *
 * Please fork & create a pull-request if the source should change.
 */
package Matryoshika.mods.saligia.rendering;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;
import net.minecraftforge.client.IItemRenderer.ItemRendererHelper;

/**
 * @author Matryoshika
 */
public class itemPaganAltarRenderer implements IItemRenderer{


	TileEntitySpecialRenderer render;
	private TileEntity tileentity;

	public itemPaganAltarRenderer(TileEntitySpecialRenderer render, TileEntity tileentity) {
	    this.render = render;
	    this.tileentity=tileentity;

	}
	
	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		return true;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
		return true;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		if (type == IItemRenderer.ItemRenderType.ENTITY)
			GL11.glTranslatef(-0.2F, 0.0F, -0.2F);
			GL11.glTranslated(0, -0.2, 0);
			this.render.renderTileEntityAt(tileentity, 0.0D, 0.0D, 0.0D, 0.0F);
	}

}
