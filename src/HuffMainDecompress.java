
import java.io.File;
import java.io.*;

public class HuffMainDecompress {
	private static final int BITS_PER_INT = 8;
	private static final int HUFF_TREE = 0;
	//private static final int PSEUDO_EOF = ?;

	public static void main(String[] args) {
		
		System.out.println("Huffman Decompress Main");
		
		File inf = FileSelector.selectFile();
		File outf = FileSelector.saveFile();
		if (inf == null || outf == null) {
			System.err.println("input or output file cancelled");
			return;
		}
		BitInputStream bis = new BitInputStream(inf);
		BitOutputStream bos = new BitOutputStream(outf);
		HuffProcessor hp = new HuffProcessor();
		hp.decompress(bis, bos);
		System.out.printf("uncompress from %s to %s\n", 
				           inf.getName(),outf.getName());		
		
		System.out.printf("file: %d bits to %d bits\n",inf.length()*8,outf.length()*8);
		System.out.printf("read %d bits, wrote %d bits\n", 
				           bis.bitsRead(),bos.bitsWritten());
		long diff = 8*(outf.length() - inf.length());
		long diff2 = bos.bitsWritten() - bis.bitsRead();
		System.out.printf("%d compared to %d\n",diff,diff2);
	}
	
// BELOW THIS POINT EDITED BY jnh24
//	public void decompress(BitInputStream in, BitOutputStream out) {
//		
//		int bits = in.readBits(BITS_PER_INT);
//		if(bits != HUFF_TREE) {
//			throw new HuffException("illegal header starts with" + bits);
//		}
//		
//		HuffNode root = readTreeHeader(in);
//		readCompressedBits(root,in,out);
//		out.close();
//	}
//
//	private void readCompressedBits(HuffNode root, BitInputStream in, BitOutputStream out) {
//			
//			int bits;
//			
//			BitOutputStream output;
//			HuffNode current = root;   // root of tree, constructed from header data
//
//	       while (true) {
//
//	           bits = in.readBits(1);
//	           if (bits == -1) {
//	               throw new HuffException("bad input, no PSEUDO_EOF");
//	           }
//	           else { 
//
//	               // use the zero/one value of the bit read
//	               // to traverse Huffman coding tree
//	               // if a leaf is reached, decode the character and print UNLESS
//	               // the character is pseudo-EOF, then decompression done
//
//	               if (bits == 0) current = current.myLeft; // read a 0, go left
//	               else current =  current.myRight;                             // read a 1, go right
//
//	               if (current.myLeft == null && current.myRight == null) { // at leaf!
//	                   if (current.myValue == PSEUDO_EOF) 
//	                       break;   // out of loop
//	                   else {
//	                       
//						output.writeBits(8,current.myValue);
//	                    current = root; // start back after leaf
//	                   }
//	               }
//	           }
//	       }
//	}
//
//	private HuffNode readTreeHeader(BitInputStream in) {
//		
//		int bit = in.readBits(1);
//		
//		if (bit == -1) throw new HuffException("bad input");
//		if (bit == 0) {
//		    HuffNode left = readTreeHeader(in);
//		    HuffNode right = readTreeHeader(in);
//		    return new HuffNode(0,0,left,right);
//		}
//		else {
//		    int value = in.readBits(9);
//		    return new HuffNode(value,0,null,null);
//		}
//	
//}
}
