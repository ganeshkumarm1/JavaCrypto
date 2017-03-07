import java.math.BigInteger;
import java.security.*;
import java.security.spec.*;
import java.io.*;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.*;
import java.net.*;
import javax.xml.bind.DatatypeConverter;

class ECC{
	static PrivateKey privateKey;
	static PublicKey publicKey;

	public static void main(String[] args) {
		try{
			Scanner input = new Scanner(System.in);
			String contents = readFile("./ganesh.txt");
			MessageDigest m = MessageDigest.getInstance("MD5");
            m.update(contents.getBytes(),0,contents.length());
			byte[] signature = signText(m.digest());
			PrintWriter out = new PrintWriter("./signature.txt");
			String signTostring = DatatypeConverter.printBase64Binary(signature);
			signTostring = URLEncoder.encode(signTostring, "UTF-8");
			out.print(signTostring);
			out.close();
			input.nextLine();

			contents = readFile("./ganesh.txt");
			String signatureContents = readFile("./signature.txt");
			String st = URLDecoder.decode(signatureContents, "UTF-8");
			byte[] sign_byte = DatatypeConverter.parseBase64Binary(st);
            m.update(contents.getBytes(),0,contents.length());
			System.out.println("Verifying Signature: "+verifySignature(m.digest(),sign_byte,publicKey));

		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	private static byte[] signText(byte[] contentBytes) throws Exception {
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("EC","SunEC");
		ECGenParameterSpec ecGenParameterSpec = new ECGenParameterSpec("sect163k1");
		keyPairGenerator.initialize(ecGenParameterSpec);
		KeyPair keyPair = keyPairGenerator.genKeyPair();
		privateKey = keyPair.getPrivate();
		publicKey = keyPair.getPublic();
		Signature signature = Signature.getInstance("SHA1withECDSA","SunEC");
		signature.initSign(privateKey);
		signature.update(contentBytes);
		byte[] signedContent = signature.sign();
		System.out.println(new BigInteger(1,signedContent).toString(16).toUpperCase());
		return signedContent;
	}

	private static boolean verifySignature(byte[] contentBytes, byte[] signature, PublicKey publicKey) throws Exception{
		Signature newSignature = Signature.getInstance("SHA1withECDSA","SunEC");
		newSignature.initVerify(publicKey);
		newSignature.update(contentBytes);
		return newSignature.verify(signature);
	}

	private static String readFile( String pathToFile ) throws IOException {

		String contents = new String(Files.readAllBytes(Paths.get(pathToFile)));
		return contents;
	}
}