package modele;

import java.nio.charset.Charset;
import java.util.ArrayList;

public class Decrypt {

    public Decrypt() { }

     /**
      * 
      * @param a byte[]
      * @param key byte[]
      * @return {@link String}
      */
      public String xor_encodeToString(byte[] a, byte[] key) {
        ArrayList<Byte> listBuffer = xor_encode(a, key);
        byte[] arrayBuffer = listToArrayOfByte(listBuffer);
        String output = this.ByteArrayToCharset(arrayBuffer);
        return output;
    }

     /**
      * 
      * @param a byte[]
      * @param key byte[]
      * @return {@link String}
      */
    public String xor_encodeToString(byte[] a, byte[] key, String encoder ){
        ArrayList<Byte> listBuffer = xor_encode(a, key);
        byte[] arrayBuffer = listToArrayOfByte(listBuffer);
        String output = this.ByteArrayToCharset(arrayBuffer, encoder);
        return output;
    }

     /**
      * 
      * @param a {@link String}
      * @param key {@link String}
      * @return {@link String}
      */
    public String xor_encodeToString(String a, String key) {
        ArrayList<Byte> listBuffer = xor_encode(a, key);
        byte[] arrayBuffer = listToArrayOfByte(listBuffer);
        String output = this.ByteArrayToCharset(arrayBuffer);
        return output;
    }

     /**
      * 
      * @param a {@link String}
      * @param key {@link String}
      * @return {@link String}
      */
    public String xor_encodeToString(String a, String key, String encoder ) {
        ArrayList<Byte> listBuffer = xor_encode(a, key);
        byte[] arrayBuffer = listToArrayOfByte(listBuffer);
        String output = this.ByteArrayToCharset(arrayBuffer, encoder);
        return output;
    }


    /**
     * Function that return a list thanks to a xor function between 
     * the "a" array of bytes and the "key" array of bytes
     * @param a byte[]
     * @param key byte[]
     * @return Return a List of Byte that is the result of the Xor function
     */
    public ArrayList<Byte> xor_encode(byte[] a, byte[] key) {
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
    public ArrayList<Byte> xor_encode(String a, String key) {
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
    public byte[] listToArrayOfByte(ArrayList<Byte> list) {
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
    public String ByteArrayToCharset(byte[] input) {
        return new String(input, Charset.defaultCharset());
    }
    
    /**
     * Convert an array of byte into a String of charset with a specific encoding
     * @param input byte[]
     * @return String
     */
    public String ByteArrayToCharset(byte[] input, String enc) {
        return new String(input, Charset.forName(enc));
    }

    public String[] getListOfUniqueWord(String text) {
        
        String regex = "([^a-zA-Z']+)'*\\1*";
        String[] n_words = text.split(regex);
        return n_words;
    }

}
