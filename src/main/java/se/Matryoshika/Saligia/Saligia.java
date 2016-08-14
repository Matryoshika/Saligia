package se.Matryoshika.Saligia;

import java.io.File;

import org.apache.logging.log4j.Level;

import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import se.Matryoshika.Saligia.Content.ContentRegistry;
import se.Matryoshika.Saligia.Utils.ConfigHandler;
import se.Matryoshika.Saligia.Utils.CreativeTabSaligia;

/**
 * This class was created by Matryoshika Aug 8, 2016
 * Property of Matryoshika. 
 * Part of the Saligia mod.
 * May be viewed for educational purposes.
 */

@Mod(modid = Saligia.MODID, version = Saligia.VERSION, name="Underworld")
public class Saligia{
	
    public static final String MODID = "saligia";
    public static final String VERSION = "0.0.1";
    
    
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
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event){
    	configDir = new File(event.getModConfigurationDirectory(), "Saligia");
		
		proxy.preInit(event);
		proxy.registerRenderers();
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event){
        
    	
    	proxy.init(event);
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event){
        
    	
    	proxy.postInit(event);
    }
    
    public static Saligia getMod(){
    	return instance;
    }
    
    public File getConfigFolder(){
    	return configDir;
    }
}
