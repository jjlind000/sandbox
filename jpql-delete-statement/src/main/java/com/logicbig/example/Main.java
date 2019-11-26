package com.logicbig.example;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.LongConsumer;

class X implements Runnable{

    private Object lock = new Object();

    private final EntityManager em;

    private LinkedBlockingQueue<Object> taskQ = new LinkedBlockingQueue<>();

    public EntityManager getEm()
    {
        return em;
    }

    public Queue getTaskQ()
    {
        return taskQ;
    }

    public X(Object lock, EntityManager em ){
        this.lock = lock;
        this.em = em;
    }

    public void run(){
        Main.p("running");
        //synchronized (lock){}
        while(true){
            try
            {
                Main.p("waiting for command..");
                final String commandToken = (String) taskQ.take();
                Main.p(String.format("received %s",commandToken));
                switch(commandToken){
                    case "LongConsumer":
                        Main.p("doing LongConsumer");
                        final LongConsumer command = (LongConsumer) taskQ.take();
                        final Long arg = (Long) taskQ.take();
                        //doIt(command, arg);
                    default:
                        Main.p("not doing it");
                }
                Main.p("done command..");

            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }

    public void doIt(LongConsumer c, long millis){
        c.accept((millis));
    }

    public void doIt(Consumer c, EntityManager em){
        c.accept((em));
    }

    public void doIt(BiConsumer c, Thread t, Long l){
        c.accept(t, l);
    }
}

public class Main
{
    private static Object LOCK = new Object();

    private static EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("example-unit");
    private static EntityManager em = emf.createEntityManager();

    public static void p(String msg){
        System.out.println(String.format("%s == %s: %s", LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss.SSS")), Thread.currentThread().getName(), msg));
    }

    public static BiConsumer<Thread,Long> doSleep = (Thread t, Long l) -> {
        try
        {
            p(String.format("Sleeping for %dms", l));
            t.sleep(l);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    };

    public static LongConsumer doWait = l -> {
        try
        {
            p(String.format("Sleeping for %dms", l));
            synchronized (Main.LOCK){
                LOCK.wait(l);
            }
            p(String.format("Out of sleep", l));
        }
        catch (InterruptedException e) {
            p(e.getMessage());
        }
    };

    public static Consumer<EntityManager> findAllEmployees = em -> {
        p("-- All employees --");
        Query query = em.createQuery(
                "SELECT e FROM Employee e");
        List<Employee> resultList = query.getResultList();
        p("Employees count: "+resultList.size());
        resultList.forEach(emp-> p("....employee: " + emp));
    };

    public static Consumer<EntityManager> persistEmployees = em -> {
        Department it = new Department("IT");
        Department admin = new Department("Admin");
        Department sales = new Department("Sales");
        Employee employee1 = Employee.create("Diana", 2000, it);
        Employee employee2 = Employee.create("Rose", 3500, admin);
        Employee employee3 = Employee.create("Denise", 2500, admin);
        Employee employee4 = Employee.create("Mike", 4000, it);
        Employee employee5 = Employee.create("Linda", 4500, sales);
        em.getTransaction().begin();
        em.persist(it);
        em.persist(admin);
        em.persist(sales);
        em.persist(employee1);
        em.persist(employee2);
        em.persist(employee3);
        em.persist(employee4);
        em.persist(employee5);
        //em.getTransaction().commit();
        //em.close();
    };


    public static Consumer<EntityManager> commit = em -> {
        p("-- Committing --");
        em.getTransaction().commit();
        p("-- Committed --");
    };


    public static void main(String[] args) {

        p("Start test...");
        final X x1 = new X(LOCK, em);
        final X x2 = new X(LOCK, em);
        Thread t1 = new Thread(x1, "t1");
        Thread t2 = new Thread(x2, "t2");
        t1.start();
        t2.start();
        x1.getTaskQ().add("LongConsumer");
        x1.getTaskQ().add(doSleep);
        x1.getTaskQ().add(new Long(2000));
/*
        //x1 verify 0 employees at start
        x1.doIt(findAllEmployees, x1.getEm());
        //x1 sleep
        x1.doIt(doWait, 10000);
        //x2 sleep
        x1.doIt(doWait, 2000);
        //x2 persist
        x2.doIt(persistEmployees, x2.getEm());
        //x1 find employees
        x1.doIt(findAllEmployees, x1.getEm());

        //x1.doIt(doSleep, 5000);
*/

        p("Closing emf");
        emf.close();
        p("emf closed");

/*
        if(1==0)
        try {
            persistEmployees(em);
            findAllEmployees();
            findAllDepartments();
            deleteEmployeeByName();
            findAllEmployees();
            deleteAllEmployees2();
            findAllEmployees();
            findAllDepartments();
        } finally {
            emf.close();
        }
*/
    }




    public static void persistEmployees(EntityManager em) {
        Department it = new Department("IT");
        Department admin = new Department("Admin");
        Department sales = new Department("Sales");

        Employee employee1 = Employee.create("Diana", 2000, it);
        Employee employee2 = Employee.create("Rose", 3500, admin);
        Employee employee3 = Employee.create("Denise", 2500, admin);
        Employee employee4 = Employee.create("Mike", 4000, it);
        Employee employee5 = Employee.create("Linda", 4500, sales);
        //EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(it);
        em.persist(admin);
        em.persist(sales);
        em.persist(employee1);
        em.persist(employee2);
        em.persist(employee3);
        em.persist(employee4);
        em.persist(employee5);
        em.getTransaction().commit();
        em.close();
    }

    private static void findAllEmployees(EntityManager em) {
        p("-- All employees --");
        Query query = em.createQuery(
                "SELECT e FROM Employee e");
        List<Employee> resultList = query.getResultList();
        System.out.println("Employees count: "+resultList.size());
        resultList.forEach(System.out::println);
        //em.close();
    }

    private static void findAllDepartments() {
        EntityManager em = emf.createEntityManager();
        System.out.println("-- All departments --");
        Query query = em.createQuery(
                "SELECT d from Department d");
        List<Department> resultList = query.getResultList();
        System.out.println("Dept count: "+resultList.size());
        resultList.forEach(System.out::println);
        em.close();
    }

    private static void deleteEmployeeByName() {
        System.out.println("-- delete employee by name 'Mike' --");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("DELETE FROM Employee e WHERE e.name = :employeeName ");
        query.setParameter("employeeName", "Mike");
        int rowsDeleted = query.executeUpdate();
        System.out.println("entities deleted: " + rowsDeleted);
        em.getTransaction().commit();
        em.close();
    }

    private static void deleteAllEmployees() {
        System.out.println("-- delete all employees --");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("DELETE FROM Employee e ");
        int rowsDeleted = query.executeUpdate();
        System.out.println("entities deleted: " + rowsDeleted);
        em.getTransaction().commit();
        em.close();
    }

    private static void deleteAllEmployees2() {
        System.out.println("-- delete all employees --");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("DELETE FROM Employee e where e.id in (select e.id from Department d, Employee e where d.name = 'IT' and d.id = e.dept.id)");
        //DELETE FROM AuthAccess aa WHERE aa.id in (SELECT aa.id FROM Session s, AuthAccess aa WHERE s.customer.id = :customerId AND s.id = aa.sessionId)
        int rowsDeleted = query.executeUpdate();
        System.out.println("entities deleted: " + rowsDeleted);
        em.getTransaction().commit();
        em.close();
    }
}