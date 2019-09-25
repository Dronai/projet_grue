package modele;

import java.nio.charset.Charset;
import java.util.ArrayList;

public class Decrypt {

    public Decrypt() { }

    /**
     * Function that return a list thanks to a xor function between 
     * the "a" array of bytes and the "key" array of bytes
     * @param a byte[]
     * @param key byte[]
     * @return Return a List of Byte that is the result of the Xor function
     */
    protected ArrayList<Byte> xor_encode(byte[] a, byte[] key) {
        ArrayList<Byte> outArray = new ArrayList<Byte>(a.length);
        int pos = 0;

        for (int i = 0; i < a.length; i++) {
            outArray.add( (byte) (a[i] ^ key[pos]) );
            if (pos == key.length -1)  { pos = 0; }  else { pos++; }
        }
        return outArray;
    }

        /**
     * Function that return a list thanks to a xor function 
     * between the String "a" and the String "key" 
     * @param a String
     * @param key String
     * @return a List of Byte that is the result of the Xor function
     */
    protected ArrayList<Byte> xor_encode(String a, String key) {
        a = a.toUpperCase();
        byte[] input = a.getBytes();
        byte[] keypass = key.getBytes();

        ArrayList<Byte> outArray = xor_encode(input, keypass);
        return outArray;
    }


    /**
     * Fonction pour Changer un ArrayList<Byte> en array de byte
     * @param list ArrayList<Byte>
     * @return an Array of byte
     */
    protected byte[] listToArrayOfByte(ArrayList<Byte> list) {
        byte[] res = new byte[list.size()];     
        for (int i = 0; i < list.size(); i++) {
            // define an "item to manipulate"
            Byte item = list.get(i);
            res[i] = item.byteValue();            
        }
        return res;
    }

    /**
     * Convert an array of byte into a String of charset
     * @param input byte[]
     * @return String
     */
    protected String ByteArrayToCharset(byte[] input) {
        return new String(input, Charset.defaultCharset());
    }
    
    /**
     * Convert an array of byte into a String of charset with a specific encoding
     * @param input byte[]
     * @return String
     */
    protected String ByteArrayToCharset(byte[] input, String enc) {
        return new String(input, Charset.forName(enc));
    }

}
