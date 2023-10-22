package Nemo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.Test;
import org.junit.jupiter.api.function.Executable;

public class NemoTest {

	@Test public void test00FindingNemo() {
		assertEquals ( NemoAtBase().getOrientation(), "north");
		assertEquals ( NemoAtBase().getX(), 0 );
		assertEquals ( NemoAtBase().getY(), 0 );
		assertEquals ( NemoAtBase().getProfundity(), 0 );
	}
	@Test public void test01NemoDoesNotMove() {
		NemoRecievesInstructionsAndGetsOrientation(NemoAtBase(), " ", "north");
		NemoRecievesInstructionsAndGetsX(NemoAtBase(), " ", 0);
		NemoRecievesInstructionsAndGetsY(NemoAtBase(), " ", 0);
		NemoRecievesInstructionsAndGetsProfundity(NemoAtBase(), " ", 0);
	}
	@Test public void test02NemoGoesUp() {
		NemoRecievesInstructionsAndGetsProfundity(NemoAtBase(), "u", 0);
		NemoRecievesInstructionsAndGetsProfundity(NemoAtBase(), "ddu", -1);
	}
	@Test public void test03NemoGoesDown() {
		NemoRecievesInstructionsAndGetsProfundity(NemoAtBase(), "d", -1);
	}
	@Test public void test04NemoGoesTwiceDown() {
		NemoRecievesInstructionsAndGetsProfundity(NemoAtBase(), "dd", -2);
	}
	@Test public void test05NemoGoesRight() {
		NemoRecievesInstructionsAndGetsOrientation(NemoAtBase(), "r", "east");
	}
	@Test public void test06NemoGoesLeft() {
		NemoRecievesInstructionsAndGetsOrientation(NemoAtBase(), "l", "west");
	}
	@Test public void test07NemoGoesForwardAndIncreasesYcoordinate() {
		NemoRecievesInstructionsAndGetsY(NemoAtBase(), "f", 1);
	}
	@Test public void test08NemoIncreasesXCoordinate() {
		NemoRecievesInstructionsAndGetsX(NemoAtBase(), "rf", 1);
	}
	@Test public void test09NemoReleasesCapsuleInsouthface() {
		NemoAtBase().sendInstructions("m");
	}
	@Test public void test10NemoReleasesCapsuleInFirstLevel() {
		NemoAtBase().sendInstructions("dm");
	}
	@Test public void test11NemoDoesEveryMovement() { 
		NemoRecievesInstructionsAndGetsOrientation(NemoAtBase(),"durlfm ", "north");
		NemoRecievesInstructionsAndGetsX(NemoAtBase(), "durlfm ", 0);
		NemoRecievesInstructionsAndGetsY(NemoAtBase(), "durlfm ", 1);
		NemoRecievesInstructionsAndGetsProfundity(NemoAtBase(), "durlfm", 0);
	}
	@Test public void test12NemoMovesNorthwards() {
		NemoRecievesInstructionsAndGetsOrientation(NemoAtBase(), "f", "north");
	}
	@Test public void test13NemoMovesFromNorthToSouth() {
		NemoRecievesInstructionsAndGetsOrientation(NemoAtBase(), "rr", "south");
		NemoRecievesInstructionsAndGetsOrientation(NemoAtBase(), "ll", "south");
	}
	@Test public void test14NemoMovesFromNorthTwest() {
		NemoRecievesInstructionsAndGetsOrientation(NemoAtBase(), "r", "east");
		NemoRecievesInstructionsAndGetsOrientation(NemoAtBase(), "lll", "east");
	}
	@Test public void test15NemoMovesFromNorthToWest() {
		NemoRecievesInstructionsAndGetsOrientation(NemoAtBase(), "rrr", "west");
		NemoRecievesInstructionsAndGetsOrientation(NemoAtBase(), "l", "west");
	}
	@Test public void test16NemoReturnsToOriginalCoordinates() {
		NemoRecievesInstructionsAndGetsOrientation(NemoAtBase(),"rrrr", "north");
		NemoRecievesInstructionsAndGetsOrientation(NemoAtBase(),"llll", "north");
	}
	@Test public void test17NemoMovesfromEastToWest() {
		assertEquals (new Nemo(new Coordinates(0,0), new EastOrientation()).sendInstructions("rr").getOrientation(), "west" );
		assertEquals (new Nemo(new Coordinates(0,0), new EastOrientation()).sendInstructions("ll").getOrientation(), "west" );
	}
	
