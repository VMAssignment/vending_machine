import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Scanner;

public class MainFrame  extends JFrame {

    //초기화, 설정
    public void home() {
        this.setTitle("자판기");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(450,520);
        this.setResizable(false);
        Dimension frameSize = this.getSize();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((screenSize.width - frameSize.width)/2, (screenSize.height - frameSize.height)/2);
    }


    public void on(){
        home(); //초기화

        //전체
        JPanel entire = new JPanel(new FlowLayout(1, 10, 20));
        this.add(entire);

        //아이콘 추가
        for(int i = 1; i <4 ; i++){
            ImageIcon img1 = new ImageIcon("src\\icon" + i + ".png");
            Image originImg = img1.getImage();
            Image changedImg = originImg.getScaledInstance(120,120, Image.SCALE_SMOOTH);
            ImageIcon icon = new ImageIcon(changedImg);
            JLabel food = new JLabel(icon);
            entire.add(food);
        }


        //아이템, 개수, 추가 (1)
        JPanel itemPanel01 = new JPanel();
        itemPanel01.setLayout(new BorderLayout());

        JTextField itemText01 = new JTextField("개수(1)", 5);
        itemPanel01.add(itemText01, BorderLayout.CENTER);
        itemPanel01.add(new JLabel("50원"), BorderLayout.NORTH);
        itemPanel01.add(new JButton("추가"),  BorderLayout.EAST);
        entire.add(itemPanel01);


        //아이템, 개수, 추가 (2)
        JPanel itemPanel02 = new JPanel();
        itemPanel02.setLayout(new BorderLayout());

        JTextField itemText02 = new JTextField("개수(2)", 5);
        itemPanel02.add(itemText02, BorderLayout.CENTER);
        itemPanel02.add(new JLabel("100원"), BorderLayout.NORTH);
        itemPanel02.add(new JButton("추가"),  BorderLayout.EAST);
        entire.add(itemPanel02);


        //아이템, 개수, 추가 (3)
        JPanel itemPanel03 = new JPanel();
        itemPanel03.setLayout(new BorderLayout());

        JTextField itemText03 = new JTextField("개수(3)", 5);
        itemPanel03.add(itemText03, BorderLayout.CENTER);
        itemPanel03.add(new JLabel("200원"), BorderLayout.NORTH);
        itemPanel03.add(new JButton("추가"),  BorderLayout.EAST);
        entire.add(itemPanel03);

        entire.add(itemPanel03);


        //현재 금액
        JPanel BuyPanel = new JPanel();
        BuyPanel.setLayout(new BorderLayout(5,10));
        BuyPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        BuyPanel.setBackground(Color.WHITE);

        JLabel buyLabel = new JLabel("투입금"); //투입금 텍스트
        buyLabel.setFont(new Font("맑은 고딕", Font.BOLD, 30));
        buyLabel.setHorizontalAlignment(JLabel.CENTER);
        BuyPanel.add(buyLabel, BorderLayout.NORTH);

        JTextField buyText = new JTextField("현재 금액",10);
        BuyPanel.add(buyText, BorderLayout.CENTER);
        BuyPanel.add(new JButton("시작"),BorderLayout.EAST);

        entire.add(BuyPanel);


        //구매
        JPanel PayPanel = new JPanel();
        PayPanel.setLayout(new BorderLayout(5,10));
        PayPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        PayPanel.setBackground(Color.WHITE);

        JLabel payLabel = new JLabel("합계금"); //텍스트, 정렬
        payLabel.setFont(new Font("맑은 고딕", Font.BOLD, 30));
        payLabel.setHorizontalAlignment(JLabel.CENTER);
        PayPanel.add(payLabel, BorderLayout.NORTH);

        JTextField payText = new JTextField("총 금액",10);
        PayPanel.add(payText, BorderLayout.CENTER);
        PayPanel.add(new JButton("구매"), BorderLayout.EAST);

        entire.add(PayPanel);


        //거스름돈 동전
        JPanel ChangePanel = new JPanel(new BorderLayout(5,10));
        ChangePanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel changeLabel = new JLabel("거스름돈"); //텍스트, 정렬
        changeLabel.setFont(new Font("맑은 고딕", Font.BOLD, 30));
        changeLabel.setHorizontalAlignment(JLabel.CENTER);
        ChangePanel.add(changeLabel, BorderLayout.NORTH);

        JPanel CoinPanel = new JPanel(new GridLayout(2,5, 6,3));

        String coinValue[] = {"10", "50", "100", "160", "500"};
        for(int i = 0; i<5; i++){CoinPanel.add(new JLabel(coinValue[i]+"원", JLabel.CENTER));}

        JTextField coin10 = new JTextField("0개",3);
        JTextField coin50 = new JTextField("0개",3);
        JTextField coin100 = new JTextField("0개",3);
        JTextField coin160 = new JTextField("0개",3);
        JTextField coin500 = new JTextField("0개",3);
        CoinPanel.add (coin10); CoinPanel.add (coin50); CoinPanel.add (coin100); CoinPanel.add (coin160); CoinPanel.add (coin500);

        ChangePanel.add(CoinPanel, BorderLayout.CENTER);
        entire.add(ChangePanel);

        this.setVisible(true);
    }


    public static class item{
        String name;
        int cost;
        int amount;
    }//item 객체

    public static void main(String[] args){
        new MainFrame().on();
        Scanner scan = new Scanner(System.in);
        int[][] coin = {{500,0}, {160,0}, {100,0}, {50,0}, {10,0}}; // 동전의 종류, 사용된 갯수

        item item1 = new item();
        item1.name="Cake";
        item1.cost=50;
        item1.amount=0;

        item item2 = new item();
        item2.name="Drink";
        item2.cost=100;
        item2.amount=0;

        item item3 = new item();
        item3.name="Doughnut";
        item3.cost=200;
        item3.amount=0;

        int cash=1000;
        //소비자가 가지고있는 초기 자본

        int charge=cash-(item1.cost*item1.amount)-(item2.cost*item2.amount)-(item3.cost*item3.amount);
        //charge -> 현재 금액

        int temp = charge;

        for (int i = 0; i < coin.length; i++) {
            while(true) {
                if (temp >= coin[i][0]) {
                    temp -= coin[i][0];
                    coin[i][1]++;
                }
                else break;//해당 동전을 최대한 사용
            }
        }
    }
}
