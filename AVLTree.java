// Class: Height balanced AVL Tree
// Binary Search Tree

public class AVLTree extends BSTree {
    
    private AVLTree left, right;     // Children. 
    private AVLTree parent;          // Parent pointer. 
    private int height;  // The height of the subtree
        
    public AVLTree() { 
        super();
        // This acts as a sentinel root node
        // How to identify a sentinel node: A node with parent == null is SENTINEL NODE
        // The actual tree starts from one of the child of the sentinel node !.
        // CONVENTION: Assume right child of the sentinel node holds the actual root! and left child will always be null.
        
    }
    public AVLTree(int address, int size, int key) { 
        super(address, size, key);
        this.height = 0;
    }

    // Implement the following functions for AVL Trees.
    // You need not implement all the functions. 
    // Some of the functions may be directly inherited from the BSTree class and nothing needs to be done for those.
    // Remove the functions, to not override the inherited functions.
    private int compareWith(Dictionary node) {//non null input required**checked**
    	if(this.key==node.key&&this.size==node.size&&this.address==node.address) {//same dictionary
    		return 0;
    	}else if(this.key<node.key) {//first node less than second return -1
    		return -1;
    	}else if(this.key>node.key) {//second node greater than first node return 1
    		return 1;
    	}else{//key equal but address not equal or null node
    		if(this.address<node.address) {//first node less than second by address
        		return -2;
        	}else{//second node greater than first node by address,equal case will never happen
        		return 2;
        	}
    	}
    }
    private AVLTree leftRotate(AVLTree current) {
    	AVLTree temp= current.right;
    	current.right=temp.left;
    	current.right.parent=current;
    	temp.left=current;
    	current.parent=temp;
    	return temp;
    }
    private AVLTree rightRotate(AVLTree current) {
    	AVLTree temp= current.left;
    	current.left=temp.right;
    	current.left.parent=current;
    	temp.right=current;
    	current.parent=temp;
    	return temp;
    }
    private AVLTree rightLeftRotate(AVLTree current) {
    	current.right=rightRotate(current.right);
    	rightRotate(current.right).parent=current;
    	return leftRotate(current);
    }
    private AVLTree leftRightRotate(AVLTree current) {
    	current.left=leftRotate(current.left);
    	leftRotate(current.left).parent=current;
    	return rightRotate(current);
    }
    private void updateHeight(AVLTree node) {//set new height of all the ancestors of the node
    	if(node==null||node.parent==null||(node.left==null&&node.right==null)) {//at sentinel node
    		return;
    	}else {
    		if(node.compareHeight()==-1){
    			node.height=node.left.height;
    		}else if(node.compareHeight()==1||node.compareHeight()==0) {
    			node.height=node.right.height;
    		}
    	}
    	updateHeight(node.parent);
		return;
    }
    private int compareHeight() {//1 for right bigger 0 for equal -1 for left bigger 
    	if(this.left==null && this.right==null) {
    		return 0;
    	}else if(this.left==null) {
    		return 1;
    	}else if(this.right==null) {
    		return -1;
    	}else if(this.left.height==this.right.height){
    		return 0;
    	}else if(this.left.height<this.right.height) {
    		return 1;
    	}else if(this.left.height>this.right.height) {
    		return -1;
    	}
		return 0;
    }
    private void fixAVL(AVLTree current) {
    	if(current==null||current.parent==null) {return;}
    	else if(current.left==null||current.right==null) {//any of child is null
    		if(current.compareHeight()==-1&&current.left.height!=0)//left child non null
    			rotate(current);
    		else if(current.compareHeight()==1&&current.right.height!=0) {//right child non null
    			rotate(current);
    		}
    		fixAVL(current.parent);
    		updateHeight(current);
    		return;
    	}else if(current.left.height-current.right.height>1||current.left.height-current.right.height<-1){
    		rotate(current);
    	}
    	fixAVL(current.parent);
    	updateHeight(current);
    }
    public void rotate(AVLTree current) {//z=current
    	if(current.compareHeight()==-1) {//y will be the left child of z
    		if(current.left.compareHeight()<=0){//x will be the left child of y
    			current=rightRotate(current);
    		}else {
    			current=leftRightRotate(current);
    		}
    	}else {//y will be the right child of z
    		if(current.right.compareHeight()>=0){//x will be the right child of y
    			current=leftRotate(current);
    		}else {
    			current=rightLeftRotate(current);
    		}
    	}
    }
    public AVLTree Insert(int address, int size, int key) 
    {	AVLTree newNode= new AVLTree(address,size,key);
    	AVLTree sentNode=this.sentinel();
		Insert(newNode,sentNode.right).parent=sentNode;//new subtree to root
		sentNode.right=Insert(newNode,sentNode.right);//root to new subtree
		updateHeight(newNode.parent);
		fixAVL(newNode.parent.parent);//is require to update new height
		return newNode;
    }
    private AVLTree sentinel() 
    {	AVLTree current = this;
		while(current.parent!=null) {
			current=current.parent;
		}
		return current;//return sentinel node
	}
    private AVLTree Insert(AVLTree newnode,AVLTree root) {//recursive insert**checked**
    	if(root==null) {//base case
    		return newnode;//returning the root of subtree
    	}else {
    		if(newnode.compareWith(root)>0) {//go right
    			Insert(newnode,root.right).parent=root;//new subtree to root
    			root.right=Insert(newnode,root.right);//root to new subtree
    		}else if(newnode.compareWith(root)<0) {//go left
    			Insert(newnode,root.left).parent=root;//new subtree to root
    			root.left=Insert(newnode,root.left);//root to new subtree
    		}
    	}return root;
    }
    public boolean Delete(Dictionary e)
    {	if(e==null) {
			return false;
		}
		AVLTree head=this.sentinel().right;//current initially equals to the head node
			return Delete(head,e);
    }
    private boolean Delete(AVLTree root,Dictionary e) {//**checked**
    	if(root==null) {
    		return false;
    	}else {
    		if(root.compareWith(e)<0) {//go to right
    			return Delete(root.right,e);
    		}else if(root.compareWith(e)>0) {//go to left
    			return Delete(root.left,e);
    		}else {//when data matches
    			//case1 when is leaf
    			if(root.left==null&&root.right==null) {//both child node empty
    				if(root.parent.left==root) {//root is left child of his parent
    					root.parent.left=null;
    				}else {//root is right child of his parent
    					root.parent.right=null;
    				}
    				
    				updateHeight(root.parent);//is require to update new height
    				fixAVL(root.parent);//since root is deleted thats why parent node
    				root.parent=null;
    				return true;// deletion done
    			//case2 root has only right child
    			}else if(root.left==null) {
    				if(root.parent.left==root) {//root is left child of his parent
    					root.parent.left=root.right;//parent of node to child of node
    				}else {//root is right child of his parent
    					root.parent.right=root.right;//parent of node to child of node
    				}
    				root.right.parent=root.parent;//child of node to parent
    				root.parent=null;
    				updateHeight(root.right);//is require to update new height
    				fixAVL(root.right);//since root is deleted thats why parent node
    				root.right=null;
    				return true;// deletion done
    			//case3 root has only left child
    			}else if(root.right==null) {//root has only left child
    				if(root.parent.left==root) {//root is left child of his parent
    					root.parent.left=root.left;//parent of node to child of node
    				}else {//root is right child of his parent
    					root.parent.right=root.left;//parent of node to child of node
    				}
    				root.left.parent=root.parent;//child of node to parent
    				root.parent=null;
    				updateHeight(root.left);//is require to update new height
    				fixAVL(root.left);//since root is deleted thats why parent node
    				root.left=null;
    				return true;// deletion done
    			//case root has both the silds
    			}else {//swap with the successor and delete node
    				AVLTree successor=root.getNext();
    				Dictionary temp=root;
    				root.key=successor.key;
    				root.address=successor.address;
    				root.size=successor.size;
    				successor.key=temp.key;
    				successor.address=temp.address;
    				successor.size=temp.size;
    				return Delete(successor,temp);
    			}
    		}
    	}
    }
    public AVLTree Find(int k, boolean exact)
    {	AVLTree current=this.sentinel().right;//variable that traverse the tree
		AVLTree temp=null;//variable that store the best find element
		if(exact) {
			while(current!=null){
				if(current.key==k) {
					temp=current;
					current=current.left;
				}else if(current.key<k){//goes right for bigger keys
				current=current.right;
				}else {
				current=current.left;
				}//goes left for smaller keys
			}
		}else {//not exact
			while(current!=null){
				if(current.key>=k) {//go left for better search
					temp=current;
					current=current.left;
				}else {//go right
					current=current.right;
				}
			}
		}return temp;
    }
    public AVLTree getFirst()
    {	AVLTree current=this.sentinel().right;
		while(current!=null&&current.left!=null ) {//to check for empty list and last element
			current=current.left;
	}
		return current;
    }
    public AVLTree getNext()
    {	if(this==this.sentinel())
			return null;
		if(this.right!=null){//successor form the right subtree
			return MinTree(this.right);                                                                                                                                 
		}else{//successor will lowest ancestor of this whose left child is also ancestor
			AVLTree y=this.parent;//
			AVLTree x=this;           
			while(y.parent!=null&&x==y.right){//not go to the sentinel node
				x=y;y=y.parent;
			}
			if(y==this.sentinel()) {
				return null;
			}else{
				return y;
			}	
		}
    }
    public boolean sanity()
    {//check the value of sentinel node
    	AVLTree sentNode=this.sentinel();
    	if(sentNode.address!=-1||sentNode.size!=-1||sentNode.key!=-1) {
    		return false;
    	}
    //check for the returning pointers
    	AVLTree current=sentNode.getFirst();
    	while(current!=null) {//traversing list
        	if(current.left!=null&&current.left.parent!=current) {//current has left child but left child has different parent
        		return false;
        	}if(current.right!=null&&current.right.parent!=current) {//current has left child but left child has different parent
        		return false;
        	}if(current.parent.left!=current&&current.parent.right!=current) {//current has parent(not sentinel) but parent not have current as child
        		return false;
        	}
        	current=current.getNext();//shifting to the next
        }
    //check for the search property
    	return checkBST(sentNode.right)&&checkAVL(sentNode.right);//
    }
    private AVLTree MinTree(AVLTree root){//returns minimum node of subtree rooted at root**checked**
        if(root==null){
        	AVLTree maxNode=new AVLTree(Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE);//will have least priority
            return maxNode;
        }
        AVLTree current = root;
        if (MinTree(root.left).compareWith(current) < 0){ 
            current = MinTree(root.left); 
        }else if (MinTree(root.right).compareWith(current) < 0){ 
            current = MinTree(root.right);
        }
        return current;
    }
    private AVLTree MaxTree(AVLTree root){//returns maximum node of subtree rooted at root**checked**
        if(root==null){
        	AVLTree minNode=new AVLTree(Integer.MIN_VALUE,Integer.MIN_VALUE,Integer.MIN_VALUE);//will have least priority
            return minNode;
        }
        AVLTree current = root;
        if (MaxTree(root.left).compareWith(current) > 0){ 
            current = MaxTree(root.left); 
        }else if (MaxTree(root.right).compareWith(current) > 0){ 
            current = MaxTree(root.right);
        }
        return current;
    }
    private boolean checkBST(AVLTree root){//**checked**
        if(root==null) {
            return true;
        }
        if(root.left!=null){
            if(MaxTree(root.left).compareWith(root)>=0){
                 return false;
            }
        }
        if(root.right!=null){
            if(MinTree(root.right).compareWith(root)<=0){
                return false;
            }
        }
        return checkBST(root.left) && checkBST(root.right);
    }
    private boolean checkAVL(AVLTree root) {
    	if(root==null) {
    		return true;
    	}else if(root.left==null&&root.right==null) {//no child
    		return root.height==0;
    	}else if(root.left==null) {//only right child 
    		return (root.height==1&&checkAVL(root.right));
    	}else if(root.right==null) {//only left child 
    		return (root.height==1&&checkAVL(root.left));
    	}else {
    		if((root.left.height-root.right.height>1) ||(root.left.height-root.right.height<-1)) {
    			return false;
    		}else {
    			return checkAVL(root.right)&&checkAVL(root.left)&&(root.height>=0);
    		}
    	}
    }
}
	

