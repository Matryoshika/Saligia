package Matryoshika.mods.saligia.rendering.GUI.book.relics;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;

import Matryoshika.mods.saligia.saligia;
import Matryoshika.mods.saligia.blocks.saligia_Blocks;
import Matryoshika.mods.saligia.items.saligia_Items;
import Matryoshika.mods.saligia.rendering.GUI.book.GUILibroSaligia;
import Matryoshika.mods.saligia.rendering.GUIHandler.MSGuiHandler;
import cpw.mods.fml.client.config.GuiButtonExt;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

public class GUILibroSaligiaRelics extends GUILibroSaligia {
	
	public static String RELIC_TEXT = StatCollector.translateToLocal("");
	
	public GUILibroSaligiaRelics() {
		super();
		


	}
	
	@Override
	public void initGui(){
		super.initGui();
		int posX = (xSize);
		int posY = (this.height - ySize);
		this.buttonList.clear();
		
		GL11.glScalef(0.5F, 0.5F, 0.5F);
		this.buttonList.add(new GuiButtonExt(0, (int)(bookX*1.13), (int)(bookY*4.85), 40, 15, (StatCollector.translateToLocal("saligia.book.return"))));
	}
	
	
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int mouseX, int mouseY) {
		
		mc.renderEngine.getTexture(res);
		
		
		
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.bindTexture((ResourceLocation) res);

		
		drawTexturedModalRect(bookX, bookY, 0, 0, 200, 256);
		
		
		
		//GL11.glScalef(1.2F, 1.2F, 1.2F);
		
		
		Tessellator t = Tessellator.instance;
		GL11.glPushMatrix();

		GL11.glScalef(0.66F, 0.66F, 0.66F);
		
		//String RELIC_TEXTFORMAT = RELIC_TEXT.split();
		
		
		fontRendererObj.drawString(StatCollector.translateToLocal("saligia.book.relics1"),(int) (bookX*1.72), (int) (bookY*2), 4210752);
		fontRendererObj.drawString(StatCollector.translateToLocal("saligia.book.relics2"),(int) (bookX*1.72), (int) (bookY*2.3), 4210752);
		fontRendererObj.drawString(StatCollector.translateToLocal("saligia.book.relics3"),(int) (bookX*1.72), (int) (bookY*2.6), 4210752);
		fontRendererObj.drawString(StatCollector.translateToLocal("saligia.book.relics4"),(int) (bookX*1.72), (int) (bookY*2.9), 4210752);
		fontRendererObj.drawString(StatCollector.translateToLocal("saligia.book.relics5"),(int) (bookX*1.72), (int) (bookY*3.2), 4210752);
		fontRendererObj.drawString(StatCollector.translateToLocal("saligia.book.relics6"),(int) (bookX*1.72), (int) (bookY*3.5), 4210752);
		fontRendererObj.drawString(StatCollector.translateToLocal("saligia.book.relics7"),(int) (bookX*1.72), (int) (bookY*4.1), 4210752);
		fontRendererObj.drawString(StatCollector.translateToLocal("saligia.book.relics8"),(int) (bookX*1.72), (int) (bookY*4.4), 4210752);
		fontRendererObj.drawString(StatCollector.translateToLocal("saligia.book.relics9"),(int) (bookX*1.72), (int) (bookY*4.7), 4210752);
		fontRendererObj.drawString(StatCollector.translateToLocal("saligia.book.relics10"),(int) (bookX*1.72), (int) (bookY*5), 4210752);
		
		/*if(mouseX >= (bookX*1.2) && mouseX <= (bookX*1.5) && mouseY >=bookY*2.9 && mouseY <= bookY*3.2){
			System.out.println("It's here, Boss");
		}*/
		
		
		GL11.glScalef(1.51F, 1.51F, 1.51F);
		
		GL11.glPopMatrix();
	}
	
	@Override
	protected void keyTyped(char par1, int par2){
		 if (par2 == 1 || par2 == this.mc.gameSettings.keyBindInventory.getKeyCode()){
			 this.mc.thePlayer.closeScreen();
			 this.mc.thePlayer.openGui((Object) saligia.instance, MSGuiHandler.GUI_LIBROSALIGIA, mc.theWorld, (int) mc.thePlayer.posX, (int) mc.thePlayer.posY, (int) mc.thePlayer.posZ);
		 }
	}
	
	@Override
	public void onGuiClosed() {
		//this.mc.thePlayer.openGui((Object) saligia.instance, MSGuiHandler.GUI_LIBROSALIGIA, mc.theWorld, (int) mc.thePlayer.posX, (int) mc.thePlayer.posY, (int) mc.thePlayer.posZ);
	}
	
	public void actionPerformed(GuiButton button){
		switch(button.id){
			case 0:{
				this.mc.displayGuiScreen(null);
				mc.thePlayer.openGui((Object) saligia.instance, MSGuiHandler.GUI_LIBROSALIGIA, mc.theWorld, (int) mc.thePlayer.posX, (int) mc.thePlayer.posY, (int) mc.thePlayer.posZ);
			break;
			}
		}
	}

}
