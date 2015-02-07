package data;

/**
 * Bin.java
 * <br>
 * Makes it easier to get a path to various resources in the JAR file.
 * 
 * @author Don McCurdy
 * @date June 2010
 *
 */
public class Bin {
	/** Singleton instance */ private static Bin instance = new Bin();

	public static Class<? extends Object> getResourceClass(){
		return instance.getClass();
	}

	/** From www.java-tips.org */
	static public final byte HALF_TONE [] =
	{ (byte) 0xAA, (byte) 0xAA, (byte) 0xAA, (byte) 0xAA, (byte) 0x55,
		(byte) 0x55, (byte) 0x55, (byte) 0x55, (byte) 0xAA, (byte) 0xAA,
		(byte) 0xAA, (byte) 0xAA, (byte) 0x55, (byte) 0x55, (byte) 0x55,
		(byte) 0x55, (byte) 0xAA, (byte) 0xAA, (byte) 0xAA, (byte) 0xAA,
		(byte) 0x55, (byte) 0x55, (byte) 0x55, (byte) 0x55, (byte) 0xAA,
		(byte) 0xAA, (byte) 0xAA, (byte) 0xAA, (byte) 0x55, (byte) 0x55,
		(byte) 0x55, (byte) 0x55, (byte) 0xAA, (byte) 0xAA, (byte) 0xAA,
		(byte) 0xAA, (byte) 0x55, (byte) 0x55, (byte) 0x55, (byte) 0x55,
		(byte) 0xAA, (byte) 0xAA, (byte) 0xAA, (byte) 0xAA, (byte) 0x55,
		(byte) 0x55, (byte) 0x55, (byte) 0x55, (byte) 0xAA, (byte) 0xAA,
		(byte) 0xAA, (byte) 0xAA, (byte) 0x55, (byte) 0x55, (byte) 0x55,
		(byte) 0x55, (byte) 0xAA, (byte) 0xAA, (byte) 0xAA, (byte) 0xAA,
		(byte) 0x55, (byte) 0x55, (byte) 0x55, (byte) 0x55, (byte) 0xAA,
		(byte) 0xAA, (byte) 0xAA, (byte) 0xAA, (byte) 0x55, (byte) 0x55,
		(byte) 0x55, (byte) 0x55, (byte) 0xAA, (byte) 0xAA, (byte) 0xAA,
		(byte) 0xAA, (byte) 0x55, (byte) 0x55, (byte) 0x55, (byte) 0x55,
		(byte) 0xAA, (byte) 0xAA, (byte) 0xAA, (byte) 0xAA, (byte) 0x55,
		(byte) 0x55, (byte) 0x55, (byte) 0x55, (byte) 0xAA, (byte) 0xAA,
		(byte) 0xAA, (byte) 0xAA, (byte) 0x55, (byte) 0x55, (byte) 0x55,
		(byte) 0x55, (byte) 0xAA, (byte) 0xAA, (byte) 0xAA, (byte) 0xAA,
		(byte) 0x55, (byte) 0x55, (byte) 0x55, (byte) 0x55, (byte) 0xAA,
		(byte) 0xAA, (byte) 0xAA, (byte) 0xAA, (byte) 0x55, (byte) 0x55,
		(byte) 0x55, (byte) 0x55, (byte) 0xAA, (byte) 0xAA, (byte) 0xAA,
		(byte) 0xAA, (byte) 0x55, (byte) 0x55, (byte) 0x55, (byte) 0x55,
		(byte) 0xAA, (byte) 0xAA, (byte) 0xAA, (byte) 0xAA, (byte) 0x55,
		(byte) 0x55, (byte) 0x55, (byte) 0x55 };

}
