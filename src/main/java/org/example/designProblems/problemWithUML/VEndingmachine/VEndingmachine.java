package org.example.designProblems.problemWithUML.VEndingmachine;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.xml.crypto.dsig.TransformService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VEndingmachine {
}


@Getter
@Setter
@AllArgsConstructor
class Product{
    private  Integer id;
    private String name;
    private Integer quantity;
    private Double price;

}

enum Money{
    HUNDRED(100),
    FIFTY(50),
    TWNETY(20),
    TEN(10);


    private final int value;

    Money(int value){
        this.value  =value;
    }
    public int getValue(){
        return value;
    }
}

@Getter
final  class ProductManager{
    public static ProductManager productManager=null;
    public static Map<Integer,Product> products;
    public static Map<Money,Integer> moneyMap;
    public static double totalMoney =0;
    private ProductManager(){};
    

    public static ProductManager getProductInstance(){
        if(productManager==null){
            synchronized (ProductManager.class){
                if(productManager==null) {
                    productManager = new ProductManager();
                    products = new HashMap<>();
                    moneyMap = new HashMap<>();
                }

            }
        }

        return productManager;
    }

    void addProduct(Product product){
        products.put(product.getId(),product);
    }
    void addMoney(Money money,int quantity){
        moneyMap.put(money,moneyMap.getOrDefault(money,0)+ quantity);
        totalMoney+= quantity*money.getValue();
    }
    void deleteProduct(Integer id){
        products.remove(id);
    }
    boolean updateProduct(Integer id,Integer removeQuantity){
        Product product = products.get(id);
        if(product.getQuantity()>=removeQuantity)
        product.setQuantity(product.getQuantity()-removeQuantity);
        else
            return false;
        return true;
    }
}

interface Transaction{
    boolean execute(Integer productId,int quantity,int money);
}

class SimpleTransaction implements Transaction{
    ProductManager productManager;
    
    SimpleTransaction(){
        productManager = ProductManager.getProductInstance();
    }
    @Override
    public boolean execute(Integer productId,int quantity,int money) {
         if(quantity*productManager.products.get(productId).getPrice()<=money){

             if(productManager.products.get(productId).getQuantity()>=quantity){
                  return true;
             }


         }else{
             return false;
         }
         return false;
    }
}

class TransactionManager{
    Transaction transaction;

    TransactionManager(Transaction transaction){
        this.transaction  = transaction;
    }

    boolean buy(Integer id ,int money,int quantity){
        return transaction.execute(id,quantity,money);
    }
}

class vm{
    public static void main(String[] args) {
        ProductManager productManager = ProductManager.getProductInstance();
        Product product = new Product(1,"Pepsi",1,100.0);
        productManager.addProduct(product);
        productManager.addMoney(Money.HUNDRED,1);

        TransactionManager transactionManager = new TransactionManager(new SimpleTransaction());
        transactionManager.buy(1,10,1);
    }
}