package Nemo;

public class Descend extends Command{

	public boolean findKey(char instruction) {
		return instruction == 'd';
	}

	public void executeCommands(Nemo nemo) {
		nemo.descend();
	}

}
