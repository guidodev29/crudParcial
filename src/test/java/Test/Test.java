package Test;


import Dominios.Producto;
import javax.persistence.EntityManager;
import org.apache.logging.log4j.*;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Test {
    static Logger log = LogManager.getRootLogger();
    
    public static void main(String[] args){
        crearProducto();
        //recuperarPorId();
        //actualizarProducto();
        //eliminarProducto();
    }
    
    private static void crearProducto(){        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("crudProducto");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Producto pro = new Producto(1,"Leche",4.50, "Salud",100);
        em.persist(pro);
        tx.commit();
        System.out.println("#### NUEVO PRODUCTO INGRESADO ####");
        System.out.println("ID: " + pro.getId());
        System.out.println("PRODUCTO: " + pro.getNombre());
        System.out.println("PRECIO: " + pro.getPrecio());
        System.out.println("PROVEEDOR: " + pro.getProveedor());
        System.out.println("STOCK: " + pro.getStock());

        em.close();
    }
    
    private static void recuperarPorId(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("crudProducto");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Producto pro = em.find(Producto.class, 2);
        tx.commit();
        if (pro != null) {
        System.out.println("####PRODUCTO ENCONTRADO####");
        System.out.println("ID: " + pro.getId());
        System.out.println("PRODUCTO: " + pro.getNombre());
        System.out.println("PRECIO: " + pro.getPrecio());
        System.out.println("PROVEEDOR: " + pro.getProveedor());
        System.out.println("STOCK: " + pro.getStock());
        
    } else {
        System.out.println("No se encontró ningún producto con ese ID");
    }
        em.close();
    }
    
    private static void actualizarProducto(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("crudProducto");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin(); //INICIA TRANSFERENCIA
        
        Producto pro = em.find(Producto.class, 4);
        tx.commit();//FINALIZA TRANSFERENCIA        
        System.out.println("####PRODUCTO ENCONTRADO####");
        System.out.println("ID: " + pro.getId());
        System.out.println("PRODUCTO: " + pro.getNombre());
        System.out.println("PRECIO: " + pro.getPrecio());
        System.out.println("PROVEEDOR: " + pro.getProveedor());
        System.out.println("STOCK: " + pro.getStock());
        
        pro.setNombre("Agua"); //MODIFICAMOS EL VALOR
        EntityTransaction tx2 = em.getTransaction(); //NUEVA TRANSACTION
        tx2.begin();// LA EMPEZAMOS
        
        em.merge(pro);// ELIMINAMOS EL REGISTRO ANTERIOR
        tx2.commit();
        System.out.println("####REGISTRO ACTUALIZADO ####");
        System.out.println("ID: " + pro.getId());
        System.out.println("PRODUCTO: " + pro.getNombre());
        System.out.println("PRECIO: " + pro.getPrecio());
        System.out.println("PROVEEDOR: " + pro.getProveedor());
        System.out.println("STOCK: " + pro.getStock());
        em.close();
        
    }
    
    private static void eliminarProducto(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("crudProducto");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        
        Producto pro = em.find(Producto.class, 7);
        tx.commit();     
        System.out.println("####REGISTRO ENCONTRADO ####");
        System.out.println("####PRODUCTO ENCONTRADO####");
        System.out.println("ID: " + pro.getId());
        System.out.println("PRODUCTO: " + pro.getNombre());
        System.out.println("PRECIO: " + pro.getPrecio());
        System.out.println("PROVEEDOR: " + pro.getProveedor());
        System.out.println("STOCK: " + pro.getStock());
        
        EntityTransaction tx2 = em.getTransaction(); 
        tx2.begin();
        
        em.remove(em.merge(pro));
        tx2.commit();
        System.out.println("####REGISTRO ELIMINADO ####");
        em.close();

    }
    
}
