package se.Matryoshika.Saligia;

import java.io.File;
import java.util.UUID;

import com.mojang.authlib.GameProfile;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.common.util.FakePlayerFactory;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import se.Matryoshika.Saligia.API.Capability.Progression.CapabilitySaligiaProgression;
import se.Matryoshika.Saligia.Content.Progression.ProgressionRegistry;
import se.Matryoshika.Saligia.Events.MainEventHandler;
import se.Matryoshika.Saligia.Rendering.GUI.GuiHandler;
import se.Matryoshika.Saligia.Utils.CreativeTabSaligia;

/**
 * This class was created by Matryoshika Aug 8, 2016
 * Property of Matryoshika. 
 * Part of the Saligia mod.
 * May be viewed for educational purposes.
 */

@Mod(modid = Saligia.MODID, version = Saligia.VERSION, name="Saligia")
public class Saligia{
	
    public static final String MODID = "saligia";
    public static final String VERSION = "0.0.1";
    
    private static FakePlayer fakePlayer;
    private static String directory;
    
    
	public File configDir;
    
    
    
    public static final CreativeTabSaligia saligiaTab = new CreativeTabSaligia("Saligia"){
    	@Override
    	@SideOnly(Side.CLIENT)
    	public Item getTabIconItem(){
    		return new ItemStack(Blocks.ANVIL).getItem();
    	}
    };
    
    
    
    @Instance(MODID)
    public static Saligia instance;
    
    
    @SidedProxy(clientSide="se.Matryoshika.Saligia.ClientProxy",serverSide="se.Matryoshika.Saligia.CommonProxy")
    public static CommonProxy proxy;
    
    @Mod.EventHandler
    public void registerBlocks(RegistryEvent.Register<Block> event){
    	//BlockRegistry.registerBlocks(event);
    }
    
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event){
    	directory = event.getModConfigurationDirectory().getAbsolutePath();
    	
    	configDir = new File(event.getModConfigurationDirectory(), "Saligia");
		
		proxy.preInit(event);
		proxy.registerRenderers();
		
		CapabilitySaligiaProgression.register();
    }
    
    @Mod.EventHandler
    public void init(FMLInitializationEvent event){
    	
    	proxy.init(event);
    	MinecraftForge.EVENT_BUS.register(MainEventHandler.class);
    	NetworkRegistry.INSTANCE.registerGuiHandler(Saligia.instance, new GuiHandler());
    	
    	ProgressionRegistry.registerProgression();
    }
    
    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event){
        
    	
    	proxy.postInit(event);
    }
    
    public static Saligia getMod(){
    	return instance;
    }
    
    public File getConfigFolder(){
    	return configDir;
    }
    
    public static FakePlayer getFakePlayer(){
    	if(fakePlayer == null)
    		fakePlayer = FakePlayerFactory.get((WorldServer) DimensionManager.getWorld(0), new GameProfile(UUID.fromString("14159265-3589-7932-3846-264338327950"), "[Saligia]"));
    	return fakePlayer;
    }
    
    public static String getDirectory(){
    	return directory;
    }
}
