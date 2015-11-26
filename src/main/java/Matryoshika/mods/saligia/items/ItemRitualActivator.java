package Matryoshika.mods.saligia.items;

import java.util.Arrays;
import java.util.List;

import Matryoshika.mods.saligia.saligia;
import Matryoshika.mods.saligia.blocks.saligia_Blocks;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
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
	public boolean badSetup = false;
	
	public ItemRitualActivator(ToolMaterial soul){
		super();
		this.maxStackSize = 16;
		this.setUnlocalizedName("ItemRitualActivator");
		this.setTextureName(saligia.MODID+":soul");
		setHasSubtypes(true);
		setMaxDamage(0);
	}
	
	@Override
    public String getItemStackDisplayName(ItemStack stack){
			return ritualName(stack);
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par){ 
		list.add(EnumChatFormatting.DARK_RED + "Terrors of the mind within your grasp");
	}
	
	
	public ItemStack onItemRightClick(ItemStack activator, World world, EntityPlayer player){
		
		int x = Minecraft.getMinecraft().objectMouseOver.blockX;
		int y = Minecraft.getMinecraft().objectMouseOver.blockY;
		int z = Minecraft.getMinecraft().objectMouseOver.blockZ;
		
		if(player.isSneaking() == true){
			activator.setItemDamage(activator.getItemDamage()+1);
			if (world.isRemote == false){
				chatComponent = (ChatComponentTranslation) new ChatComponentTranslation("Selected Ritual: "+ritualName(activator)).setChatStyle(new ChatStyle().setColor(EnumChatFormatting.WHITE));
				player.addChatMessage(chatComponent);
			}
			if(activator.getItemDamage() >= 20){
				activator.setItemDamage(0);
			}
		}
		Block block = world.getBlock(x, y, z);
		if(block == saligia_Blocks.CentreRitual && activator.getItemDamage() == 1){
			
			final int [][] GHASTLY_BLOCKS = new int [][]{
				{0,1,-4},{3,1,-3},{4,1,0},{3,1,3},{0,1,4},{-3,1,3},{-4,1,0},{-3,1,-3}
			};
			
			for(int[] coords : GHASTLY_BLOCKS) {
				int x1 = x + coords[0];
				int y1 = y + coords[1];
				int z1 = z + coords[2];
				
				Block blockAtPos = world.getBlock(x1, y1, z1);
				int meta = world.getBlockMetadata(x, y, z);
				if(blockAtPos == saligia.GhastlyBlock) {
					for(int[] coords2 : GHASTLY_BLOCKS) {
						int x2 = x + coords[0];
						int y2 = y + coords[1];
						int z2 = z + coords[2];
						world.addWeatherEffect(new EntityLightningBolt(world, x2, y2, z2));
						world.setBlock(x, y, z, saligia_Blocks.CentreCOTH);
						
						
					}
				}
				else{
						wrongSetup(player, world);
						break;
					}
				}
		}
		else{
			return activator;
		}
		
		
		
		
		super.getItemStackDisplayName(activator);
		return activator;
	}
	
	public String ritualName(ItemStack stack){
		if (stack.getItemDamage() == 0){
			ritualSelected = new String("ÅòkSource Of Sin");
		}
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
	}
	
	public void wrongSetup(EntityPlayer player, World world){
		if(world.isRemote == false)
			player.addChatMessage(new ChatComponentTranslation("The ritual-pattern is not correct for the chosen ritual!").setChatStyle(new ChatStyle().setColor(EnumChatFormatting.RED)));
	}
	
	
}