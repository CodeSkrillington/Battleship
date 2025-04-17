import java.util.Random;
import java.util.Scanner;

public class Player {

    private boolean isHuman;
    public Board board = new Board();
    Scanner scanner = new Scanner(System.in);

//Player object constructor
    public Player(boolean isHuman)
    {
        this.isHuman = isHuman;
    }

//Return if player or computer
    public boolean getLifeform()
    {
        return isHuman;
    }

//Return board
    public Board getBoard()
    {
        return board;
    }

//Method for making and placing ships
    public void placingShips(boolean isHuman)
    {
        if (isHuman)
        {
            Ship destroyer = new Ship("Destroyer", 2);
            placeSingleShip(destroyer);
            Ship submarine = new Ship("Submarine", 3);
            placeSingleShip(submarine);
            Ship cruiser = new Ship("Cruiser", 3);
            placeSingleShip(cruiser);
            Ship battleship = new Ship("Battleship", 4);
            placeSingleShip(battleship);
            Ship carrier = new Ship("Carrier", 5);
            placeSingleShip(carrier);
        }
        else
        {
            Ship destroyer = new Ship("Destroyer", 2);
            placeSingleShipAI(destroyer);
            Ship submarine = new Ship("Submarine", 3);
            placeSingleShipAI(submarine);
            Ship cruiser = new Ship("Cruiser", 3);
            placeSingleShipAI(cruiser);
            Ship battleship = new Ship("Battleship", 4);
            placeSingleShipAI(battleship);
            Ship carrier = new Ship("Carrier", 5);
            placeSingleShipAI(carrier);
        }

    }

//Method for placing single ships
    private void placeSingleShip(Ship ship)
    {
        boolean placed = false;

        while (!placed)
        {
            System.out.println("Placing " + ship.getName() + " (size: " + ship.getSize() + ")");
            System.out.println("Enter Row: ");
            int row = scanner.nextInt();
            System.out.println("Enter Column: ");
            int col = scanner.nextInt();
            System.out.println("Horizontal? true/false");
            boolean horizontal = scanner.nextBoolean();

            placed = board.placeShip(ship, row, col, horizontal);

            if (!placed) 
            {
                System.out.println("Invalid Placement");
            }
        }
    }

//Method for placing single ships randomly for AI
private void placeSingleShipAI(Ship ship)
{
    boolean placed = false;
    Random rand = new Random();
    while (!placed)
    {
        System.out.println("Placing " + ship.getName() + " (size: " + ship.getSize() + ")");
        int row = rand.nextInt(10);
        int col = rand.nextInt(10);
        boolean horizontal = rand.nextBoolean();

        placed = board.placeShip(ship, row, col, horizontal);

        if (!placed) 
        {
            System.out.println("Invalid Placement");
        }
    }
}
    public void move(Player opponent)
    {
        int row, col;
        boolean moveMade = false;

        while (!moveMade)
        {
            System.out.println("Enter attack row: ");
            row = scanner.nextInt();
            System.out.println("Enter attack column: ");
            col = scanner.nextInt();
            
            if (opponent.getBoard().attackHits(row, col))
            {
                System.out.println("Hit!");
            }
            else
            {
                System.out.println("Miss.");
            }
            moveMade = true;
        }
    }

    public void moveAI(Player opponent)
    {
        int row, col;
        boolean moveMade = false;
        Random rand = new Random();

        while (!moveMade)
        {
            
            row = rand.nextInt(10);
            col = rand.nextInt(10);
            
            if (opponent.getBoard().attackHits(row, col))
            {
                System.out.println("AI has hit one of your ships!");
            }
            else
            {
                System.out.println("AI has missed.");
            }
            moveMade = true;
        }
    }
}
