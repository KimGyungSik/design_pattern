package Chapter4;
// Strategy패턴 : 같은 문제를 다른 방법으로 해결하기 쉽게 만들어 주는 패턴
// 위임이라는 약한 결합을 사용하므로 알고리즘을 용이하게 전환할 수 있다
// 위임을 이용하여 알고리즘 전환, 특히 동적 전환이 가능해짐

import java.util.Random;
import java.util.Scanner;

public class StrategyEx {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int seed1 = s.nextInt();
        int seed2 = s.nextInt();
        Player player1 = new Player("KIM",new WinnningStrategy(seed1));
        Player player2 = new Player("LEE",new ProbStrategy(seed2));

        for(int i=0; i<10000; i++) {
            Hand nextHand1 = player1.nextHand();
            Hand nextHand2 = player2.nextHand();
            if(nextHand1.isStrongThan(nextHand2)) {
                System.out.println("Winner:"+player1);
                player1.win();
                player2.lose();
            }else if(nextHand2.isStrongThan(nextHand1)) {
                System.out.println("Winner:"+player2);
                player1.lose();
                player2.win();
            }else {
                System.out.println("Even...");
                player1.even();
                player2.even();
            }
        }
        System.out.println("Total result:");
        System.out.println(player1);
        System.out.println(player2);

    }
}


enum Hand {
    // 가위 바위 보를 나타내느 세 개의 enum 상수
    ROCK("바위",0),
    SCISSORS("가위",1),
    PAPER("보",2);

    //enum이 가진 필드
    private String name; // 가위 바위 보 손의 이름
    private int handvalue; // 가위 바위 보 손의 값

    // 손의 값으로 상수를 얻기 위한 배열
    private static Hand[] hands = {
            ROCK,SCISSORS,PAPER
    };

    //생성자
    private Hand(String name,int handvalue) {
        this.name = name;
        this.handvalue = handvalue;
    }
    //손의 값으로 enum 상수를 가져온다
    public static Hand getHand(int handvalue) {
        return hands[handvalue];
    }
    // this가 h보다 강할 떄 true
    public boolean isStrongThan(Hand h) {
        return fight(h) ==1;
    }
    // this가 h보다 약할 떄 true
    public boolean isWeakerThan(Hand h) {
        return fight(h) == -1;
    }
    //무승부는 0, this가 이기면 1, h가 이기면 -1
    private int fight(Hand h) {
        if(this==h) {
            return 0;
        } else if ((this.handvalue+1)%3 == h.handvalue) {
            return 1;
        } else {
            return -1;
        }
    }

    @Override
    public String toString() {
        return name;
    }
}

interface Strategy {
    Hand nextHand(); // 다음에 낼 손을 얻기 위한 메서드
    void study(boolean win); // 직전에 낸 손으로 이겼는지 졌는지를 학습하는 메서드 이긴 경우에 study(true)를 호출, 진 경우에는 study(false)를 호출
}

class WinnningStrategy implements Strategy {
    private Random random;
    private boolean won = false;
    private Hand prevHand;

    public WinnningStrategy(int seed) {
        random = new Random(seed);
    }

    @Override
    public Hand nextHand() {
        if(!won) {
            prevHand = Hand.getHand(random.nextInt(3));
        }
        return prevHand;
    }

    @Override
    public void study(boolean win) {
        won = win;
    }
}

class ProbStrategy implements Strategy {
    private Random random;
    private int prevHandValue = 0;
    private int currentHandValue = 0;
    private int[][] history = {
            {1,1,1},
            {1,1,1},
            {1,1,1},
    };
    public ProbStrategy(int seed) {
        random = new Random(seed);
    }

    @Override
    public Hand nextHand() {
        int bet = random.nextInt(getSum(currentHandValue));
        int handvalue = 0;
        if(bet<history[currentHandValue][0]) {
            handvalue = 0;
        } else if (bet < history[currentHandValue][0]+history[currentHandValue][1]) {
            handvalue = 1;
        }else {
            handvalue = 2;
        }
        prevHandValue = currentHandValue;
        currentHandValue = handvalue;
        return Hand.getHand(handvalue);
    }

    private int getSum(int handvalue) {
        int sum = 0;
        for(int i=0; i<3; i++) {
            sum += history[handvalue][i];
        }
        return sum;
    }

    @Override
    public void study(boolean win) {
        if(win) {
            history[prevHandValue][currentHandValue]++;
        }else  {
            history[prevHandValue][(currentHandValue+1)%3]++;
            history[prevHandValue][(currentHandValue+2)%3]++;
        }
    }
}

class Player {
    private String name;
    private Strategy strategy;
    private int wincount;
    private int losecount;
    private int gamecount;

    // 이름과 전력을 받아서 플레이어를 만든다
    public Player(String name,Strategy strategy) {
        this.name = name;
        this.strategy = strategy;
    }

    //전략에 따라 다음 손을 결정한다.
    public Hand nextHand() {
        return strategy.nextHand();
    }

    //승리
    public void win() {
        strategy.study(true);
        wincount++;
        gamecount++;
    }
    //패배
    public void lose() {
        strategy.study(false);
        losecount++;
        gamecount++;
    }
    //무승부
    public void even() {
        gamecount++;
    }

    @Override
    public String toString() {
        return "["
                +name+":"
                + gamecount+"gaems, "
                + wincount + " win, "
                + losecount + " lose"
                + "]";
    }
}


