import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Arrays;
import java.util.Scanner; // Import the Scanner class to read text files

//constructor
public class HuffmanConverter{

        // ASCII number of characters
        public static final int NUMBER_OF_CHARACTERS = 256;

        private String contents;
        private HuffmanTree huffmanTree;
        private int count[];
        private String code[];

        // Construct using an input string.
        // Initialize count and code.
        public HuffmanConverter(String input) {
          this.contents = input;
          this.count = new int[NUMBER_OF_CHARACTERS];
          this.code = new String[NUMBER_OF_CHARACTERS];
        }

        // Record how often each character occurs and store in count.
        public void recordFrequencies() {
            for (char c : contents.toCharArray()) {
                int i = (int) c;
                count[i]++;
            }
            for (int i = 0; i < NUMBER_OF_CHARACTERS; i++) {
                if (count[i] > 0) {
                    char character = (char) i;
                    if (character == '\n'){System.out.print("<" + "\\n" + "," + count[i] + "> ");}
                    else{System.out.print("<" + character + "," + count[i] + "> ");}
                }
        }
         System.out.println();
        }

        // Construct a Huffman tree from count and 
        // store the tree in huffmanTree.
        public void frequenciesToTree() {
            BinaryHeap<HuffmanNode> heap = new BinaryHeap<>(count.length);
            for (int i = 0; i < count.length; i++) {
                if (count[i] > 0) {
                    char c = (char) i;
                    String letter = String.valueOf(c);
                    double frequency = (double)count[i];
                    heap.insert(new HuffmanNode(letter, frequency));
                }
            }
            huffmanTree = HuffmanTree.createFromHeap(heap);
        }

        // Construct code from huffmanTree.
        public void treeToCode() {
            Arrays.fill(code, "");
            treeToCode(this.huffmanTree.root, "");
            System.out.println();
            this.huffmanTree.printLegend();
        }

        private void treeToCode(HuffmanNode t, String encoding) {
            if (t!= null) {
                if (t.letter.length() > 1) {
                    treeToCode(t.left, encoding + "0");
                    treeToCode(t.right, encoding + "1");
                }
                else{
                    code[(int) t.letter.charAt(0)] = encoding;
                }
            }
        }

        // Encode content using code.
        public String encodeMessage() {
            StringBuilder encodedMessage = new StringBuilder();
            for (char c : contents.toCharArray()) {
                encodedMessage.append(code[(int) c]);
            }
            System.out.println();
            System.out.println("Huffman Encoding:");
            return encodedMessage.toString();
            
        }
        
        // Decode a Huffman encoding.
        public String decodeMessage(String encodedStr) {
            
            StringBuilder decodedMessage = new StringBuilder();
            HuffmanNode current = huffmanTree.root;

        for (char bit : encodedStr.toCharArray()) {
            if (bit == '0') {
                current = current.left;
            } else if (bit == '1') {
                current = current.right;
            }

            if (current.letter.length() == 1) {
                decodedMessage.append(current.letter);
                current = huffmanTree.root;
            }
        }
        

        System.out.println();
        return decodedMessage.toString();
        }

        // Read an input file.
        public static String readContents(String filename) {
            String temp = "";
            try {
                File file = new File(filename);
                Scanner sc = new Scanner(file);
                while (sc.hasNextLine()) {
                    temp += sc.nextLine();
                    temp += "\n";
                }
                sc.close();
                return temp;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            return "";
        }

        public static void main(String args[]) {
                String input = HuffmanConverter.readContents(args[0]); 
                HuffmanConverter h = new HuffmanConverter(input);
                h.recordFrequencies();
                // Print a list of characters and frequencies here!
                h.frequenciesToTree();
                h.treeToCode();
                // Print the Huffman encoding here!
                String encoded = h.encodeMessage();
                System.out.println(encoded+"\n");
                System.out.println("Message size in ASCII encoding: "+h.contents.length()*8);
                System.out.println("Message size in Huffman coding: "+encoded.length()+"\n");
                System.out.println(h.decodeMessage(encoded));
        }

}
