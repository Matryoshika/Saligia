package Matryoshika.mods.saligia.items;

import Matryoshika.mods.saligia.saligia;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class ItemRitualActivator extends Item {
	
	ChatComponentTranslation chatComponent;
	int activatorCount = 0;
	public String ritualSelected;
	
	public ItemRitualActivator(ToolMaterial soul){
		super();
		this.maxStackSize = 16;
		this.setUnlocalizedName("ItemRitualActivator");
		this.setTextureName(saligia.MODID+":soul");
		setHasSubtypes(true);
		setMaxDamage(20);
	}
	
	
	public ItemStack onItemRightClick(ItemStack activator, World world, EntityPlayer player){
		
		activator.setItemDamage(activator.getItemDamage()+1);
		if (world.isRemote == false){
			chatComponent = (ChatComponentTranslation) new ChatComponentTranslation("Selected Ritual: "+ritualName(activator)).setChatStyle(new ChatStyle().setColor(EnumChatFormatting.WHITE));
			player.addChatMessage(chatComponent);
		}
		if(activator.getItemDamage() >= 20){
			activator.setItemDamage(0);
		}
		
		
		
		return activator;
	}
	
	public String ritualName(ItemStack stack){
		if (stack.getItemDamage() == 1){
			ritualSelected = new String("Cognizance of the Hellmouth"); 
		}
		if (stack.getItemDamage() == 2){
			ritualSelected = new String("Roar of the Thundering Skies"); 
		}
		if (stack.getItemDamage() == 3){
			ritualSelected = new String("Fountain of the Divine's blood"); 
		}
		if (stack.getItemDamage() == 4){
			ritualSelected = new String("Blessings of the Arid Earth"); 
		}
		if (stack.getItemDamage() == 5){
			ritualSelected = new String("Gift from the Ocean Grave"); 
		}
		if (stack.getItemDamage() == 6){
			ritualSelected = new String("Gaze of the Abyss"); 
		}
		if (stack.getItemDamage() == 7){
			ritualSelected = new String("Placeholder"); 
		}
		if (stack.getItemDamage() == 8){
			ritualSelected = new String("Placeholder"); 
		}
		if (stack.getItemDamage() == 9){
			ritualSelected = new String("Placeholder"); 
		}
		if (stack.getItemDamage() == 10){
			ritualSelected = new String("Placeholder"); 
		}
		if (stack.getItemDamage() == 11){
			ritualSelected = new String("Placeholder"); 
		}
		if (stack.getItemDamage() == 12){
			ritualSelected = new String("Placeholder"); 
		}
		if (stack.getItemDamage() == 13){
			ritualSelected = new String("Placeholder"); 
		}
		if (stack.getItemDamage() == 14){
			ritualSelected = new String("Placeholder"); 
		}
		if (stack.getItemDamage() == 15){
			ritualSelected = new String("Placeholder"); 
		}
		if (stack.getItemDamage() == 16){
			ritualSelected = new String("Placeholder"); 
		}
		if (stack.getItemDamage() == 17){
			ritualSelected = new String("Placeholder"); 
		}
		if (stack.getItemDamage() == 18){
			ritualSelected = new String("Placeholder"); 
		}
		if (stack.getItemDamage() == 19){
			ritualSelected = new String("Placeholder"); 
		}
		if (stack.getItemDamage() == 20){
			ritualSelected = new String("Placeholder"); 
		}
		return ritualSelected;
		
		
		/*if(activatorCount == 0){
			activatorCount++;
			chatComponent = (ChatComponentTranslation) new ChatComponentTranslation("Will now cycle through the available rituals").setChatStyle(new ChatStyle().setColor(EnumChatFormatting.WHITE));
			player.addChatMessage(chatComponent);
			System.out.println("Hello");
		}
		if(activatorCount > 0 && activatorCount < 20){
			activatorCount++;
			
			chatComponent = (ChatComponentTranslation) new ChatComponentTranslation("Current ritual: "+ritualName(string)).setChatStyle(new ChatStyle().setColor(EnumChatFormatting.WHITE));
			player.addChatMessage(chatComponent);
			}
			*/
			
			
		
	}
	
}