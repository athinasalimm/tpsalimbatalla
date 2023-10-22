package Nemo;

import java.util.Arrays;

import java.util.List;

public abstract class Command{
	
	public  static List<Command> commandslist = Arrays.asList(
		new Descend(),
		new Ascend(),
		new RightTurn(),
		new LeftTurn(),
		new Forward(),
		new EmptyMovement(),
		new CapsuleLaunching()
		);
	
	public static Nemo executeInstructions(char instruction, Nemo nemo) {
		commandslist.stream()
        .filter(commandObject -> commandObject.findKey(instruction)) 
        .forEach(commandObject  -> commandObject.executeCommands(nemo));
		return nemo;
	}
	
	public abstract boolean findKey(char comando);
	
	public abstract void executeCommands(Nemo nemo);
	
 }
