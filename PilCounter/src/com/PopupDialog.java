package com;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class PopupDialog extends JDialog {

    private int tentativiScelti;

    public PopupDialog(JFrame parent) {
        super(parent, "Imposta tentativi", true);
        initComponents();
        setResizable(false);
    }

    private void initComponents() {
        JTextField textField = new JTextField(10);
        JButton confermaButton = new JButton("Conferma");

        confermaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    tentativiScelti = Integer.parseInt(textField.getText());
                    if (tentativiScelti > 0 && tentativiScelti <= 500) {
                        dispose();  // Chiudi la finestra di dialogo se il numero è valido
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(PopupDialog.this,
                            "Inserisci un numero valido compreso tra 1 e 500",
                            "Errore",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Chiudi l'applicazione se l'utente preme la "X"
                System.exit(0);
            }
        });
        setLayout(new FlowLayout());
        add(new JLabel("Inserisci il numero di tentativi: "));
        add(textField);
        add(confermaButton);

        pack();
        setLocationRelativeTo(null);  // Posiziona la finestra al centro dello schermo
    }

    public int getTentativi() {
        return tentativiScelti;
    }
}