public class Pen {
    public enum Color {
        RED("red"), GREEN("green"), BLUE("blue");
        private final String color;

        //Constructor for enum
        Color(String color) {
            this.color = color;
        }



        @Override
        public String toString() {
            return color;
        }
    }
    //instance variable whether cap is on or off, color of pen
    private Color color;
    private boolean capOn;

    public Pen(Color color){
        this.color = Color.RED;
        this.capOn = true;
    }


    public void capOn(){
        capOn = true;

    }
    public void capOff(){
        capOn = false;
    }
    public void changeColor(Color newColor){
        if (capOn) {
        this.color = newColor;
        }
    }
    public String draw(){
        // Only draws if the cap is off
        return capOn ? "" : "Drawing " + color.toString();
    }
}