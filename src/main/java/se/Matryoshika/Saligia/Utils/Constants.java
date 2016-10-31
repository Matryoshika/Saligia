/**
 * 
 */
package se.Matryoshika.Saligia.Utils;

import org.apache.commons.codec.binary.Base64;

/**
 * This class was created by Matryoshika Aug 15, 2016
 * Property of Matryoshika. 
 * Part of the Saligia mod.
 * May be viewed for educational purposes.
 */
public class Constants {
	
	//I shall do everything in my power to mess with you :)
	//I know ya'll love me. Oh, "hate" me, my bad.
	
	//Ya'll who know how to crack this very simple salt, mind keeping all of this a seeeeecret?
	//I promise you cookies & icecream!
	private static final String ANIMUN_STORAGE_TIER_1 = "NjY2";
	private static final String ANIMUN_STORAGE_TIER_2 = "NDQzNTU2";
	private static final String ANIMUN_STORAGE_TIER_3 = "Mjk1NDA4Mjk2";
	
	private static final String ANIMUN_HAND_HELD_1 = "MTAw";
	private static final String ANIMUN_HAND_HELD_2 = "NjY2MDA=";
	private static final String ANIMUN_HAND_HELD_3 = "NDQzNTU2MDA=";
	
	public static float getStorageValues(float i){
		Base64 base64 = new Base64();
		if(i == 1)
			return Integer.valueOf(new String(base64.decode(ANIMUN_STORAGE_TIER_1.getBytes())));
		else if(i == 2)
			return Integer.valueOf(new String(base64.decode(ANIMUN_STORAGE_TIER_2.getBytes())));
		else if(i == 3)
			return Integer.valueOf(new String(base64.decode(ANIMUN_STORAGE_TIER_3.getBytes())));
		else
			return 0;
	}
	
	
	
	//These will take some time to get incorporated, but boy what fun we'll have >:3
	private static final String TTE_SSELB = "+gCuymkKwE5BQVe7Tp2zr0eT4JqPODk/jRQPUFHXRJE=";
	private static final String AVT_SSELB = "Zk26nALOdCiS2SeTm5lbOEy7TmMx1vkRg6Gmq/VgYGE=";
	private static final String ERT_SSELB = "yEWcQJx1/vEKHnan93uJ4buRXljx5iy1TQ5pRtwWLmA=";
	private static final String ARY_SSELB = "3WCDOel+C0O4yVVchVX51sXOIJdYsVerVG9Z5kH+dY0=";
	private static final String MEF_SSELB = "NzRMvUiyXAc5A3SCjzoKzrAbLPhLXGngJshf6+d241g=";
	private static final String XES_SSELB = "n8RPWQbpTK4E416rPIu0PZBGV8YzlEp6OnfVowGACu0=";
	private static final String UJS_SSELB = "DLzsxOCj4t33yQ29+fkYbEqYMKTFkZ2vqSVMfpvZLBs=";
	private static final String ATT_SSELB = "Xrv+sGByeo+m653e5VDoibj2IxieRROppnuwJmr8NI8=";
	private static final String OIN_SSELB = "ZO6/qxden6BJYcCYnaNm2mGCKES9Ly78BXCs8vpC0EA=";
	
	public static String TEGGNARTS(int i){
		switch(i){
		case 1:{ return TTE_SSELB;}
		case 2:{ return AVT_SSELB;}
		case 3:{ return ERT_SSELB;}
		case 4:{ return ARY_SSELB;}
		case 5:{ return MEF_SSELB;}
		case 6:{ return XES_SSELB;}
		case 7:{ return UJS_SSELB;}
		case 8:{ return ATT_SSELB;}
		case 9:{ return OIN_SSELB;}
		default:{ return "";}
		}
	}

}
