package Nemo;

public class Forward extends Command{

	public boolean findKey(char comando) {
		return comando == 'f';
	}

	public void executeCommands(Nemo nemo) {
		nemo.forward();
	}

}
