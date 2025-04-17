import java.util.ArrayList;
import java.util.Arrays;

public class Board {

    private int gSize = 10;
    private char[][] grid = new char[gSize][gSize];
    private ArrayList<Ship> ships = new ArrayList<>();

// Loops through each row of the grid and fills it with
// ~ representing water
    public Board() 
    {
        for (char[] row : grid) 
        {
            Arrays.fill(row, '~');
        }
    }

// Checks if piece can be placed if it can it will place it
    public boolean placeShip(Ship ship, int row, int col, boolean isHorizontal) {
        int size = ship.getSize();

        if (isHorizontal) 
        {
// Return false if ship will go out of borders
            if (col + size > gSize) 
            {
                return false;
            }
// Loops the area ship will be place and
// if ship does not encounter water will return false
            for (int i = 0; i < size; i++) 
            {
                if (grid[row][col + i] != '~') 
                {
                    return false;
                }
            }
// If the area it will be placed is valid will place the ship
            for (int i = 0; i < size; i++) 
            {
                grid[row][col + i] = 'S';
            }

        } 
        else 
        {
// Check if it goes out of borders
            if (row + size > gSize) 
            {
                return false;
            }
            for (int i = 0; i < size; i++) 
            {
                if (grid[row + i][col] != '~') 
                {
                    return false;
                }
            }
            for (int i = 0; i < size; i++) 
            {
                grid[row + i][col] = 'S';
            }
        }
        ship.place(row, col, isHorizontal);
        ships.add(ship);
        return true;
    }

    public boolean attackHits(int row, int col) 
    {
        int size;
// Returns false if that area has already been hit
        if (grid[row][col] == 'X' || grid[row][col] == '0') 
        {
            return false;
        }
// If that location is a ship
        if (grid[row][col] == 'S') 
        {
// Changes it to X to mark the location as a hit
            grid[row][col] = 'X';
// Loops through the ships locations to see if there is a ship there
            for (Ship ship : ships) 
            {
                if (isHit(ship, row, col)) 
                {
                    ship.hit();
                }
            }
            return true;
        } 
        else 
        {
// If it doesn't hit replaces that location to 0 marking a miss
            grid[row][col] = '0';
            return false;
        }
    }

// Loops through all the ships to see if they have all been sunk
    public boolean allShipsSunk()
    {
        for (Ship ship : ships)
        {
            if (!ship.isSunk())
            {
                return false;
            }
        }
        return true;
    }

    public void print(boolean revealShips)
    {
        System.out.print("  ");
        for (int col = 0; col < gSize; col++)
        {
            System.out.print(col + " ");
        }
        System.out.println();

        for (int row = 0; row < gSize; row++)
        {
            System.out.print(row + " ");
            for (int col = 0; col < gSize; col++)
            {
                char c = grid[row][col];
                if (!revealShips && c == 'S')
                {
                    System.out.print("~ ");
                } else {
                    System.out.print(c + " ");
                }
            }
            System.out.println();
        }
    }

// Checks if target corresponds to a ship location
    public boolean isHit(Ship ship, int row, int col)
    {
        int r = ship.getRow();
        int c = ship.getCol();

        for (int i = 0; i < ship.getSize(); i++)
        {
// If ship is horizontal uses left value if not uses right value
            int checkRow = ship.isHorizontal() ? r : r + i;
            int checkCol = ship.isHorizontal() ? c + i : c;
            if (checkRow == row && checkCol == col)
            {
                return true;
            }
        }
        return false;
    }
}