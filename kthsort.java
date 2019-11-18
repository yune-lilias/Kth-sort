
import java.util.Scanner;

public class kthsort {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // Read in k, which represents the maximum
        // distance between a number's current position
        // and sorted position
        int k = Integer.parseInt(sc.nextLine());
        
        // Read in the list of numbers
        int[] numbers;
        String input = sc.nextLine();
        if (input.equals("")) {
            numbers = new int[0];
        } else {    
            String[] numberStrings = input.split(" ");
            numbers = new int[numberStrings.length];
            for (int i = 0; i < numberStrings.length; i++) {
                numbers[i] = Integer.parseInt(numberStrings[i]);
            }
        }

        // Sort the list
        sort(numbers, k);

        // Print the sorted list
        StringBuilder resultSb = new StringBuilder();
        for (int i = 0; i < numbers.length; i++) {
            resultSb.append(new Integer(numbers[i]).toString());
            if (i < numbers.length - 1) {
                resultSb.append(" ");
            }
        }
        System.out.println(resultSb.toString());
    }
    
    public static void sort(int[] numbers, int k) {
        myheap data = new myheap(k+1);
        for(int i=0;i<numbers.length;i++){
          data.insert(numbers[i]);
          if(data.isfull()){
           numbers[i-k] = data.extractMin();
          }
        }
        for(int t=data.size-1;t>=0;t--)
            numbers[numbers.length-t-1] = data.extractMin();
     }
     
    
}

class myarraylist{
  private int[] elements;
  private int size = 0;
  public int size(){
    return size;
  }
  
  public myarraylist(){
    this(10);
  }
  public myarraylist(int initsize){
    elements = new int[initsize];
  }
  
  public void add(int o1){
    elements[size++] = o1;
    if(size==elements.length){
      int[] newa = new int[2*size+1];
      System.arraycopy(elements,0,newa,0,elements.length);
      elements = newa;
    }
  }
  
  public int get(int index){
    return elements[index];
  }
  
  public void remove(int index){
    System.arraycopy(elements,index+1,elements,index,size-index-1);
    size--;
  }
  
  public void set(int index,int ob){
    elements[index] = ob;
  }
}

class myheap{
  myarraylist heap;
  public int maxsize;
  public int size = 0;
  
  public myheap(int maxsize){
    this.maxsize = maxsize;
    heap =new myarraylist(maxsize);
  }
  
  private void swap(int a, int b){
    int temp = heap.get(a);
    int temp2 = heap.get(b);
    heap.set(a,temp2);
    heap.set(b,temp);
  }
  
 /* public void remove(int index){
    heap.remove(index);
  }*/
  
  private int getParent(int index){ 
    return (index - 1) / 2; 
  }
  
  private int getLeftChild(int index){ 
    return index * 2 + 1; 
  } 

  private int getRightChild(int index){ 
    return index * 2 + 2;  
  } 

  public void insert(int ob){
    heap.add(ob);
    int currentindex = heap.size()-1;
    while(heap.get(currentindex)<heap.get(getParent(currentindex))){
      swap(currentindex,getParent(currentindex));
      currentindex = getParent(currentindex);
    }
    size++;
  }
  
   public boolean isfull(){
      return size == maxsize;  
   }
   
   public int extractMin(){
    int min = heap.get(0);
    swap(0, heap.size() - 1);
    heap.remove(heap.size() - 1);
    minHeapify(0);
    size--;
    return min;
  }
  
   void minHeapify(int index) {
    // If it's a leaf - return
    if (getLeftChild(index) >= heap.size()) {
      return;
    } 
    // If there's only a left child
    if (getRightChild(index) >= heap.size()) {
      if (heap.get(index) > heap.get(getLeftChild(index))) {
        swap(index, getLeftChild(index));
        return;
      }
    }
    else if (heap.get(index) > heap.get(getLeftChild(index)) ||
      heap.get(index) > heap.get(getRightChild(index))
    ) {
      if (heap.get(getLeftChild(index)) < heap.get(getRightChild(index))) {
        swap(index, getLeftChild(index));
        minHeapify(getLeftChild(index));
      } else {
        swap(index, getRightChild(index));
        minHeapify(getRightChild(index));
      }
    }
  }
}