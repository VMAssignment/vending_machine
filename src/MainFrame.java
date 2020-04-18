import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MainFrame  extends JFrame {
//
//    private BufferedImage img01, img02, img03;
//
//    public void ImagePanel() {
//        try {
//            img01 = ImageIO.read(new File("icon1.png"));
//            img02 = ImageIO.read(new File("icon2.png"));
//            img03 = ImageIO.read(new File("icon3.png"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }


    //초기화, 설정
    public void home() {
        this.setTitle("자판기");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(420,500);
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
        itemPanel01.setLayout(new GridLayout(0,2));

        JTextField itemText01 = new JTextField("개수(1)", 5);
        itemPanel01.add(itemText01);

        itemPanel01.add(new JButton("추가"));
        entire.add(itemPanel01);


        //아이템, 개수, 추가 (2)
        JPanel itemPanel02 = new JPanel();
        itemPanel02.setLayout(new GridLayout(0,2));

        JTextField itemText02 = new JTextField("개수(2)", 5);
        itemPanel02.add(itemText02);

        itemPanel02.add(new JButton("추가"));
        entire.add(itemPanel02);


        //아이템, 개수, 추가 (3)
        JPanel itemPanel03 = new JPanel();
        itemPanel03.setLayout(new GridLayout(0,2));

        JTextField itemText03 = new JTextField("개수(3)", 5);
        itemPanel03.add(itemText03);

        itemPanel03.add(new JButton("추가"));
        entire.add(itemPanel03);


        //현재 금액, 시작
        JPanel BuyPanel = new JPanel();
        BuyPanel.setLayout(new BorderLayout(10,10));
        BuyPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        BuyPanel.setBackground(Color.WHITE);

        JLabel buyLabel = new JLabel("합계"); //합계 텍스트, 정렬
        buyLabel.setFont(new Font("맑은 고딕", Font.BOLD, 30));
        buyLabel.setHorizontalAlignment(JLabel.CENTER);
        BuyPanel.add(buyLabel, BorderLayout.NORTH);

        JTextField buyText = new JTextField("현재 금액",15);
        BuyPanel.add(buyText, BorderLayout.CENTER);
        BuyPanel.add(new JButton("시작"),BorderLayout.EAST);
        entire.add(BuyPanel);


        //합계, 구매
        JPanel PayPanel = new JPanel();
        PayPanel.setLayout(new BorderLayout(10,10));
        PayPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        PayPanel.setBackground(Color.WHITE);

        JLabel payLabel = new JLabel("합계"); //합계 텍스트, 정렬
        payLabel.setFont(new Font("맑은 고딕", Font.BOLD, 30));
        payLabel.setHorizontalAlignment(JLabel.CENTER);
        PayPanel.add(payLabel, BorderLayout.NORTH);

        JTextField payText = new JTextField("총 금액",15);
        PayPanel.add(payText, BorderLayout.CENTER);
        PayPanel.add(new JButton("구매"), BorderLayout.EAST);
        entire.add(PayPanel);


//        JPanel CoinPanel = new JPanel(); // 거스름돈 동전
//        entire.add(CoinPanel);
//            CoinPanel.setBackground(Color.BLACK);





        this.setVisible(true);
    }

    public static void main(String[] args){
        new MainFrame().on();
    }
}
