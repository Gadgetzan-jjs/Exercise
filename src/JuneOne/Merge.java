package JuneOne;

public class Merge {
    public static void main(String[] args) {
        MyMerge myMerge=new MyMerge();
        int data[]={9,2,6,3,5,7,10,11,12};
        myMerge.merSort(data,0,data.length-1);
        for (int a:data){
            System.out.print(a+" ");
        }
    }
}
class MyMerge {
    void merSort(int[] arr, int left, int right) {
        if (left<right) {
            int mid = (left+right) / 2;
            merSort(arr,left,mid);
            merSort(arr,mid+1,right);
            merge(arr,left,mid,right);
        }
    }

    void merge(int[] arr, int left, int mid, int right) {
        int[] temp=new int[right - left + 1];
        int i=left;
        int j=mid + 1;
        int k=0;
        while (i<=mid&&j<=right) {
            if(arr[i]<arr[j]) {
                temp[k++]=arr[i++];
            }else{
                temp[k++]=arr[j++];
            }
        }
        while (i<=mid) {
            temp[k++]=arr[i++];
        }
        while (j<=right) {
            temp[k++]=arr[j++];
        }
        for (int k2=0;k2<temp.length;k2++) {
            arr[k2+left]=temp[k2];
        }
    }
}
