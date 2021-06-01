package MayThirty;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        String str = "abc";
        char[] arr = {'a', 'b', 'c'};
        String str2 = new String(arr);
        str2 = str2.intern();
        System.out.println(str == str2);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 1000000000; i++) {
            list.add(String.valueOf(i).intern());
        }
        //执行到四千年多万运行更慢，但一直在执行
        //修改jvm参数会报出OutOfMemoryError错误
    }
}
