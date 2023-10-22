package Nemo;

public class RightTurn extends Command {

	public boolean findKey(char instruction) {
		return instruction == 'r';
	}

	public void executeCommands(Nemo nemo) {
		nemo.turnRight();
	}

}
