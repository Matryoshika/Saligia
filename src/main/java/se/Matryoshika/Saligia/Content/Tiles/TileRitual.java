/**
 * 
 */
package se.Matryoshika.Saligia.Content.Tiles;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import se.Matryoshika.Saligia.Saligia;
import se.Matryoshika.Saligia.Utils.Print;

/**
 * This class was created by Matryoshika Aug 13, 2016
 * Property of Matryoshika. 
 * Part of the Saligia mod.
 * May be viewed for educational purposes.
 */
public class TileRitual extends CustomTileClass implements ITileMasterRitual{
	
	public boolean isClicked;
	
	private int timer;
	public static String renderMultiBlockKey;
	
	public TileRitual setRenderKey(){
		this.renderMultiBlockKey = null;
		return this;
	}

	@Override
	public void setRitualMultiblocks(Object[][] coordArray) {
		multiblocks.put(getName(), coordArray);
		
	}

	@Override
	public Object[][] getRitualMultiblocks() {
		String ritualName = getName();
		return (Object[][]) multiblocks.get(ritualName);
	}

	public static String getName(){
		return Saligia.MODID+":"+"baseRitualBlock";
	}
	
	public static void dispatchTEToNearbyPlayers(World world, BlockPos pos) {
		TileEntity tile = world.getTileEntity(pos);
		if(tile != null)
			dispatchTEToNearbyPlayers(tile);
	}
	
	public static void dispatchTEToNearbyPlayers(TileEntity tile) {
		World world = tile.getWorld();
		List players = world.playerEntities;
		if(!tile.getWorld().isRemote){
			for(Object player : players)
				if(player instanceof EntityPlayerMP) {
					EntityPlayerMP mp = (EntityPlayerMP) player;
					if(pointDistancePlane(mp.posX, mp.posZ, tile.getPos().getX() + 0.5, tile.getPos().getZ() + 0.5) < 64){
						final SPacketUpdateTileEntity updatePacket = tile.getUpdatePacket();
						if(updatePacket != null)
							mp.connection.sendPacket(tile.getUpdatePacket());
					}	
				}
		}
	}
	
	public static float pointDistancePlane(double x1, double y1, double x2, double y2) {
		return (float) Math.hypot(x1 - x2, y1 - y2);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		renderMultiBlockKey = compound.getString(Saligia.MODID+"TileRenderKey");
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		compound.setString(Saligia.MODID+"TileRenderKey", renderMultiBlockKey);
		return compound;
	}
	
	@SideOnly(Side.CLIENT)
    public net.minecraft.util.math.AxisAlignedBB getRenderBoundingBox(){
        return new AxisAlignedBB(pos, pos.add(50, 50, 50));
    }

}
