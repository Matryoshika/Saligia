/**
 * 
 */
package se.Matryoshika.Saligia.Content.Progression;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import scala.actors.threadpool.Arrays;
import se.Matryoshika.Saligia.API.Progression;
import se.Matryoshika.Saligia.API.Progression.ProgressionType;

/**
 * This class was created by Matryoshika Nov 7, 2016
 * Property of Matryoshika. 
 * Part of the Saligia mod.
 * May be viewed for educational purposes.
 */
public class ProgressionRegistry {
	
	private final List<Progression> empty = new ArrayList<Progression>(); 
	
	private static List<Progression> progressions;
	
	public static void registerProgression(){
		/*
		 * Because how each progression requires children & parents, they all 
		 * need to be created without them, initially, and then have them added to them.
		 * Otherwise, you'll face the issue off initializing z that requires x & y,
		 * but both x & y require z in return.
		 * 
		 * The naming convention for progressions is all caps, underscore spaced, description, ending in the tree.
		 * Example: TEST_PROGRESSION_RITUAL
		 */
		Progression STARTER_INFUSION_MAIN = new Progression(ProgressionType.RITUAL, "STARTER_INFUSION_MAIN", 0, 0);
		Progression SIN_ROSE_MAIN = new Progression(ProgressionType.ENVIRONMENT, "SIN_ROSE_MAIN", 2, 2);
		Progression VILE_METAL_MAIN = new Progression(ProgressionType.ENVIRONMENT, "VILE_METAL_MAIN", -2, 2);
		Progression FINDING_ALTAR_ALTARS = new Progression(ProgressionType.ALTAR, "FINDING_ALTAR_ALTARS", 0, 0);
		Progression DAGGER_USE_EQUIPMENT = new Progression(ProgressionType.EQUIPMENT, "DAGGER_USE_EQUIPMENT", 0,0);
		Progression ARMOUR_VILE_EQUIPMENT = new Progression(ProgressionType.EQUIPMENT, "ARMOUR_VILE_EQUIPMENT", -2, 2);
		Progression ARMOUR_CORRUPTED_EQUIPMENT = new Progression(ProgressionType.EQUIPMENT, "ARMOUR_CORRUPTED_EQUIPMENT", -2, 4);
		Progression ARMOUR_INFERNAL_EQUIPMENT = new Progression(ProgressionType.EQUIPMENT, "ARMOUR_INFERNAL_EQUIPMENT", -2, 6);
		Progression ARMOUR_SIN_EQUIPMENT = new Progression(ProgressionType.EQUIPMENT, "ARMOUR_SIN_EQUIPMENT", -2, 8);
		Progression RITUAL_ACTIVATOR_EQUIPMENT = new Progression(ProgressionType.EQUIPMENT, "RITUAL_ACTIVATOR_EQUIPMENT", 2, 2);
		
		STARTER_INFUSION_MAIN.addChild(SIN_ROSE_MAIN).addChild(FINDING_ALTAR_ALTARS).addChild(DAGGER_USE_EQUIPMENT).addChild(ARMOUR_VILE_EQUIPMENT);
		
		SIN_ROSE_MAIN.addParent(STARTER_INFUSION_MAIN).addChild(VILE_METAL_MAIN);
		
		VILE_METAL_MAIN.addParent(SIN_ROSE_MAIN);
		
		FINDING_ALTAR_ALTARS.addParent(STARTER_INFUSION_MAIN);
		
		DAGGER_USE_EQUIPMENT.addParent(STARTER_INFUSION_MAIN).addParent(VILE_METAL_MAIN);
		
		ARMOUR_VILE_EQUIPMENT.addParent(STARTER_INFUSION_MAIN).addParent(VILE_METAL_MAIN).addChild(ARMOUR_CORRUPTED_EQUIPMENT);
		
		ARMOUR_CORRUPTED_EQUIPMENT.addParent(ARMOUR_VILE_EQUIPMENT).addChild(ARMOUR_INFERNAL_EQUIPMENT);
		
		ARMOUR_INFERNAL_EQUIPMENT.addParent(ARMOUR_CORRUPTED_EQUIPMENT).addChild(ARMOUR_SIN_EQUIPMENT);
		
		ARMOUR_SIN_EQUIPMENT.addParent(ARMOUR_INFERNAL_EQUIPMENT);
		
		RITUAL_ACTIVATOR_EQUIPMENT.addParent(VILE_METAL_MAIN);
		
		progressions = new ArrayList<Progression>(){{
			add(STARTER_INFUSION_MAIN);
			add(SIN_ROSE_MAIN);
			add(VILE_METAL_MAIN);
			add(FINDING_ALTAR_ALTARS);
			add(DAGGER_USE_EQUIPMENT);
			add(ARMOUR_VILE_EQUIPMENT);
			add(ARMOUR_CORRUPTED_EQUIPMENT);
			add(ARMOUR_INFERNAL_EQUIPMENT);
			add(RITUAL_ACTIVATOR_EQUIPMENT);
		}};
		
	}
	
	public static List<Progression> getBlankProgressions(){
		
		return progressions;
	}

}
