package Matryoshika.mods.saligia.rendering.GUI;

import org.lwjgl.opengl.GL11;

import Matryoshika.mods.saligia.saligia;
import Matryoshika.mods.saligia.tile.ContainerRunicScribe;
import Matryoshika.mods.saligia.tile.TileRunicScribe;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

public class GUIRunicScribe extends GuiContainer{
	
	public static ResourceLocation res = new ResourceLocation(saligia.MODID + ":" + "textures/gui/RunicScribeNew.png");

	protected int xSize = 256;
    protected int ySize = 256;
	
	public GUIRunicScribe (InventoryPlayer inventoryPlayer, TileRunicScribe tileEntity) {
    //the container is instanciated and passed to the superclass for handling
		super(new ContainerRunicScribe(inventoryPlayer, tileEntity));
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int param1, int param2) {
		//draw text and stuff here
		//the parameters for drawString are: string, x, y, color
		fontRendererObj.drawString("Runic Scribe", (width - xSize) / 20, (height-110 - ySize) , 4210752);
		//draws "Inventory" or your regional equivalent
		fontRendererObj.drawString(StatCollector.translateToLocal("container.inventory"), -34, ySize - 185 + 2, 4210752);
		fontRendererObj.drawString("Info", 140, ySize-290, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
		//draw your Gui here, only thing you need to change is the path
		mc.renderEngine.getTexture(res);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.bindTexture((ResourceLocation) res);
		int x = (width - xSize) / 2;
		int y = (height - ySize) / 2;
		this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
}	
	
}
