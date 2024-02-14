
public class HuffmanNode implements Comparable<HuffmanNode> {
    public String letter;
    public Double frequency;
    public HuffmanNode leftlet, rightlet;

    public HuffmanNode(String letter, Double frequency) {
        this.letter = letter;
        this.leftlet = null;
        this.rightlet = null;
        this.frequency = frequency;
    }

    // constructor
    public HuffmanNode(HuffmanNode left, HuffmanNode right) {
        this.letter = left.letter + right.letter;
        this.leftlet = left;
        this.rightlet = right;
        this.frequency = left.frequency + right.frequency;
    }

    // comparer
    @Override
    public int compareTo(HuffmanNode o) {
        return this.frequency.compareTo(o.frequency);
    }

    // converter
    @Override
    public String toString() {
        if (letter.equals("\n")) {
            return "<\\n, " + frequency + ">";
        } else {
            return "<" + letter + ", " + frequency + ">";
        }
    }
}

