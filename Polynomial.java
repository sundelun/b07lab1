public class Polynomial{
    double[] arr;
    public Polynomial(){
        arr=new double[]{0.0};
    }
    public Polynomial(double[] arrr){
        arr=arrr;
    }
    public Polynomial add(Polynomial p){
        int length1=arr.length;
        int length2=p.arr.length;
        int diff=length1-length2;
        int size=Math.max(length1,length2);
        int minimum=Math.min(length1,length2);
        double[] newarr=new double[size];
        for(int i=0;i<minimum;i++){
            newarr[i]=arr[i]+p.arr[i];
        }
        if(diff==0){
            Polynomial re=new Polynomial(newarr);
            return re;
        }
        for(int i=minimum;i<size;i++){
            if(diff>1){
                newarr[i]=arr[i];
            }
            else{
                newarr[i]=p.arr[i];
            }
        }
        Polynomial re=new Polynomial(newarr);
        return re;
    }
    public double evaluate(double x){
        int length=arr.length;
        double result=0.0;
        for(int i=0;i<length;i++){
            result += arr[i]*Math.pow(x,i);
        }
        return result;
    }
    public boolean hasRoot(double x){
        int length=arr.length;
        double result=0.0;
        for(int i=0;i<length;i++){
            result += arr[i]*Math.pow(x,i);
        }
        return result==0.0;
    }
}