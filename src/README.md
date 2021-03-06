자판기 - Vending Machine

조원 : 이금주, 박세영, 석재호

------

**Swing과 ActionEvent를 위해 import**

```java
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
```

------

**각 품목을 설명하는 class 생성**

```java
static class item {
        int cost;
        int amount;
}
```

------

**기본 설정**

```java
public void home() {
        this.setTitle("자판기");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(450,520);
        this.setResizable(false);
        Dimension frameSize = this.getSize();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((screenSize.width - frameSize.width)/2, (screenSize.height - frameSize.height)/2);
    }
```

------

**사용되는 변수들**

```java
		int[][] coin = {{500,0}, {100,0}, {50,0}, {10,0}}; // 동전의 종류, 사용된 갯수

        item item1 = new item();
        item1.cost=50;
        item1.amount=0;

        item item2 = new item();
        item2.cost=100;
        item2.amount=0;

        item item3 = new item();
        item3.cost=200;
        item3.amount=0;

        String[] cash = {"현재 금액"};
```

------

**TextField 설정**

```java
		JTextField itemText01 = new JTextField(Integer.toString(item1.amount), 5);
        JTextField itemText02 = new JTextField(Integer.toString(item2.amount), 5);
        JTextField itemText03 = new JTextField(Integer.toString(item3.amount), 5);
        JTextField buyText = new JTextField(cash[0],10);
        JTextField payText = new JTextField("총 금액",10);

        JTextField coin10 = new JTextField("0개",3);
        JTextField coin50 = new JTextField("0개",3);
        JTextField coin100 = new JTextField("0개",3);
        JTextField coin500 = new JTextField("0개",3);
```

------

**품목 관련 버튼과 각각의 ActionEvent**

```java
JButton item1B = new JButton("추가");
        item1B.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                item1.amount++;
                itemText01.setText(Integer.toString(item1.amount));
            }
        });
        //1번 품목에 대해서 수량 조절 버튼및 작동

        JButton item2B = new JButton("추가");
        item2B.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                item2.amount++;
                itemText02.setText(Integer.toString(item2.amount));
            }
        });
        //2번 품목에 대해서 수량 조절 버튼및 작동

        JButton item3B = new JButton("추가");
        item3B.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                item3.amount++;
                itemText03.setText(Integer.toString(item3.amount));
            }
        });
		//3번 품목에 대해서 수량 조절 버튼및 작동
```

------

**투입금 입력및 합계금 출력의 버튼과 ActionEvent**

```java
		JButton cashB = new JButton("시작");
        cashB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
               cash[0] =buyText.getText();
            }
        });

        JButton chargeB = new JButton("구매");
        chargeB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int charge = Integer.parseInt(cash[0]) -(item1.cost*item1.amount)-(item2.cost*item2.amount)-(item3.cost*item3.amount);
                payText.setText(Integer.toString(charge));
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
                coin10.setText(Integer.toString(coin[3][1])+"개");
                coin50.setText(Integer.toString(coin[2][1])+"개");
                coin100.setText(Integer.toString(coin[1][1])+"개");
                coin500.setText(Integer.toString(coin[0][1])+"개");
                //coin들 개수 알려주기
            }
        });
```

------

**전체 틀**

```java
JPanel entire = new JPanel(new FlowLayout(1, 10, 20));
this.add(entire);
```

------

**품목의 icon 추가**

```java
for(int i = 1; i <4 ; i++){
            ImageIcon img1 = new ImageIcon("src\\icon" + i + ".png");
            Image originImg = img1.getImage();
            Image changedImg = originImg.getScaledInstance(120,120, Image.SCALE_SMOOTH);
            ImageIcon icon = new ImageIcon(changedImg);
            JLabel food = new JLabel(icon);
            entire.add(food);
        }
```

------

**폼목을 보여주는 Panel의 설정**

