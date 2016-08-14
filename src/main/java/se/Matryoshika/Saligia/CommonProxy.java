package se.Matryoshika.Saligia;

import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import se.Matryoshika.Saligia.API.Content.BlockRegistryInjector;
import se.Matryoshika.Saligia.API.Content.ItemRegistryInjector;
import se.Matryoshika.Saligia.Content.ContentRegistry;
import se.Matryoshika.Saligia.Utils.ConfigHandler;
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

	public void registerEntities(){
		
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
		ItemRegistryInjector.registerSaligiaItem();
	}
	
	public void init(FMLInitializationEvent event){
		
	}
	
	public void postInit(FMLPostInitializationEvent event){
		SaligiaBaseMultiblocks.register();
	}
}
