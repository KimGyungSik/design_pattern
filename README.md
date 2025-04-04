﻿# :sunny: 디자인패턴 

## :star: 디자인 패턴이란?
  * ### OOP설계를 위한 전체 중 일부 의미 있는 클래스들을 묶은 각각의 집합
  * ### 객체 지향 4대 특성 (캡슐화,상속,추상화,다형성)과 설계 원칙(SOLID)을 기반으로 구현
  * ### 클래스의 재사용성을 높이는 것 -> OCP 충족하게끔 (확장 open, 변경 close)

## :star: 패턴을 선택하는 기준?
  * ### 그 패턴을 사용하는 목적, 그 패턴으로 해결하려는 문제가 무엇인가를 항상 먼저 생각해봐야함 

## :star: 설계도를 볼 때 중요포인트
  * ### 화살표의 방향 
    * A -> B : A는 B를 알고있다(의존한다)
    * B가 변경되면 A에 영향을 미친다는 것
    * 즉, 누가 누구를 알고 있는지 누가 무엇을 있고 있는지가 중요
  
  * ### 클래스의 역할(기능)
    * 어느 클래스가 어느 메소드를 사용할 수 있는지(사용해도 좋은지)

-----
##  :scroll: 목차

> ###  :bookmark: 01. 인스턴스를 효율적으로 생성(6)  
- [Singleton 패턴](#pushpin-singleton-패턴)
- [Flyweight 패턴](#pushpin-flyweight-패턴)
- [Prototype 패턴](#pushpin-prototype-패턴)
- [Builder 패턴](#pushpin-builder-패턴)
- [Factory Method 패턴](#pushpin-factory-Method-패턴)
- [Abstract Factory 패턴](#pushpin-abstract-factory-패턴)
> ###  :bookmark: 02. 변경되는 부분을 분리(7)  
- [Iterator 패턴](#pushpin-iterator-패턴)
- [Template Method 패턴](#pushpin-template-method-패턴)
- [Strategy 패턴](#pushpin-strategy-패턴)
- [Bridge 패턴](#pushpin-bridge-패턴)
- [State 패턴](#pushpin-state-패턴)
- [Adapter 패턴](#pushpin-adapter-패턴)
- [Visitor 패턴](#pushpin-visitor-패턴)
> ###  :bookmark: 03. 동일시 취급(6)  
- [Composite 패턴](#pushpin-composite-패턴)
- [Decorator 패턴](#pushpin-decorator-패턴)
- [Proxy 패턴](#pushpin-proxy-패턴)
- [Command 패턴](#pushpin-command-패턴)
- [Chain Of Responsibility 패턴](#pushpin-chain-of-responsibility-패턴)
- [Interpreter 패턴](#pushpin-Interpreter-패턴)
> ###  :bookmark: 04. 인스턴스를 관측과 단순화(4)  
- [Observer 패턴](#pushpin-13-observer-패턴)
- [Facade 패턴](#pushpin-facade-패턴)
- [Mediator 패턴](#pushpin-mediator-패턴)
- [Memento 패턴](#pushpin-memento-패턴)



-----

## :pushpin: Singleton 패턴  
## 더 Deep한 내용이 궁금한다면 ? https://www.notion.so/19eff25b883b80178ff6cbb1168f5d39?pvs=4 

## :star:  단 하나의 유일한 객체를 만들기 위한 패턴
* ### 인스턴스 수를 1개로 제한 why? 자원(메모리)을 아끼기 위해서
* ### 생성자가 private이며 static Factory Method를 이용하여 객체 반환  
* ### 멀티쓰레드 환경에서는 여러 쓰레드가 공유하기 때문에 `iv(인스턴스 변수)`가 없는게 좋음
* > ### `싱글톤으로 등록할떄 고려해야할 사항`
  > * 해당 객체가 정보 공유가 가능한지
  > * 멀티쓰레드 환경에서 iv 오염이 발생하지 않는지
  > * iv가 동기화처리가 되었는지 혹은 상수를 처리하였는지
* ### enum을 이용한 Singleton
  ```java
  enum Singleton {
    INSTANCE;
    public void hello() {
        System.out.println("hello is called.");
    }
  }      
    * Singleton.INSTANCE라는 유일한 인스턴스가 생성됨
    * Singleton.INSTANCE.hello();로 접근 가능함  

---

## :pushpin: Flyweigth 패턴

## :star:  n개의 인스턴스를 한번만 생성하고 등록하여 공유하는 패턴
* ### 싱글톤의 확장버전 ( 싱글톤은 1개 , 플라이웨이트는 n개 )
![KakaoTalk_20240611_190151253](https://github.com/KimGyungSik/GoF-DP-Practice/assets/139200972/374983d8-6d5d-4c9c-b735-2b1393db0e26)
* ### `Map`에 싱글톤 객체들을 등록하여 공유하는 것
  > * 싱글톤 객체들을 등록하기 떄문에 멀티쓰레드 환경에 주의해야함
  > * 해당 객체를 반환하는 메서드가 동기화 처리되있어야함 
  >   * 서로 다른 쓰레드가 동시에 같은 인스턴스를 사용하길 원하면 해당 인스턴스가 없는 경우 여러번 생성될 수 있음  
* ### `Spring Container`와 유사함
  > * 생성과 사용의 분리 ( 사용자는 사용, Factory가 생성)
  > * 스프링에서도 `Bean(객체)`을 등록하면 `Map`형태의 `Container`에 저장됨
  > * 사용자는 `Container`에서 빈을 꺼내 쓰기만 하면됨


---


## :pushpin: Prototype 패턴
## 더 Deep한 내용이 궁금한다면 ? https://www.notion.so/1adff25b883b80559b0deede08261412?pvs=4
## :star:  인스턴스를 복사해서 새 인스턴스를 만드는 패턴

![KakaoTalk_20240617_175524879_01](https://github.com/KimGyungSik/GoF-DP-Practice/assets/139200972/9ac460a2-db8d-4df7-8258-54c5ce708524)


* ### 왜 `Prototype`이  필요한 것일까?
  > * ### 종류가 너무 많아 클래스로 정리할 수 없는 경우
  >   * 클래스 수가 많아지면 소스 프로그램을 관리하기 어려움
  > * ### 클래스로부터 인스턴스 생성이 어려운 경우
  >   *  동일한 인스턴스 생성을 원하는 경우 클래스 사용이 아닌 인스턴스를 복사가 편함
  > * ### 프레임워크와 생성하는 인스턴스를 분리하고 싶은 경우
  >   *  클래스 이름의 속박으로부터 프레임워크를 분리

* ### 인스턴스의 복사는 `iv(인스턴스 변수)`값들의 복사
  > * ### 얕은 복사 vs 깊은 복사
  >   * 얕은 복사는 참조변수를 복사하므로 원본의 영향 O
  >   * 깊은 복사는 서로 다른 객체를 참조하므로 원본의 영향 X
  > * ### clone메서드는 얕은 복사에 해당됨
  >   * clone메서드는 객체의 복제를 위한 메서드
  >   * 해당 인스턴스를 복제하여 새로운 인스턴스를 생성해 그 참조값을 반환함

* ### Flyweight 패턴과의 차이점
  > * ###  Prototype 패턴에서는 현재 인스턴스와 동일한 상태의 별도의 인스턴스를 만들어 이용
  > * ###  Flyweight 패턴에서는 하나의 인스턴스를 여러 장소에서 공유하여 이용함 

---
## :pushpin: Builder 패턴
## 더 Deep한 내용이 궁금한다면 ? https://www.notion.so/1acff25b883b804ca1ece045cd65ce1d?pvs=4

## :star: 복잡한 메서드를 조합하여 인스턴스를 구축

 <img src="https://github.com/user-attachments/assets/a41d9624-7b35-4843-b8a5-e88016d9f55c" width="500">

* ### Facade 패턴과 유사
  * ### 복잡한 인스턴스를 생성해주는 간단한 인터페이스를 외부에 제공 -> Builder 패턴
  * ### 복잡한 내부 모듈을 조합하여, 간단한 인터페이스를 외부에 제공 -> Facade 패턴
* ### 내부적으로 Bridge패턴과 Template Method패턴이 적용되어 있음


---

## :pushpin: Factory Method 패턴
## 더 Deep한 내용이 궁금한다면 ? https://www.notion.so/19fff25b883b807b9be9e1555c9f2e24?pvs=4

## :star:  상위 클래스에서 인스턴스 생성 방법을 결정,하위 클래스에서 구체화  

 <img src="https://github.com/user-attachments/assets/7c25327f-aaf0-4336-ae6d-4a4f41b5c1eb" width="500">

* ### Template Method 패턴의 확장버전
  * ### 상위 클래스에서 인스턴스 생성을 위한 뼈대(프레임워크)와 실제 인스턴스를 생성하는 하위클래스로 분리
  * <img src="https://github.com/user-attachments/assets/60449de1-4689-4b50-ab8d-afc5d5207d88" width="500">
  * ### Factory Method 패턴에서는 패키지 단위로 분리 
  * ### 변하지 않는 부분과 변하는 부분을 분리하여 추상 패키지는 1개, 구현 패키지는 n개 -> OCP 충족
* ### static Factory Method와는 다른 것임 


---

## :pushpin: Abstract Factory 패턴
## 더 Deep한 내용이 궁금하다면 ? https://www.notion.so/1a4ff25b883b800f9e09c86451a8393b?pvs=4

## :star:  추상적인 '제품'을 추상적인 '부품'으로 쪼개 추상 '팩터리'를 통해 조립 

 <img src="https://github.com/user-attachments/assets/294654b1-fd89-43f9-a16e-b1a934749c0c" width="500">

* ### Factory Method 패턴의 확장버전
  * ### 복잡한 구조를 가진 인스턴스를 부품으로 쪼개 팩터리에서 인스턴스 생성 방법을 결정
* ### 추상 팩터리 패키지 1개, 구현 팩터리 패키지 n개 -> OCP 충족
* ### 구현에 집중 X -> 인터페이스에 주목

---

## :pushpin: Iterator 패턴

## :star:  반복문의 조건식을 분리(사용 -> 구현)

![KakaoTalk_20240617_175524879](https://github.com/KimGyungSik/GoF-DP-Practice/assets/139200972/f7c41e6e-7e2c-4f2c-bbff-3af9565d6d99)

* ### 자주 변경되는 부분을 분리하여 변경에 유리한 코드를 만듬
  ````java
  // 루프 변수 i를 사용한 예
  for(int i=0; i < arr.length; i++) {
    System.out.println(arr[i]);
  }
  
  <자주 변경되는 부분> arr.length
  
  // 확장 for문을 사용한 예
  for (int e : arr) {
    System.out.println(e);
  }
  
  arr에는 배열, List, Set이 올 수 있음 ( Iterable을 구현한 인스턴스들 )
  
* ### Iterator를 사용하면 구현과 분리하여 반복할 수 있음
  ````java
  Iterator<BooK> it = bookShelf.iterator();
  while (it.hasNext()) {
    Book book = it.next();
    System.out.println(book.getName());
  }
  
  bookShelf가 집약하고 있는 것이 배열이 아닌 ArrayList를 사용하더라도 해당 while문은 변경 X
  즉, 어떤 구현체가 와도 iterator메서드를 가지고 있고, 올바른 Iterator<Book>을 반환하면 됨 

---
## :pushpin: Template Method 패턴

## :star:  상위 클래스에서 처리의 뼈대를 결정하고 하위 클래스에서 그 구체적 내용을 결정하는 패턴

![KakaoTalk_20240617_175946046_01](https://github.com/KimGyungSik/GoF-DP-Practice/assets/139200972/381cc9d0-bc29-4ed4-92d9-5a07200f9f3e)


* ### 변하지 않는 부분을 상위 클래스에 두고 변하는 부분을 추상메서드로 정의해둬서, 상속을 통해 완성
* ### IoC(제어의 역전)개념을 활용
  * ##### 모든 종류의 작업을 사용하는 쪽에서 제어 
  * ##### 제어권을 상위 템플릿 메서드에 넘기고, 자신이 필요할때 호출되어 사용되도록 사용하는 쪽에서 제어
* ### 전략 패턴과 차이점
  *  ##### 템플릿 메서드 패턴은 추상클래스를 상속을 통해 완성 ( OCP = 확장 open, 변경 close)
      * ##### 일부 프로그램의 동작을 변경  
  *  ##### 전략 패턴은 매개변수(포함)를 주입 받아서 완성 
      * #### 일부를 변경하기보다는 알고리즘 전체를 모두 전환
* ### 브릿지 패턴과 차이점
  * ##### 템플릿 메서드 패턴은 1개의 추상메서드를 다루지만
  * ##### 브릿지 패턴은 n개의 추상메서드를 인터페이스로 추출하여 선언(기능)과 구현을 분리한 형태
---
## :pushpin: Strategy 패턴


## :star:  일련의 알고리즘(자주 변경되는 코드)을 (매개변수/생성자)를 통해 외부에서 공급받는(의존성 주입) 패턴

![KakaoTalk_20240617_175946046](https://github.com/KimGyungSik/GoF-DP-Practice/assets/139200972/4096985a-3ead-4a40-a5dc-97a97aee0d52)

* ### 변하는 것과 변하지 않는 것의 분리
  *  ##### 자주 변하는 코드를 외부로 분리하여 의존성 주입을 통해 알고리즘 전체를 변경(교체)
  *  ##### 클라이언트에 따라 알고리즘을 독립적으로 변경 가능
* ### 브릿지 패턴과 차이점
  * ##### 전략패턴은 객체의 생성시점이 아닌 동작시점의 행동방식 변경에 초점을 둠, 객체는 언제든지 행동 객체의 입력에 따라 그 동작방식이 변경 될 수 있음
  * ##### 브릿지 패턴은 생성시점에 초점이 맞춰져 있음. 전략 패턴처럼 런타임에 행동이 변경되는 것이 아니라 객체의 생성시점에 입력으로 받는 행동객체에 따라 브리지 객체의 행동방식이 결정됨.
* ### 전략 패턴의 단점 '런타임 비용'
  * ##### 해결책 : 플라이웨이트 패턴(여러 콘텍스트에서 동시에 사용할 수 있는 공유 객체)을 적용
  * ##### -> 새로운 콘텍스트에서 동일 전략 객체를 반복해서 적용할 떄는 새로 생성되는 비용을 줄이기 위해 즉, 기존 전략 객체가 있으면 재사용하기 위해서 
* ### 전략 패턴을 이용한 것들 
  * #### sort메서드, Thread(Runnable r)


---
## :pushpin: Bridge 패턴

## :star:  기능의 클래스 계층과 구현의 클래스 계층의 분리


![KakaoTalk_20240617_182605630](https://github.com/KimGyungSik/GoF-DP-Practice/assets/139200972/3b677785-adae-4ef0-b804-cc537902c482)


* ### n개의 추상메서드를 인터페이스로 추출하여 기능과 구현을 분리 
  * ### 각 계층은 독립적으로 확장이 가능함
    ```java
              interface Movable {
                   abstract void move(int x, int y);
                  abstract void stop();
                }


              // 브릿지 패턴 - 여러 추상 메서드를 인터페이스로 대체
              abstract class Unit {
                  // abstract void move(int x, int y);
                  // abstract void stop();
                  Movable m; // 선언

                  // 구현체를 매개변수로 받아서 사용
                  Unit(Movable m){ // 전략 패턴.  Thread(Runnable r)
                      this.m = m;
                  }

                  ...

              }

    [참고] Thread클래스
    class Thread {
                Runnable r;

                  Thread(Ruunable r) {
                      this.r = r;
                  }

              }



---
## :pushpin: State 패턴

## :star:  조건을 클래스로 정의

![KakaoTalk_20240617_182605630_01](https://github.com/KimGyungSik/GoF-DP-Practice/assets/139200972/7acad7f1-ad6b-4ed7-8c44-b38389c0db05)

* ### if문의 조건은 변경되거나 추가될 위험이 있음
* ### 그래서 변하는 부분인 조건을 클래스로 정의하여 분리
* ### 변하는 것과 변하지 않는 것을 분리하므로 Bridge 패턴, Strategy 패턴과 비슷함

```java
[before]
// 호출
// Collections.sort(list, "DA");
void sort(List list, String order) { // order DA면 사전순 오름, DD, 사전순 내림, NA(숫자오름)
  for (...) {
    for (...) {
      if (order.equals("DA")) {
                ...
      } else if (order.equals("DD")) {
                ...
      } else if (order.equals("NA")) {
                ...
      }
    }
  }
}
[after]
// 호출
// if(order.equals("DA"))
//     comp = new DicAscComparator();
// else if(order.equals("DD"))
//     comp = new DicDscComparator();
// Collections.sort(list, comp);

void sort(List list, Comparator comp) {
  for (...) {
    for (...) {
      if (comp.compare()) {
                ...
      }
    }
  }
}
[after2] - State 패턴

// 호출
// if(order.equals("DA"))
//     sorter = new Sorter(new DicAscComparator());
// else if(order.equals("DD"))
//     sorter = new Sorter(new DicDscComparator());

class Sorter {
    Comparator comp;

    Sorter(Comparator comp) {
        this.comp = comp;
    }

    // void sort(List list, Comparator comp) {
    void sort(List list) {
        for (...) {
            for (...) {
                if (comp.compare()) {
                    ...
                }
            }
        }
    }
}
```
---
## :pushpin: Adapter 패턴
## 더 Deep한 내용이 궁금한다면 ? https://www.notion.so/1afff25b883b8030a33eccf12e3c7794?pvs=4

## :star: '이미 제공된 것'과 '필요한 것'사이의 '차이'를 메우는 패턴

 <img src="https://github.com/user-attachments/assets/d4f94f5b-782e-4f93-b242-72e08c0405d5" width="500">


* ### 무엇인가를 포장해서 다른 용도로 사용할 수 있도록 변환해주는 것이 어댑터
* ### 클래스를 부품으로써 재사용하고 싶을 떄 


---

## :pushpin: Visitor 패턴

## :star: '데이터 구조'와 '처리'를 분리한 패턴

<img src="https://github.com/user-attachments/assets/842ad19d-c60a-4dae-974d-8f4a9241c51e" width="500">

* ### 더블 디스패치
  * #### visit 메서드와 accpet 메서드가 서로 상대방을 호출 (일반적인 재귀적 호출과 다름)
  * <img src="https://github.com/user-attachments/assets/6929f26b-e015-4bdc-bbc3-1fbf17b706a5" width="500">
* ### '데이터 구조'와 '처리'는 독립적으로 개발이 가능
  * #### '데이터 구조'의 집합을 부품으로서의 독립성을 높여줌
  * #### 새로운 '처리'를 추가할때마다 '데이터 구조'를 수정할 필요가 없어짐 -> 'OCP'충족
* ### 각자의 역할에 집중함 
  * #### '데이터 구조'와 '처리'는 각자의 역할에 집중할 수 있음 -> 'SRP'충족


---
## :pushpin: Composite 패턴

## :star:  그릇과 내용물을 동일시하여 재귀적인 구조를 만드는 패턴


![KakaoTalk_20240617_182605630_03](https://github.com/KimGyungSik/GoF-DP-Practice/assets/139200972/2ca0cb5a-8cd5-4ff2-b135-8a650ebc46e2)


* ### 디렉터리와 파일은 서로 다르지만, 둘다 디렉터리 안에 넣을 수 있음
* ### 디렉터리와 파일을 합쳐서 `디렉터리 엔트리`라고 부름
* ### 트리 구조로 된 데이터 구조가 Composite 패턴에 해당됨



---
## :pushpin: Decorator 패턴

## :star:  장식틀과 내용물을 동일시하는 패턴

![KakaoTalk_20240617_182605630_02](https://github.com/KimGyungSik/GoF-DP-Practice/assets/139200972/a08ae4ee-80ed-4b28-afc5-c1900a8bd994)


* ### 껍데기와 알맹이를 분리했다가 알맹이를 주입(전략패턴)받아서 합치기
* ```` java
  public abstract class Border extends Display {
  
    protected Display display; // 주입받을 알맹이
  
    protected Border(Display display) {
          this.display = display;
    }
  }

* ### 포함과 상속을 동시에 
  * ### `why?` 껍데기가 알맹이 행새(다형성)를 해야하므로
  * ###  껍데기에 또 껍데기를 씌울 수 있게 하기 위해서
  
* ```` java
  Display d = new Border(new StringDisplay());
  
  Border : 껍데기
  StringDisplay : 알맹이
---
## :pushpin: Proxy 패턴

## :star:  어떤 객체에 대한 접근을 제어하는 용도로 대리인에 해당하는 객체를 제공하는 패턴


![KakaoTalk_20240618_195732610](https://github.com/KimGyungSik/GoF-DP-Practice/assets/139200972/6ce94391-f177-4ae1-b1d5-123a86cec7e8)


* ### Proxy 역할이 대리인이 되어 가능한 한 처리를 대신함
  * ### 실생활에 비유하자면 연예인을 섭외하기 위해서 연예인에게 직접 전화를 거는 것이 아니라 매니저에게 전화를 걸어 섭외하는 것

* ###  RealSubject(핵심) 객체의 기능이 필요한 시점까지 객체의 생성을 늦출 수 있음
  * ### Proxy와 RealSubject는 동일한 인터페이스를 구현했기 때문에 Proxy가 진짜행세를 할 수 있음
  * ### 핵심 객체의 생성은 필요할때 생성됨

* ### Decorator 패턴과 차이점
  * ### Decorator 패턴의 목적은 새로운 기능을 추가하는 것이지만,
  * ### Proxy 패턴의 목적은 새로운 기능을 추가하기 보단 본인의 작업을 위임하여 본인에 대한 액세스를 줄이는 것 
  * ### 공통점은 둘 다 동일시 하기 위한 인터페이스를 정의함

* ### Spring의 AOP 기술과 비슷
  * ###  부가 기능(Advice)를 동적으로 추가해주는 기술
  * ### 핵심 기능과 부가 기능을 분리하여 실행되는 과정에서 합쳐짐

---
## :pushpin: Command 패턴

## :star:  n개의 명령어들을 하나의 타입으로 취급하여, 그것을 객체로 표현하는 패턴

<img src="https://github.com/KimGyungSik/GoF-DP-Practice/assets/139200972/958f82fd-cb71-4b0e-83ad-18310f3cdddd" width="500">
<img src="https://github.com/user-attachments/assets/4af43980-087d-49c4-b98e-3fee34b4bf8f" width="500">

* ### 명령어의 집합을 저장해서 관리
  * ### 명령어들이 스택에 쌓이게 되는데, 같은 타입으로 취급하여 스택에 쌓임
  * ### 즉 내용과 그릇을 동일시 취급하는 Composite패턴과 유사함
* ### 커맨드 패턴의 목적
  * ### 연산을 실행하는 객체(호출자)와 연산을 구현하는 객체(수신자)를 분리하는 것
* ### State 패턴과 유사한 점
  * ### 명령어를 클래스로 분리함
---

## :pushpin: Chain of Responsibility 패턴

## :star:  n개의 처리를 묶어 동적으로 요청을 처리

<img src="https://github.com/user-attachments/assets/13b57c99-1a0c-466a-8bfb-ff8e0ecf74be" width="500">

* ### 어떤 요청을 처리할 객체를 고정적으로 결정할 수 없는 경우
  * ### 해결책 : n개 객체를 연쇄적으로 묶어 차례대로 돌면서 원하는 객체를 결정
  * ### 책임을 떠넘기는 구조
* ### '요청'과 '처리'의 분리
  * ### 각각 부품으로 독립시킬 수 있음 -> OCP,SRP 충족
* ### Template Method, Composite, Command 패턴들이 녹아있음
  * <img src="https://github.com/user-attachments/assets/33405571-2727-488c-ab1b-ac99f7adc51a" width="500">
  * <img src="https://github.com/user-attachments/assets/3897b0e9-6031-408b-8715-8bfceed9d93b" width="500">
---
## :pushpin: Interpreter 패턴

## :star:  문법적 규칙을 클래스화 하여, 일련의 규칙을 통해 언어/문법을 해석하는 패턴

 <img src="https://github.com/user-attachments/assets/f8ec7e5e-45bd-4b57-848b-416ba1e62e37" width="500">

* ### 문법 규칙을 수정하거나 추가할 때 유용
  * ### 새로운 규칙을 추가하거나 기존 규칙을 수정하여 새로운 규칙 추가 가능 / 각 규칙들은 독립적 -> OCP 충족
* ### Composite 패턴과 유사 
  * ### 재귀적 구조를 형성하기 때문에 파싱트리가 형성됨 
* ### java의 컴파일러, 정규표현식과 spring의 EL태그도 인터프리터 패턴을 적용



---

## :pushpin: Observer 패턴
## 더 Deep한 내용이 궁금한다면 ? https://www.notion.so/1b5ff25b883b80a1b967f70dd927341e?pvs=4
## :star: 관찰자(Observer)에게 상태 변화를 알려주는 패턴

![KakaoTalk_20240705_143648590](https://github.com/KimGyungSik/design_pattern/assets/139200972/b4f8e915-51ea-446d-82c7-ed706c33b2cb)



  * ###  관찰 대상(객체)의 상태 변화를 관찰자(Observer)에게 알린다
    * ### 상태가 변하면 관찰자를 호출(메서드)하여 관찰자가 상태 변화에 따른 작업처리를 함
    * ![KakaoTalk_20240705_144256508](https://github.com/KimGyungSik/design_pattern/assets/139200972/cbac054d-00c2-4ca0-ab59-9c943754992d)
    * ![KakaoTalk_20240705_145328348](https://github.com/KimGyungSik/design_pattern/assets/139200972/b2f7d2e3-814b-41e9-a68a-d0a7c0eef0d7)
    * ### 상태 변화에 따라 작업 처리를 n개로 확장할 수 있음 -> OCP 충족
  * ### MVC 패턴과 유사
    * ### Model과 View의 관계는 관찰대상과 관찰자 관계에 대응함
    * ### Model은 '표시 형식에 의존하지 않는 내부 모델', View는 Model을 '어떻게 보여 줄지'관리하는 부분
    * ### 하나의 Model에 여러 View가 대응
  
---
## :pushpin: Facade 패턴

## :star: '복잡한 것'을 창구를 통해 '단순'하게 보여주기 위한 패턴

<img src="https://github.com/user-attachments/assets/c2e1d528-79a1-484b-8aff-1287e93452d0" width="500">

* ### 외부와 복잡한 내부를 분리
  * ### 외부에선 복잡한 내부 사정을 몰라도 됨 
  * ### 'Facade'(창구)에 요청만 하면됨 -> 복잡도를 낮추고 단순화시킴 
  * ### 복잡한 로직을 모아 하나의 클래스에 모은 것 , 클라이언트는 몰라도 되도록 하는 것
    
* ### Spring Application Context은 Facade패턴으로 만들어짐
  * ### 복잡한 서브 시스템을 쉽게 사용할 수 있도록 간단하고 통일된 인터페이스를 제공 
* ### Abstract Factory 패턴과 유사
  * ### '객체를 생성하기 위해서는 이것만 호출하면 OK라는 인터페이스'를 제공하기 때문
  
  
---

## :pushpin: Mediator 패턴

## :star: 복잡한 객체들 간의 통신을 '중재자'를 통해 통신

<img src="https://github.com/user-attachments/assets/039afe08-195c-442b-a659-7b45501b0311" width="500">

* ### 멤버는 모두 중재자에게만 보고하고, 중재자만 멤버에게 지시
  * ### 멤버들 간의 복잡도를 낮추기 위해 중재자를 둠
  * ### 멤버들은 자신의 역할에 집중할 수 있음 -> SRP 충족
  * ### 중재자와 멤버들은 분리되어 있기 때문에 확장가능 -> OCP 충족
* ### 통신 경로의 증가를 억제 
  * ### 인스턴스가 증가함에 따라 통신 경로가 증가 -> 프로그램 복잡도 증가
  * ### 중재자를 둠으로써 복잡도를 낮출 수 있음
* ### Facade 패턴과 차이점 
  * ### Mediator는 양방향(중재자와 멤버들이 상호작용), Facade는 단방향(클라이언트가 요청만 함) 

  
---
## :pushpin: Memento 패턴

## :star: 특정 시점의 인스턴스 상태를 저장후 복원 

<img src="https://github.com/user-attachments/assets/5f220fc0-4387-41ad-b175-f23da5adab83" width="500">

* ### 특정 시점의 인스턴스를 복원하기 위해 저장
  * ### 인스턴스의 현재 상태를 저장 -> 특정 시점에 복원
  * ### 배열을 사용하여 다양한 시점의 객체 상태를 저장할 수도 있음 
* ### 역할을 분담하여 처리
  * ### Caretaker는 어느 시점에 저장할지, 되돌릴지 결정
  * ### Originator는 Memento를 만들고, Memento를 사용하여 자신의 상태를 되돌리는 일을 함
  * ### -> 각자 역할에 집중 (SRP 충족), 확장 용이(OCP 충족)

  
---
