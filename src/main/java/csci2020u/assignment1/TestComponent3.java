package csci2020u.assignment1;

public class TestComponent3 extends Component {
    public void Init(int a) {
        this.a = a;
    }
    
    @Override
    public void onCreate() {
        System.out.println("TestComponent3 OnCreate()");
    }

    @Override
    public void onUpdate() {
        a += 5;
        System.out.printf("TestComponent3 OnUpdate()\n\t- TestComponent3::a = %d\n", a);
    }

    @Override
    public void onDestroy() {
        System.out.printf("TestComponent3 OnDestroy()\n\t- TestComponent3::a = %d\n", a);
    }

    public int GetA() {
        return a;
    }

    private int a = 0;
}
