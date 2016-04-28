package Matryoshika.mods.saligia.rendering.GUIHandler;

import java.text.DecimalFormat;

import Matryoshika.mods.saligia.API.ISaligiaBlockSensor;
import Matryoshika.mods.saligia.blocks.saligia_Blocks;
import Matryoshika.mods.saligia.items.ItemLibroSaligia;
import Matryoshika.mods.saligia.items.saligia_Items;
import Matryoshika.mods.saligia.rendering.GUI.GUIStorage;
import Matryoshika.mods.saligia.rendering.GUI.HUDRitual;
import Matryoshika.mods.saligia.rendering.GUI.HUDTransferringFrom;
import Matryoshika.mods.saligia.rendering.GUI.HUDTransferringTo;
import Matryoshika.mods.saligia.tile.TileRitualCOTH;
import Matryoshika.mods.saligia.tile.soulsystem.TileSoulBrazier;
import Matryoshika.mods.saligia.tile.soulsystem.TileSoulNexus;
import Matryoshika.mods.saligia.tile.soulsystem.TileSoulObelisk;
import Matryoshika.mods.saligia.utils.math;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;

public class HUDRenderHandler{
	
	int X;
	int Y;
	int Z;

	@SubscribeEvent(priority = EventPriority.LOWEST)
	@SideOnly(Side.CLIENT)
	public void onRenderExperienceBar(RenderGameOverlayEvent event){
		
		if(event.isCancelable() || event.type != ElementType.EXPERIENCE){      
	      return;
	    }
		
		EntityPlayer player = Minecraft.getMinecraft().thePlayer;
	    if (player == null) return;
	    
	    if(Minecraft.getMinecraft().objectMouseOver != null && player.worldObj.isRemote && player.inventory.getCurrentItem() != null && player.inventory.getCurrentItem().getItem() == saligia_Items.SoulCrucible){
	    	X =+ Minecraft.getMinecraft().objectMouseOver.blockX;
			Y =+ Minecraft.getMinecraft().objectMouseOver.blockY;
			Z =+ Minecraft.getMinecraft().objectMouseOver.blockZ;
			TileEntity tile = player.worldObj.getTileEntity(X, Y, Z);
			if(tile instanceof TileSoulBrazier ||tile instanceof TileSoulObelisk ||tile instanceof TileSoulNexus){
				if(!player.isSneaking()){
		    		new HUDTransferringTo(Minecraft.getMinecraft());
		    	}
		    	if(player.isSneaking()){
		    		new HUDTransferringFrom(Minecraft.getMinecraft());
		    	}
			}
	    }
	    
	    
	    if(Minecraft.getMinecraft().objectMouseOver!=null && player.worldObj.isRemote){
			X =+ Minecraft.getMinecraft().objectMouseOver.blockX;
			Y =+ Minecraft.getMinecraft().objectMouseOver.blockY;
			Z =+ Minecraft.getMinecraft().objectMouseOver.blockZ;
			TileEntity tile = player.worldObj.getTileEntity(X, Y, Z);
	    
			if(tile instanceof TileSoulBrazier && !(tile instanceof TileSoulObelisk)){
				TileSoulBrazier tb = (TileSoulBrazier) tile;
				double brazier = ((double)tb.amount / Matryoshika.mods.saligia.utils.math.SoulBrazierMax())*100;
				String percentage = new DecimalFormat("##.#").format(brazier).toString();
				String type = saligia_Blocks.SoulBrazier.getLocalizedName();
				Double bar = brazier;
				new GUIStorage(Minecraft.getMinecraft(), percentage, bar, type);
			}
			if(tile instanceof TileSoulObelisk && !(tile instanceof TileSoulNexus)){
				TileSoulObelisk to = (TileSoulObelisk) tile;
				double obelisk = ((double)to.amount / Matryoshika.mods.saligia.utils.math.SoulObeliskMax())*100;
				String percentage = new DecimalFormat("##.#").format(obelisk).toString();
				String type = saligia_Blocks.SoulObelisk.getLocalizedName();
				Double bar = obelisk;
				new GUIStorage(Minecraft.getMinecraft(), percentage, bar, type);
			}
			if(tile instanceof TileSoulNexus){
				TileSoulNexus tn = (TileSoulNexus) tile;
				double nexus = ((double)tn.amount / Matryoshika.mods.saligia.utils.math.SoulNexusMax())*100;
				String percentage = new DecimalFormat("##.#").format(nexus).toString();
				String type = saligia_Blocks.SoulNexus.getLocalizedName();
				Double bar = nexus;
				new GUIStorage(Minecraft.getMinecraft(), percentage, bar, type);
			}
			
			if(tile instanceof ISaligiaBlockSensor){
				Block block = player.worldObj.getBlock(X, Y, Z);
				String name = block.getLocalizedName();
				
				new HUDRitual(Minecraft.getMinecraft(), name);
			}
	    }
	}
	
	public void altarCrafting1(World world, Item item1, Item item2, Item result){
		
	}
}
