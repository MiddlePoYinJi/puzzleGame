import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;


//这个类表示游戏的主界面
//和游戏相关的所有逻辑都写在这里
public class GameJFrame extends JFrame implements KeyListener, ActionListener {
    Random rand = new Random();
    int[][] data = new int[4][4];

    int[][] win = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 0}};

    //定义一个变量计算步数
    int step;

    //记录空白方块在二维数组中的位置
    int x = 0;
    int y = 0;

    //定义一个变量，记录当前展示图片的位置
    String path = "/Users/ly/IdeaProjects/Game/image/animal/animal3/";

    //创建选项下面的条目对象
    JMenuItem replayItem = new JMenuItem("重新游戏");
    JMenuItem reLoginItem = new JMenuItem("重新登陆");
    JMenuItem closeItem = new JMenuItem("关闭游戏");
    JMenuItem accountItem = new JMenuItem("公众号");
    JMenuItem girl = new JMenuItem("美女");
    JMenuItem animal = new JMenuItem("动物");
    JMenuItem sport = new JMenuItem("运动");

    public GameJFrame() {

        //初始化主界面
        initJFrame();

        //初始化菜单
        iniJMenuBar();

        //initData -> 打乱图片
        initData();

        //初始化图片
        initImage();


        //显示代码建议写在最后
        this.setVisible(true);
    }

    private void initData() {
        Random rand = new Random();
        int[] tempArr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};

        for (int i = 0; i < tempArr.length; i++) {
            int index = rand.nextInt(15);
            int num = tempArr[i];
            tempArr[i] = tempArr[index];
            tempArr[index] = num;
        }

        int index = 0;

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (tempArr[index] == 0) {
                    x = i;
                    y = j;
                }
                data[i][j] = tempArr[index];
                index++;

            }
        }

