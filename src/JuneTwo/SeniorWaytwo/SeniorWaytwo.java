package JuneTwo.SeniorWaytwo;

import java.util.ArrayList;

interface Ops{
    public boolean haddle(int x,int y);
}
class DataList{
    public ArrayList getArrayList() {
        return arrayList;
    }
    public void setArrayList(ArrayList arrayList) {
        this.arrayList = arrayList;
    }

    private ArrayList<Integer> arrayList=null;
    public DataList(ArrayList arrayList){
        this.arrayList=arrayList;
        
    }
    public DataList(){

    }
    public DataList filter(Ops s,int x) {
        ArrayList newarrayList = new ArrayList();
        for (Integer i:this.arrayList) {
            if (s.haddle(i, x)){
                newarrayList.add(i);
            }
            }
            DataList myDatademo=new DataList(newarrayList);
            return myDatademo;

        }
}
public class SeniorWaytwo {
    public static void main(String[] args) {
        Ops deal = new Ops() {
            @Override
            public boolean haddle(int x, int y) {
                if (x >= y) {
                    return true;
                } else {
                    return false;
                }
            }
        };
        DataList dataList = new DataList();
        ArrayList arr = new ArrayList();
        arr.add(3);
        arr.add(5);
        arr.add(7);
        arr.add(9);
        arr.add(11);
        dataList.setArrayList(arr);
        arr=dataList.filter(deal, 4).getArrayList();
        for (int i = 0; i < arr.size(); i++) {
            System.out.print(arr.get(i)+" ");
        }
        System.out.println();
        arr=dataList.filter(deal,4).filter(deal,6).filter(deal,9).getArrayList();
        for (int a=0;a<arr.size();a++){
            System.out.print(arr.get(a)+" ");
        }
    }
}
