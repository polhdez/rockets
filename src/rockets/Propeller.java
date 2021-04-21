package rockets;

public class Propeller extends Thread {

	private int potency = 0;
	private int goalPotency = 0;
	private int maxPotency = 0;
	private Boolean accelerating = false;
	private Boolean decelerating = false;
	private Boolean running = true;

	public Propeller(int maxPotency) {
		this.maxPotency = maxPotency;
	}

	public void setPotency(int goalPotency) {
		if (goalPotency < this.potency) {
			this.decelerating = true;
			this.accelerating = false;
		} else {
			this.decelerating = false;
			this.accelerating = true;
		}
		if (potency <= this.maxPotency) {
			this.goalPotency = goalPotency;
			System.out.println("[*] Goal potency set to: " + this.goalPotency);
		} else {
			System.out.println("[!] Warning, propeller overloaded! Defaulting to max potency: " + this.maxPotency);
			this.goalPotency = this.maxPotency;
		}
	}

	public double getPotency() {
		return this.potency;
	}

	public double getMaxPotency() {
		return this.maxPotency;
	}

	@Override
	public void run() {
		while (this.running) {
			if (this.accelerating) {
				if (this.goalPotency > this.potency) {
					this.potency++;
					System.out.println("Increased potency to " + this.potency);
				} else {
					System.out.println("[*] Target potency reached! " + this.potency);
					this.accelerating = false;
				}
			}
			if (this.decelerating) {
				if (this.goalPotency < this.potency) {
					this.potency--;
					System.out.println("Decreased potency to " + this.potency);
				} else {
					System.out.println("[*] Target potency reached! " + this.potency);
					this.decelerating = false;
				}
			}
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void cancel() {
		this.running = false;
	}
}
