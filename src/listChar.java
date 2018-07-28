/**
 * Created by MY_COMPUTER on 09/04/2017.
 */
class node{
    public char data;
    public node next;

    public node(char data){
        this.data=data;
        next=null;
    }

    public void displayLink(){
        System.out.print("{"+data+"}");
    }
}

public class listChar {
    private node first;
    private int size;

    public int getSize(){
        return size;
    }

    public listChar(){
        first=null;
        size=0;
    }

    public node getFirst(){
        return first;
    }

    public boolean isEmpty(){
        return(first==null);
    }

    public void firstIn(char data){
        node newnode=new node(data);
        newnode.next=first;
        first=newnode;
        size++;
    }

    public void lastIn(char data){
        if(first==null){
            firstIn(data);
        }else{
            node temp=first;
            while(temp.next!=null){
                temp=temp.next;
            }
            temp.next=new node(data);
            size++;
        }
    }

    public char peekFirst(){
        return first.data;
    }

    public char firstOut(){
        node temp=first;
        char karakter=temp.data;
        first = first.next;
        size--;
        return karakter;
    }

    public char lastOut(){
        node temp=first;
        while(temp.next.next!=null){
            temp=temp.next;
        }
        node output=temp.next;
        char karakter2=temp.next.data;
        temp.next=null;
        size--;
        return karakter2;
    }

    public String toString(){
        String ret = "";
        if (!isEmpty()) {
            node temp = first;
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
