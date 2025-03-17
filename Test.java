import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Test {
    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
        jFrame.setSize(603,680);
        jFrame.setTitle("事件演示");
        jFrame.setAlwaysOnTop(true);
        jFrame.setLocationRelativeTo(null);

        jFrame.setDefaultCloseOperation(3);

        jFrame.setLayout(null);

        //创建一个按钮对象
        JButton jbt = new JButton("点我呀");
        //设置位置和宽高
        jbt.setBounds(0,0,100,50);
        //给按钮添加动作监听
        //jtb：组件对象，表示你要给哪个组件添加事件
        //addActionListener:表示我要给组件添加哪个事件监听
        //（动作监听鼠标左键点击，空格）
        //ActionListener是一个接口，所以需要一个类先去实现(MyActionListener)
        //或者使用匿名内部类
        //参数：表示事件触发后要执行的代码
        jbt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("按钮被点击了");
            }
        });

        //把按钮添加到整个界面中
        jFrame.getContentPane().add(jbt);


        jFrame.setVisible(true);
    }
}
