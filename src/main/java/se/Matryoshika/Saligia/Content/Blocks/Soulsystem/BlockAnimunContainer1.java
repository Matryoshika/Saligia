package se.Matryoshika.Saligia.Content.Blocks.Soulsystem;

import org.apache.commons.codec.binary.Base64;
import se.Matryoshika.Saligia.Utils.Constants;

/**
 * This class was created by Matryoshika Aug 15, 2016
 * Property of Matryoshika. 
 * Part of the Saligia mod.
 * May be viewed for educational purposes.
 */
public class BlockAnimunContainer1 extends AnimunContainer{

	@Override
	public int maxStorage() {
		return Constants.getStorageValues(storageTier());
	}

	@Override
	public int currentAmount() {
		return currentAnimun;
	}

	@Override
	public int storageTier() {
		return 1;
	}

	@Override
	public void addToAmount(int amount) {
		if(amount + currentAmount() <= maxStorage())
		currentAnimun += amount;
	}

}
