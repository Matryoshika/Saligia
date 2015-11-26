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
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.StatCollector;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class ItemRitualActivator extends Item {
	
	ChatComponentTranslation chatComponent;
	
	ChatComponentTranslation ritualname;
	
	int activatorCount = 0;
	public String ritualSelected;
	public String ritualSelected2;
	public String tooltip;
	public boolean badSetup = false;
	public Vec3 posVec;
	
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
		tooltip = new String(StatCollector.translateToLocal("saligia.ritualactivator.tooltip"));
		list.add(EnumChatFormatting.GRAY + tooltip);
	}

	public ItemStack onItemRightClick(ItemStack activator, EntityPlayer player, World world){
		if(player.isSneaking() == true){
			activator.setItemDamage(activator.getItemDamage()+1);
			if (world.isRemote == false){
				ritualSelected2 = new String(StatCollector.translateToLocal("saligia.ritual.select"));
				player.addChatMessage(new ChatComponentTranslation(ritualSelected2+ritualName(activator)).setChatStyle(new ChatStyle().setColor(EnumChatFormatting.WHITE)));
			}
			if(activator.getItemDamage() >= 20){
				activator.setItemDamage(0);
			}
		}
		
		return activator;
	}
	

	public boolean onItemUse(ItemStack activator, EntityPlayer player, World world, int x, int y, int z, int par7, float par8, float par9, float par10){

		//System.out.println("{"+x+","+y+","+z+"}");
		
		if(player.isSneaking() == true){
			activator.setItemDamage(activator.getItemDamage()+1);
			if (world.isRemote == false){
				ritualSelected2 = new String(StatCollector.translateToLocal("saligia.ritual.select"));
				player.addChatMessage(new ChatComponentTranslation(ritualSelected2+ritualName(activator)).setChatStyle(new ChatStyle().setColor(EnumChatFormatting.WHITE)));
			}
			if(activator.getItemDamage() >= 20){
				activator.setItemDamage(0);
			}
		}
		
		
		
		
		Block block = world.getBlock(x, y, z);
		if(block == saligia_Blocks.CentreRitual && activator.getItemDamage() == 1 && !player.isSneaking()){
			
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
						//world.addWeatherEffect(new EntityLightningBolt(world, x1, y1, z1));
						world.playSoundAtEntity(player, "mob.enderdragon.growl", 10F, 0.1F);
						//world.setBlock(x, y, z, saligia_Blocks.CentreCOTH);
						world.setBlock(x, y, z, saligia_Blocks.CentreCOTH, 0, 2);
						break;
	
				}
				else{
						wrongSetup(player, world);
						break;
					}
				}
		}

		super.getItemStackDisplayName(activator);
		return true;
		
	}
	
	public String ritualName(ItemStack stack){
		if (stack.getItemDamage() == 0){
			ritualSelected = "ÅòkSource of Sin";
		}
		if (stack.getItemDamage() == 1){
			ritualSelected = new String(StatCollector.translateToLocal("saligia.ritual.COTH"));  
		}
		if (stack.getItemDamage() == 2){
			ritualSelected = new String(StatCollector.translateToLocal("saligia.ritual.ROTTS")); 
		}
		if (stack.getItemDamage() == 3){
			ritualSelected = new String(StatCollector.translateToLocal("saligia.ritual.FOTDB"));  
		}
		if (stack.getItemDamage() == 4){
			ritualSelected = new String(StatCollector.translateToLocal("saligia.ritual.BOTAE")); 
		}
		if (stack.getItemDamage() == 5){
			ritualSelected = new String(StatCollector.translateToLocal("saligia.ritual.GFTOG"));
		}
		if (stack.getItemDamage() == 6){
			ritualSelected = new String(StatCollector.translateToLocal("saligia.ritual.GOTA"));
		}
		if (stack.getItemDamage() == 7){
			ritualSelected = new String(StatCollector.translateToLocal("saligia.ritual.placeholder")); 
		}
		if (stack.getItemDamage() == 8){
			ritualSelected = new String(StatCollector.translateToLocal("saligia.ritual.placeholder"));
		}
		if (stack.getItemDamage() == 9){
			ritualSelected = new String(StatCollector.translateToLocal("saligia.ritual.placeholder")); 
		}
		if (stack.getItemDamage() == 10){
			ritualSelected = new String(StatCollector.translateToLocal("saligia.ritual.placeholder")); 
		}
		if (stack.getItemDamage() == 11){
			ritualSelected = new String(StatCollector.translateToLocal("saligia.ritual.placeholder")); 
		}
		if (stack.getItemDamage() == 12){
			ritualSelected = new String(StatCollector.translateToLocal("saligia.ritual.placeholder")); 
		}
		if (stack.getItemDamage() == 13){
			ritualSelected = new String(StatCollector.translateToLocal("saligia.ritual.placeholder")); 
		}
		if (stack.getItemDamage() == 14){
			ritualSelected = new String(StatCollector.translateToLocal("saligia.ritual.placeholder")); 
		}
		if (stack.getItemDamage() == 15){
			ritualSelected = new String(StatCollector.translateToLocal("saligia.ritual.placeholder")); 
		}
		if (stack.getItemDamage() == 16){
			ritualSelected = new String(StatCollector.translateToLocal("saligia.ritual.placeholder")); 
		}
		if (stack.getItemDamage() == 17){
			ritualSelected = new String(StatCollector.translateToLocal("saligia.ritual.placeholder")); 
		}
		if (stack.getItemDamage() == 18){
			ritualSelected = new String(StatCollector.translateToLocal("saligia.ritual.placeholder")); 
		}
		if (stack.getItemDamage() == 19){
			ritualSelected = new String(StatCollector.translateToLocal("saligia.ritual.placeholder")); 
		}
		if (stack.getItemDamage() == 20){
			ritualSelected = new String(StatCollector.translateToLocal("saligia.ritual.placeholder")); 
		}
		return ritualSelected;		
	}
	
	public void wrongSetup(EntityPlayer player, World world){
		if(world.isRemote == false)
			player.addChatMessage(new ChatComponentTranslation("saligia.wrongSetup").setChatStyle(new ChatStyle().setColor(EnumChatFormatting.RED)));
	}
	
	
}