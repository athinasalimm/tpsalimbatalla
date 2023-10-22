package Nemo;

public class EmptyMovement extends Command{

	public boolean findKey(char comando) {
		return comando == ' ';
	}

	public void executeCommands(Nemo nemo) {}

}
