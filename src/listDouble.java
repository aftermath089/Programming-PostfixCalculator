/**
 * Created by MY_COMPUTER on 09/04/2017.
 */
class node2{
    public double data;
    public node2 next;

    public node2(double data){
        this.data=data;
        next=null;
    }

    public void displayLink(){
        System.out.print("{"+data+"}");
    }
}

public class listDouble {
    private node2 first;
    private int size;

    public int getSize(){
        return size;
    }

    public listDouble(){
        first=null;
        size=0;
    }

    public node2 getFirst(){
        return first;
    }

    public boolean isEmpty(){
        return(first==null);
    }

    public void firstIn(double data){
        node2 newnode=new node2(data);
        newnode.next=first;
        first=newnode;
        size++;
    }

    public void lastIn(double data){
        if(first==null){
            firstIn(data);
        }else{
            node2 temp=first;
            while(temp.next!=null){
                temp=temp.next;
            }
            temp.next=new node2(data);
            size++;
        }
    }

    public double peekFirst(){
        return first.data;
    }

    public double firstOut(){
        node2 temp=first;
        double angka=temp.data;

        first=first.next;

        size--;
        return angka;
    }

    public double lastOut(){
        node2 temp=first;
        while(temp.next.next!=null){
            temp=temp.next;
        }
        node2 output=temp.next;
        double dabel=temp.next.data;
        temp.next=null;
        size--;
        return dabel;
    }

    public String toString(){
        String ret = "";
        if (!isEmpty()) {
            node2 temp = first;
            while (temp.next != null) {
                ret += temp.data;
                temp = temp.next;
            }
            ret += temp.data;
        }
        ret += '\n';
        return ret;
    }
}
