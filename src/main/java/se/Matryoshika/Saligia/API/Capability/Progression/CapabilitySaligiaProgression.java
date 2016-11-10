/**
 * 
 */
package se.Matryoshika.Saligia.API.Capability.Progression;

import javax.annotation.Nullable;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import se.Matryoshika.Saligia.API.Progression;
import se.Matryoshika.Saligia.API.Capability.SimpleCapabilityProvider;
import se.Matryoshika.Saligia.Content.Progression.ProgressionRegistry;

/**
 * This class was created by Matryoshika Nov 8, 2016
 * Property of Matryoshika. 
 * Part of the Saligia mod.
 * May be viewed for educational purposes.
 */
public class CapabilitySaligiaProgression {
	
	//Creating the instance of the Capability
	@CapabilityInject(ISaligiaProgression.class)
	public static final Capability<ISaligiaProgression> SALIGIA_PROGRESSION_CAPABILITY = null;
	
	//Default facing for this capability
	public static final EnumFacing DEFAULT_FACING = null;
	
	//The unique id of this capability
	public static final ResourceLocation ID = new ResourceLocation("saligia", "Progression");
	
	public static void register(){
		CapabilityManager.INSTANCE.register(ISaligiaProgression.class, new Capability.IStorage<ISaligiaProgression>(){
			
			@Override
			public NBTBase writeNBT(Capability<ISaligiaProgression> capability, ISaligiaProgression instance, EnumFacing side){
			
				NBTTagCompound bools = new NBTTagCompound();
				for(Progression prog : ProgressionRegistry.getBlankProgressions()){
					if(!instance.getPlayerProgression(null, prog.getName()))
					bools.setBoolean(prog.getName(), false);
					else
						bools.setBoolean(prog.getName(), true);
				}
				return bools;
			}

			@Override
			public void readNBT(Capability<ISaligiaProgression> capability, ISaligiaProgression instance, EnumFacing side, NBTBase nbt) {
				
				for(Progression prog : ProgressionRegistry.getBlankProgressions()){

					instance.setPlayerProgression(null, prog.getName(), ((NBTTagCompound)nbt).getBoolean(prog.getName()));
				}

			}
			
		}, () -> new SaligiaProgression(null));
	}
	
	public static ICapabilityProvider createProvider(ISaligiaProgression progression) {
		return new SimpleCapabilityProvider<>(SALIGIA_PROGRESSION_CAPABILITY, DEFAULT_FACING, progression);
	}
	
	@Nullable
	public static ISaligiaProgression getProg(EntityPlayer player) {
		return SimpleCapabilityProvider.getCapability(player, SALIGIA_PROGRESSION_CAPABILITY, DEFAULT_FACING);
	}
	
	@Mod.EventBusSubscriber
	public static class EventHandler {
		
		
		
		//Attach the  capability to all living entities.
		@SubscribeEvent
		public static void attachCapabilities(AttachCapabilitiesEvent<Entity> event) {
			if (event.getObject() instanceof EntityPlayer) {
				final SaligiaProgression prog = new SaligiaProgression((EntityPlayer) event.getObject());
				event.addCapability(ID, createProvider(prog));
			}
		}

		//Copy the player's progress when they respawn after dying or returning from the end.
		@SubscribeEvent
		public static void playerClone(PlayerEvent.Clone event) {
			
			if(event.getOriginal().hasCapability(SALIGIA_PROGRESSION_CAPABILITY, DEFAULT_FACING) && event.getEntityPlayer().hasCapability(SALIGIA_PROGRESSION_CAPABILITY, DEFAULT_FACING)){
				event.getEntityPlayer().getCapability(SALIGIA_PROGRESSION_CAPABILITY, DEFAULT_FACING).copyPlayerProgression(event.getOriginal(), event.getEntityPlayer());
			}
		}
	}

	@CapabilityInject(ISaligiaProgression.class)
    private static void capRegistered(Capability<ISaligiaProgression> cap)
    {
        System.out.println("IExampleCapability was registered wheeeeee!");
    }

}
