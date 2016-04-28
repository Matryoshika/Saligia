package Matryoshika.mods.saligia;

import Matryoshika.mods.saligia.blocks.saligia_Blocks;
import Matryoshika.mods.saligia.entities.EntityAcedia;
import Matryoshika.mods.saligia.entities.EntityAvaritia;
import Matryoshika.mods.saligia.entities.EntityGula;
import Matryoshika.mods.saligia.entities.EntityInvidia;
import Matryoshika.mods.saligia.entities.EntityIra;
import Matryoshika.mods.saligia.entities.EntityLuxuria;
import Matryoshika.mods.saligia.entities.EntitySuperbia;
import Matryoshika.mods.saligia.rendering.itemPaganAltarRenderer;
import Matryoshika.mods.saligia.rendering.itemSoulNexusRenderer;
import Matryoshika.mods.saligia.rendering.tileLeyLineCrystalRenderer;
import Matryoshika.mods.saligia.rendering.tilePaganAltarRenderer;
import Matryoshika.mods.saligia.rendering.tileSoulNexusRenderer;
import Matryoshika.mods.saligia.rendering.GUIHandler.HUDRenderHandler;
import Matryoshika.mods.saligia.rendering.entities.RenderAcedia;
import Matryoshika.mods.saligia.rendering.entities.RenderAvaritia;
import Matryoshika.mods.saligia.rendering.entities.RenderGula;
import Matryoshika.mods.saligia.rendering.entities.RenderInvidia;
import Matryoshika.mods.saligia.rendering.entities.RenderIra;
import Matryoshika.mods.saligia.rendering.entities.RenderLuxuria;
import Matryoshika.mods.saligia.rendering.entities.RenderSuperbia;
import Matryoshika.mods.saligia.tile.altars.TilePaganAltar;
import Matryoshika.mods.saligia.tile.soulsystem.TileLeyLineCrystal;
import Matryoshika.mods.saligia.tile.soulsystem.TileSoulNexus;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.registry.EntityRegistry;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.item.Item;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;

public class ClientProxy extends CommonProxy {
	
	int ModEntityID;

	public void registerRenderers(){
		
		RenderingRegistry.registerEntityRenderingHandler(EntityAcedia.class, new RenderAcedia(new ModelBiped(), 0));
		RenderingRegistry.registerEntityRenderingHandler(EntityAvaritia.class, new RenderAvaritia(new ModelBiped(), 0));
		RenderingRegistry.registerEntityRenderingHandler(EntityGula.class, new RenderGula(new ModelBiped(), 0));
		RenderingRegistry.registerEntityRenderingHandler(EntityInvidia.class, new RenderInvidia(new ModelBiped(), 0));
		RenderingRegistry.registerEntityRenderingHandler(EntityIra.class, new RenderIra(new ModelBiped(), 0));
		RenderingRegistry.registerEntityRenderingHandler(EntityLuxuria.class, new RenderLuxuria(new ModelBiped(), 0));
		RenderingRegistry.registerEntityRenderingHandler(EntitySuperbia.class, new RenderSuperbia(new ModelBiped(), 0));
		
		
		MinecraftForge.EVENT_BUS.register(new HUDRenderHandler());
		
		//TESR
		ClientRegistry.bindTileEntitySpecialRenderer(TilePaganAltar.class, new tilePaganAltarRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileLeyLineCrystal.class, new tileLeyLineCrystalRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileSoulNexus.class, new tileSoulNexusRenderer());
		
		//ItemBlock
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(saligia_Blocks.SoulNexus), new itemSoulNexusRenderer(new tileSoulNexusRenderer(), new TileSoulNexus()));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(saligia_Blocks.AltarPagan), new itemPaganAltarRenderer(new tilePaganAltarRenderer(), new TilePaganAltar()));
	}
	
}
