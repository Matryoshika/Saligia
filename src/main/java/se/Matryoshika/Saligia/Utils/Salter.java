package se.Matryoshika.Saligia.Utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Base64;

/**
 * This class was created by Matryoshika Aug 15, 2016
 * Property of Matryoshika. 
 * Part of the Saligia mod.
 * May be viewed for educational purposes.
 */
public class Salter {
	

	public static String getSalted(String password){
		String encodedPassword = "";
		Base64 base64 = new Base64();
		String saltChars = "All the innocent involved die in their plan; Sacrificial lambs;"
				+ "This unwarrented cycle will never end; With no one there to help;"
				+ "Holding up this one torch, that your friends helped you claim;"
				+ "And the bells ring at dawn, sending a chill; Tones of Glory sound aloud;"
				+ "Kneeling down, the Messiah accepts His will; Surrendering;"
				+ "Finally, she understands, and smiles to herself;"
				+ "Mourning her nine friends who sacrificed their lives;"
				+ "From the Tower she reaches up to the skies;";
		byte[] salt = base64.decode(saltChars);
		MessageDigest digest;
		try {
			digest = MessageDigest.getInstance("SHA-256");
			digest.reset();
			digest.update(salt);
			
			byte[] btPass = digest.digest(password.getBytes("UTF-8"));
			for(int i = 0; i < password.length(); i++){
				digest.reset();
				btPass = digest.digest(btPass);
			}
			encodedPassword = base64.encodeAsString(btPass);
			
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			
		}
		return encodedPassword;
	}

}
