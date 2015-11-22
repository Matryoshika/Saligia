package Matryoshika.mods.saligia.utils;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ConfigHandler {
	
	public static Configuration config;
	
	//Generator
		public static boolean isGeneratorEnabled;
		public static int numbersOfEachTypeAllowed;
		public static int maxCapOnRFProduction;
		public static boolean useOriginalFuelsOrSinFuel;
		
		
		
		public static void init(Configuration configuration) {
			config = configuration;
	        config.load();
	        syncConfig();
	        config.save();
	        
		}
		
		public static void syncConfig() {
			
			try {
				//Generator
				config.addCustomCategoryComment("Generator", "These are the configs for the Dynamic Generator. Sin-fueled generator may be more optimal than original fueled");
				isGeneratorEnabled = config.getBoolean("Is Generator enabled", "Generator", true, "Set to false to disable this machine");
				numbersOfEachTypeAllowed = config.getInt("Allowed amount of generators per type", "Generator", 128, 1, 4096, "Sets how many generators of the same type you can use.");
				maxCapOnRFProduction = config.getInt("RF-generation cap", "Generator", 2147483647, 20, 2147483647, "Sets how much you can produce in the Generator.");
				useOriginalFuelsOrSinFuel = config.getBoolean("Fuel type", "Generator", true, "Default: true = use Sin fuels. Set to false to require the Generator to use each fueltype that the generators inside it required");
				
				
				
				
				
			}
			
			catch(Exception except){
				System.out.println("Unable to load config for Matryoshika's Sinners");
				}
			finally{
				if(config.hasChanged()){
					config.save();
				}
			}
		}
}
