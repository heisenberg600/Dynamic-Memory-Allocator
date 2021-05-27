// Implements Dictionary using Doubly Linked List (DLL)
// Implement the following functions using the specifications provided in the class List

public class A1List extends List {

    private A1List  next; // Next Node
    private A1List prev;  // Previous Node 

    public A1List(int address, int size, int key) { 
        super(address, size, key);
    }
    
    public A1List(){
        super(-1,-1,-1);
        // This acts as a head Sentinel

        A1List tailSentinel = new A1List(-1,-1,-1); // Intiate the tail sentinel
        
        this.next = tailSentinel;
        tailSentinel.prev = this;
    }


    public A1List Insert(int address, int size, int key)
    {
        if (this.next==null){
            return null;
        }
        A1List newNode = new A1List(address,size,key);
        newNode.next=this.next;
        this.next=newNode;
        newNode.prev=this;
        if (newNode.next!=null){
            newNode.next.prev=newNode;
        }
        return newNode;
    }

    public boolean Delete(Dictionary d) 
    {    
        if (d.size==-1 && d.address==-1 && d.key==-1){
            return false;
        }        
        A1List temp=this;
        while (temp!=null){
            if (temp.key==d.key && temp.size==d.size && temp.address==d.address ){
                temp.prev.next=temp.next;
                temp.next.prev=temp.prev;
                return true;   
            }
            temp=temp.next;
        }
        temp=this;
        while (temp!=null){
            if (temp.key==d.key && temp.size==d.size && temp.address==d.address ){
                temp.prev.next=temp.next;
                temp.next.prev=temp.prev;
                return true;
            }
            temp=temp.prev;
        }
        return false;
    }

    public A1List Find(int k, boolean exact)
    { 
        A1List temp= this.getFirst();
        if (exact){
            while (temp!=null){
                if (temp.key==k){
                    return temp;
                }
                temp=temp.next;
            }
            return null;
        }
        else {
            while (temp!=null){
                if (temp.key>=k){
                    return temp;
                }
                temp=temp.next;
            }
            return null;
        }
    }

    public A1List getFirst()
    {
       A1List temp = this ;
       if (temp.prev==null){
           if (temp.next.next!=null){
               return temp.next;
           }
           else {
               return null;
           }
       }
       while (temp.prev.prev!=null) {
           temp=temp.prev;
       }
       return temp; 
    }
    
    public A1List getNext() 
    {
        if (this.next.next==null){
            return null;
        }
        else {
            return this.next;
        }
    }

    public boolean sanity()
    {   
        A1List tail=this;
        A1List head=this;
        //checks the circularilty in clockwise direction and gives us tail
        while (tail.next!=null ){
            tail=tail.next;
            if (tail==this){
                    return false;
                }
            }
        //checks the circularity in counter-clockwise direction and gives us head 
        while (head.prev!=null){
            head=head.prev;
            if (head==this){
                return false;
                }
            }
        //checks that next of tail and prev of head are null
        if (!((tail.key==-1 && tail.address==-1 && tail.size==-1)&&(head.key==-1 && head.address==-1 && head.size==-1))){
            return false;
        }
        A1List temp1 = head;
        //checks whether prev of the next element points to self
        while (temp1.next!=null){
            if (temp1.next.prev!=temp1){
                return false;
            }
            temp1=temp1.next;       
        }
        A1List temp2=tail;
        //check whether next of prev element points to self
        while (temp2.prev!=null){
            if (temp2.prev.next!=temp2){
                return false;
            }
            temp2=temp2.prev;
        }
        return true;
    }
}




