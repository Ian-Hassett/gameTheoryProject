

public class PayoffMatrix {
    //ArrayList<ArrayList<Node>> payoffs;
    
    MatrixArray payoffs = new MatrixArray();
    
    public PayoffMatrix(Node start){
        payoffs.add(new MatrixArray());
        generateMatrix(start, payoffs, 0);
        payoffs = (MatrixArray) payoffs.get(0);
        
    }
    public MatrixArray generateMatrix(Node node,MatrixArray array,int index){
        
        if(!node.followers.isEmpty()){
            for (int i =0;i<node.followers.size();i++) {
                ((MatrixArray)array.get(index)).add(new MatrixArray());
                generateMatrix(node.followers.get(i), (MatrixArray)array.get(index), i);
            }
        }else{array.set(index, node);
        }
        return payoffs;
    }
    public void display(){
        System.out.println(payoffs);
    }
}