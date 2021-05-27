// Class: Implementation of BST in A2
// Implement the following functions according to the specifications provided in Tree.java

public class BSTree extends Tree {

    private BSTree left, right;     // Children.
    private BSTree parent;          // Parent pointer.
        
    public BSTree(){  
        super();
        // This acts as a sentinel root node
        // How to identify a sentinel node: A node with parent == null is SENTINEL NODE
        // The actual tree starts from one of the child of the sentinel node!.
        // CONVENTION: Assume right child of the sentinel node holds the actual root! and left child will always be null.
    }    

    public BSTree(int address, int size, int key){
        super(address, size, key); 
    }

    public BSTree Insert(int address, int size, int key) 
    { 
        BSTree newNode = new BSTree(address,size,key);//creating a new node
        BSTree temp=this.getHead();//get to the sentinal node 
        //if empty tree insert at the right 
        if (temp.right==null){
            temp.right=newNode;
            newNode.parent=temp;
            return newNode;
        }
        else{
            temp=temp.right;
            BSTree trail=temp;//maintains the previous node visited
            while(temp!=null){
                trail=temp;
                //if key to insert is greater visit right
                if (temp.key<key){
                    temp=temp.right;
                }
                //if key to insert is less visit left
                else if (temp.key>key){
                    temp=temp.left;
                }
                //if key to insert is equal to existing key 
                else{
                    //if address to be inserted is greater visit right
                    if (temp.address<address){
                        temp=temp.right;
                    }
                    //if address to be inserted if smaller visit left
                    else{
                        temp=temp.left;
                    }
                }
            }
            //insert at right key>
            if (trail.key<key){
                trail.right=newNode;
                newNode.parent=trail;
            }
            //insert at left key<
            else if (trail.key>key){
                trail.left=newNode;
                newNode.parent=trail;
            }
            //check for adress key=
            else{
                //insert at right
                if (trail.address<address){
                    trail.right=newNode;
                    newNode.parent=trail;               
                }
                //insert at left
                else{
                    trail.left=newNode;
                    newNode.parent=trail;
                }
            }
        }
        return newNode;
    }

    public boolean Delete(Dictionary e)
    {
        if (e==null){
            return false;
        } 
        BSTree temp=(this.getHead().right);
        while (temp!=null){
            //key to delete is greater so go right subtree
            if (temp.key<e.key){
                temp=temp.right;
            }
            //key to delete is smaller so go left subtree
            else if (temp.key>e.key){
                temp=temp.left;
            }
            //key to delete is equal so check address
            else {
                //address to delete is greater so go right subtree
                if (temp.address<e.address){
                    temp=temp.right;
                }
                //address to delete is smaller so go left subtree
                else if (temp.address>e.address){
                    temp=temp.left;
                }
                //address to delete is equal so check size 
                else {
                    //If size is equal and its not the sentinal node
                    if (temp.size==e.size && temp.parent!=null){
                        break;
                    }
                    return false;
                }
            }
        }
        if (temp!=null){
            BSTree temp1;
            //node to be deleted is a leaf
            if (temp.left==null && temp.right==null){
                //is a right child
                if (temp.parent.right==temp){
                    temp.parent.right=null;
                }
                //is a left child
                else{
                    temp.parent.left=null;
                }
                return true;

            }
            //node to be deleted has 2 childs
            else if (temp.left!=null && temp.right!=null){
                temp1=temp.right;
                while (temp1.left!=null){
                    temp1=temp1.left;
                }
                temp.key=temp1.key;
                temp.address=temp1.address;
                temp.size=temp1.size;
                temp=temp1;
                //if successor has no child 
                if (temp.left==null && temp.right==null){
                    //is a right child
                    if (temp.parent.right==temp){
                        temp.parent.right=null;
                    }
                    //is a left child
                    else{
                        temp.parent.left=null;
                    }
                    return true;                     
                }
                //if successor has one child (can have only one child that is right)
                else{
                    //is a right child
                    if (temp.parent.right==temp){
                        temp.right.parent=temp.parent;
                        temp.parent.right=temp.right;
                    }
                    //is a left child
                    else{
                        temp.right.parent=temp.parent;
                        temp.parent.left=temp.right;
                    
                    return true;
                    }
                }
            }
            //node to be deleted has one child
            else if (temp.left==null || temp.right==null){
                //has a left child
                if (temp.left!=null){
                    if (temp.parent.right==temp){
                        temp.parent.right=temp.left;
                        temp.left.parent=temp.parent;
                    }
                    else{
                        temp.parent.left=temp.left;
                        temp.left.parent=temp.parent;
                    }
                    return true;
                }
                //has a right child
                if (temp.right !=null){
                    if (temp.parent.right==temp){
                        temp.parent.right=temp.right;
                        temp.right.parent=temp.parent;
                    }
                    else{
                        temp.parent.left=temp.right;
                        temp.right.parent=temp.parent;
                    }
                    return true;                    
                }
            }
            return true;
        }
        return false;   
    }


        
    public BSTree Find(int key, boolean exact)
    {   
        BSTree temp=(this.getHead().right);
        BSTree minaddress=null;//auxilary variable to store node with same key and min adresss (best fit)
        if (exact){
            while (temp!=null){
                //key to find is greater go right
                if (key>temp.key){
                    temp=temp.right;
                }
                //key to find is smaller go left
                else if (key<temp.key){
                    temp=temp.left;
                }
                //key to find is equal check address
                else {
                    minaddress=temp;
                    temp=temp.left;
                }
            }   
        }
        else{
            while (temp!=null){
                //key to find is greater go right
                if (key>temp.key){
                    temp=temp.right;
                }
                //key to find is key than or equal 
                else {
                    minaddress=temp;
                    temp=temp.left;
                }
            }       
        }
        return minaddress;         
    }           
    

