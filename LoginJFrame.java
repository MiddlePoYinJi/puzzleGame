import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

//public class LoginJFrame extends JFrame {
//    ArrayList<User> list = new ArrayList<>();
//
//
//    public LoginJFrame() {
//        this.setSize(488,430);
//        this.setVisible(true);
//
//        //设置界面的标题
//        this.setTitle("登陆");
//        //设置界面置顶
//        this.setAlwaysOnTop(true);
//        //设置界面居中
//        this.setLocationRelativeTo(null);
//        //设置关闭模式
//        //点击关闭后控制台一起关闭
//        this.setDefaultCloseOperation(3);
//
//        initView();
//
//        //显示代码建议写在最后
//        this.setVisible(true);
//    }
//
//    public void initView() {
//        JLabel usernameLabel = new JLabel(new ImageIcon("/Users/ly/IdeaProjects/Game/image/login/用户名.png"));
//        usernameLabel.setBounds(116,135,47,17);
//        this.getContentPane().add(usernameLabel);
//
//        JTextField username = new JTextField();
//        username.setBounds(195,134,200,30);
//        this.getContentPane().add(username);
//
//        JLabel passwordLabel = new JLabel(new ImageIcon("/Users/ly/IdeaProjects/Game/image/login/密码.png"));
//        passwordLabel.setBounds(130,195,32,16);
//        this.getContentPane().add(passwordLabel);
//
//        JTextField password = new JTextField();
//        password.setBounds(195,195,200,30);
//        this.getContentPane().add(password);
//
//        JLabel codeText = new JLabel(new ImageIcon("/Users/ly/IdeaProjects/Game/image/login/验证码.png"));
//        codeText.setBounds(133,256,50,30);
//        this.getContentPane().add(codeText);
//
//        JTextField code = new JTextField();
//        code.setBounds(195,256,100,30);
//        this.getContentPane().add(code);
//
//        String codeStr = CodeUtil.getCode();
//        JLabel rightCode = new JLabel();
//        rightCode.setText(codeStr);
//        rightCode.setBounds(300,256,50,30);
//        this.getContentPane().add(rightCode);
//
//
//    }
//}


public class LoginJFrame extends JFrame implements MouseListener {
    //创建一个集合存储正确的用户名和密码
    static ArrayList<User> list = new ArrayList<>();

    static {
        list.add(new User("zhangsan", "123"));
        list.add(new User("lisi", "1234"));
    }

    JTextField username = new JTextField();
    JLabel rightCode = new JLabel();
    JTextField code = new JTextField();
    JButton login = new JButton();
    JTextField password = new JTextField();


    public LoginJFrame() {
        //初始化界面
        initJFrame();

        //在这个界面中添加内容
        initView();

        //让当前界面显示出来
        this.setVisible(true);
    }

    public void initView() {
        //1. 添加用户名文字
        JLabel usernameText = new JLabel(new ImageIcon("/Users/ly/IdeaProjects/Game/image/login/用户名.png"));
        usernameText.setBounds(116, 135, 47, 17);
        this.getContentPane().add(usernameText);

        //2.添加用户名输入框
        username.setBounds(195, 134, 200, 30);
        this.getContentPane().add(username);

        //3.添加密码文字
        JLabel passwordText = new JLabel(new ImageIcon("/Users/ly/IdeaProjects/Game/image/login/密码.png"));
        passwordText.setBounds(130, 195, 32, 16);
        this.getContentPane().add(passwordText);

        //4.密码输入框

        password.setBounds(195, 195, 200, 30);
        this.getContentPane().add(password);

        //验证码提示
        JLabel codeText = new JLabel(new ImageIcon("/Users/ly/IdeaProjects/Game/image/login/验证码.png"));
        codeText.setBounds(133, 256, 50, 30);
        this.getContentPane().add(codeText);

        //验证码的输入框

        code.setBounds(195, 256, 100, 30);
        this.getContentPane().add(code);

        String codeStr = CodeUtil.getCode();

        //设置内容
        rightCode.setText(codeStr);
        //位置和宽高
        rightCode.setBounds(300, 256, 50, 30);
        //添加到界面
        this.getContentPane().add(rightCode);

        //5.添加登录按钮

        login.setBounds(123, 310, 128, 47);
        login.setIcon(new ImageIcon("/Users/ly/IdeaProjects/Game/image/login/登录按钮.png"));
        //去除按钮的默认边框
        login.setBorderPainted(false);
        //去除按钮的默认背景
        login.setContentAreaFilled(false);
        login.addMouseListener(this);
        this.getContentPane().add(login);


        //6.添加注册按钮
        JButton register = new JButton();
        register.setBounds(256, 310, 128, 47);
        register.setIcon(new ImageIcon("/Users/ly/IdeaProjects/Game/image/login/注册按钮.png"));
        //去除按钮的默认边框
        register.setBorderPainted(false);
        //去除按钮的默认背景
        register.setContentAreaFilled(false);
        this.getContentPane().add(register);

        //7.添加背景图片
        JLabel background = new JLabel(new ImageIcon("/Users/ly/IdeaProjects/Game/image/login/background.png"));
        background.setBounds(0, 0, 470, 390);
        this.getContentPane().add(background);
    }


    public void initJFrame() {
        this.setSize(488, 430);//设置宽高
        this.setTitle("拼图游戏 V1.0登录");//设置标题
        this.setDefaultCloseOperation(3);//设置关闭模式
        this.setLocationRelativeTo(null);//居中
        this.setAlwaysOnTop(true);//置顶
        this.setLayout(null);//取消内部默认布局
    }


    //要展示用户名或密码错误
    public void showJDialog(String content) {
        //创建一个弹框对象
        JDialog jDialog = new JDialog();
        //给弹框设置大小
        jDialog.setSize(200, 150);
        //让弹框置顶
        jDialog.setAlwaysOnTop(true);
        //让弹框居中
        jDialog.setLocationRelativeTo(null);
        //弹框不关闭永远无法操作下面的界面
        jDialog.setModal(true);

        //创建Jlabel对象管理文字并添加到弹框当中
        JLabel warning = new JLabel(content);
        warning.setBounds(0, 0, 200, 150);
        jDialog.getContentPane().add(warning);

        //让弹框展示出来
        jDialog.setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Object obj = e.getSource();

        if(obj == login) {
            System.out.println("点击了登录按钮");
            String usernameInput = username.getText();
            String passwordInput = password.getText();
            String codeInput = code.getText();

            User user = new User(usernameInput,passwordInput);
            System.out.println("输入的用户名:" + usernameInput);
            System.out.println("输入的密码" + passwordInput);

            if(codeInput.isEmpty()) {
                System.out.println("验证码不能为空");
            } else if(usernameInput.isEmpty() || passwordInput.isEmpty()) {
                System.out.println("账号或者密码为空");

                showJDialog("账号或密码为空");
            } else if(!codeInput.equalsIgnoreCase(rightCode.getText())) {
                showJDialog("验证码错误");
            } else if(contains(user)) {
                System.out.println("登录成功，可以开始玩游戏了");
                this.setVisible(false);
                new GameJFrame();
            } else {
                showJDialog("不存在");
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public boolean contains(User user) {

        for(int i = 0 ; i < list.size() ; i ++) {
            if(user.getUsername().equals(list.get(i).getUsername()) && user.getPassword().equals(list.get(i).getPassword()) )
                return true;
        }
        return false;
    }
}