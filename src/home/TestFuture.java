package home;

        /// USGAE OF FUTURE API ...IT HAS FLAW LIKE IT BLOCKS THE THREAD UNTILL FUTURE IS PROCESSED
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import home.FibonacHelper;
public class TestFuture{


    public static void main(String[] args){

        ThreadPoolExecutor executor = (ThreadPoolExecutor)Executors.newFixedThreadPool(4);
        List<Map<Integer,Future<Integer>>> results=new ArrayList<>();
        Random random= new Random();
        for(int k=0;k<6;k++){

            int pos=random.nextInt(100)+10;
            FibonacHelper helper = new FibonacHelper(pos);
            Map<Integer,Future<Integer>> result=new HashMap<>();
            result.put(pos,executor.submit(helper));
            results.add(result);

        }


        for(Map<Integer,Future<Integer>> pair:results){

            Optional<Integer> optional =pair.keySet().stream().findFirst();
            if(!optional.isPresent()){
                return;
            }
           

            
            try {
                Integer key=optional.get();
                System.out.printf("value is %d%n",key);
                Future future=pair.get(key);
                int result=((Number)future.get()).intValue();
                boolean is_done=future.isDone();
                log("RESULT is ", String.valueOf(result));
                log("STATUS", String.valueOf(is_done));
                log("-------", "END-------");
            } catch (Exception e) {
                e.printStackTrace();
            }
        



        }
        executor.shutdown();

    }
    public static void log(String title,String message){
        System.out.println(title+": "+message);
    }
}