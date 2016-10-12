/**
 * 
 */
package se.Matryoshika.Saligia.Content.Items;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import se.Matryoshika.Saligia.Saligia;
import se.Matryoshika.Saligia.API.Content.BlockRegistryInjector;
import se.Matryoshika.Saligia.API.Rituals.IRitualBlock;
import se.Matryoshika.Saligia.API.Rituals.RitualRegistry;
import se.Matryoshika.Saligia.Content.Blocks.RitualMasters.BlockRitualMaster;
import se.Matryoshika.Saligia.Content.Tiles.TileRitual;
import se.Matryoshika.Saligia.Utils.Print;

/**
 * This class was created by Matryoshika Aug 13, 2016
 * Property of Matryoshika. 
 * Part of the Saligia mod.
 * May be viewed for educational purposes.
 */
public class ItemRitualActivator extends Item{
	
	public static String ritualName;
	int counter = 0;
	
	public ItemRitualActivator(){
		this.setMaxStackSize(1);
		this.setRegistryName("ItemRitualActivator");
		this.setUnlocalizedName(this.getRegistryName().toString());
		this.setCreativeTab(Saligia.saligiaTab);
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced){ 
		super.addInformation(stack, playerIn, tooltip, advanced);
		if(ritualName != null)
		tooltip.add(ritualName);
	}

	
	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ){
		if(player.isSneaking()){
			List<String> names = RitualRegistry.getAllNames();
			int size = names.size();
			ritualName = names.get(stack.getItemDamage());
			stack.setItemDamage(stack.getItemDamage()+1);
			if(stack.getItemDamage() >= size){
				stack.setItemDamage(0);
			}
		}
		
		
		
		if(world.getBlockState(pos).getBlock() instanceof BlockRitualMaster && ritualName != null && !(player.isSneaking())){
			Object[][] pattern = RitualRegistry.getMultiblock(ritualName);
			
			checkMultiBlock(pattern, pos, world, ritualName);
		}	
		return EnumActionResult.PASS;
	}

	public static void checkMultiBlock(Object[][] pattern, BlockPos pos, World world, String name){
		//System.out.println("Checking multiblock");
		boolean canPlace = true;
		for(Object[] object : pattern){
			int x = pos.getX() + (Integer)object[0];
			int y = pos.getY() + (Integer)object[1];
			int z = pos.getZ() + (Integer)object[2];
			Block block =        (Block)  object[3];
			
			Block blockAtPos = world.getBlockState(new BlockPos(x,y,z)).getBlock();
			if(blockAtPos != block){
				canPlace = false;
				if(world.getTileEntity(pos) instanceof TileRitual){
					TileRitual master = (TileRitual) world.getTileEntity(pos);
					master.isClicked = true;
					master.renderMultiBlockKey = ritualName;
					if(world.isRemote)
						Saligia.proxy.renderMultiblock(master, pos.getX(), pos.getY(), pos.getZ());
				}
				break;
			}
			if(canPlace){
				BlockRitualMaster master = RitualRegistry.getBlock(name);
				world.setBlockState(pos, master.getDefaultState());
				world.scheduleUpdate(pos, master, 1);
			}
			else{
				
				
			}
		}
	}
}
