public class HuffmanTree{
    //mainn
    public static void main(String[] args){
        String legend="A 20 E 24 G 3 H 4 I 17 L 6 N 5 O 10 S 8 V 1 W 2";
        BinaryHeap<HuffmanNode> heap=toHeap(legend);
        heap.printHeap();
        HuffmanTree huffmanTree=createfromHeap(heap);
        huffmanTree.printLegend();
    }

    HuffmanNode root;
    public HuffmanTree(HuffmanNode huff){
        this.root=huff;
    }

    //printer
    private void printLegend(HuffmanNode node, String code){
        if(node.letter.length()>1){
            printLegend(node.leftlet,code+"0");
            printLegend(node.rightlet,code+"1");
        } 
        else{
            System.out.println(node.letter+"="+code);
        }
    }

    //printer
    public void printLegend(){
        printLegend(root,"");
    }

    //converter
    public static BinaryHeap<HuffmanNode>toHeap(String legend){
        BinaryHeap<HuffmanNode> heap=new BinaryHeap<>();
        String[] splitpart=legend.split(" ");
        for (int i=0;i<splitpart.length;i+=2){
            String letter=splitpart[i];
            Double frequency=Double.parseDouble(splitpart[i+1]);
            HuffmanNode node=new HuffmanNode(letter,frequency);
            heap.insert(node);
        }
        return heap;
    }

    //constructor
    public static HuffmanTree createfromHeap(BinaryHeap<HuffmanNode> heap){
        while(heap.getSize()>1){
            HuffmanNode left=heap.deleteMin();
            HuffmanNode right=heap.deleteMin();
            HuffmanNode newNode=new HuffmanNode(left,right);
            heap.insert(newNode);
        }
        HuffmanNode root=heap.deleteMin();
        return new HuffmanTree(root);
    }
}