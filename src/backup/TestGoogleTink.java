package backup;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;

import com.google.crypto.tink.Aead;
import com.google.crypto.tink.CleartextKeysetHandle;
import com.google.crypto.tink.JsonKeysetReader;
import com.google.crypto.tink.JsonKeysetWriter;
import com.google.crypto.tink.KeysetHandle;
import com.google.crypto.tink.aead.AeadConfig;
import com.google.crypto.tink.aead.AeadKeyTemplates;
import com.google.crypto.tink.config.TinkConfig;
import com.google.crypto.tink.proto.KeyTemplate;

import util.GetRuntimeInput;

public class TestGoogleTink {
	public static void main(String[] args) {
		try {
			// Get Input
			GetRuntimeInput util = new GetRuntimeInput();
			String input = util.getInputOnce("Input String:");

			// Before using any of Tink APIs we need to initialize them.
			// If we need to use all implementations of ALL PRIMITIVES in Tink, use this:
			//TinkConfig.register();

			// While, for example, if we only need AEAD primitive, we can use
			// AeadConfig.register() method:
			AeadConfig.register();

			// A primitive can have multiple implementations

			// each primitive needs a key structure that contains all the key material and
			// parameters
			// Tink provides an object – KeysetHandle – which wraps a keyset with some
			// additional parameters and metadata
			KeyTemplate keyTemplate = AeadKeyTemplates.AES256_GCM;
			KeysetHandle aeadKeysetHandle = KeysetHandle.generateNew(keyTemplate);
			// We can obtain a primitive by calling KeysetHandle's getPrimitive() and
			// passing it a class
			Aead aead = aeadKeysetHandle.getPrimitive(Aead.class);
			// Variables for encryption
			String plaintext = input;
			String associatedData = "SignInPwd";
			// get the primitive and encrypt the desired data
			byte[] ciphertext = aead.encrypt(plaintext.getBytes(), associatedData.getBytes());

			System.out.println("Encode Plaintext (String) into AEAD Cipher (byte[]): " + ciphertext);
			System.out.println("Same ciphertext as String: "+ciphertext.toString());

//			// byte[] cipher -> hexstring
//			TestGoogleTink tink = new TestGoogleTink();
//			String wow = tink.encodeHexString(ciphertext);
//			System.out.println("Encode AEAD Cipher (byte[]) into HexString (String):" + wow);
//			// hexstring -> byte[] cipher
//			byte[] omg = tink.decodeHexString(wow);
//			System.out.println("Decode Hexstring (String) into AEAD Cipher (byte[]):" + omg);

			// we can decrypt the ciphertext using the decrypt() method:
			String decrypted = new String(aead.decrypt(ciphertext, associatedData.getBytes()));
			System.out.println("Decode AEAD Cipher (byte[]) into Plaintext (String): " + decrypted);
			
			
			// And after generating a key, we might want to persist it:
			String keysetFilename = "keyset.json";
			CleartextKeysetHandle.write(aeadKeysetHandle, JsonKeysetWriter.withFile(new File(keysetFilename)));

			// we can subsequently load it:
			// String keysetFilename = "keyset.json";
			KeysetHandle keysetHandle = CleartextKeysetHandle.read(JsonKeysetReader.withFile(new File(keysetFilename)));

		} catch (GeneralSecurityException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String byteToHex(byte num) {
		char[] hexDigits = new char[2];
		hexDigits[0] = Character.forDigit((num >> 4) & 0xF, 16);
		hexDigits[1] = Character.forDigit((num & 0xF), 16);
		return new String(hexDigits);
	}

	public byte hexToByte(String hexString) {
		int firstDigit = Character.digit(hexString.charAt(0), 16);
		if (firstDigit == -1) {
			throw new IllegalArgumentException("Invalid Hexadecimal Character: " + hexString.charAt(0));
		}
		int secondDigit = Character.digit(hexString.charAt(1), 16);
		if (secondDigit == -1) {
			throw new IllegalArgumentException("Invalid Hexadecimal Character: " + hexString.charAt(1));
		}
		return (byte) ((firstDigit << 4) + secondDigit);
	}

	public String encodeHexString(byte[] byteArray) {
		StringBuffer hexStringBuffer = new StringBuffer();
		for (int i = 0; i < byteArray.length; i++) {
			hexStringBuffer.append(byteToHex(byteArray[i]));
		}
		return hexStringBuffer.toString();
	}

	public byte[] decodeHexString(String hexString) {
		if (hexString.length() % 2 == 1) {
			throw new IllegalArgumentException("Invalid hexadecimal String supplied.");
		}

		byte[] bytes = new byte[hexString.length() / 2];
		for (int i = 0; i < hexString.length(); i += 2) {
			bytes[i / 2] = hexToByte(hexString.substring(i, i + 2));
		}
		return bytes;
	}

	// Byte Array to Hexadecimal String
//	public String encodeHexString(byte[] byteArray) {
//		StringBuffer hexStringBuffer = new StringBuffer();
//		for (int i = 0; i < byteArray.length; i++) {
//			char[] hexDigits = new char[2];
//			hexDigits[0] = Character.forDigit((byteArray[i] >> 4) & 0xF, 16);
//			hexDigits[1] = Character.forDigit((byteArray[i] & 0xF), 16);
//			hexStringBuffer.append(new String(hexDigits));
//		}
//		return hexStringBuffer.toString();
//	}

	// Hexadecimal String to Byte Array
//	public byte[] decodeHexString(String hexString) {
//		if (hexString.length() % 2 == 1) {
//			throw new IllegalArgumentException("Invalid hexadecimal String supplied.");
//		}
//		byte[] bytes = new byte[hexString.length() / 2];
//		for (int i = 0; i < hexString.length(); i += 2) {
//			int firstDigit = Character.digit(hexString.charAt(0), 16);
//			if (firstDigit == -1) {
//				throw new IllegalArgumentException("Invalid Hexadecimal Character: " + hexString.charAt(0));
//			}
//			int secondDigit = Character.digit(hexString.charAt(1), 16);
//			if (secondDigit == -1) {
//				throw new IllegalArgumentException("Invalid Hexadecimal Character: " + hexString.charAt(1));
//			}
//			bytes[i / 2] = (byte) ((firstDigit << 4) + secondDigit);
//		}
//		return bytes;
//	}
}
