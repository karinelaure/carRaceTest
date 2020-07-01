import com.sun.org.apache.xalan.internal.res.XSLTErrorResources_en;

import java.util.Random;

public class Car implements Runnable{

    private static final long SECOND = 1000;
    private static final double SPEED = 0.05; //km/s
    private double totalProgress;
    private String name;

    public String getName() {
        return name;
    }

    private CarGroup carGroup;
    private int slept ;
    private static final Random random = new Random();


    public Car(String name,CarGroup carGroup) {
        this.totalProgress = 0;
        this.name  = name;
        this.slept=0;
        this.carGroup = carGroup;
    }


    @Override
    public void run() {
        long start = System.currentTimeMillis();
        long t= start+SECOND;
        while(System.currentTimeMillis() < t){
            int rand = random.nextInt(1000);
            if(rand < 5  || slept == 1){
                synchronized (this) {
                    if (!carGroup.getCon().get()) {
                        carGroup.pitStop(this);
                        slept =0;
                    } else{
                        /*if(slept == 0) {
                            System.out.println(name + " is waiting ;"+System.currentTimeMillis());
                        }*/
                        slept = 1;
                        totalProgress += SPEED;
                        continue;
                    }
                }
            } else {
                totalProgress += SPEED;
            }

        }
        System.out.println(name+" Runs meter: "+totalProgress );

    }


}
