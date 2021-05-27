// Class: A1DynamicMem
// Implements DynamicMem
// Does not implement defragment (which is for A2).

public class A1DynamicMem extends DynamicMem {
      
    public A1DynamicMem() {
        super();
    }

    public A1DynamicMem(int size) {
        super(size);
    }

    public A1DynamicMem(int size, int dict_type) {
        super(size, dict_type);
    }

    public void Defragment() {
        return ;
    }

    // In A1, you need to implement the Allocate and Free functions for the class A1DynamicMem
    // Test your memory allocator thoroughly using Doubly Linked lists only (A1List.java).
    // While inserting into the list, only call insert at the head of the list
    // Please note that ALL insertions in the DLL (used either in A1DynamicMem or used independently as the dictionaryclass implementation) are to be made at the HEAD (from the front).
    // Also, the find-first should start searching from the head (irrespective of the use for A1DynamicMem). Similar arguments will follow with regards to the ROOT in the case of trees (specifying this in case it was not so trivial to anyone of you earlier)
    public int Allocate(int blockSize) {
        if (blockSize<=0){
            return -1;
        }
        Dictionary memoryBlock= freeBlk.Find(blockSize, false);
        if (memoryBlock!=null){
            int k1= memoryBlock.address;
            int k2=memoryBlock.size;
            if (memoryBlock.size==blockSize){
                freeBlk.Delete(memoryBlock);
                allocBlk.Insert(k1, blockSize,k1);
            }
            else {
                allocBlk.Insert(k1, blockSize, k1);
                freeBlk.Delete(memoryBlock);
                freeBlk.Insert(k1+blockSize, k2-blockSize,k2-blockSize);               
            }
            return memoryBlock.address;     
        }
        else{

            return -1;
        }

    } 
    
    public int Free(int startAddr) {
        if (startAddr<0){
            return -1;
        }

        Dictionary memoryBlock= allocBlk.Find(startAddr,true);
        if (memoryBlock!=null){
            freeBlk.Insert(startAddr, memoryBlock.size,memoryBlock.size);
            allocBlk.Delete(memoryBlock);
            return 0;
        }
        else {
            return -1;

        }
        
    }
}

