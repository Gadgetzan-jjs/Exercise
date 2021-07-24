package JulyTwentyTwo;

import java.util.ArrayList;
import java.util.HashMap;

class Node{
    private String cotext;
    private Node leftc;
    private Node rightc;
    private int priority;
    public Node(){
        priority=2;
    }
    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public Node getLeftc() {
        return leftc;
    }

    public void setLeftc(Node leftc) {
        this.leftc = leftc;
    }

    public Node getRightc() {
        return rightc;
    }

    public void setRightc(Node rightc) {
        this.rightc = rightc;
    }

    public String getCotext() {
        return cotext;
    }

    public void setCotext(String cotext) {
        this.cotext = cotext;
    }
}
class OpsNode {
    public Node insertNode(Node root, Node child) {
        if (root == null) {
            root=child;
        } else if (root.getPriority() < child.getPriority()) {
            root.setRightc(insertNode(root.getRightc(), child));
        } else if (root.getPriority() > child.getPriority()) {
            root.setLeftc(insertNode(root.getLeftc(), child));
        } else {

        }
        return root;
    }
}
class Demo{
    private HashMap<String,String> valuemap=new HashMap();
    private Node headnode=new Node();
    private HashMap<String, Integer> constpool=new HashMap();
    private OpsNode opsNode=new OpsNode();
    public Demo(){
        constpool.put("if",16);
        constpool.put("string",16);
        constpool.put("for",16);
        constpool.put("else",16);

    }
    private String[] registerlist={"","","","","","","","","",""};

    public int ops(String string){
        Node treeobject=new Node();
        String[] context=string.split("\n");
        int contextlength=context.length;
        for (int i=0;i<contextlength;i++){
            char[] array=context[i].toCharArray();
            if (array.length<=4) continue;
            try {


                switch (array[0] + array[1]) {
                    case '{' + '{':
                        if (isvalue(array)) dealvalue(array);
                        break;
                    case '{' + '%':
                        if (iscontroll(array)) dealcontroll(array);
                        break;
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }


        return 1;
    }
    //判断是否为赋值语句
    public boolean isvalue(char[] chs){
        return (chs[chs.length - 2] + chs[chs.length - 1])=='}'+'}';
    }

    //判断是否为控制语句
    public boolean iscontroll(char[] chs){
        return (chs[chs.length - 2] + chs[chs.length - 1])=='%'+'}';
    }

    //判断是否为注释
    public boolean isdefine(char[] chs){
        return (chs[chs.length - 2] + chs[chs.length - 1])=='#'+'}';
    }

    public boolean isstring(char[] chs){ return (chs[chs.length-1]=='\'');}
    //将定义的变量存入寄存器hashmap
    public void dealvalue(char[] chs){
        int strlength=chs.length;
        String targetstr="";
        for (int i=2;i<strlength-2;i++){
            if (chs[i]!=' '){
                targetstr+=chs[i];
            }
        }
        if (targetstr!=null)
            valuemap.put(targetstr, targetstr);
        Node node=new Node();
        node.setCotext(valuemap.get(targetstr));
        opsNode.insertNode(headnode,node);
        System.out.println(node.getPriority());
        System.out.println(valuemap.get(targetstr));
    }

    public void dealcontroll(char[] chs) throws Exception {
        int strlength=chs.length;
        String targetstr= "";
        for (int i=2;i<strlength-2;i++){
            if (chs[i]!=' '){
                if (chs[i]=='\''){
                    for (int j=i+1;j<strlength-2;j++){
                        if (chs[j]=='\'') {
                            int accessindex=registerDispatch();
                            if (accessindex<0){
                                throw new Exception();
                            }
                            registerlist[accessindex]=targetstr;
                            i=j;
                            targetstr="";
                            break;
                        }
                        targetstr+=chs[j];

                    }
                }
                targetstr+=chs[i];
                if (constpool.get(targetstr)!=null){
                    //调用常量池，每个关键字是一个对象，判断优先级，优先级低的为根节点
                    Node node=new Node();
                    node.setPriority(constpool.get(targetstr));
                    opsNode.insertNode(headnode,node);
                    targetstr="";
                }
                else if (valuemap.get(targetstr)!=null){
                    Node node=new Node();
                    node.setCotext(targetstr);
                    opsNode.insertNode(headnode,node);
                    targetstr="";
                }
            }
        }
    }
    public void replacevalue(String target,String value){
        if (valuemap.get(target)!=null){
            valuemap.put(target,value);
        }
        System.out.println(valuemap.get(target));
    }
    public int registerDispatch(){
        for (int i=0;i<registerlist.length;i++){
            if (registerlist[i]==""){
                return i;
            }
        }
        return -1;

    }
    }

public class Test {


    public static void main(String[] args) {
        String setname="{{ name }}"+"\n{% string name = 'zoro' %}"+"\n{% if name == 'zoro' %}"+"\n{% else %}"
                +"\n{% string name = 'luffy' %}";
        String replacename="{% if 'zoro' == 'zoro' %}";
        Demo demo=new Demo();
        demo.ops(replacename);
        demo.replacevalue("name","zoro");

    }

}
