package csci2020u.assignment1;

public class TestComponent1 extends Component {
    @Override
    public void onCreate() {
        System.out.println("TestComponent1 OnCreate()");
    }
    
    @Override
    public void onUpdate() {
        System.out.println("TestComponent1 OnUpdate()");
    }
    
    @Override
    public void onDestroy() {
        System.out.println("TestComponent1 OnDestroy()");
    }
}
