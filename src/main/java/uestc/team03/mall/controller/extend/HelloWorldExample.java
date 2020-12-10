package uestc.team03.mall.controller.extend;

import org.neo4j.driver.AuthTokens; //验证令牌接口
import org.neo4j.driver.Driver;     //驱动程序接口
import org.neo4j.driver.GraphDatabase;//图形数据库类
import org.neo4j.driver.Session;      //接口
import org.neo4j.driver.Result;
import org.neo4j.driver.Transaction;
import org.neo4j.driver.TransactionWork;//事vvu接口

import static org.neo4j.driver.Values.parameters;

public class HelloWorldExample implements AutoCloseable//AutoCloseable自动关闭接口，程序运行结束调用close()方法，关闭之前没关闭的资源
{
    private final Driver driver;//接口变量，赋值必须实现Driver接口的对象

    public HelloWorldExample(String uri, String user, String password) {
        driver = GraphDatabase.driver(uri, AuthTokens.basic(user, password));//创建图形数据库驱动程序，driver是实现Driver接口的对象 这里相当于开启一个驱动程序线程，驱动程序的实现通常是线程安全的
    }

    @Override
    public void close() throws Exception //实现接口方法
    {
        driver.close();//关闭驱动程序
    }

    public void printGreeting(final String message) {
        try (Session session = driver.session()) {
            String greeting = session.writeTransaction(new TransactionWork<String>() {
                @Override
                public String execute(Transaction tx) {
                    Result result = tx.run("CREATE (a:Greeting) " +
                                    "SET a.message = $message " +
                                    "RETURN a.message + ', from node ' + id(a)",
                            parameters("message", message));
                    //result.single().get( 0 ).asString()
                    System.out.println(result.single().get(0).asString());//返回结果数据流,转成字符串给greeting
                    return "ss";//返回第一条数据
                }
            });
            System.out.println(greeting);
            System.out.println(greeting);
        }
    }
}
