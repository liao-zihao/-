package observable;

public class Test {
    public static void main(String[] args){
        ProductList observable=ProductList.getInstance();
        TaoBao t=new TaoBao();
        JingDong j=new JingDong();
        observable.addObserver(j);
        observable.addObserver(t);
        observable.addProduct("1");
    }
}
