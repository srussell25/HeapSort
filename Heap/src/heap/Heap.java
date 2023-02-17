package heap;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Heap{
    public static void main(String[] args)throws FileNotFoundException{
       HeapHelper myHeap = new HeapHelper();
        try(
             Scanner inFile = new Scanner(new FileReader( "inputFile.txt"))){
                 int OpCount = inFile.nextInt();
                 for(int i = 0; i < OpCount; i++){
                     if(inFile.hasNext("(IN)")){
                         inFile.next();
                         myHeap.HeapInsert(inFile.nextInt());
                     }
                     else if(inFile.hasNext("(EM)")){
                         inFile.next();
                         myHeap.extractMin();
                     }
                     else if(inFile.hasNext("(DK)")){
                         inFile.next();
                         myHeap.decreaseKey(inFile.nextInt(),inFile.nextInt());
                     }
                 }
             }
             System.out.println(myHeap.extractLast());
    }
}
 class HeapHelper {
        private int arraySize = 1000;
         private int [] A;
        private int heapSize;
        
        public HeapHelper(){
       A = new int[arraySize];
       heapSize = 0;
        }
        public String StringHeap(){
            String HeapString = "[";
            for(int i = 0; i < heapSize; i++){
                if(i == heapSize-1)
                    HeapString += A[i]+"]";
                else
                    HeapString += A[i]+", ";
                            }
            return HeapString;
        }
        public void floatUp(int index){
            //The function to run the float up method
            int parentIndex = (index-1)/3;
            int currentIndex = index;
            
            while(currentIndex > 0 && A[parentIndex] > A[currentIndex]){
                swap(currentIndex,parentIndex);
                currentIndex = parentIndex;
                parentIndex = parentIndex/3;
            }
        }
        public void sinkDown(int index){
            int smallest = index;
            int leftChild = 3*index+1;
            int midChild = 3*index+2;
            int rightChild = 3*index+3;
            
            if(leftChild < heapSize-1 && A[smallest] > A[leftChild]){
                smallest = leftChild;
            }
            if(midChild < heapSize-1 && A[smallest] > A[midChild]){
                smallest = midChild;
            }
            if(rightChild < heapSize-1 && A[smallest] > A[rightChild]){
                smallest = rightChild;
            }
            if(smallest != index){
                swap(index,smallest);
                sinkDown(smallest);
            }
        }
        public void swap(int a, int b){
            int temp = A[a];
            A[a]= A[b];
            A[b]= temp;
        }
        public void HeapInsert(int element){
            int tp = heapSize;
                    heapSize++;
             A[tp]= element;
            floatUp(tp);
        }
        public void decreaseKey(int index, int newElements){
          if(A[index] > newElements){
              A[index] = newElements;
              floatUp(index);
          }
        }
        public int extractMin(){
            int min = A[0];
            A[0] = A[heapSize-1];
            A[heapSize-1] = 0;
            sinkDown(0);
            heapSize --;
            A[heapSize] = min;
            return min;
        }
        public int extractLast(){
            return A[heapSize];
        }
        public String printHeap(){
            String HeapString = "[";
            for(int i = 0; i < heapSize; i++){
                if(i== heapSize-1)
                    HeapString += A[i]+ "]";
                else
                    HeapString += A[i]+", ";
            }
            return HeapString;
        }
    }
 
