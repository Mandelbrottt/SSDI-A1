package csci2020u.assignment1;

public class TestComponent2 extends Component {
    @Override
    public void onCreate() {
        System.out.println("TestComponent2 OnCreate()");
        testComponent3 = (TestComponent3) getRegistry().addComponent("TestComponent3");
    }

    @Override
    public void onUpdate() {
        a += testComponent3.GetA();
        System.out.printf("TestComponent2 OnUpdate()\n\t- TestComponent2::a = %d\n", a);
    }

    @Override
    public void onDestroy() {
        System.out.printf("TestComponent2 OnDestroy()\n\t- TestComponent2::a = %d\n", a);
        testComponent3 = null;
    }

    private int a = 0;

    TestComponent3 testComponent3;
}
