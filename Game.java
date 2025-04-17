public class Game {

    public static void main(String[] args) 
    {
        
        Player person = new Player(true);
        Player computer = new Player(false);
        person.placingShips(true);
        computer.placingShips(false);

        while (true) 
        { 
            System.out.println("Your board: ");
            person.getBoard().print(true);
            System.out.println("Enemy board: ");
            computer.getBoard().print(false);

            person.move(computer);
            if (computer.getBoard().allShipsSunk())
            {
                System.out.print("You are victorious!");
                break;
            }
            
            computer.moveAI(person);
            if (person.getBoard().allShipsSunk())
            {
                System.out.print("You have lost the battle!");
                break;
            }
        }
    }
}
