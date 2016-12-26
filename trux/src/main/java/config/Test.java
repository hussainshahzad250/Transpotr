package config;
import java.awt.EventQueue;
import java.lang.reflect.InvocationTargetException;


public class Test {
  public static void main(String[] args) throws InvocationTargetException, InterruptedException {
    try {
      EventQueue.invokeAndWait(new Runnable() {
        
        public void run() {
          throw new RuntimeException("exception");
        }
      });
    } catch (Exception e) {
      //ignore
    }
    EventQueue.invokeAndWait(
        new Runnable() {
          
          public void run() {
            System.out.println("EDT restarted");
          }
        }
    );
    System.out.println("All runnables finished");
  }
}