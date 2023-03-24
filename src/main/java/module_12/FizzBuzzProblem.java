package module_12;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class FizzBuzzProblem {

    private volatile AtomicInteger number;
    private final int n;
    private volatile ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<>();


    public FizzBuzzProblem(int number){
        this.number = new AtomicInteger(1);
        this.n = number;
    }


    public void solve() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        FizzBuzzProblem fizzBuzzProblem = new FizzBuzzProblem(n);
        executorService.submit(fizzBuzzProblem::fizz);
        executorService.submit(fizzBuzzProblem::buzz);
        executorService.submit(fizzBuzzProblem::fizzbuzz);
        executorService.submit(fizzBuzzProblem::number);
        executorService.submit(fizzBuzzProblem::print);
        Thread.sleep(4000);
        executorService.shutdownNow();
    }




    private synchronized void fizz(){
        while (number.get() < n){
            if(number.get() % 3 == 0 && number.get() % 5 != 0){
                queue.add("fizz");
                number.incrementAndGet();
                notifyAll();
            }
            else {
                try {
                    wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }

        }
    }

    private synchronized void buzz(){
        while (number.get() < n ) {
            if (number.get() % 5 == 0 && number.get() % 3 != 0) {
                queue.add("buzz");
                number.incrementAndGet();
                notifyAll();
            } else {
                try {
                    wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
        }
    }

    private synchronized void fizzbuzz(){
        while (number.get() < n){
            if(number.get() % 15 == 0){
                queue.add("fizzbuzz");
                number.incrementAndGet();
                notifyAll();
            }
            else {
                try {
                    wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }

        }
    }


    private synchronized void number(){
        while (number.get() < n){
            if(number.get() % 3 != 0 && number.get() % 5 != 0){
                queue.add(String.valueOf(number));
                number.incrementAndGet();
                notifyAll();
            }
            else {
                try {
                    wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
        }
    }



    private void print(){
        while(true){
            if(queue.isEmpty()){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            while (!queue.isEmpty()){
                System.out.println(queue.poll());
            }
        }
    }

    }

