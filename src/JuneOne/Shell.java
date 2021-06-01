package JuneOne;

class MyShell{
    int[] SSort(int arr[]){
        int rarr[];
        for (int num=arr.length/2;num>0;num/=2) {
            for (int i=num; i<arr.length; i++) {
                int thevalue=arr[i];
                int j;
                for (j=i-num;j>=0&&arr[j]>thevalue;j-=num) {
                    arr[j+num]=arr[j];
                }
                arr[j+num]=thevalue;
            }
        }
        rarr=arr;
        return rarr;
    }
}


public class Shell {
    public static void main(String[] args) {
        MyShell myShell=new MyShell();
        int data[]={10,8,6,20,4,3,22,1,0,15,16};
        int newdata[];
        int i;
        newdata=myShell.SSort(data);
        for (i=0;i<newdata.length;i++){
            System.out.print(newdata[i]+" ");
        }
    }
}
