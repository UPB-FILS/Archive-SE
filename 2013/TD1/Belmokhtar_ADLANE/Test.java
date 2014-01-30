public class Test {
  public static void main(String[] args) {
    TestThread t = new TestThread("Ilyas");
    TestThread t2 = new TestThread(" Adlane");
    t.start();
    t2.start();
  }
}