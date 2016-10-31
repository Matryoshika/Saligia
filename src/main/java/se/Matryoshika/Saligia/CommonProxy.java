package se.Matryoshika.Saligia;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import se.Matryoshika.Saligia.API.Content.BlockRegistryInjector;
import se.Matryoshika.Saligia.API.Content.ItemRegistryInjector;
import se.Matryoshika.Saligia.API.Soulsystem.AnimunLogic;
import se.Matryoshika.Saligia.API.Soulsystem.IAnimun;
import se.Matryoshika.Saligia.Content.ContentRegistry;
import se.Matryoshika.Saligia.Content.Tiles.TileRitual;
import se.Matryoshika.Saligia.Utils.ConfigHandler;
import se.Matryoshika.Saligia.Utils.NormalRecipeRegistry;
import se.Matryoshika.Saligia.Utils.SaligiaBaseMultiblocks;
import se.Matryoshika.Saligia.Utils.SaligiaFuelHandler;

/**
 * This class was created by Matryoshika Aug 8, 2016
 * Property of Matryoshika. 
 * Part of the Saligia mod.
 * May be viewed for educational purposes.
 */
public class CommonProxy {
	
	public void registerRenderers(){
		
	}
	
	public void preInit(FMLPreInitializationEvent event){
		//Just adds items & blocks to a list.
		ContentRegistry.registerBlocks();
		ContentRegistry.registerItems();
		
		ContentRegistry.registerTiles();
		GameRegistry.registerFuelHandler(SaligiaFuelHandler.getFuelHandler());
		
		ConfigHandler.readConfigs(event);
		
		//Actual registration is done from the API
		BlockRegistryInjector.registerSaligiaBlock();
		BlockRegistryInjector.registerSaligiaRitualBlock();
		BlockRegistryInjector.registerSaligiaUtilityBlock();
		ItemRegistryInjector.registerSaligiaItem();
		
		NormalRecipeRegistry.registerRecipes();
	}
	
	public void init(FMLInitializationEvent event){
		
	}
	
	public void postInit(FMLPostInitializationEvent event){
		SaligiaBaseMultiblocks.register();
	}
	
	public void renderMultiblock(TileRitual te, double x, double y, double z){
		
	}
	
	public void renderEntity(Entity entity, Block block){
		
	}
	

	public void particle(double x, double y, double z, int r, int g, int b) {

	}
}
