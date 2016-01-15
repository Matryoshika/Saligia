package Matryoshika.mods.saligia;


import Matryoshika.mods.saligia.rendering.GUIHandler.MSGuiHandler;
import Matryoshika.mods.saligia.rendering.GUIHandler.HUDRenderHandler;
import Matryoshika.mods.saligia.utils.ConfigHandler;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;

public class CommonProxy {
	
	
	public void registerRenderers(){
		
	}
	
	public void registerEntities(){
		
	}
	
	public void initCommon() {
	       NetworkRegistry.INSTANCE.registerGuiHandler(saligia.MODID, new MSGuiHandler());
	       
	}

	
	public void initClient() {
		
	}

	public void initServer() {

	}
	
	public void preInit(FMLPreInitializationEvent event){
	}
	
}
