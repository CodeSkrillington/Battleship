
public class Ship {

    private String name;
    private int size;
    private int row, col;
    private boolean isHorizontal;
    private int hits;
    

//Ship object constructor
    public Ship(String type, int size)
    {
        name = type;
        this.size = size;
    }

//Return size of ship
    public int getSize()
    {
        return size;
    }

//Return name of ship
    public String getName()
    {
        return name;
    }

//Method to return row position
    public int getRow()
    {
        return row;
    }

//Method to return column
    public int getCol()
    {
        return col;
    }

//Return if ship is horizontal or vertical
    public boolean isHorizontal()
    {
        return isHorizontal;
    }

//Method to place ships in verified spot
    public void place(int row, int col, boolean isHorizontal)
    {
        this.row = row;
        this.col = col;
        this.isHorizontal = isHorizontal;
    }

//Adjust size for successful hit of ship
    public void hit()
    {
        size--;
    }
//Check if ship is sunk
    public boolean isSunk()
    {
        if (size > 0)
        {
            return false;
        }
        else
        {
            return true;
        }
    }


}
