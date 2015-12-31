package Matryoshika.mods.saligia;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import org.apache.logging.log4j.Level;

import Matryoshika.mods.saligia.blocks.BlockGhastly;
import Matryoshika.mods.saligia.blocks.ItemBlockGhastly;
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
import Matryoshika.mods.saligia.tile.TileRitualCOTH;
import Matryoshika.mods.saligia.tile.TileRitualFOTDB;
import Matryoshika.mods.saligia.tile.TileRitualFOTI;
import Matryoshika.mods.saligia.tile.TileRitualROTTS;
import Matryoshika.mods.saligia.tile.soulsystem.TileSoulBrazier;
import Matryoshika.mods.saligia.utils.ConfigHandler;
import Matryoshika.mods.saligia.utils.CreativeTabMatryoshika;
import Matryoshika.mods.saligia.utils.SaligiaFuelHandler;
import Matryoshika.mods.saligia.utils.SinnersDelight;
import Matryoshika.mods.saligia.utils.matryoshikaEventHandler;
import Matryoshika.mods.saligia.worldgen.worldGenAltar;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.IFuelHandler;
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
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.oredict.OreDictionary;

	@Mod(modid=saligia.MODID, version=saligia.VERSION, name="saligia")
		public class saligia {	
		public static final String MODID="saligia";
		public static final String LOCALIZING = "MS";
		public static final String VERSION="1.0";
		
		
		public static String[] allowedGeneratorsArray;
		
		public static Block GhastlyBlock;
		
		public static File ConfigDir;
	    private Configuration mainConf;
	    
	    public static float bossHealth;
	    public static double bossSpeed;
	    public static double bossAttack;
	    public static double bossToPlayerRange;
	    public static int acediaMinionMax;
	    public static int avaritiaCounter;
	    public static float gulaHeal;
	    public static boolean isPickEnabled;
	    public static int pickRange;
	    
	    
	    

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
		
		ConfigDir = event.getModConfigurationDirectory();
		mainConf = new Configuration(new File(ConfigDir.getPath() + File.separator + "saligia", "main.cfg"));
		readMainConfig();
		
		ConfigHandler.init(new Configuration(event.getSuggestedConfigurationFile()));
		saligia_Items.registerItems();
		saligia_Blocks.registerBlocks();
		
		saligia.GhastlyBlock = new BlockGhastly(GhastlyBlock);
		GameRegistry.registerBlock(saligia.GhastlyBlock, ItemBlockGhastly.class, "GhastlyBlock");
		
		GameRegistry.registerTileEntity(TileRitualCOTH.class, "tileRitualCOTH");
		GameRegistry.registerTileEntity(TileRitualROTTS.class, "tileRitualROTTS");
		GameRegistry.registerTileEntity(TileRitualFOTDB.class, "tileRitualFOTDB");
		GameRegistry.registerTileEntity(TileRitualFOTI.class, "tileRitualFOTI");
		
		GameRegistry.registerTileEntity(TileSoulBrazier.class, "tileSoulBrazier");
		
	}
	
	
	
	@EventHandler
	public void Init(FMLInitializationEvent event){
		MinecraftForge.EVENT_BUS.register(new matryoshikaEventHandler());
		proxy.registerRenderers();
		proxy.registerEntities();
		
		OreDictionary.registerOre("materialSaligiaSoul", new ItemStack(saligia_Items.AnimalSoul));
		OreDictionary.registerOre("materialSaligiaSoul", new ItemStack(saligia_Items.BuffMobSoul));
		OreDictionary.registerOre("materialSaligiaSoul", new ItemStack(saligia_Items.VillagerSoul));
		OreDictionary.registerOre("materialSaligiaSoul", new ItemStack(saligia_Items.ZombieSoul));

		
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
		GameRegistry.registerFuelHandler(SaligiaFuelHandler.getFuelHandler());

	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event){
		
		
		
	}
	
	private void readMainConfig(){
		
        Configuration cfg = mainConf;
        try {
            cfg.load();
            cfg.addCustomCategoryComment("Boss Configs", "Shared between all bosses");
            bossHealth = cfg.getFloat("Boss Health", "Boss Configs", 200, 500, 5000, "This sets the health for all Bosses");
            bossSpeed = cfg.getFloat("Boss Speed", "Boss Configs", (float) 0.4, 0, 1, "This sets the base speed of Bosses (Some add/subtract to this)");
            bossAttack = cfg.getInt("Boss Attack Damage", "Boss Configs", 100, 100, 500, "This sets the rate of how hard the Bosses will hit you");
            bossToPlayerRange = cfg.getFloat("Player Detection Range", "Boss Configs", 25, 20, 64, "Sets how far away Bosses will detect players");
            cfg.addCustomCategoryComment("Boss Specific configs", "Configs that only alter a specific Boss");
            acediaMinionMax = cfg.getInt("Acedia Minion count", "Boss Specific Configs", 11, 11, 30, "Sets how many of Acedia's minions can exist in the world");
            avaritiaCounter = cfg.getInt("Sets the tick timer for when Avaritia will steal items", "Boss Specific configs", 20, 0, 20, "This is also affected by a random counter, with a 1/32 chance");
            gulaHeal = cfg.getFloat("Regen amount per block", "Boss Specific configs", 10, 10, 30, "How much Gula will regen for every block eaten");
            
            cfg.addCustomCategoryComment("Item Configs", "Configs for all items");
            
            isPickEnabled = cfg.getBoolean("Allow Pickaxe of Greed", "Item Configs", true, "Wether or not this item is enabled. False will disable it.");
            pickRange = cfg.getInt("Range of magnetization", "Item Configs", 1, 0, 20, "How far the pickaxe will drag items towards the player");
        }
        catch (Exception e1){
            FMLLog.log(Level.ERROR, e1, "Problem loading Saligia Main config file!");
        }
        finally {
            if (mainConf.hasChanged()) {
                mainConf.save();
            }
        }
	}
}
