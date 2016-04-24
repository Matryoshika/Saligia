package Matryoshika.mods.saligia.tile.soulsystem;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;

public class TileLeyLineCrystal extends TileEntity{
	
	final static int MAX_RANGE = 16;
	final static double STANDARD_PACKET = Math.floor(Math.pow(6, tierCrystal()+bonusAtt()));
	final static double MINIMUM_PACKET = Math.floor(Math.pow(tierCrystal(), tierCrystal()+bonusAtt()));
	//T1 = 6,1 : T2 = 529,11 : T3 = 7776,243
	int timer = 0;
	int update = 0;
	
	TileEntity SOURCE = null;
	public int SOURCE_X;
	public int SOURCE_Y;
	public int SOURCE_Z;
	
	TileEntity DESTINATION = null;
	public int DEST_X;
	public int DEST_Y;
	public int DEST_Z;
	
	public boolean SOURCE_EXISTS = false;
	public boolean DEST_EXISTS = false;
	
	
	final int [][] SIDES = new int [][]{
		//pos X, neg X,    pos Z, neg Z,  pos Y,   neg Y
		{1,0,0},{-1,0,0},{0,0,1},{0,0,-1},{0,1,0},{0,-1,0}
	};
	
	
	@Override
	public void updateEntity() {
		update++;
		timer++;
		
		if(timer >= 30){
			if(SOURCE != null && DESTINATION != null){
				if(SOURCE instanceof TileSoulBrazier && DESTINATION instanceof TileSoulBrazier){
					shootAnimun(SOURCE, DESTINATION);
					timer = 0;
				}
				else{
					timer = 0;
					return;
				}
			}
			else{
				timer = 0;
				return;
			}
		}
		
		/*
		 * North = Negative Z
		 * South = Positive Z
		 * West = Negative X
		 * East = Positive X
		 * Up = Positive Y
		 * Down = Negative Y
		 */
		if(update >= 20){
			
			getSource();
			
			update = 0;
			
			if(SOURCE != null && DESTINATION == null){
				calculateDirection();
			}
			if(SOURCE != null && DEST_EXISTS == false){
				calculateDirection();
			}
		}
		
		//if(SOURCE != null){System.out.println("Source is found, boss");}
		
	}
	public void getSource(){
		
		for(int[] coords : SIDES) {
			int x1 = this.xCoord + coords[0];
			int y1 = this.yCoord + coords[2];
			int z1 = this.zCoord + coords[1];
			
			TileEntity poss_source = worldObj.getTileEntity(x1, y1, z1);
			if(poss_source != null && poss_source instanceof TileSoulBrazier){
				SOURCE = poss_source;
			}
		}
	}
	
	public void calculateDirection(){
		
		int dx = this.xCoord - SOURCE.xCoord;
		int dy = this.yCoord - SOURCE.yCoord;
		int dz = this.zCoord - SOURCE.zCoord;
		
		if(dx != 0){
			for(int i = 1; i<= MAX_RANGE; i++){
				TileEntity poss_dest = worldObj.getTileEntity((this.xCoord+dx)+(i*dx), this.yCoord, this.zCoord);
				if(poss_dest != null && poss_dest instanceof TileSoulBrazier){
					DESTINATION = poss_dest;
					break;
				}
			}
		}
		else if(dy != 0){
			for(int i = 1; i<= MAX_RANGE; i++){
				TileEntity poss_dest = worldObj.getTileEntity(this.xCoord, (this.yCoord+dy)+(i*dy), this.zCoord);
				if(poss_dest != null && poss_dest instanceof TileSoulBrazier){
					DESTINATION = poss_dest;
					break;
				}
			}
		}
		else if(dz != 0){
			for(int i = 1; i<= MAX_RANGE; i++){
				TileEntity poss_dest = worldObj.getTileEntity(this.xCoord, this.yCoord, (this.zCoord+dz)+(i*dz));
				if(poss_dest != null && poss_dest instanceof TileSoulBrazier){
					DESTINATION = poss_dest;
					break;
				}
			}
		}
	}
	
	public void shootAnimun(TileEntity source, TileEntity destination){
		//
		if(source instanceof TileSoulBrazier && destination instanceof TileSoulBrazier){
			TileSoulBrazier tileSOURCE = (TileSoulBrazier) source;
			TileSoulBrazier tileDEST = (TileSoulBrazier) destination;
			
			//Double-check to make sure that SOURCE & DEST still exist.
			
			if(worldObj.getTileEntity(tileSOURCE.xCoord, tileSOURCE.yCoord, tileSOURCE.zCoord) != null && worldObj.getTileEntity(tileSOURCE.xCoord, tileSOURCE.yCoord, tileSOURCE.zCoord) == SOURCE){
				SOURCE_EXISTS = true;
			}
			else{
				SOURCE_EXISTS = false;
			}
			if(worldObj.getTileEntity(tileDEST.xCoord, tileDEST.yCoord, tileDEST.zCoord) != null && worldObj.getTileEntity(tileDEST.xCoord, tileDEST.yCoord, tileDEST.zCoord) == DESTINATION){
				DEST_EXISTS = true;
			}
			else{
				DEST_EXISTS = false;
			}
			
			//System.out.println("Source is at : "+ SOURCE.xCoord + ", " + SOURCE.yCoord + ", " +SOURCE.zCoord);
			
			//if either nothing to take, or destination is full
			if(tileDEST.amount >= tileDEST.getCapacity()){
				System.out.println(tileDEST.getCapacity());
				//System.out.println(tileDEST.capacity);
				return;
			}
			//if can shoot a full packet from source to destination
			if((tileSOURCE.amount >= STANDARD_PACKET) && (tileDEST.amount+STANDARD_PACKET < tileDEST.getCapacity())){
				if(SOURCE_EXISTS && DEST_EXISTS){
					tileSOURCE.amount -= STANDARD_PACKET;
					tileDEST.amount += STANDARD_PACKET;
					//System.out.println("Shooting Standard");
					return;
				}
			}
			//if source can shoot, but destination cannot take full standard
			if((tileSOURCE.amount <= STANDARD_PACKET) && (tileDEST.amount+STANDARD_PACKET > tileDEST.getCapacity()) && (tileDEST.amount < tileDEST.getCapacity())){
				tileSOURCE.amount -= MINIMUM_PACKET;
				tileDEST.amount += MINIMUM_PACKET;
				//System.out.println("Shooting Minimum");
				return;
			}
		}
	}
	
	
	public static double tierCrystal(){
		return 1;
	}
	public static double bonusAtt(){
		if(tierCrystal() == 1) return 0;     // 1+0   = 1
		if(tierCrystal() == 2) return 1.5;   // 2+1.5 = 3.5
		return 2;                            // 3+2   = 5
	}

}
