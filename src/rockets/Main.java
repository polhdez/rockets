package rockets;

import javax.swing.JOptionPane;

public class Main {

	public static void main(String[] args) {

		String name = promptGetName();
		int nPropellers = promptGetNPropellers();
		int maxPotency = promptGetPotency();
		Rocket rocket = new Rocket(name, nPropellers, maxPotency);
		rocket.start();

		while (true) {
			int option;
			String[] buttons = { "Accelerate", "Decelerate", "Modify speed step (now " + rocket.speedStep + ")",
					"Quit" };
			option = JOptionPane.showOptionDialog(null, "Rocket Control Panel", "Confirmation",
					JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, buttons, buttons[3]);
			switch (option) {
			case 0:
				rocket.accelerate();
				break;
			case 1:
				rocket.decelerate();
				break;
			case 2:
				promptGetSpeedStep(rocket);
				break;
			case 3:
				rocket.stopPropellers();
				System.out.println("[!] Exiting!");
				return;
			}
		}
	}

	private static String promptGetName() {
		String name;
		while (true) {
			name = JOptionPane.showInputDialog("Create a rocket with name");
			if (!name.equals("")) {
				break;
			}
			JOptionPane.showMessageDialog(null, "Wrong input!");
		}
		return name;
	}

	private static int promptGetNPropellers() {
		int nPropellers;
		while (true) {
			try {
				nPropellers = Integer.parseInt(JOptionPane.showInputDialog("Enter the number of propellers"));
				break;
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Wrong input!");
			}
		}
		return nPropellers;
	}

	private static void promptGetSpeedStep(Rocket rocket) {
		while (true) {
			try {
				rocket.speedStep = Integer.parseInt(JOptionPane.showInputDialog("Enter the new speed step"));
				break;
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Wrong input!");
			}
		}
	}

	private static int promptGetPotency() {
		int potency;
		while (true) {
			try {
				potency = Integer.parseInt(JOptionPane.showInputDialog("Max propeller potency"));
				break;
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Wrong input!");
			}
		}
		return potency;
	}
}