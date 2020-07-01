import java.util.Arrays;
import java.util.concurrent.CountDownLatch;


public class Main {


    public static void main(String[] args){

        String[] groups = {"Ferrari", "Mercedes","Red Bull", "McLaren", "Haas"
        ,"Renault","Sauber","Toro Rosso"};
        final CountDownLatch latch = new CountDownLatch(1);
        Arrays.stream(groups).forEach(gr -> { new Thread(new CarGroup(latch,gr)).start();
             });

        latch.countDown();
    }
}


