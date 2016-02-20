package Matryoshika.mods.saligia.blocks;

import java.util.List;
import java.util.Random;

import Matryoshika.mods.saligia.saligia;
import Matryoshika.mods.saligia.tile.TileRunicScribe;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class BlockRunicScribe extends BlockContainer{

	protected BlockRunicScribe(Block BlockRunicScribe) {
		super(Material.rock);
		setStepSound(soundTypeStone);
		setBlockTextureName(saligia.MODID+":BlockRunicScribe");
		setHardness(10);
		this.setResistance(150);
		this.setBlockName("RunicScribe");
	}
	
	@Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int metadata, float dontKnow, float whatThese, float floatsAre) {    
		TileEntity tileEntity = world.getTileEntity(x, y, z);
        if (tileEntity == null || player.isSneaking()) {
        	return false;
        }

    player.openGui(saligia.instance, 2, world, x, y, z);
    	return true;
    }
	
	private void dropItems(World world, int x, int y, int z){
        Random rand = new Random();

        TileEntity tileEntity = world.getTileEntity(x, y, z);
        if (!(tileEntity instanceof IInventory)) {
                return;
        }
        IInventory inventory = (IInventory) tileEntity;

        for (int i = 0; i < inventory.getSizeInventory(); i++) {
        	
            ItemStack item = inventory.getStackInSlot(i);

            if (item != null && item.stackSize > 0) {
            	 float rx = rand.nextFloat() * 0.8F + 0.1F;
                 float ry = rand.nextFloat() * 0.8F + 0.1F;
            	 float rz = rand.nextFloat() * 0.8F + 0.1F;

                 EntityItem entityItem = new EntityItem(world,
                 	x + rx, y + ry, z + rz,
                 	new ItemStack(item.getItem(), item.stackSize, item.getItemDamage()));

                 	if (item.hasTagCompound()) {
                    	entityItem.getEntityItem().setTagCompound((NBTTagCompound) item.getTagCompound().copy());
                 	}

                 	float factor = 0.05F;
                 	entityItem.motionX = rand.nextGaussian() * factor;
                	entityItem.motionY = rand.nextGaussian() * factor + 0.2F;
                 	entityItem.motionZ = rand.nextGaussian() * factor;
                 	world.spawnEntityInWorld(entityItem);
                 	item.stackSize = 0;
        	}
        }
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileRunicScribe();
	}
}
