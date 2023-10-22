package Nemo;

public class CapsuleLaunching extends Command{

	public boolean findKey(char instructions) {
		return instructions == 'm';
	}

	public void executeCommands(Nemo nemo) {
		nemo.capsuleLaunching();
	}

}
