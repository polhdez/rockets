package rockets;

public class Rocket extends Thread {

	private String name;
	private Propeller[] propellers;
	private int speed = 0;
	private int goalSpeed = 0;
	public int speedStep;

	public Rocket(String name, int nPropellers, int maxPotency) {
		this.name = name;
		this.propellers = new Propeller[nPropellers];
		for (int i = 0; i < nPropellers; i++) {
			this.propellers[i] = new Propeller(maxPotency);
		}
		this.speedStep = 100 * nPropellers;
		this.startPropellers();
	}

	public void accelerate() {
		this.speed = this.getSpeed();
		int goalSpeed = this.speed + this.speedStep;
		this.setPropellersPotency(goalSpeed);
	}

	public void decelerate() {
		this.speed = this.getSpeed();
		int goalSpeed = this.speed - this.speedStep;
		this.setPropellersPotency(goalSpeed);
	}

	public void getInfo() {
		System.out.println("Name: " + this.name);
		System.out.println("Goal Speed: " + this.goalSpeed);
		System.out.println("Current Speed: " + this.getSpeed());
	}

	public int getSpeed() {
		int totalPotency = getPropellersPotency();
		return (int) (100 * Math.sqrt(totalPotency));
	}

	private int getPropellersPotency() {
		int totalPotency = 0;
		for (Propeller propeller : this.propellers) {
			totalPotency += propeller.getPotency();
		}
		return totalPotency;
	}

	private void setPropellersPotency(int goalSpeed) {
		int totalPotency = (int) Math.pow(goalSpeed / 100, 2);
		int potency = totalPotency / this.propellers.length;
		for (Propeller propeller : this.propellers) {
			propeller.setPotency(potency);
		}
	}

	private void startPropellers() {
		System.out.println("[*] Starting propellers...");
		for (Propeller propeller : this.propellers) {
			propeller.start();
		}
	}

	public void stopPropellers() {
		System.out.println("[*] Stopped propellers!");
		for (Propeller propeller : this.propellers) {
			propeller.cancel();
		}
	}
}
