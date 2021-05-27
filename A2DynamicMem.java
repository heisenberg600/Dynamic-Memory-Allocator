// Class: A2DynamicMem
// Implements Degragment in A2. No other changes should be needed for other functions.

public class A2DynamicMem extends A1DynamicMem {
      
    public A2DynamicMem() {  super(); }

    public A2DynamicMem(int size) { super(size); }

    public A2DynamicMem(int size, int dict_type) { super(size, dict_type); }

    // In A2, you need to test your implementation using BSTrees and AVLTrees. 
    // No changes should be required in the A1DynamicMem functions. 
    // They should work seamlessly with the newly supplied implementation of BSTrees and AVLTrees
    // For A2, implement the Defragment function for the class A2DynamicMem and test using BSTrees and AVLTrees. 
    //Your BST (and AVL tree) implementations should obey the property that keys in the 
    //left subtree <= root.key < keys in the right subtree. 
    //How is this total order between blocks defined? It shouldn't be a problem when using key=address since those are unique 
    //(this is an important invariant for the entire assignment123 module).
    //When using key=size, use address to break ties i.e. if there are multiple blocks of the same size, 
    //order them by address. Now think outside the scope of the allocation problem and think of handling tiebreaking in blocks, 
    //in case key is neither of the two. 
    public void Defragment() {//check for the tree**checked**
    	BSTree defragTree=new BSTree();
        Dictionary current=this.freeBlk.getFirst();
        if(current==null) {//freeBLK is empty
        	return;
        }
        while(current!=null) {
        	defragTree.Insert(current.address,current.size,current.address);//making address as key
        	current=current.getNext();//shifting to the next
        }//defragTree has all the nodes now
        current=defragTree.getFirst();
        while(current.getNext()!=null) {//last element or single element
        	if((current.key+current.size)==current.getNext().key) {//contagious blocks
        		this.freeBlk.Delete(current);//delete current block from free memory
        		this.freeBlk.Delete(current.getNext());//delete next block to current from free memory
        		current.size=current.size+current.getNext().size;//change in the block of new tree
        		defragTree.Delete(current.getNext());//delete from the new tree
        		this.freeBlk.Insert(current.address,current.size,current.size);//insert newnode in the free block key==size
        		current=defragTree.getFirst();
        	}else {
        		current=current.getNext();
        	}
        }
        return;
    }	
}