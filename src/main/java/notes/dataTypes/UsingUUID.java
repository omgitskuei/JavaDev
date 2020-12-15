package main.java.notes.dataTypes;

import java.util.UUID;

public class UsingUUID {
    
    /**
     * UUID (Universally Unique Identifier), also known as GUID (Globally Unique Identifier) represents a 128-bit long value that is unique for all practical purposes. 
     * A UUID is made of up of hex digits  (4 chars each) along with 4 “-” symbols which make its length equal to 36 characters.
     *     eg. 123e4567-e89b-12d3-a456-556642440000
     * 
     * @param args
     */
    public static void main(String[] args) {
        
        /**
         * Creating UUIDs
         */
        // The Nil UUID is a special form of UUID in which all bits are set to zero.
        UUID id = new UUID(0, 0);
        System.out.println(id);         // prints 00000000-0000-0000-0000-000000000000
        int variant = id.variant();
        int version = id.version();
        System.out.println(variant);
        System.out.println(version);
        
        UUID uuid = UUID.randomUUID();
        System.out.println(uuid);
        int variant1 = id.variant();
        int version1 = id.version();
        System.out.println(variant1);
        System.out.println(version1);
        
        String uuidHexDigitString = "00000001-0000-0000-0000-00000000000";
        UUID uuid2 = UUID.fromString( uuidHexDigitString);
        System.out.println(uuid2);
         
        uuidHexDigitString = "00000001-0000-0000-0000-0000000000012345678910";      // Note: No errors even when too long!
        uuid2 = UUID.fromString( uuidHexDigitString);                               // returns 00000001-0000-0000-0000-012345678910  // it's been shortened
        System.out.println(uuid2);
        
        uuidHexDigitString = "ABCDEFGH-IJKL-0000-0000-00000000000";                 // Letters are NOT ok
        uuid2 = UUID.fromString( uuidHexDigitString);
        System.out.println(uuid2);
    }

}
