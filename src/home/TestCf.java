package home;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

public class TestCf {

   static ThreadPoolExecutor executor=(ThreadPoolExecutor)Executors.newFixedThreadPool(4);
    public static void main(String[] args){

        Supplier<Integer> supplier=new Supplier<Integer>() {
            @Override
            public Integer get() {
                
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (Exception e) {
                   
                }
                return find_fib(8);
            }
        };

    CompletableFuture cf=CompletableFuture.supplyAsync(supplier).completeAsync(supplier,executor).whenComplete((res,ex)->{

        System.out.println("result is "+res);
        System.exit(0);
    });

    
        // try {
        //     int result=((Integer)cf.get()).intValue();

         
        // } catch (InterruptedException e) {
            
        //     e.printStackTrace();
        // } catch (ExecutionException e) {
      
        //     e.printStackTrace();
        // }

        if(cf.isDone()){
            System.exit(0);
        }
        
    }

    public static int find_fib(int pos){

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