```java
//아이템, 개수, 추가 (1)
JPanel itemPanel01 = new JPanel();
itemPanel01.setLayout(new BorderLayout());
itemPanel01.add(itemText01, BorderLayout.CENTER);
itemPanel01.add(new JLabel("50원"), BorderLayout.NORTH);
itemPanel01.add(item1B,  BorderLayout.EAST);
entire.add(itemPanel01);

//아이템, 개수, 추가 (2)
JPanel itemPanel02 = new JPanel();
itemPanel02.setLayout(new BorderLayout());
itemPanel02.add(itemText02, BorderLayout.CENTER);
itemPanel02.add(new JLabel("100원"), BorderLayout.NORTH);
itemPanel02.add(item2B,  BorderLayout.EAST);
entire.add(itemPanel02);


//아이템, 개수, 추가 (3)
JPanel itemPanel03 = new JPanel();
itemPanel03.setLayout(new BorderLayout());
itemPanel03.add(itemText03, BorderLayout.CENTER);
itemPanel03.add(new JLabel("200원"), BorderLayout.NORTH);
itemPanel03.add(item3B,  BorderLayout.EAST);
entire.add(itemPanel03);
```

------

**투입금을 나타내는 Panel의 설정**

```java
//현재 금액
JPanel BuyPanel = new JPanel();
BuyPanel.setLayout(new BorderLayout(5,10));
BuyPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
BuyPanel.setBackground(Color.WHITE);
JLabel buyLabel = new JLabel("투입금"); //투입금 텍스트
buyLabel.setFont(new Font("맑은 고딕", Font.BOLD, 30));
buyLabel.setHorizontalAlignment(JLabel.CENTER);
BuyPanel.add(buyLabel, BorderLayout.NORTH);


BuyPanel.add(buyText, BorderLayout.CENTER);
BuyPanel.add(cashB,BorderLayout.EAST);

entire.add(BuyPanel);
```

------

**합계금을 나타내는 Panel의 설정**

```java
//구매
JPanel PayPanel = new JPanel();
PayPanel.setLayout(new BorderLayout(5,10));
PayPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
PayPanel.setBackground(Color.WHITE);

JLabel payLabel = new JLabel("합계금"); //텍스트, 정렬
payLabel.setFont(new Font("맑은 고딕", Font.BOLD, 30));
payLabel.setHorizontalAlignment(JLabel.CENTER);
PayPanel.add(payLabel, BorderLayout.NORTH);

PayPanel.add(payText, BorderLayout.CENTER);
PayPanel.add(chargeB, BorderLayout.EAST);

entire.add(PayPanel);
```

------

**거스름돈을 나타내는 Panel의 설정**

```java
JPanel ChangePanel = new JPanel(new BorderLayout(5,10));
ChangePanel.setBorder(new EmptyBorder(10, 10, 10, 10));

JLabel changeLabel = new JLabel("거스름돈"); //텍스트, 정렬
changeLabel.setFont(new Font("맑은 고딕", Font.BOLD, 30));
changeLabel.setHorizontalAlignment(JLabel.CENTER);
ChangePanel.add(changeLabel, BorderLayout.NORTH);

JPanel CoinPanel = new JPanel(new GridLayout(2,5, 6,3));

String coinValue[] = {"10", "50", "100", "500"};
for(int i = 0; i<4; i++){CoinPanel.add(new JLabel(coinValue[i]+"원", JLabel.CENTER));}

CoinPanel.add (coin10); CoinPanel.add (coin50); CoinPanel.add (coin100); CoinPanel.add (coin500);

ChangePanel.add(CoinPanel, BorderLayout.CENTER);
entire.add(ChangePanel);

this.setVisible(true);
```

------

**main 함수**

```java
public static void main(String[] args) {
    new MainFrame().on();
}
```

------

## [실행창]


![결과 사진1](https://user-images.githubusercontent.com/62582301/80056680-b8f9fe00-855f-11ea-96ff-f809b08dab3b.png)



이용 방법 안내

1. 각 품목의 해당하는 '추가' 버튼을 클릭하면 개수가 1씩 증가.
2. 자신이 가지고 있는 금액을 투입금에 있는 입력란에 입력 후, '시작' 버튼 클릭
3. '구매' 버튼을 클릭
4. 결과로, 잔돈을 계산한 값은 합계금에 나타나고 동전들의 개수는 각각 출력



ex)

![결과 사진2](https://user-images.githubusercontent.com/62582301/80056687-bb5c5800-855f-11ea-95d5-533a616add43.png)



![결과 사진3](https://user-images.githubusercontent.com/62582301/80056691-bdbeb200-855f-11ea-8bd5-16cde7aa4017.png)