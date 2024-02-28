/** 
 * This class represents the HuffmanTree and its corresponding methods
* @author Ananya Mittal (am12523)
*/

public class HuffmanTree {

    HuffmanNode root;

    // Constructor method creating the HuffmanTree object whose root is a HuffmanNode
    public HuffmanTree(HuffmanNode huff){
        this.root = huff;
    }

    //Initiates the printing of the legend by calling on the private recursive method printLegend.
    public void printLegend(){
        printLegend(root, "");
    }

    /** 
    * Traverses the HuffmanTree and prints the legend
    * @param node The node whose letter is appended to the code
    * @param code the string which represents the legend
    */
    private void printLegend(HuffmanNode node, String code){
        if (node != null) {
            if (node.letter.length() > 1) {
                printLegend(node.left, code + "0");
                printLegend(node.right, code + "1");
            }
            else{
                if (node.letter.charAt(0) == '\n') {System.out.println("'"+"\\n"+"'"+"="+code);}
                else{System.out.println("'"+node.letter+"'"+"="+code);}
            }
        }
    }

    /** 
    * Creates a heap of HuffmanNodes from the legend
    * @param legend The string which is converted to a binary heap
    * @return a heap of HuffmanNodes containing each letter and its frequency in the legend
    */
    public static BinaryHeap<HuffmanNode> legendToHeap(String legend) {
        BinaryHeap<HuffmanNode> huffmanTree = new BinaryHeap<HuffmanNode>();
        String[] legend_list = legend.split("");

        for (int i = 0; i < legend_list.length; i+=2){
            String letter = legend_list[i];
            double frequency = Double.parseDouble(legend_list[i+1]);
            HuffmanNode node = new HuffmanNode(letter, frequency);
            huffmanTree.insert(node);
        }
        
        return huffmanTree;
    }

    /** 
    * Creates a HuffmanTree from a heap
    * @param heap the binary heap made from the legend
    * @return a HuffmanTree with a root made from the single node left in the heap after applying the algorithm
    */
    public static HuffmanTree createFromHeap(BinaryHeap<HuffmanNode> heap){
        
        while (heap.getSize()>1){
            HuffmanNode n1 = heap.deleteMin();
            HuffmanNode n2 = heap.deleteMin();

            HuffmanNode merge = new HuffmanNode(n1, n2);

            heap.insert(merge);
        }
        HuffmanNode root = heap.deleteMin();
        HuffmanTree encoded = new HuffmanTree(root);

        return encoded;
    }
    
}
