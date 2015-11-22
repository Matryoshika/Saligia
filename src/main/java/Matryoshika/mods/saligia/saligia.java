package Matryoshika.mods.saligia;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import Matryoshika.mods.saligia.blocks.saligia_Blocks;
import Matryoshika.mods.saligia.enchantments.EnchantmentPurify;
import Matryoshika.mods.saligia.entities.EntityAcedia;
import Matryoshika.mods.saligia.entities.EntityAvaritia;
import Matryoshika.mods.saligia.entities.EntityGula;
import Matryoshika.mods.saligia.entities.EntityInvidia;
import Matryoshika.mods.saligia.entities.EntityIra;
import Matryoshika.mods.saligia.entities.EntityLuxuria;
import Matryoshika.mods.saligia.entities.EntitySuperbia;
import Matryoshika.mods.saligia.entities.saligia_Entities;
import Matryoshika.mods.saligia.items.saligia_Items;
import Matryoshika.mods.saligia.rendering.GUIHandler.MSGuiHandler;
import Matryoshika.mods.saligia.utils.ConfigHandler;
import Matryoshika.mods.saligia.utils.CreativeTabMatryoshika;
import Matryoshika.mods.saligia.utils.SinnersDelight;
import Matryoshika.mods.saligia.utils.matryoshikaEventHandler;
import Matryoshika.mods.saligia.worldgen.worldGenAltar;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;

	@Mod(modid=saligia.MODID, version=saligia.VERSION, name="saligia")
		public class saligia {	
		public static final String MODID="saligia";
		public static final String LOCALIZING = "MS";
		public static final String VERSION="1.0";
		
		
		public static String[] allowedGeneratorsArray;

	public static final CreativeTabMatryoshika MatryoshikaTab = new CreativeTabMatryoshika("Matryoshika's Sinners"){
		@Override
		@SideOnly(Side.CLIENT)
		public Item getTabIconItem() {
			return Items.ender_eye;
		}
	};
		
	@Instance("saligia")
	public static saligia instance;
	
	public static final Enchantment purify = new EnchantmentPurify(83, 1);
	public static Potion potionsSinnersDelight;
	
	public static worldGenAltar worldGen = new worldGenAltar();
	
	matryoshikaEventHandler events = new matryoshikaEventHandler();
	
	
	@SidedProxy(clientSide="Matryoshika.mods.saligia.ClientProxy",serverSide="Matryoshika.mods.saligia.CommonProxy")
	public static CommonProxy proxy;
	
	
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event){
		ConfigHandler.init(new Configuration(event.getSuggestedConfigurationFile()));
		saligia_Items.registerItems();
		saligia_Blocks.registerBlocks();
		
	}
	
	
	
	@EventHandler
	public void Init(FMLInitializationEvent event){
		MinecraftForge.EVENT_BUS.register(new matryoshikaEventHandler());
		proxy.registerRenderers();
		proxy.registerEntities();

		
		//Register entities
		saligia_Entities.registerEntities(EntityAcedia.class, "Acedia");
		saligia_Entities.registerEntities(EntityAvaritia.class, "Avaritia");
		saligia_Entities.registerEntities(EntityGula.class, "Gula");
		saligia_Entities.registerEntities(EntityInvidia.class, "Invidia");
		saligia_Entities.registerEntities(EntityIra.class, "Ira");
		saligia_Entities.registerEntities(EntityLuxuria.class, "Luxuria");
		saligia_Entities.registerEntities(EntitySuperbia.class, "Superbia");
		
		potionsSinnersDelight = new SinnersDelight(28, true, 0).setIconIndex(0,0).setPotionName("Sinners Delight");
		
		GameRegistry.registerWorldGenerator(worldGen, 33);
		
		NetworkRegistry.INSTANCE.registerGuiHandler(this, new MSGuiHandler());

	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event){
		
		
		
	}
}
