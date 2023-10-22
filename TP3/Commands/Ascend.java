package Nemo;

public class Ascend extends Command{

	public boolean findKey(char instruction) {
		return instruction == 'u';
	}

	public void executeCommands(Nemo nemo) {
		nemo.ascend();
	}


}
