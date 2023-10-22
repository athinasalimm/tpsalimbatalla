package Nemo;

public class LeftTurn extends Command{

	public boolean findKey(char instruction) {
		return instruction == 'l';
	}

	public void executeCommands(Nemo nemo) {
		nemo.turnLeft(); 

		
	}

}
