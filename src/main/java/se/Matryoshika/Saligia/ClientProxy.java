package se.Matryoshika.Saligia;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import se.Matryoshika.Saligia.API.Rendering.RenderMultiBlockEvent;
import se.Matryoshika.Saligia.API.Rendering.RenderMultiblock;
import se.Matryoshika.Saligia.API.Rendering.RenderingRegistryInjector;
import se.Matryoshika.Saligia.Content.ContentRegistry;
import se.Matryoshika.Saligia.Content.Blocks.Utility.FileCreatorUtility;
import se.Matryoshika.Saligia.Content.Tiles.TileCauldron;
import se.Matryoshika.Saligia.Content.Tiles.TileGhastSpawner;
import se.Matryoshika.Saligia.Content.Tiles.TileRitual;
import se.Matryoshika.Saligia.Content.Tiles.Utility.TileSmelter;
import se.Matryoshika.Saligia.Events.ClientTicks;
import se.Matryoshika.Saligia.Rendering.RenderRegister;
import se.Matryoshika.Saligia.Rendering.Tiles.TileCauldronTESR;
import se.Matryoshika.Saligia.Rendering.Tiles.TileGhastSpawnerTESR;
import se.Matryoshika.Saligia.Rendering.Tiles.TileSmelterTESR;

/**
 * This class was created by Matryoshika Aug 8, 2016
 * Property of Matryoshika. 
 * Part of the Saligia mod.
 * May be viewed for educational purposes.
 */
@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy{
	
	@Override
	public void postInit(FMLPostInitializationEvent event){
		MinecraftForge.EVENT_BUS.register(ClientTicks.class);
	}
	
	@Override
	public void registerRenderers(){
		OBJLoader.INSTANCE.addDomain(Saligia.MODID);
		RenderRegister.registerRenderers();
		RenderingRegistryInjector.regWithLocation((Block)ContentRegistry.BLOCK_RITUAL_COTH, new ModelResourceLocation(Saligia.MODID+":"+ContentRegistry.BLOCK_RITUAL_COTH.getUnlocName(), "inventory"), 0);
		
		ClientRegistry.bindTileEntitySpecialRenderer(TileSmelter.class, new TileSmelterTESR());
		ClientRegistry.bindTileEntitySpecialRenderer(TileGhastSpawner.class, new TileGhastSpawnerTESR());
		ClientRegistry.bindTileEntitySpecialRenderer(TileCauldron.class, new TileCauldronTESR());
	}
	
	@Override
	public void init(FMLInitializationEvent event){
		MinecraftForge.EVENT_BUS.register(RenderMultiBlockEvent.class);
		
		FileCreatorUtility.createFiles();
	}
	
	@Override
	public void renderMultiblock(TileRitual te, double x, double y, double z){
		RenderMultiblock.renderAt(te, x, y, z);
	}
	
	@Override
	public void particle(double x, double y, double z, int r, int g, int b, Particle particle) {
		//GOD DAMNIT have to change spawning particles to an Enum or something. >_< Particle is client-side only class = CommonProxy gonna 'splode
		Minecraft.getMinecraft().effectRenderer.addEffect(particle);
	}

}
