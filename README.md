# :sunny: 디자인패턴 

-----

## :pushpin: 1. Singleton 패턴

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

## :pushpin: 2. Flyweigth 패턴

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


## :pushpin: 3. Prototype 패턴

## :star:  인스턴스를 복사해서 새 인스턴스를 만드는 패턴
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

## :pushpin: 4. Iterator 패턴

## :star:  for문의 조건식을 이동시켜 반복하는 패턴
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
## :pushpin: 5. Template Method 패턴

## :star:  상위 클래스에서 처리의 뼈대를 결정하고 하위 클래스에서 그 구체적 내용을 결정하는 패턴

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
## :pushpin: 6. Strategy 패턴


## :star:  자주 변경되는 코드를 매개변수를 통해 외부에서 공급받는(의존성 주입) 패턴

* ### 변하는 것과 변하지 않는 것의 분리
  *  ##### 자주 변하는 코드를 외부로 분리하여 의존성 주입을 통해 알고리즘 전체를 변경(교체)
* ### 전략 패턴을 이용한 것들 
  * #### sort메서드, Thread(Runnable r)


---
## :pushpin: 7. Bridge 패턴

## :star:  기능의 클래스 계층과 구현의 클래스 계층의 분리

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
## :pushpin: 8. State 패턴

## :star:  조건을 클래스로 정의

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


## :pushpin: 9. Composite 패턴

## :star:  그릇과 내용물을 동일시하여 재귀적인 구조를 만드는 패턴

* ### 디렉터리와 파일은 서로 다르지만, 둘다 디렉터리 안에 넣을 수 있음
* ### 디렉터리와 파일을 합쳐서 `디렉터리 엔트리`라고 부름
* ### 트리 구조로 된 데이터 구조가 Composite 패턴에 해당됨



---
## :pushpin: 10. Decorator 패턴

## :star:  장식틀과 내용물을 동일시하는 패턴

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
## :pushpin: 11. Proxy 패턴

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

