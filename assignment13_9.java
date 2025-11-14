public class assignment13_9
{
     public static void main(String[] args)
        {
            Circle cir1 = new Circle(5);
            Circle cir2 = new Circle(5);
            System.out.println(cir1.compareTo(cir2));
        }
}
// GeometricObject.java: The abstract GeometricObject class
    abstract class GeometricObject 
    {
        private String color = "white";
        private boolean filled;
        private java.util.Date dateCreated;

        /**Default construct*/
        protected GeometricObject() {
        }

        /**Construct a geometric object*/
        protected GeometricObject(String color, boolean filled) {
            dateCreated = new java.util.Date();
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

        public java.util.Date getDateCreated(){
            return dateCreated;
        }

        @Override
        public String toString(){
            return "created on" + dateCreated + "\ncolor:" + color + "and filled: " + filled;
        }
        /**Abstract method findArea*/
        public abstract double getArea();

        /**Abstract method getPerimeter*/
        public abstract double getPerimeter();

    }

    class Circle extends GeometricObject implements Comparable<Circle>{
        private double radius;
        public Circle(){

        }
        public Circle(double radius){
            this.radius = radius;
        }
        //Return Radius
        public double getRadius(){
            return radius;
        }
        //Set radius
        public void setRadius(double radius){
            this.radius = radius;
        }
        //Return Area
        @Override
        public double getArea(){
            return radius * radius * Math.PI;
        }
        public double getDiameter(){
            return 2 * radius;
        }
        //Return Perimeter
        @Override
        public double getPerimeter(){
            return 2 * radius * Math.PI;
        }
        public void printCircle(){
            System.out.println("The circle is created " + getDateCreated() + "and the radius is: " + getRadius());
        }
        @Override
        public int compareTo(Circle other)
        {
            return Double.compare(this.radius, other.radius);
        }
    }
   