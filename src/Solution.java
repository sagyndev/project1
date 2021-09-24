
import java.util.Scanner;

public class Solution{

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String className = input.nextLine();

        // Checking the implementation of the Position class
        if(className.equals("Position")){
            Position p1 = new Position(input.nextInt(), input.nextInt());
            System.out.println(p1);
            p1.setX(5);
            System.out.println(p1.getX());

            Position p2 = new Position(5, 10);
            System.out.println(p1.equals(p2));
        }

        // Checking the implementation of the Map class
        else if(className.equals("Map")){
            try{
                Map map = new Map(input);
                map.print();
                int size = map.getSize();
                System.out.println(size);
                System.out.println(map.getValueAt(size/2, size/2));
            }
            catch(Exception e){}
        }

        // Checking the Player interface and the MyPlayer class
        else if(className.equals("Player")){
            Player player = new MyPlayer();
            System.out.println(Player.class.isInterface());
            System.out.println(player instanceof Player);
            System.out.println(player instanceof MyPlayer);
        }

        // Checking the InvalidMapException class
        else if(className.equals("Exception")){
            try{
                throw new InvalidMapException("Some message");
            }
            catch(InvalidMapException e){
                System.out.println(e.getMessage());
            }
        }

        // Checking the Game class and all of its components
        else if(className.equals("Game")){

            // Initialize player, map, and the game
            Player player = new MyPlayer();
            Game game = null;

            try{
                game = new Game(new Map(input));
            }
            catch(InvalidMapException e){ // custom exception
                System.out.println(e.getMessage());
                System.exit(0);
            }

            game.addPlayer(player);

            // Make the player move based on the commands given in the input
            String moves = input.next();
            char move;
            for(int i=0; i<moves.length(); i++){
                move = moves.charAt(i);
                switch(move){
                    case 'R':
                        player.moveRight();
                        break;
                    case 'L':
                        player.moveLeft();
                        break;
                    case 'U':
                        player.moveUp();
                        break;
                    case 'D':
                        player.moveDown();
                        break;
                }
            }

            // Determine the final position of the player after completing all the moves above
            Position playerPosition = player.getPosition();
            System.out.println("Player final position");
            System.out.println("Row: " + playerPosition.getY());
            System.out.println("Col: " + playerPosition.getX());
        }
    }
}
class Map {
    public int size;
    public int startingPointR;
    public int startingPointC;
    public char[][] map;

    Map(Scanner scan) throws InvalidMapException {
        int newSize = scan.nextInt();
        this.size = newSize;
        char[][] map1 = new char[size][size];
        this.map = map1;
        if (size == 0) {
            throw new InvalidMapException("Map size can not be zero");
        } else {
            int i = 0;
            while (i < size) {
                scan.nextLine();
                int j = 0;
                while(j < size) {
                    map[i][j] = scan.next().charAt(0);

                    if (map[i][j] == 'P' || map[i][j] == '0' || map[i][j] == '1') {
                        if (map[i][j] == 'P') {
                            startingPointR = i;
                            startingPointC = j;
                        }
                    } else
                        throw new InvalidMapException();
                    j++;
                }
              i++;
            }
        }

    }

    public int getSize(){
        return size;
    }

    public int getStartingPointR(){
        return startingPointR;
    }

    public int getStartingPointC(){
        return startingPointC;
    }

    public char[][] getMap(){
        return map;
    }

    public char getValueAt(int r, int c){
        return map[r][c];
    }

    public void print(){
        int i = 0;
        while (i < size) {
            int j = 0;
            while (j < size) {
                System.out.print(map[i][j]+" ");
                j++;
            }
            System.out.println();
            i++;
        }
    }
}
class Game {
    public Map newMap;

    public Game(Map map){
        newMap = map;
    }

    public void setMap(Map map) {
        newMap = map;
    }

    public Map getMap(){
        return newMap;
    }

    public void addPlayer(Player player)  {
        player.setMap(newMap);
    }
}

/*
            ==========================================
            |            PLAYER INTERFACE            |
            ==========================================
*/
interface Player {
    public void setMap(Map map) ;
    public void moveRight();
    public void moveLeft();
    public void moveUp();
    public void moveDown();
    Position getPosition();
}

/*
            ==========================================
            |             MyPLAYER CLASS             |
            ==========================================
*/
class MyPlayer implements Player{
    public Map map;
    public int size;
    public int pointY;
    public int pointX;

    @Override
    public void setMap(Map map){
        Map newMap = map;
        this.map = newMap;
        int newSize = map.getSize();
        int startx = map.getStartingPointC();
        this.pointX = startx;
        int starty = map.getStartingPointR();
        this.pointY = starty;
    }

    @Override
    public void moveRight(){
        if(pointX != (size-1) && (map.getMap()[pointY][pointX+1] == '0' || map.getMap()[pointY][pointX+1] == 'P')) {
            pointX++;
        }
    }

    @Override
    public void moveLeft(){
        if(pointX != 0 && (map.getMap()[pointY][pointX-1] == '0' || map.getMap()[pointY][pointX-1] == 'P')){
            pointX--;
        }
    }

    @Override
    public void moveUp() {
        if(pointY != 0 && (map.getMap()[pointY-1][pointX] == '0' || map.getMap()[pointY-1][pointX] == 'P')) {
            pointY--;
        }
    }

    @Override
    public void moveDown(){
        if(pointY != (size-1) && (map.getMap()[pointY+1][pointX] == '0' || map.getMap()[pointY+1][pointX] == 'P')) {
            pointY++;
        }
    }

    @Override
    public Position getPosition() {
        return new Position(pointX, pointY);
    }
}

/*
            ==========================================
            |             POSITION CLASS             |
            ==========================================
*/
class Position {
    public int x;
    public int y;

    Position(int x, int y){
        int x1 = x;
        this.x = x1;
        int y1 =y;
        this.y = y1;
    }

    public void setX(int x) {
        int x1 = x;
        this.x = x1;
    }

    public void setY(int y) {
        int y1 = y;
        this.y = y1;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean equals(Position pos) {
        return x == pos.getX() && y == pos.getY();
    }

    public String toString(){
        return "("+x+","+y+")";
    }
}

/*
            ==========================================
            |       InvalidMapException CLASS        |
            ==========================================
*/
class InvalidMapException extends Exception{
    static final long serialVersionUID = 1L;
    InvalidMapException(){
        super("Not enough map elements");
    }
    InvalidMapException(String str){
        super(str);
    }
}

