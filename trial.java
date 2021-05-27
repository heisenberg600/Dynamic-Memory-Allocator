public class trial {
    public static void main(String[] args) {
    AVLTree temp = new AVLTree();
    int i=10;
    while (i>=5){
        temp.Insert(i, 2*i, i);
        System.out.println(temp.sanity());

        System.out.println(temp.getFirst().key);
        i--;
    }
    AVLTree temp3=temp.getFirst();
    while (temp3!=null){
        temp.Delete(temp3);
        temp3=temp.getFirst();
        for(AVLTree curr = temp.getFirst(); curr != null; curr = curr.getNext()){
            System.out.print(" Height: "+curr.height+" Key: "+curr.key);
            if (curr.getNext()==null){
                System.out.println(' ');
            }
        }           
    }
    // temp.Insert(0,0,10);
    // temp.Insert(0, 0, 9);
    // temp.Insert(0, 0, 8);
    // AVLTree temp2=temp.getFirst();
    // temp.Delete(temp2);
    // for(AVLTree curr = temp.getFirst(); curr != null; curr = curr.getNext()){
    //     System.out.print(" Height: "+curr.height+" Key: "+curr.key);
    //     if (curr.getNext()==null){
    //         System.out.println(' ');
    //     }

    // }
}
}