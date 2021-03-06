package com.company;
import java.io.*;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

class Student
{
    private int id;
    private String name;
    // this block always called before any constructor.
    {
        System.out.println("This is init block");
    }
    Student(){}
    Student(int id, String nm)
    {
        this.id = id;
        this.name = nm;
    }
    public void showRecord()
    {
        System.out.println(id+" "+name);
    }
    Student(Student s)
    {
        // to call default constructor - this()
        id = s.id;
        name = s.name;
    }
    public void changeValue(Student s)
    {
        s.id++;
        s.name= "Jayesh";
    }
};
//Implements singleton design concept.
class Database
{
  static Database db = null;
  String lang;
  private Database() {}
  static public Database addLanguage(String l)
  {
      if(db==null) {
          db = new Database();
          db.lang = l;
      }
      return db;
  }
  public void langConnected()
  {
      System.out.println("Database is connected with  "+ lang);
  }
};

class anms
{
   anms()
  {
      System.out.println("this is the anonumous function");
  }

};

abstract class Parent
{
    abstract public void msg1();
    public void msg2()
    {
        System.out.println("This is Msg2 from Parent class");
    }
//    If we don’t want a method to be overridden, we declare it as final.
    final public void msg3()
    {
        System.out.println("This is Msg3 from Parent class");
    }
}

class Child1 extends Parent
{
    public void msg1()
    {
        System.out.println("This is MSg1 from child1 class");
    }
};
class Child2 extends Parent
{
    public void msg1() // we can change the accessibility of instance while extending and overriding
    {
        System.out.println("This is MSg1 from child2 class");
    }
};

//Interface Implementation

interface ShapeProperty
{
    double area();
    double circumferance();
}
class Square implements Cloneable,ShapeProperty
{
    float side;
    public Square(float side) {
        this.side = side;
    }

    public void setSide(float side) {
        this.side = side;
    }

    @Override
    public double area()
    {
        return side*side;
    }
    @Override
    public double circumferance()
    {
        return 4*side;
    }
    @Override
    public boolean equals(Object obj)
    {
        if(this == obj)
            return true;

        if (!(obj instanceof Square)) {
            return false;
        }

        Square t = (Square) obj;
        if(side==t.side)
        {
            return true;
        }
        return false;
    }
    @Override
    public Object clone() throws CloneNotSupportedException
    {
        Square s = (Square)super.clone();
        s.side = side;

        return s;
    }
    @Override
    public String toString()
    {
        return "Side of this Square are : "+side;
    }
}
class Triangle implements Cloneable,ShapeProperty
{
    float side1,side2,side3;

    public Triangle(float side1, float side2, float side3) {
        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
    }

    public void setSide1(float side1) {
        this.side1 = side1;
    }

    public void setSide2(float side2) {
        this.side2 = side2;
    }

    public void setSide3(float side3) {
        this.side3 = side3;
    }

    @Override
    public double area()
    {
        double s,a;
        s= (side1+side3+side2)/2;
        a = s*(s-side1)*(s-side2)*(s-side3);
        return Math.sqrt(a);
    }
    @Override
    public double circumferance()
    {
        return side1+side2+side3;
    }
    @Override
    public boolean equals(Object obj)
    {
        if(this == obj)
            return true;

        if (!(obj instanceof Triangle)) {
            return false;
        }

        Triangle t = (Triangle)obj;
        if(side1==t.side1 && side2==t.side2 && side3==t.side3)
        {
            return true;
        }
        return false;
    }
    @Override
    public Object clone() throws CloneNotSupportedException
    {
        Triangle t = (Triangle) super.clone();
        t.side3 = side3;
        t.side1 = side1;
        t.side2 = side2;

        return t;
    }
    @Override
    public String toString()
    {
        return "Sides of this Triangle are : "+side1+","+side2+","+side3;
    }
}
class SerialEx implements Serializable
{
    private int n ;
    private String str;

    public SerialEx(int n, String str) {
        this.n = n;
        this.str = str;
    }
    public void show()
    {
        System.out.println("Number : "+n+" String : "+str);
    }
}


