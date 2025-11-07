// GeometricObject.java: The abstract GeometricObject class
import java.util.Scanner;
public abstract class GeometricObject {
  public static void main(String[] args) {
      Scanner scan = new Scanner(System.in);
      System.out.println("Enter the length of the first side of the triangle.");
      double si1 = scan.nextDouble();
      scan.nextLine();
      System.out.println("Enter the length of the second side of the triangle.");
      double si2 = scan.nextDouble();
      scan.nextLine();
      System.out.println("Enter the length of the third side of the triangle.");
      double si3 = scan.nextDouble();
      scan.nextLine();
      Triangle tri = new Triangle(si1,si2,si3);
      System.out.println("What color is the triangle?");
      String userColor = scan.next();
      tri.setColor(userColor);
      System.out.println("Is the triangle filled, or non-filled? True/False");
      boolean filled = scan.nextBoolean();
      tri.setFilled(filled);
      double triPer = tri.getPerimeter();
      double triArea = tri.getArea();
      if (tri.isFilled() == true)
      {
        System.out.println("The triangle has an area of: " + triArea + " and a perimeter of: " + triPer + " and a color of: " + tri.getColor() + ". The triangle is filled.");
      }
      else{
        System.out.println("The triangle has an area of: " + triArea + " and a perimeter of: " + triPer + " and a color of: " + tri.getColor() + ". The triangle isn't filled.");
      }
      
      
  }
  private String color = "white";
  private boolean filled;

  /**Default construct*/
  protected GeometricObject() {
  }

  /**Construct a geometric object*/
  protected GeometricObject(String color, boolean filled) {
    this.color = color;
    this.filled = filled;
  }

  /**Getter method for color*/
  public String getColor() {
    return color;
  }

  /**Setter method for color*/
  public void setColor(String color) {
    this.color = color;
  }

  /**Getter method for filled. Since filled is boolean,
     so, the get method name is isFilled*/
  public boolean isFilled() {
    return filled;
  }

  /**Setter method for filled*/
  public void setFilled(boolean filled) {
    this.filled = filled;
  }

  /**Abstract method findArea*/
  public abstract double getArea();

  /**Abstract method getPerimeter*/
  public abstract double getPerimeter();
}
class Triangle extends GeometricObject{
  double side1;
  double side2;
  double side3;

  public Triangle()
  {
    side1 = 1.0;
    side2 = 2.0;
    side2 = 3.0;
  }
  public Triangle(double s1 , double s2 ,double s3)
  {
    side1 = s1;
    side2 = s2;
    side3 = s3;
  }

  @Override
  public double getPerimeter()
  {
    double per = side1 + side2 + side3;
    return per;
  }
  @Override
  public double getArea()
  {
    double semiP = .5 * getPerimeter();
    double area = semiP * (semiP-side1) * (semiP - side2) * (semiP - side3);
    area = Math.sqrt(area);
    return area;
  }
  public String toString()
  {
    String info = "Side 1 = " + side1 + " Side 2 = " + side2 + " Side 3 = " +side3;
    return info;
  }
  
  
}
