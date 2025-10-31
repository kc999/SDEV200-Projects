public class assignment09_09 {
  public static void main(String[] args) 
  {
    RegularPolygon polygon1 = new RegularPolygon();
    RegularPolygon polygon2 = new RegularPolygon(6,4);
    RegularPolygon polygon3 = new RegularPolygon(10,4,5.6,7.8);

    System.out.println("The area of the first polygon is " + polygon1.getArea() + " And the perimeter is " + polygon1.getPerimeter());
    System.out.println("The area of the second polygon is " + polygon2.getArea() + " And the perimeter is " + polygon2.getPerimeter());
    System.out.println("The area of the third polygon is " + polygon3.getArea() + " And the perimeter is " + polygon3.getPerimeter());
  }
}


class RegularPolygon {
    //Number of sides in polygon
    private int n = 3;
    //Length of Side
    private double side = 1;
    //x Coord of center of polygon
    private double x = 0;
    //y coord of center of polygon
    private double y = 0;

    public RegularPolygon()
    {
        this.y = 0;
        this.x = 0;
    }
    public RegularPolygon(int sides, double length)
    {
        this.n = sides;
        this.side = length;
    }

    public RegularPolygon(int sides, double length, double xcord, double ycord)
    {
        this.x = xcord;
        this.y = ycord;
        this.n = sides;
        this.side = length;
    }
    public int getSides()
    {
        return n;
    }
    public void setSides(int sides)
    {
        this.n = sides;
    }
    public double getLength()
    {
        return side;
    }
    public void setLength(double length)
    {
        this.side = length;
    }
    public double getX()
    {
        return x;
    }
    public void setX(double nX)
    {
        this.x = nX;
    }
    public double getY()
    {
        return y;
    }
    public void setY(double nY)
    {
        this.x = nY;
    }
    public double getPerimeter()
    {
        double perim;
        perim = this.n * side;
        return perim;
    }
    public double getArea()
    {
        double area;
        area = (this.n * (side * side)) / (4 * Math.tan(Math.PI/this.n));
        return area;
    }

}