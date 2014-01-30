public class TestThread extends Thread {
  
	public TestThread(String name){
    
		super(name);
  }
  public void run(){
    for(int i = 0; i < 10; i++)
      System.out.println(this.getName());
  }      
}