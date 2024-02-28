/** 
 * This class represents the HuffmanNode and its corresponding methods
* @author Ananya Mittal (am12523)
*/

public class HuffmanNode implements Comparable<HuffmanNode>{

    public String letter;
    public Double frequency;
    public HuffmanNode left, right;


    // Constructor method creating the HuffmanNode object that has no children with letter and frequency as its two parameters 
    public HuffmanNode(String letter, Double frequency){
        this.letter = letter;
        this.frequency = frequency;
        this.left = null;
        this.right = null;

    }

    // Constructor method creating the HuffmanNode object with left and right children
    public HuffmanNode(HuffmanNode left, HuffmanNode right){
        this.left = left;
        this.right = right;
        this.letter = left.letter + right.letter; //getter?
        this.frequency = left.frequency + right.frequency; //getter?
    }

    /** 
    * compares the frequency of two HuffmanNodes
    * @param huff an input HuffmanNode
    * @return an integer telling us whether the frequency of this is higher than that of huff or viceversa
    */
    public int compareTo(HuffmanNode huff){
        return this.frequency.compareTo(huff.frequency);
    }


     /** 
    * returns a string representation of the HuffmanNode
    * @return the string representation of the HuffmanNode
    */
    public String toString(){
        return "<" + letter + ", " + frequency + ">";

    }
    
}
