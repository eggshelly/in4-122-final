public class TestObj2 {
    public void print(){
        System.out.println("print from games mod");
    }

    public static void columnsTest()
    {
        Game c = new Columns("John");
        c.run();
    }

    public static void bejeweledTest()
    {
        Game b = new Bejeweled("John");
        b.run();
    }


    public static void main(String[] args){
//        System.out.println("aAAA");
//        TestObj t1 = new TestObj();
//        t1.print();
//        Test3 t = new Test3();
//        t.print();
//        columnsTest();

        bejeweledTest();

    }
}
