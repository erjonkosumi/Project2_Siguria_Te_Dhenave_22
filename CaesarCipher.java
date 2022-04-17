import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;


public class CaesarCipher {

    public static void main(String[] args) throws Exception {

        String plaintext = "ILOVECAKE";
        int shift = 3;
        String cipherText = CaesarCipher.encrypt(plaintext, 3);

        System.out.println("Plaintex: " + plaintext);
        System.out.println("Shift: " + shift);
        System.out.println("Ciphertext: " + cipherText);

        String decryptedText = CaesarCipher.decrypt(cipherText, 3);
        System.out.println("Decryption result: " + decryptedText);

        System.out.println("\nBrute Force Attack");
        CaesarCipher.bruteForceAttack(cipherText);

        System.out.println("FREQUENCY ANALYSIS");
        System.out.println("Encrypting The Restaurant at the End of the Universe...");
        String bigText = CaesarCipher.readFile(CaesarCipher.class.getResource("/hitch2.txt"));
        String bigCipherText = CaesarCipher.encrypt(bigText, 3);
        System.out.println(bigCipherText.substring(0, 200) + "...");

        System.out.println("Performing frequency analysis...");
        String decryptedBigCipherText = CaesarCipher.frequencyAnalysis(bigCipherText);
        System.out.println(decryptedBigCipherText.substring(0, 200) + "...");

    }

 
    public static String readFile(URL url) throws IOException, URISyntaxException {
        byte[] encoded = Files.readAllBytes(Paths.get(url.toURI()));
        return Charset.defaultCharset().decode(ByteBuffer.wrap(encoded)).toString();
    }


    public static String encrypt(String plaintext, int shift) {
       
        plaintext = plaintext.replaceAll("[^a-zA-Z]", "").toUpperCase();
        StringBuilder ciphertext = new StringBuilder();
        for (char c : plaintext.toCharArray()) {
        
            int newPos = c - 'A';
          
            newPos += shift;
           
            newPos = Math.floorMod(newPos, LetterFrequencyUtils.ALPHABET_COUNT);
           
            newPos += 'A';
            ciphertext.append((char) newPos);
        }
        return ciphertext.toString();
    }

 
    public static String decrypt(String ciphertext, int shift) {
   
        ciphertext = ciphertext.replaceAll("[^a-zA-Z]", "").toUpperCase();
        StringBuilder plaintext = new StringBuilder();
        for (char c : ciphertext.toCharArray()) {
          
            int newPos = c - 'A';
     
            newPos -= shift;
          
            newPos = Math.floorMod(newPos, LetterFrequencyUtils.ALPHABET_COUNT);
            
            newPos += 'A';
            plaintext.append((char) newPos);
        }
        return plaintext.toString();
    }


    public static void bruteForceAttack(String ciphertext) {
        // shift of 0 or 26 would result in no change
        for (int shift = 1; shift < LetterFrequencyUtils.ALPHABET_COUNT; shift++)
            System.out.println("shift: " + shift + ", " + decrypt(ciphertext, shift));
    }


    public static String frequencyAnalysis(String ciphertext) {
        ciphertext = ciphertext.replaceAll("[^a-zA-Z]", "");
        int bestShift = calculateShift(ciphertext);
        return decrypt(ciphertext, bestShift);
    }

   
    public static int calculateShift(String ciphertext) {
        ciphertext = ciphertext.replaceAll("[^a-zA-Z]", "");
        int shift = 0;
        double fitness = Integer.MAX_VALUE;
        for (int i = 0; i < LetterFrequencyUtils.ALPHABET_COUNT; i++) {
       
            double tempFitness = LetterFrequencyUtils.chiSquareAgainstEnglish(CaesarCipher.decrypt(ciphertext, i));
         
            if (tempFitness < fitness) {
                fitness = tempFitness;
                shift = i;
            }
        }
        return shift;
    }

}