package JuneThree.Producerone;
import java.util.concurrent.ArrayBlockingQueue;
class Collection{
    public int source=0;
    public ArrayBlockingQueue<Integer> arrayBlockingQueue=new ArrayBlockingQueue<Integer>(10);
}
class Produce implements Runnable{
    private ArrayBlockingQueue Parr=null;
    private Collection collection=null;
    public Produce(ArrayBlockingQueue Parr,Collection collection){
        this.Parr=Parr;
        this.collection=collection;
    }
    @Override
    public void run() {
        while (true) {
            try {
                Thread.currentThread().sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Produce();
        }
    }
    private void Produce(){
        try {
            Parr.put(collection.source++);
            System.out.println("生产第"+Parr.size()+"个产品");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
class Consumer implements Runnable{
    private ArrayBlockingQueue Carr=null;
    public Consumer(ArrayBlockingQueue Carr){
        this.Carr=Carr;
    }
    @Override
    public void run() {
        while (true) {
            try {
                Thread.currentThread().sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Consumer();
        }
    }
    private void Consumer() {
        Object result=Carr.poll();
        if (result==null) {
            return;
        }else{
            System.out.println("消费第" + (Carr.size()+1) + "个产品");
        }
    }
}
public class Test {
    public static void main(String[] args) {
        Collection collection=new Collection();
        Produce producer=new Produce(collection.arrayBlockingQueue,collection);
        Consumer consumer=new Consumer(collection.arrayBlockingQueue);
        Thread producerThread=new Thread(producer);
        Thread consumerThread=new Thread(consumer);
        producerThread.start();
        consumerThread.start();
    }
}
