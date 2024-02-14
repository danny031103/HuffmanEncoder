import java.io.File;
import java.io.IOException;
import java.util.Scanner;

//RUN ON COMMAND LINE COMMANDS
//javac -Xlint:unchecked HuffmanConverter.java
//java HuffmanConverter ../lib/love_poem_58.txt

public class HuffmanConverter{
	
	public static final int NUMBER_OF_CHARACTERS = 256;
	private String contents;
	private HuffmanTree huffmanTree;
	private int count[];
	private String code[];
	private int uniqueChars = 0;
	
	public HuffmanConverter(String input) {
		this.contents=input;
		this.count=new int[NUMBER_OF_CHARACTERS];
		this.code=new String[NUMBER_OF_CHARACTERS];
	}
	
	public void recordFrequencies() {
		for (int i=0;i<this.contents.length();i++){
			char character=this.contents.charAt(i);
			int c=character;
			count[c]+=1;
		}
	}
	
	public void frequenciesToTree() {
		for (int i=0;i<this.count.length;i++) {
			if(this.count[i]!=0){
				this.uniqueChars+=1;
			}
		}
		HuffmanNode[] huffman=new HuffmanNode[uniqueChars];
		int index=0;
		for (int i=0;i<this.count.length;i++) {
			if (this.count[i]!=0) {
				char charAtIndex=(char)i;
				String str=Character.toString(charAtIndex);
				huffman[index]=new HuffmanNode(str, (double)count[i]);
				index++;
			}
		}
		BinaryHeap heap=new BinaryHeap(huffman);
		heap.printHeap();
		this.huffmanTree = HuffmanTree.createfromHeap(heap);
	}
	
	public void treeToCode() {
		for (int i=0;i<code.length;i++){
			code[i]="";
		}
		treeToCode(huffmanTree.root, "");
	}
	
	public void treeToCode(HuffmanNode t, String s) {
		String letter=(String) t.letter;
		int length=letter.length();
		if (length>1){
			treeToCode(t.leftlet, s+"1");
			treeToCode(t.rightlet, s+"0");
		}
		if (length==1){
			char c=t.letter.charAt(0);
			code[(int)c]=s;        
            if (t.letter.equals("\n")) {
                System.out.println("'\\n'" + "=" + s);
            } 
            
            else{
                System.out.println("'"+t.letter+"'"+"="+s);
            }
		}
	}
	
	public String encodeMessage() {
		String message="";
		for (int i=0;i<this.contents.length();i++){
			char character=this.contents.charAt(i);
			int c=character;
			message+=code[c];
		}
		return message;
	}
	
    // Read an input file.
	public static String readContents(String filename) throws IOException {
		File File=new File(filename);
        Scanner input=new Scanner(File);
        String fileContent=input.useDelimiter("\\Z").next();
        return fileContent;
	}
	
	public String decodeMessage(String encodedStr) {
		String decode="";
		String pl="";
		while(!encodedStr.isEmpty()) {
			pl+=encodedStr.charAt(0);
			for (int i=0;i<this.code.length;i++) {
				String huffStr=code[i];
				if (huffStr.equals(pl)) {
					pl="";
					String c=(char) i+"";
					decode+=c;
                    break;
				}
			}
			encodedStr=encodedStr.substring(1);
		}
		return decode;
	}
	public static void main(String args[]) throws IOException {
        String input = HuffmanConverter.readContents(args[0]);
        HuffmanConverter h = new HuffmanConverter(input);
		System.out.println();
		h.recordFrequencies();
		h.frequenciesToTree();
		h.treeToCode();
		System.out.println();
		String encoded = h.encodeMessage();
		System.out.println("Huffman Enconding:");
		System.out.println(encoded);
		System.out.println();
		System.out.println("Message size in ASCII encoding: " + (h.contents.length()*8+8));
        System.out.println("Message size in Huffman encoding: "+ (encoded.length()+h.code[(int)'\n'].length()));
        System.out.println();
		String decodedMessage = h.decodeMessage(encoded);
		System.out.println(decodedMessage);
		}
}