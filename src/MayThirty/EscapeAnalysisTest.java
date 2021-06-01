package MayThirty;

public class EscapeAnalysisTest {

    public static void main(String[] args) {
        long a1 = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            alloc();
        }
        // 查看执行时间
        long a2 = System.currentTimeMillis();
        System.out.println("cost " + (a2 - a1) + " ms");
        // 为了方便查看堆内存中对象个数，线程sleep
        try {
            Thread.sleep(100000);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
    }

    private static void alloc() {
        User user = new User();
    }

    static class User {

    }
}
//    Attaching to process ID 25271, please wait...
//        Debugger attached successfully.
//        Server compiler detected.
//        JVM version is 25.72-b15
//
//        using thread-local object allocation.
//        Parallel GC with 10 thread(s)
//
//        Heap Configuration:
//        MinHeapFreeRatio         = 0 //对应jvm启动参数 -XX：MinHeapFreeRatio设置JVM堆最小空闲比率
//        MaxHeapFreeRatio         = 100 //对应jvm启动参数 -XX:MaxHeapFreeRatioFreeRatio设置JVM堆空间最大空闲比率
//        MaxHeapSize              = 4156555264 (3964.0MB)//对应JVM启动参数 -XX:MaxHeapSize=设置JVM的最大大小
//        NewSize                  = 86507520 (82.5MB) //-XX:NewSize=设置JVM堆的“新生代”的默认大小
//        MaxNewSize               = 1385168896 (1321.0MB)//-XX:MaxNewSize=设置“新生代”的最大大小
//        OldSize                  = 173539328 (165.5MB)//-XX:OldSize=<value>:设置JVM堆的“；老生代”的大小
//        NewRatio                 = 2 //-XX:NewRatio=:"新生代"和“老生代”的大小比率
//        SurvivorRatio            = 8 //-XX:SurvivorRatio=设置年轻代中Eden区与Survivor区的大小比值
//        MetaspaceSize            = 21807104 (20.796875MB) //-XX:MetaspaceSize=<value>:设置JVM堆的‘元空间’的初始大小
//        CompressedClassSpaceSize = 1073741824 (1024.0MB)
//        MaxMetaspaceSize         = 17592186044415 MB ////对应jvm启动参数-XX:MaxMetaspaceSize= :设置JVM堆的‘元空间’的最大大小
//        G1HeapRegionSize         = 0 (0.0MB)
//
//        Heap Usage: //堆内存分布
//        PS Young Generation //年轻带内存分布
//        Eden Space: Eden内存分布
//        capacity = 65536000 (62.5MB) eden总容量
//        used     = 10491640 (10.005607604980469MB) eden已经使用
//        free     = 55044360 (52.49439239501953MB) eden剩余容量
//        16.00897216796875%     used eden使用比率
//        From Space: 其中 from区的内存分布
//        capacity = 10485760 (10.0MB)
//        used     = 0 (0.0MB)
//        free     = 10485760 (10.0MB)
//        0.0% used
//        To Space: to 区的内存分布
//        capacity = 10485760 (10.0MB)
//        used     = 0 (0.0MB)
//        free     = 10485760 (10.0MB)
//        0.0% used
//        PS Old Generation 老年代的内存分布
//        capacity = 173539328 (165.5MB)
//        used     = 0 (0.0MB)
//        free     = 173539328 (165.5MB)
//        0.0% used
//
//        2284 interned Strings occupying 160824 bytes.