//Lambda Expression
interface op
{
    void findOddorEven(Vector<Integer> v);
}
class LambdaTest
{
    static public op even = (v)->{
        System.out.println("Even values:");
        v.forEach((value)->{
            if(value % 2 == 0)
            {
                System.out.print(value+" ");
            }
        });
    };

    static public op odd = (v)->{
        System.out.println("Odd values:");
        v.forEach((value)->{
            if(value % 2 != 0)
            {
                System.out.print(value+" ");
            }
        });
    };
}

//Thread manipulation with thread class

class ThreadEx extends Thread
{
    public ThreadEx(String name) {
        super(name);
    }
    @Override
    public void run()
    {
        try
        {
            System.out.println("Thread with ID : "+Thread.currentThread().getId()+"And with Name " +
                    ": "+Thread.currentThread().getName()+" is running");
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
}
class ThreadEx1 implements Runnable
{
    @Override
    public void run()
    {
        try
        {
            System.out.println("Thread with ID : "+Thread.currentThread().getId()+"And with Name " +
                    ": "+Thread.currentThread().getName()+" is running");
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
}

class SyncEx extends Thread
{
    static Integer n = 10;

    @Override
    public void run()
    {
        try
        {
            synchronized (n)
            {
                System.out.println("Thread ID : "+Thread.currentThread().getId()+" Started Running.");
                System.out.println("Thread with ID : "+Thread.currentThread().getId()+" Comes inside " +
                        "the critical section.");
                n++;
                System.out.println("Thread with ID : "+Thread.currentThread().getId()+" Updates n " +
                        "value to : "+n);
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
}

class Factorial implements Runnable
{
    private int num ;

    public Factorial(int num) {
        this.num = num;
    }
    @Override
    public void run()
    {
        long f = 1;
        for(int i=1;i<=num;i++)
        {
            f = f*i;
        }
        synchronized (System.out)
        {
            System.out.println("Thread ID : "+Thread.currentThread().getId()+" having number " +num+
                    " calculates Factorial : "+f);
        }
    }
}

class Fact implements Callable
{
    private int num;

    public Fact(int num) {
        this.num = num;
    }
    @Override
    public Object call()
    {
        long f = 1;
        for(int i=1;i<=num;i++)
        {
            f = f*i;
        }
        synchronized (System.out){
        System.out.println("Thread ID : "+Thread.currentThread().getId()+" is calculating " +
                "factorial of "+num);}
        return f;
    }
}
public class Main {

    public static void fn1(int n)
    {
        n++;
    }
    public static void basicsImp(String args[])
    {
        System.out.println("hello");

        //The Java codes are first compiled into byte code (machine independent code). Then the byte code runs on Java Virtual Machine (JVM) regardless of the underlying architecture.
        //Writing a program is done by a java programmer like you and me.
        //The compilation is done by the JAVAC compiler which is a primary Java compiler included in the Java development kit (JDK). It takes Java program as input and generates bytecode as output.
        //In the Running phase of a program, JVM executes the bytecode generated by the compiler

        //JVM is part of JRE and JRE is the part of JDK

        //Execution
        //Interpreter: It interprets the bytecode line by line and then executes. The disadvantage here is that when one method is called multiple times, every time interpretation is required.
        //Just-In-Time Compiler(JIT) : It is used to increase the efficiency of an interpreter. It compiles the entire bytecode and changes it to native code so whenever the interpreter sees repeated method calls, JIT provides direct native code for that part so re-interpretation is not required, thus efficiency is improved.

        //In Java, programmers can’t delete the objects. To delete or recollect that memory JVM has a program called Garbage Collector. Garbage Collectors can recollect the of objects that are not referenced. So Java makes the life of a programmer easy by handling memory management. However, programmers should be careful about their code whether they are using objects that have been used for a long time. Because Garbage cannot recover the memory of objects being referenced.

        //command line arg : alt+shift+f10 to change the program config, shift+f10 run
        for(String val : args)
        {
            System.out.println(val+" ");
        }

        //But java uses the Unicode system not the ASCII code system and to represent Unicode system 8 bit is not enough to represent all characters so java uses 2 bytes for characters.

        //Java strings are not terminated with a null character.

        //Since arrays are immutable(cannot grow), Strings are immutable as well. Whenever a change to a String is made, an entirely new String is created

        //Usefull functions of string:
        // indexOf(str), substring(in1,in2), concat(s1,s2), lastIndexOf(str), str1.equals(str2),
        // str1.equalsIgnoreCase(str2), .trim()- white spaces, .replace(what,str)
        // toLowerCase() or toUpperCase()

        //StringBuffer

        //userfull fun: length(), capacity(), append(str or int), insert(index,what), reverse(),
        // delete(in1,in2), deleteCharAt(index), charAt(ch), indexOf(str), setcharAt(ind,ch),

        String s1 = new String("Hello");
        s1 = s1 + "Bye";
        System.out.println(s1);

        //In Java, all arrays are dynamically allocated.

        int[] a = new int[10];
        int[] b = {1,2,4,5,6,7,1,9,0,3};
        for(int i = 0;i<a.length; i++)
        {
            a[i] = i;
            System.out.print(" "+a[i]);
        }
        System.out.println("\n");
        for(int ii: b)
        {
            System.out.print(" "+ii);
        }


        //Taking User inputs
        /*Scanner in = new Scanner(System.in);

        System.out.println("enter String");
        String s = in.nextLine();

        System.out.println("enter Int");
        int n = in.nextInt();

        System.out.println(n+" "+s);*/

        //Jagged array

        String arrs[][] = new String[][]
                {
                        new String[]{"jayesh","Jitubhai", "Lalchandani"},
                        new String[]{"joy", "Lalchandani"},
                        new String[]{"Jayesh", "Lalchandani"}
                };

        String arrs2[][] = arrs; // Just reference initialization not copy.

        for(String name[]: arrs)
        {
            for (String fullname : name)
            {
                System.out.print(fullname+" ");
            }
            System.out.println();
        }

        //Copy one array to another
        String arrs3[][] = arrs.clone();
        // other : arrays.copyOf(array_name, how many), arrays.copyOfRange(array_name, st,end),

        //convert array to string
        int a1[][] = {{1,1,1},{1,1,1}};
        int a2[][] = {{1,1,1},{1,1,1}};
        String copystr = Arrays.toString(a1);
        System.out.println(copystr);
        //if array contains some objects elements or multidimenstionals, than it should be compare
        // with deepEquals()
        System.out.println(Arrays.deepEquals(a1,a2));

        //Java is strictly pass by value.
        //by value
        int t = 30;
        fn1(t);
        System.out.println(t);
        //Good practise is create one reference and point it to different memories, mostly used
        // in inheritance where we create parent class reference and creates child class memory.

        Student std = new Student(12013,"Joy");
        std.showRecord();

        // Like C++, Java does not provide default copy constructor.
        Student std1 = new Student(std);
        std.changeValue(std);
        std.showRecord();
        std1.showRecord();

        // anonomous objects are not referenced by any class reference, they are created for just
        // method calling
        new anms();

        /*What is a Singleton class?
        As the name implies, a class is said to be singleton if it limits the number of objects of that class to one.
        We can’t have more than a single object for such classes.*/

//        Implementation of the singleton design pattern
        Database db1 = Database.addLanguage("Java");
        Database db2 = Database.addLanguage("C++");// will not create any instance of Database
        // object , rather point to same db1

        db1.langConnected();
        db2.langConnected();

        // Java doesn’t support the Operator Overloading.
        //An abstract class is a class that is declared with abstract keyword.
        //An abstract method is a method that is declared without implementation.
        //An abstract class may or may not have all abstract methods. Some of them can be concrete methods
        //static methods cannot be overridden
        //Method overriding
        Parent p;
        p = new Child1();
        p.msg1();
        p.msg2();

        p = new Child2();
        p.msg1();
        p.msg2();

        //all the methods in an interface are declared with an empty body and are public and all fields are public, static and final by default.
    }
    public static void shapeImp()
    {
        try
        {
            Square s1 = new Square(10.5f);
            Square s2 = (Square) s1.clone();

            s2.setSide(11.5f);

            Triangle t1 = new Triangle(5.5f,3.5f,4.5f);
            Triangle t2 = (Triangle) t1.clone();

            t2.setSide1(2.5f);

            System.out.println(t1.equals(t2));

            System.out.println(t1.toString()+" , Area : "+t1.area()+" , Circumferance: "+t1.circumferance());
            System.out.println(t2.toString()+" , Area : "+t2.area()+" , Circumferance: "+t2.circumferance());
            System.out.println(s1.toString()+" , Area : "+s1.area()+" , Circumferance: "+s1.circumferance());
            System.out.println(s2.toString()+" , Area : "+s2.area()+" , Circumferance: "+s2.circumferance());
        }
        catch (CloneNotSupportedException e)
        {
            System.out.println(e);
        }
    }
    public static void SerialImp()
    {
        //Serialization is a mechanism of converting the state of an object into a byte stream. Deserialization is the reverse process where the byte stream is used to recreate the actual Java object in memory. This mechanism is used to persist the object
        //java.io.Serializable interface. use ObjectOuputStream and ObjectInputStream
        SerialEx obj = new SerialEx(10,"Hello");
        String file = "serialexfile.txt";
        try
        {
            FileOutputStream outfile = new FileOutputStream(file);
            ObjectOutputStream outobj = new ObjectOutputStream(outfile);

            outobj.writeObject(obj);

            System.out.println("Serialization done.");
        }
        catch(Exception e)
        {
            System.out.println(e);
        }

        obj = null;
        try
        {
            FileInputStream infile = new FileInputStream(file);
            ObjectInputStream inobj = new ObjectInputStream(infile);

            obj = (SerialEx) inobj.readObject();

            System.out.println("Deserialization done.");
            obj.show();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }
    public static void lambdaImp()
    {
        Vector<Integer> v = new Vector<>();
        int arr[] = {1,5,2,4,6,7,8,9};
        for (int val:arr)
        {
            v.add(val);
        }

        LambdaTest.even.findOddorEven(v);
        LambdaTest.odd.findOddorEven(v);
    }
    public static void ThreadImp()
    {
//        Threads can be created by using two mechanisms :
        //Extending the Thread class
//        for(int i=0;i<5;i++)
//        {
//            ThreadEx objThread = new ThreadEx("Thread-"+i);
//            objThread.start();
//        }
//        //Implementing the Runnable Interface
//        for(int i=0;i<5;i++)
//        {
//            Thread obj = new Thread(new ThreadEx1());
//            obj.start();
//        }
        //Implementing Syncronised work
//        for(int i=0;i<5;i++)
//        {
//            SyncEx obj = new SyncEx();
//            obj.start();
//        }

        //A thread pool reuses previously created threads to execute current tasks and offers a
        // solution to the problem of thread cycle overhead and resource thrashing.Since the thread is already existing when the request arrives, the delay introduced by thread creation is eliminated, making the application more responsive.

        //Java provides the Executor framework which is centered around the Executor interface, its sub-interface –ExecutorService and the class-ThreadPoolExecutor, which implements both of these interfaces. By using the executor, one only has to implement the Runnable objects and send them to the executor to execute.

        //Setting maximum size of pool
        //final int MAX_SIZE = 3;

        //Creating Runnable inteface Objects to run.
        /*Runnable objects[] = new Runnable[10];
        int n = 10;
        for(int i=0;i<9;i++)
        {
            objects[i] = new Factorial(n);
            n++;
        }
        //creating pool for 3 threads.
        ExecutorService pool = Executors.newFixedThreadPool(MAX_SIZE);
        for(int i=0;i<9;i++)
        {
            pool.execute(objects[i]);
        }
        pool.shutdown();
        */
        //Daemon thread is a low priority thread that runs in background to perform tasks such as
        // garbage collection.

        //Future And callable Implementation
        try
        {
            int n = 1;
            FutureTask[] factAns = new FutureTask[10];
            Callable[] factNums = new Callable[10];
            for(int i=0;i<9;i++)
            {
                factNums[i] = new Fact(n);
                n++;
                factAns[i] = new FutureTask(factNums[i]);
                Thread t = new Thread(factAns[i]);
                t.start();
            }

            for(int i=0;i<9;i++)
            {
                System.out.println(factAns[i].get());
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    public static void main(String[] args) {
        ThreadImp();
    }
}