//        for(int i = 0 ; i < tempArr.length ; i ++) {
//            data[i / 4][i % 4] = tempArr[i];
//        }

    }


    //初始化图片
    //拼图游戏里面有15张图，分布在四行四列
    //且已知每一行的Y轴都加105，每一行的图片的X轴也都加105
    //例如第一行第一张（0，0），第一行第二张（105，0）
    //第二行第二张（210，105）
    //所以可以用双重循环
    private void initImage() {
        //清空原本已经出现的所有图片
        this.getContentPane().removeAll();

        if (victory()) {
            JLabel winJLabel = new JLabel(new ImageIcon("/Users/ly/IdeaProjects/Game/image/win.png"));
            winJLabel.setBounds(203, 283, 197, 73);
            this.getContentPane().add(winJLabel);
        }

        //统计步数
        JLabel stepJLabel = new JLabel("步数" + step);
        stepJLabel.setBounds(50, 30, 100, 20);
        this.getContentPane().add(stepJLabel);
        //先加载的图片在上方，后加载的图片在下方
        //所以背景图片要在所有图片添加完后再进行添加
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                JLabel jLabel = new JLabel(new ImageIcon(path + data[i][j] + ".jpg"));
                jLabel.setBounds(105 * j + 83, 105 * i + 134, 105, 105);

                //给图片添加边框
                jLabel.setBorder(new BevelBorder(BevelBorder.LOWERED));
                this.getContentPane().add(jLabel);
            }
        }

        //添加背景图片
        JLabel background = new JLabel(new ImageIcon("/Users/ly/IdeaProjects/Game/image/background.png"));
        background.setBounds(40, 40, 508, 560);
        //将背景图片添加到界面当中
        this.getContentPane().add(background);

        //刷新一下界面
        this.getContentPane().repaint();


    }


    private void iniJMenuBar() {
        //初始化菜单
        //创建整个菜单对象
        JMenuBar jMenuBar = new JMenuBar();

        //创建菜单上面的两个选项的菜单（功能 关于我们）
        JMenu functionJMenu = new JMenu("功能");
        JMenu aboutJMenu = new JMenu("关于我们");
        JMenu changeImage = new JMenu("更换图片");


        changeImage.add(girl);
        changeImage.add(animal);
        changeImage.add(sport);

        //将每一个选项下面的条目添加到选项中
        functionJMenu.add(replayItem);
        functionJMenu.add(reLoginItem);
        functionJMenu.add(closeItem);
        functionJMenu.add(changeImage);

        aboutJMenu.add(accountItem);

        //给条目添加事件
        replayItem.addActionListener(this);
        reLoginItem.addActionListener(this);
        closeItem.addActionListener(this);
        accountItem.addActionListener(this);
        girl.addActionListener(this);
        animal.addActionListener(this);
        sport.addActionListener(this);

        //将菜单里面的两个选项添加到菜单当中
        jMenuBar.add(functionJMenu);
        jMenuBar.add(aboutJMenu);

        //给整个界面设置一个菜单
        this.setJMenuBar(jMenuBar);
    }

    private void initJFrame() {
        this.setSize(603, 680);

        //设置界面的标题
        this.setTitle("拼图单机版 v1.0");
        //设置界面置顶
        this.setAlwaysOnTop(true);
        //设置界面居中
        this.setLocationRelativeTo(null);
        //设置关闭模式
        //点击关闭后控制台一起关闭
        this.setDefaultCloseOperation(3);

        //取消默认的居中方式，只有取消了才会将图片按照XY轴的形式添加组件
        this.setLayout(null);

        //给整个界面添加键盘监听事件
        this.addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    //按下不松时会调用这个方法
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == 65) {
            this.getContentPane().removeAll();
            JLabel jLabel = new JLabel(new ImageIcon(path + "all.jpg"));
            jLabel.setBounds(83, 134, 420, 420);
            this.getContentPane().add(jLabel);

            //添加背景图片
            JLabel background = new JLabel(new ImageIcon("/Users/ly/IdeaProjects/Game/image/background.png"));
            background.setBounds(40, 40, 508, 560);
            //将背景图片添加到界面当中
            this.getContentPane().add(background);

            //刷新界面
            this.getContentPane().repaint();


        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        //判断游戏是否胜利，如果胜利，此方法需要直接结束，不能再执行移动操作了
        if (victory()) {
            return;
        }
        //对上下左右进行判断
        //左：37，右：39，上：38，下：40
        int code = e.getKeyCode();
        if (code == 37) {
            if (y == 3) {
                return;
            }
            System.out.println("向左移动");
            data[x][y] = data[x][y + 1];
            data[x][y + 1] = 0;
            y++;
            step++;
            initImage();
        } else if (code == 38) {
            System.out.println("向上移动");
            //表示空白方块已经在最下方了，下方已经没有图片可以移动了
            if (x == 3) {
                return;
            }
            //将空白方块下方的数字往上移动
            //x,y表示空白方块
            //x + 1,y表示空白方块下方的数字
            data[x][y] = data[x + 1][y];
            data[x + 1][y] = 0;
            //交换完位置后，空白方块的索引也要变
            x++;
            step++;

            //调用方法，按照最新的数字去添加图片
            initImage();

        } else if (code == 39) {
            System.out.println("向右移动");
            if (y == 0) {
                return;
            }
            data[x][y] = data[x][y - 1];
            data[x][y - 1] = 0;
            y--;
            step++;
            initImage();
        } else if (code == 40) {
            System.out.println("向下移动");
            if (x == 0) {
                return;
            }
            data[x][y] = data[x - 1][y];
            data[x - 1][y] = 0;
            x--;
            step++;
            initImage();
        } else if (code == 65) {
            initImage();
            //按住w作弊
            //将数据调为正常的再重新初始化图片即可
        } else if (code == 87) {
            data = new int[][]{
                    {1, 2, 3, 4},
                    {5, 6, 7, 8},
                    {9, 10, 11, 12},
                    {13, 14, 15, 0}};
            initImage();
        }
    }

    //如果win和data里面的内容相同，返回true，反之，返回false
    public boolean victory() {

        //有一个数据不一样，直接false
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                if (data[i][j] != win[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //当前被点击的条目对象
        Object obj = e.getSource();

        if (obj == replayItem) {
            System.out.println("重新开始游戏");
            //计步器清零
            step = 0;
            //再次打乱二维数组中的数据
            initData();
            //重新加载图片
            initImage();

        } else if (obj == reLoginItem) {
            System.out.println("重新登录");
            //关闭当前的游戏界面
            this.setVisible(false);
            //打开登陆界面
            new LoginJFrame();

        } else if (obj == closeItem) {
            System.out.println("关闭游戏");
            //直接关闭虚拟机
            System.exit(0);
        } else if (obj == accountItem) {
            System.out.println("公众号");
            //创建一个弹框对象
            JDialog jDialog = new JDialog();
            //创建一个管理图片的容器对象JLabel
            JLabel jLabel = new JLabel(new ImageIcon("/Users/ly/IdeaProjects/Game/image/about.png"));
            //设置位置和宽高
            jLabel.setBounds(0, 0, 258, 258);
            //把图片添加到弹框中
            jDialog.getContentPane().add(jLabel);
            jDialog.setSize(344, 344);
            jDialog.setAlwaysOnTop(true);
            jDialog.setLocationRelativeTo(null);
            //弹窗不关闭无法进行下面的操作
            jDialog.setModal(true);
            jDialog.setVisible(true);
        } else if (obj == girl) {
            step = 0;
            int index = rand.nextInt(13) + 1;
            path = "/Users/ly/IdeaProjects/Game/image/girl/girl" + index + "/";
            initData();
            initImage();
        } else if (obj == animal) {
            step = 0;
            int index = rand.nextInt(8) + 1;
            path = path = "/Users/ly/IdeaProjects/Game/image/animal/animal" + index + "/";
            initData();
            initImage();
        } else if (obj == sport) {
            step = 0;
            int index = rand.nextInt(10) + 1;
            path = path = "/Users/ly/IdeaProjects/Game/image/sport/sport" + index + "/";
            initData();
            initImage();
        }
    }
}
