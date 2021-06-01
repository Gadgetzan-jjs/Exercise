package JuneOne;

public class Quick {
    public static void main(String[] args) {
        final int data[]={8,5,4,7,6,1,6,3,8,12,10};
        MyQuick myQuick=new MyQuick();
        myQuick.QuickSort(data,0,10);
        for (int a:data){
            System.out.println(a+" ");
        }
    }
}
class MyQuick{
    void QuickSort(int[] data,int left,int right) {
        int i,j;
        int returndata[];
        int first,temp;
        i = left;
        j = right;
        first = data[left];
        while(true) {
            while((++i)<right-1 && data[i]<first);
            while((--j)>left && data[j]>first);
            if(i>=j)
                break;
            temp=data[i];
            data[i]=data[j];
            data[j]=temp;
        }
        data[left]=data[j];
        data[j]=first;
        if(left<j)
            QuickSort(data,left,j);
        if(right>i)
            QuickSort(data,i,right);
    }
}
