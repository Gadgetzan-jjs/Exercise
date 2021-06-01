package JuneOne;

public class Heap {
    public static void main(String[] args) {
        int arr[]={2,8,6,1,10,15,3,12,11};
        MyHeap myHeap=new MyHeap();
        myHeap.heapsort(arr);
        for (int a:arr){
            System.out.print(a+" ");
        }
    }
}
class MyHeap{
    void heapsort(int[] data) {
        int length = data.length;
        for (int k=length/2; k>=1;k--) {
            sink(data,k,length);
        }
        while (length>1) {
            exch(data,1,--length);
            sink(data,1,length);
        }
    }
     void sink(int[] a,int k,int p) {
        while ((2*k + 1)<p&&((a[k]<a[2*k+1])||(a[k]<a[2*k]))){
            if (a[2*k+1]>a[2*k]){
                exch(a,k,2*k+1);
                k=2*k+1;
            }else{
                exch(a,k,2*k);
                k=2*k;
            }
        }
    }
     void exch (int[] a,int i,int j) {
        int t=a[i];
        a[i]=a[j];
        a[j]=t;
    }
}
