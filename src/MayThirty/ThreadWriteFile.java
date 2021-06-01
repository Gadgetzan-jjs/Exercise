package MayThirty;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class ThreadWriteFile {
    public static void main(String[] args) {
        Object o=new Object();
//        Demo demo=new Demo();
        File file=new File("src/MayThirty/test.txt");
        MyThread threadone=new MyThread(file,o);
        String one="锄禾日当午";
        String two="谁知盘中餐";
        threadone.setFrist(one);
        threadone.setSecond(two);
        String three="汗滴禾下土";
        String four="粒粒皆辛苦";
        MyThread threadtwo=new MyThread(file,o);
        threadtwo.setFrist(three);
        threadtwo.setSecond(four);
        threadone.setArrayList(one,two);
        threadtwo.setArrayList(three,four);
        threadone.start();
        threadtwo.start();
    }
}
class MyThread extends Thread {
    private boolean tf = true;
    private File file;
//    private Demo demo;
    private Object object;
    private ArrayList<String> arrayList = new ArrayList();

    public MyThread(File file,Object o) {
        this.file = file;
        this.object = o;
    }

    private String frist;
    private String second;

    public String getFrist() {
        return frist;
    }

    public void setFrist(String frist) {
        this.frist = frist;
    }

    public String getSecond() {
        return second;
    }

    public void setSecond(String second) {
        this.second = second;
    }

    public void setArrayList(String one, String two) {
        arrayList.add(one);
        arrayList.add(two);
    }

    @Override
    public void run() {
        try {
            synchronized (object) {
                FileOutputStream fileOutputStream = new FileOutputStream(file, true);
                for (int i = 0; i < 2; i++) {
                    fileOutputStream.write((arrayList.get(i)).getBytes());
                    fileOutputStream.write("\r\n".getBytes());
                    fileOutputStream.flush();
                    object.notify();
                    if (tf) {
                        tf = false;

                        object.wait();

                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
//class Demo{
//    public void writeFile(File file,String content) {
//        try {
//            FileOutputStream fileOutputStream = new FileOutputStream(file,true);
//            fileOutputStream.write((content).getBytes());
//            fileOutputStream.write("\r\n".getBytes());
//            fileOutputStream.flush();
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//}