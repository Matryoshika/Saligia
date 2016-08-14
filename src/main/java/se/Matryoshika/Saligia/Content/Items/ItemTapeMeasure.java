/**
 * 
 */
package se.Matryoshika.Saligia.Content.Items;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import se.Matryoshika.Saligia.Saligia;
import se.Matryoshika.Saligia.Utils.Print;

/**
 * This class was created by Matryoshika Aug 13, 2016
 * Property of Matryoshika. 
 * Part of the Saligia mod.
 * May be viewed for educational purposes.
 */
public class ItemTapeMeasure extends Item{
	
	int xa, ya, za, xb, yb, zb, X, Y, Z;
	ITextComponent component;
	ITextComponent component2;
	Block block;
	
	public ItemTapeMeasure(){
		this.setMaxStackSize(1);
		this.setRegistryName("ItemTapeMeasure");
		this.setUnlocalizedName(this.getRegistryName().toString());
		this.setCreativeTab(Saligia.saligiaTab);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack tape, World world, EntityPlayer player, EnumHand hand){
		if(world.isRemote){
			if(Minecraft.getMinecraft().objectMouseOver != null){
				if(player.isSneaking()){
					xa = Minecraft.getMinecraft().objectMouseOver.getBlockPos().getX();
					ya = Minecraft.getMinecraft().objectMouseOver.getBlockPos().getY();
					za = Minecraft.getMinecraft().objectMouseOver.getBlockPos().getZ();
					if(world.getTileEntity(new BlockPos(xa,ya,za)) != null){
						System.out.println(world.getTileEntity(new BlockPos(xa,ya,za)));
					}
				}
				else{
					xb = Minecraft.getMinecraft().objectMouseOver.getBlockPos().getX();
					yb = Minecraft.getMinecraft().objectMouseOver.getBlockPos().getY();
					zb = Minecraft.getMinecraft().objectMouseOver.getBlockPos().getZ();
					block = world.getBlockState(Minecraft.getMinecraft().objectMouseOver.getBlockPos()).getBlock();
					countAxis(player);
				}
			}
		}
		return new ActionResult(EnumActionResult.PASS, tape);
	}
	
	public void countAxis(EntityPlayer player){
		if(player.worldObj.getTileEntity(new BlockPos(xa,ya,za)) != null){
			component2 = new TextComponentTranslation(player.worldObj.getTileEntity(new BlockPos(xa,ya,za)).toString());
			player.addChatMessage(component2);
		}
		X = xb-xa;
		Y = yb-ya;
		Z = zb-za;
		component = new TextComponentTranslation(Print.printXYZ(X, Y, Z));
		player.addChatMessage(component);
	}

}
