package main.projects.raffle;

import java.util.concurrent.ThreadLocalRandom;

public class RandomizerUtils {

	protected int getRandomNumber(int min, int maxExcl) {
		return ThreadLocalRandom.current().nextInt(min, maxExcl);
	}
}
