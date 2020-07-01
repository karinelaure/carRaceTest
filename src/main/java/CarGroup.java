import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class CarGroup implements Runnable {
    CountDownLatch latch;
    private String name;
    private AtomicBoolean con = new AtomicBoolean(false);

    public CarGroup(CountDownLatch latch,String name) {
        this.latch = latch;
        this.name = name;
    }

    public void run() {
            new Thread(new Car(name+"-car1",this)).start();
            new Thread(new Car(name+"-car2",this)).start();
        try {
            latch.await();

            System.out.println("starting : "+name +" time: "+System.currentTimeMillis());

        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public AtomicBoolean getCon() {
        return con;
    }

    public synchronized void pitStop(Car car){
        con.set(true);
        try {
           // System.out.println(car.getName()+" went to sleep "+System.currentTimeMillis());
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        con.set(false);

    }
}
