package Matryoshika.mods.saligia.rendering;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.IItemRenderer;

public class itemSoulNexusRenderer implements IItemRenderer{


	TileEntitySpecialRenderer render;
	private TileEntity tileentity;

	public itemSoulNexusRenderer(TileEntitySpecialRenderer render, TileEntity tileentity) {
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
			GL11.glScalef(0.5F, 0.5F, 0.5F);
			GL11.glScalef(0.75F, 0.75F, 0.75F);
			GL11.glTranslated(0, -1, 0);
			this.render.renderTileEntityAt(tileentity, 0.0D, 0.0D, 0.0D, 0.0F);
			GL11.glScalef(1.25F, 1.25F, 1.25F);
			GL11.glScalef(2F, 2F, 2F);
	}

}
