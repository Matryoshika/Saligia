package Matryoshika.mods.saligia.rendering.GUIHandler;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class MSGuiHandler implements IGuiHandler {

	public enum GUI {
		generator(0);

		public int ordinal;

		private GUI(int id) {
			this.ordinal = id;
		}

		private static GUI[] allValues = values();

		public static GUI fromOrdinal(int n) {
			return allValues[n];
		}
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		GUI gui = GUI.fromOrdinal(ID);

		if (gui == GUI.generator) {
			//TileGenerator generator = (TileGenerator) world.getTileEntity(x, y, z);
			//return new ContainerGenerator(player.inventory, generator);
		}
		return null;

	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		GUI gui = GUI.fromOrdinal(ID);

		if (gui == GUI.generator) {
			//TileGenerator generator = (TileGenerator) world.getTileEntity(x, y, z);
			//return new GuiDynamicGenerator(player.inventory, generator);
		}

		return null;
	}

}
