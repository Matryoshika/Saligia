package se.Matryoshika.Saligia.Utils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import se.Matryoshika.Saligia.Saligia;
import se.Matryoshika.Saligia.API.Content.BlockRegistryInjector;
import se.Matryoshika.Saligia.API.Content.ItemRegistryInjector;
import se.Matryoshika.Saligia.Content.Blocks.RitualMasters.BlockRitualMaster;
import se.Matryoshika.Saligia.Content.Tiles.TileRitualCOTH;


/**
 * This class was created by Matryoshika Aug 8, 2016
 * Property of Matryoshika. 
 * Part of the Saligia mod.
 * May be viewed for educational purposes.
 */
public class ConfigHandler {
	
	public static File configDir;
    public static Configuration mainConf;
    public static Configuration bossConf;
    public static Configuration itemConf;
    public static Configuration blockConf;
    public static Configuration ritualConf;
    
    public static HashMap isBlockEnabledMap = new HashMap();
    public static HashMap isRitualEnabledMap = new HashMap();
    public static HashMap isItemEnabledMap = new HashMap();
	
	public static void readConfigs(FMLPreInitializationEvent event){
		
		mainConf = new Configuration(new File(Saligia.getMod().getConfigFolder(), "main.cfg"));
		bossConf = new Configuration(new File(Saligia.getMod().getConfigFolder(), "bosses.cfg"));
		itemConf = new Configuration(new File(Saligia.getMod().getConfigFolder(), "items.cfg"));
		blockConf = new Configuration(new File(Saligia.getMod().getConfigFolder(), "blocks.cfg"));
		ritualConf = new Configuration(new File(Saligia.getMod().getConfigFolder(), "rituals.cfg"));
		
		

		mainConf.load();
		bossConf.load();
		itemConf.load();
		blockConf.load();
		ritualConf.load();
		
		blockConf.addCustomCategoryComment("Enabled Blocks", "All enabled blocks");
		for(Block block : BlockRegistryInjector.blockList){
			boolean isBlockEnabled = blockConf.getBoolean(block.getRegistryName().toString(), "Enabled Blocks", true, "Wether or not this block is enabled");
			isBlockEnabledMap.put(block.getRegistryName().toString(), isBlockEnabled);
		}
		
		ritualConf.addCustomCategoryComment("Enabled Rituals", "All enabled rituals");
		for(BlockRitualMaster block : BlockRegistryInjector.ritualList){
			boolean isRitualEnabled = ritualConf.getBoolean(block.getRitualName(), "Enabled Rituals", true, "Wether or not this ritual is enabled");
			isRitualEnabledMap.put(block.getRitualName(), isRitualEnabled);
		}
		
		itemConf.addCustomCategoryComment("Enabled Items", "All enabled items");
		for(Item item : ItemRegistryInjector.itemList){
			boolean isItemEnabled = itemConf.getBoolean(item.getRegistryName().toString(), "Enabled Items", true, "Wether or not this item is enabled");
			isItemEnabledMap.put(item.getRegistryName().toString(), isItemEnabled);
		}
		
		
		
		
		//Boss Configs-----------------------------------------------------------------------------
		bossConf.addCustomCategoryComment("Boss Configs", "Configs for all Saligia Bosses");
		float bossHealth = bossConf.getFloat("Boss Health", "Boss Configs", 200, 500, 5000, "This sets the health for all Bosses");
        float bossSpeed = bossConf.getFloat("Boss Speed", "Boss Configs", (float) 0.4, 0, 1, "This sets the base speed of Bosses (Some add/subtract to this)");
        float bossAttack = bossConf.getInt("Boss Attack Damage", "Boss Configs", 100, 100, 500, "This sets the rate of how hard the Bosses will hit you");
        float bossToPlayerRange = bossConf.getFloat("Player Detection Range", "Boss Configs", 25, 20, 64, "Sets how far away Bosses will detect players");
        
        bossConf.addCustomCategoryComment("Boss Specific configs", "Configs that only alter a specific Boss");
        int acediaMinionMax = bossConf.getInt("Acedia Minion count", "Boss Specific Configs", 11, 11, 30, "Sets how many of Acedia's minions can exist in the world");
        int avaritiaCounter = bossConf.getInt("Sets the tick timer for when Avaritia will steal items", "Boss Specific configs", 20, 0, 20, "This is also affected by a random counter, with a 1/32 chance");
        float gulaHeal = bossConf.getFloat("Regen amount per block", "Boss Specific configs", 10, 10, 30, "How much Gula will regen for every block eaten");
        
        
        
        
        //Item Configs-----------------------------------------------------------------------------
        itemConf.addCustomCategoryComment("Item Configs", "Configs for all items");
        boolean isPickEnabled = itemConf.getBoolean("Allow Pickaxe of Greed", "Item Configs", true, "Wether or not this item is enabled. False will disable it.");
        int pickRange = itemConf.getInt("Range of magnetization", "Item Configs", 1, 0, 20, "How far the pickaxe will drag items towards the player");
        
        
        if(mainConf.hasChanged())
        	mainConf.save();
        
        if(bossConf.hasChanged())
        	bossConf.save();
        
        if(itemConf.hasChanged())
        	itemConf.save();
        
        if(blockConf.hasChanged())
        	blockConf.save();
        
        if(ritualConf.hasChanged())
        	ritualConf.save();
        
	}

}
