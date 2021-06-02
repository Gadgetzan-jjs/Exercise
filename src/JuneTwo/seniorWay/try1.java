package JuneTwo.seniorWay;
interface senior{
    public int process(int x,int y);
}
class operation{
    public static int doit(senior s,int x,int y){
        return s.process(x,y);
    }
}
///////////////////////////////////////
abstract class senior2{
    public abstract boolean ops(int x,int y);
        }
class datademo {
    public static boolean Do(senior2 s,int x,int y){
        return s.ops(x,y);
    }
}
//////////////////////////////////////
public class try1 extends senior2{

    public static void main(String[] args) {
        senior plus=new senior(){
            @Override
            public int process(int x, int y) {
                return x+y;
            }
        };
        senior ride=new senior(){
            @Override
            public int process(int x, int y) {
                return x*y;
            }
        };
        senior2 s2=new senior2() {
            @Override
            public boolean ops(int x, int y) {
                return (x==y);
            }
        };

        int num1,num2;
        boolean b;
        num1=operation.doit(plus,10,10);
        System.out.println(num1);
        num2=operation.doit(ride,20,30);
        System.out.println(num2);
        b=datademo.Do(s2,15,20);
        System.out.println(b);
    }
    @Override
    public boolean ops(int x, int y) {
        return true;
    }
}
