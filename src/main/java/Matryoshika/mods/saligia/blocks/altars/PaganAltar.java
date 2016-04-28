package Matryoshika.mods.saligia.blocks.altars;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.lwjgl.opengl.GL11;

import Matryoshika.mods.saligia.saligia;
import Matryoshika.mods.saligia.blocks.altars.AltarCrafting;
import Matryoshika.mods.saligia.blocks.saligia_Blocks;
import Matryoshika.mods.saligia.entities.misc.customLightningBolt;
import Matryoshika.mods.saligia.items.saligia_Items;
import Matryoshika.mods.saligia.items.runes.ItemRune;
import Matryoshika.mods.saligia.rendering.tilePaganAltarRenderer;
import Matryoshika.mods.saligia.tile.TileRitualCOTH;
import Matryoshika.mods.saligia.tile.altars.TilePaganAltar;
import cpw.mods.fml.common.registry.GameData;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.BlockSand;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class PaganAltar extends BlockContainer implements ITileEntityProvider{
	
	int decreasedPerStack;
	int left = 0;
	public static boolean shouldPurge;
	private static final double RANGE = 2F;
	int splitInEight;
	int leftOver;
	boolean clearCrafting = false;
	int yLevel = 1;
	
	public boolean step1 = true;
	public boolean step2 = false;
	
	EntityItem entItem = null;
	
	
	public PaganAltar (Block PaganAltar){
		super(Material.iron);
		setStepSound(soundTypeStone);
		setBlockTextureName(saligia.MODID+":BlockSoulPyre");
		setHardness(-1);
		this.setResistance(18000000);
		this.setBlockName("PaganAltar");
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
		TilePaganAltar altar = (TilePaganAltar) world.getTileEntity(x, y, z);
		ItemStack stack = player.getCurrentEquippedItem();
		
		altar.updateRecipe();
		
		//renderItem(world, altar, x, y, z);

		if(player.isSneaking()) {
			if(altar.requiredEnergy == 0)
				for(int i = altar.getSizeInventory() - 1; i >= 0; i--) {
					ItemStack stackAt = altar.getStackInSlot(i);
					if(stackAt != null) {
						ItemStack copy = stackAt.copy();
						if(!player.inventory.addItemStackToInventory(copy))
							player.dropPlayerItemWithRandomChoice(copy, false);
						altar.setInventorySlotContents(i, null);
						world.func_147453_f(x, y, z, this);
						break;
					}
				}
		}
		else if(stack != null && stack.getItem() != saligia_Items.LibroSaligia){
			return altar.addItem(player, stack);
		}else {
			if(altar.hasValidRecipe()){
				altar.tryCraft();
				if(altar.getStackInSlot(1).getItem() instanceof ItemRune && altar.getStackInSlot(1).stackTagCompound.getInteger("saligiaTier")>=3){
					return false;
				}else{
					for(int i = 0; i <= 90; i++){
				    	Random rand = new Random();
						double motionX = rand.nextGaussian() * 0.02D;
						double motionY = rand.nextGaussian() * 0.02D;
						double motionZ = rand.nextGaussian() * 0.02D;
				    	world.spawnParticle("reddust", x+0.5+(motionX*8), y+1+(motionY*8), z+0.5+(motionZ*8), -1.0D*motionX, 0.0D*motionY, 0.0D*motionZ);   
				    }
					for(int i = 0; i < altar.getSizeInventory(); i++){
						if(altar.getStackInSlot(i) != null){
							altar.setInventorySlotContents(i, null);
						}
					}
				}
			}
			
		}
			
		
		return false;
		
		
		
	}
	
	public void onBlockAdded(World world, int x, int y, int z){
		
		world.setTileEntity(x, y, z, this.createTileEntity(world, world.getBlockMetadata(x, y, z)));
		Random random = new Random();
        if(world.getBlock(x, y-yLevel, z) == Blocks.air){
        	updateTick(world, x, y, z, random);
        }
    }
	
	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TilePaganAltar();
	}
	
	
	@SuppressWarnings("unchecked")
	public void updateTick(World world, int x, int y, int z, Random random){      
        callPlayers(world);
        
    }

	
	public void callPlayers(World world){
		int x = (int) this.getBlockBoundsMinX();
		int y = (int) this.getBlockBoundsMinY();
		int z = (int) this.getBlockBoundsMinZ();
		
		if(world.getClosestPlayer(x, y, z, 100) != null){
			EntityPlayer player = world.getClosestPlayer(x, y, z, 100);
			player.addChatMessage(new ChatComponentTranslation("Come to me...").setChatStyle(new ChatStyle().setColor(EnumChatFormatting.WHITE)));
		}
	}
	
	public boolean shouldSideBeRendered(IBlockAccess iblockaccess, int i, int j, int k, int l){
	   return false;
	}

	public boolean isOpaqueCube(){
	   return false;
	}
}
