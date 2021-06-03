package JuneThree.Producer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
class Collection{
    public int source=0;
    private Lock lock;
    private Condition condition;
    public Collection(Lock lock){
        this.lock=lock;
    }
    public void addsource(){
        try {
            condition=lock.newCondition();
            lock.lock();
            if (source >=10) {
                try {
                    condition.await();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }else{
                System.out.println("生产者正在生产");
                System.out.println("生产者生产第" + (++source) + "个产品");
                condition.signalAll();
            }
        }finally {
            lock.unlock();
        }
    }
    public void getsource(){
        try{
            condition=lock.newCondition();
            lock.lock();
            if (source<=0){
                try {
                    condition.await();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }else{
                System.out.println("消费者正在消费");
                System.out.println("消费者消费第" + (source--) + "个产品");
                condition.signalAll();
            }
        }finally {
            lock.unlock();
        }
    }
}
class Producer implements Runnable {
    private Collection collection = null;
    public Producer(Collection collection) {
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
            collection.addsource();
        }
    }
}
class Consumer implements Runnable {
    Collection collection = null;
    public Consumer(Collection collection) {
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
            collection.getsource();
        }
    }
}
public class Test {
    public static void main(String[] args) {
        Lock lock=new ReentrantLock();
        Collection collection=new Collection(lock);
        Thread producer=new Thread(new Producer(collection),"Producer");
        Thread consumer=new Thread(new Consumer(collection),"Consumer");
        producer.start();
        consumer.start();
    }
}
