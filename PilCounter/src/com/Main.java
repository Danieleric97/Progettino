package com;

import javax.swing.SwingUtilities;

public class Main {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
            Finestra finestra = new Finestra();
        });
    }
}
