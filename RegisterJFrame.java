import javax.swing.*;

public class RegisterJFrame extends JFrame {
    public RegisterJFrame() {
        this.setSize(488,500);
        this.setVisible(true);

        //设置界面的标题
        this.setTitle("注册");
        //设置界面置顶
        this.setAlwaysOnTop(true);
        //设置界面居中
        this.setLocationRelativeTo(null);
        //设置关闭模式
        //点击关闭后控制台一起关闭
        this.setDefaultCloseOperation(3);

        //显示代码建议写在最后
        this.setVisible(true);
    }
}
