package Chapter3;

public class CloneMethodEx {
    public static void main(String[] args) {

    }
}
class Circle implements Cloneable {
    Point p;
    double r;
    Circle(Point p,double r) {
        this.p = p;
        this.r=r;
    }
    public Circle clone() { // 얇은 복사
        Object obj = null;
        try {
            obj = super.clone();
        }catch (CloneNotSupportedException e) {}

        return (Circle)obj;
    }
    public Circle deepClone() { //깊은 복사
        Object obj = null;
        try {
            obj = super.clone();
        }catch (CloneNotSupportedException e) {}

         Circle c = (Circle) obj;
         c.p = new Point(this.p.x, this.p.y);
         return c;
    }
}
class Point {
    int x;
    int y;
    Point(int x,int y) {
        this.x = x;
        this.y = y;
    }

    public String toString() {
        return x+" "+y;
    }
}
