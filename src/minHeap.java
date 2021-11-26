public class minHeap {

    public SLL[] A;
    public int heap_size;

    public minHeap(SLL[] adjList) {
        this.A = A;
        A = new SLL[adjList.length+1];
        for (int i = 1; i < A.length; i++){
            A[i] = adjList[i-1];
        }
        this.heap_size = A.length - 1; // 0 doesnt count: 1-based indexing on heap
    }
    public void heapsort(){
        Build_Min_Heap();
        for(int i = A.length-1; i > 1; i--){
            swap(1, i);
            heap_size--;
            Min_Heapify(1);
        }
    }
    public SLL Extract_Min(){
        if (heap_size < 1) {
            System.out.println("Error: cannot call extract min when heap size is 0");
        }
        SLL smallest = A[1];
        swap(1,heap_size); // swap lowest right-most non-leaf node
        heap_size--;
        Min_Heapify(1);
        return smallest;
    }
    public void Build_Min_Heap(){
        heap_size = A.length-1;
        for(int i = (int)Math.floor((A.length-1)/2); i > 0; i--){
            Min_Heapify(i); //goes from lowest, rightmost NON-LEAF node, all the way up to root
            //we start from non-leaf nodes because min_heapify requires that both children be min heaps
        }
    }
    public void Min_Heapify(int i){


        if (i == 0) {return;} // we use 1 based indexing for heap, so index 0 is not used

        else if (i >= A.length) throw new IllegalArgumentException("i cannot be greater than array size");

        if (isLeafNode(i)){ return; }

        SLL smallestPathSumVertex;

        SLL leftChild = getLeftChild(i);
        SLL rightChild = getRightChild(i);

        if (rightChild != null && A[i].shortestPathSum > rightChild.shortestPathSum){
            smallestPathSumVertex = rightChild; // if right child is smaller, set it as smallestNode
        } else {
            smallestPathSumVertex = A[i];
        }
        if (leftChild != null && A[i].shortestPathSum > leftChild.shortestPathSum){
            smallestPathSumVertex = leftChild; // if left child is smallest, move smallestNode pointer to left child
        }

        // if smallest node == i, entire subtree is a min heap. else, we need to repair the tree we swapped with.
        // of course, if swapped node is a leaf node, this is not necessary

        if (smallestPathSumVertex != A[i]) {
            // repair the damaged heap
            Min_Heapify(smallestPathSumVertex.key);
        }

    }

    public SLL getRightChild(int i) {
        int ret = (i*2)+1;
        if (ret > A.length-1) {
            return null;//original heap code said: return -1// heap_size is array index -1, adjusts for A index 0
        }
        else return A[ret];
    }

    public SLL getLeftChild(int i) {
        int ret = (i * 2);
        if (ret > A.length-1) {
            return null;
        } else {
            return A[ret];
        }
    }
    public SLL getParentVertex(int i){
        return A[(int)Math.floor(i/2)];
    }

    public void swap(int idx, int idx2){

        SLL temp = A[idx];
        A[idx] = A[idx2];
        A[idx2] = temp;
    }

    public boolean isLeafNode(int i){
        if (i > (this.heap_size)/2){
            return true;
        }
        else return false;
    }

public void printHeap(){
    for (int i = 1; i < A.length; i++) {

        System.out.println("heap position: "+i + ", vertex key: " + A[i].key);
        SLL leftChild = getLeftChild(i);
        SLL rightChild = getRightChild(i);
        if (leftChild != null) {
            System.out.println("  heap right child: at " + i * 2 + ": " + leftChild.key + " with pathSum: "+leftChild.shortestPathSum);
        } else {
            System.out.print("  left child null");
        }
        if (rightChild != null) {
            System.out.println("  heap left child: " + (i * 2 + 1) + ": " + rightChild.key + " with pathSum: "+rightChild.shortestPathSum);
        } else {
            System.out.print("  right child null\n");
        }
        System.out.println();

    }
}




}