	@Test public void test18NemoGoesEast() {
		NemoRecievesInstructionsAndGetsOrientation(NemoAtBase(), "rllrfur", "east");
	}
	@Test public void test19NemoGoesSouth() {
		NemoRecievesInstructionsAndGetsOrientation(NemoAtBase(), "lllr", "south");
	}
	@Test public void test20NemoRetursToBaseCoordinates() {
		NemoRecievesInstructionsAndGetsX(NemoAtBase(), "ffrrff", 0);
		NemoRecievesInstructionsAndGetsY(NemoAtBase(), "ffrrff ", 0);
	}
	@Test public void test21NemoStartsInAnyLocationAndMoves() {
		NemoRecievesInstructionsAndGetsY(new Nemo(new Coordinates(8,-8), new NorthOrientation()), "lffrurmdd", -8);
		NemoRecievesInstructionsAndGetsX(new Nemo(new Coordinates(8,-8), new NorthOrientation()), "lffrurmdd", 6);
	}
	@Test public void test22NemoStartsInAnyOrientationAndMoves() {
		NemoRecievesInstructionsAndGetsOrientation(new Nemo(new Coordinates(3, 13), new EastOrientation()), "rllmrfurdd", "south");
	}
	@Test public void test23NemoRemainsInPlaceAfterLauchingMissile() {
		NemoRecievesInstructionsAndGetsX(new Nemo(new Coordinates(8,40), new NorthOrientation()), "m", 8);
		NemoRecievesInstructionsAndGetsY(new Nemo(new Coordinates(8,40), new NorthOrientation()), "m", 40);
	}
	@Test public void test24NemoMovesDiagonal() {
		NemoRecievesInstructionsAndGetsX(NemoAtBase(), "rfdrfd" , 1);
		NemoRecievesInstructionsAndGetsY(NemoAtBase(), "rfdrfd", -1);
		NemoRecievesInstructionsAndGetsOrientation(NemoAtBase(), "rfdrfd", "south");
		NemoRecievesInstructionsAndGetsProfundity(NemoAtBase(), "rfdrfd", -2);
	}
	@Test public void test25NemoCannotThrowCapsuleInProfundityLessThanLevel1() {
		Nemo nemo = NemoAtBase();
		nemo.sendInstructions("duddrff");
		assertThrowsLike(() -> nemo.sendInstructions("m"), DepthState.NemoCannotThrowCapsule);
	}
	  private void assertThrowsLike(Executable executable, String message){
		  assertEquals(message, assertThrows(Error.class, executable).getMessage());
	  }
	  private Nemo NemoAtBase() {
		  return new Nemo(new Coordinates(0,0), new NorthOrientation());
		}
	  private void NemoRecievesInstructionsAndGetsOrientation(Nemo nemo, String instructions, String orientationToBe) {
			assertEquals ( nemo.sendInstructions(instructions).getOrientation(), orientationToBe );
		}
	  private void NemoRecievesInstructionsAndGetsX(Nemo nemo, String instructions, int xCoordinateToBe) {
		  assertEquals ( nemo.sendInstructions(instructions).getX(), xCoordinateToBe );
	  }
	  private void NemoRecievesInstructionsAndGetsY(Nemo nemo, String instructions, int yCoordinateToBe) {
		  assertEquals ( nemo.sendInstructions(instructions).getY(), yCoordinateToBe);
	  }
	  private void NemoRecievesInstructionsAndGetsProfundity(Nemo nemo, String instructions, int zCoordinateToBe) {
		  assertEquals (nemo.sendInstructions(instructions).getProfundity(), zCoordinateToBe);
	  }
	  
}