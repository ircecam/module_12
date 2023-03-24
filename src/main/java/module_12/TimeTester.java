package module_12;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TimeTester {
    public void getTimeFromStartOfProgram() throws InterruptedException {
        long time = System.currentTimeMillis();
        ScheduledExecutorService service = Executors.newScheduledThreadPool(2);
        service.scheduleAtFixedRate(()->System.out.println((System.currentTimeMillis() - time)/1000),
                0,
                1,
                TimeUnit.SECONDS
                );
        service.scheduleAtFixedRate(()-> System.out.println("5 seconds have passed"),
                5,
                5,
                TimeUnit.SECONDS
        );
        Thread.sleep(15000);
        service.shutdownNow();


    }
}
