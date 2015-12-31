package Matryoshika.mods.saligia.utils;

public class math {

	public static double py3d(double dx, double dy, double dz) {
		double val;
		val = dx*dx+dy*dy+dz*dz;
		return Math.sqrt(val);
	}
	
	public static int SoulBrazierMax(){
		return Integer.decode("0x29A");
	}
	public static int SoulObelisk(){
		return Integer.decode("0x6C4A4");
	}
	public static int SoulNexus(){
		return Integer.decode("0x119B92A8");
	}
	
}
