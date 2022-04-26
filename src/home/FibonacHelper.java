package home;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class FibonacHelper implements Callable<Integer>{

    private int position;

    public FibonacHelper(int position){
        this.position=position;
    }


    @Override
    public Integer call() throws Exception {
     
        int result=find_fib(this.position);
        
    
        TimeUnit.MILLISECONDS.sleep();

        return result;
    }

    public int find_fib(int pos){

        if(pos==1){
            return 0;
        }
        else if(pos==2){
            return 1;
        }
        else{
        return find_fib(pos-1)+find_fib(pos-2);

        }
    }
}