    private BSTree getHead()//private helper function to get to the head of the BST
    { 
        BSTree temp = this;
        while (temp.parent!=null){
            temp=temp.parent;
        }
        return temp;

    }

    public BSTree getFirst()
    {
        BSTree temp=((this.getHead()).right);
        if (temp==null){
            return null;
        }
        while (temp.left!=null){
            temp=temp.left;
        }
        return temp;
    }


    public BSTree getNext()
    { 
        BSTree temp=this;
        //if called on the sentinal node return null(as provided in the A2 clarification piazza)
        if (temp.parent==null){
            return null;
        }
        //if right subtree isnt null
        if (temp.right!=null){
            temp=temp.right;
            //find the leftmost node in the right subtree
            while (temp.left!=null){
                temp=temp.left;
            }
            return temp;
        }
        //if right subtree is null
        else{
            BSTree parentN=temp.parent;//keeps track of the parent of the current pointer
            while (parentN!=null && temp==parentN.right ){ 
                temp=parentN;
                parentN=parentN.parent;
            }
            return parentN;
        }

    }

    private boolean searchProperty(BSTree head , BSTree l , BSTree r){
        //if null then search property is automatically fulfilled 
        if (head==null){
            return true;
        }
        //right key or adress is smaller then sp is violated
        if (r!=null && (r.key<head.key || (r.key==head.key && r.address<head.address))){
            return false;
        }
        //left key or address is greater then sp is violated 
        if (l!=null &&(l.key>head.key || (l.key==head.key && l.address>head.address))){
            return false;
        }
        //recursivly call on every node 
        return searchProperty(head.left, l, head) && searchProperty(head.right, head, r);
    }

    public boolean sanity()
    { 
        BSTree temps=this;//pointer moves one level at a time
        BSTree tempf=this.parent;//pointer moves two level at a time also stores our head 
        //if we are not already at the root
        if (tempf!=null){
            //traverse till we reach the root
            while (tempf.parent!=null){
                //if circularity detected return false
                if (tempf==temps){
                    return false;
                }
                //make the slow pointer move one up
                temps=temps.parent;
                //ensuring tempf doesnt become null to prevent null pointer exception
                if (tempf.parent.parent==null){
                    tempf=tempf.parent;
                    break;
                }
                //make the fast pointer move two steps a time
                tempf=tempf.parent.parent;
            }
        }
        //if we are already at the root
        else{
            tempf=temps;
        }
        //if there exist a subtree at the left of the sentinal root node (violates convention) 
        if (tempf.left!=null){
            return false;
        }
        //check the search property on the right subtree of the head sentinal
        if (!searchProperty(tempf.right,null,null)){
            return false;
        }
        return true;
    }

}

