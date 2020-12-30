package main.notes.dataTypes;

import java.util.UUID;

public class UsingUUID {
    
    /**
     * UUID (Universally Unique Identifier), also known as GUID (Globally Unique Identifier) represents a 128-bit long value that is unique for all practical purposes. 
     * A UUID is made of up of hex digits  (4 chars each) along with 4 ??-?? symbols which make its length equal to 36 characters.
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
        System.out.println("variant = " + variant);     // prints 0
        System.out.println("version =" + version);      // prints 0
        
        UUID uuid = UUID.randomUUID();
        System.out.println(uuid);
        int variant1 = uuid.variant();
        int version1 = uuid.version();
        System.out.println("variant = " + variant1);    // prints 2
        // The UUID v4 implementation uses random numbers as the source.
        // The Java implementation is SecureRandom ?? which uses an unpredictable value as the seed to generate random numbers to reduce the chance of collisions.
        System.out.println("version =" + version1);     // prints 4
        
        String uuidHexDigitString = "00000001-0000-0000-0000-00000000000";
        UUID uuid2 = UUID.fromString( uuidHexDigitString);
        System.out.println(uuid2);
        int variant2 = uuid2.variant();
        int version2 = uuid2.version();
        System.out.println("variant = " + variant2);    // prints 0
        System.out.println("version =" + version2);     // prints 0
        
        uuidHexDigitString = "00000001-0000-0000-0000-0000000000012345678910";      // Note: No errors even when too long!
        uuid2 = UUID.fromString( uuidHexDigitString);                               // returns 00000001-0000-0000-0000-012345678910  // it's been shortened
        System.out.println(uuid2);
        variant2 = uuid2.variant();
        version2 = uuid2.version();
        System.out.println("variant = " + variant2);    // prints 0
        System.out.println("version =" + version2);     // prints 0
        
        uuidHexDigitString = "ABCDEFGH-IJKL-0000-0000-00000000000";                 // "ABCDEFGH" Letters are NOT valid, throws NumberFormatException
        uuid2 = UUID.fromString( uuidHexDigitString);                               // it tried to Long.parseLong, Long.valueOf, Long.decode, all failed
        System.out.println(uuid2);
        
        
    }

}
