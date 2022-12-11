package test;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class BloomFilter {

    int bitSize;
    String[] files;
    BitSet bitSet;

    public BloomFilter(int size, String file1, String file2) {

        bitSize = size;
        files = new String[2];
        files[0] = file1;
        files[1] = file2;
        bitSet = new BitSet();
    }

    public void add(String s)  {

        for (String file : files) {

            MessageDigest md = null;
            try {

                //init hash func (if getInstance(file) not working it will go to catch)
                md = MessageDigest.getInstance(file);
                //return bytes array for s from the hash func
                byte[] bytes = md.digest(s.getBytes());
                //get bytes value
                BigInteger bigInt = new BigInteger(bytes);
                int x = Math.abs(bigInt.intValue());
                //byte value mod bitSize give index to turn on
                bitSet.set(x % bitSize, true);
            }
            catch (NoSuchAlgorithmException e) { e.printStackTrace(); }
        }
    }

    @Override
    public String toString() {

        StringBuilder str = new StringBuilder(bitSet.length());
        for (int i = 0; i < bitSet.length(); i++)
            str.append(bitSet.get(i) ? "1" : "0");

        return str.toString();
    }

    public boolean contains(String s) {

        for (String file : files) {

            MessageDigest md = null;
            try {

                md = MessageDigest.getInstance(file);
                byte[] bytes = md.digest(s.getBytes());
                BigInteger bigInt = new BigInteger(bytes);
                int x = Math.abs(bigInt.intValue());
                if (bitSet.get(x % bitSize))
                    return true;
            }
            catch (NoSuchAlgorithmException e) { e.printStackTrace(); }
        }
        return false;
    }
}
