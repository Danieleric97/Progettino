package com;
import java.awt.*;
import java.awt.event.ActionEvent;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;
import java.util.Map;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

public final class Finestra extends JFrame {

	private final Map<JButton, Integer> punteggiBottoni = new HashMap<>();
	private final Map<JButton, Integer> punteggiBottoni2 = new HashMap<>();
	private final Stack<JButton> bottoniCliccati = new Stack<>();
	
    JButton[] bottoni = {
        new JButton("Out Of Lab"),
        new JButton("Rivale (1)"),
        new JButton("Brock"),
        new JButton("MonteLuna"),
        new JButton("Rivale Ponte"),
        new JButton("Rivale M/N Anna"),
        new JButton("Misty"),
        new JButton("LT Surge"),
        new JButton("Tunnel Roccioso"),
        new JButton("Rivale Torre Lavandonia"),
        new JButton("Erika"),
        new JButton("Dojo Karate"),
        new JButton("Koga"),
        
    };
   
    JButton[] bottoni2 = {
    	new JButton("Rivale Silph"),
        new JButton("Silph"),
        new JButton("Sabrina"),
        new JButton("Blaine"),
        new JButton("Villa Pokémon"),
        new JButton("Giovanni"),
        new JButton("Rivale Prelega"),
        new JButton("Via Vittoria"),
        new JButton("Lorelei"),
        new JButton("Bruno"),
        new JButton("Agatha"),
        new JButton("Lance"),
        new JButton("Campione")
    };
   
    JLabel tentativiLabel = new JLabel("TENATIVI: ");
    JTextField tentativiCountTextField = new JTextField("1", 3);
    JLabel tentativiCountLabel = new JLabel("/ ");  // Inizializzato con un valore predefinito
    JPanel tentativiPanel = new JPanel();
    JLabel totalLabel = new JLabel("TOTALE:");
    JLabel totalScoreLabel = new JLabel("0");
    JPanel totalPanel = new JPanel();
    JButton indietroButton = new JButton("Indietro");
    
    public Finestra() {
        super("PilCalculator");
        showInputDialog();
        
        punteggiBottoni.put(bottoni[0], 20);  // Imposta il punteggio per il primo bottone
        punteggiBottoni.put(bottoni[1], 10);  // Imposta il punteggio per il secondo bottone
        punteggiBottoni2.put(bottoni2[0], 20);  // Imposta il punteggio per il primo bottone
        punteggiBottoni2.put(bottoni2[1], 10);  // Imposta il punteggio per il secondo bottone
        
        JPanel pannello = new JPanel();
        setContentPane(pannello);
        setSize(800, 600);
        pannello.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        pannello.setLayout(new BorderLayout());  

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        Dimension buttonSize = new Dimension(150, 30);
        buttonsPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        for (JButton button : bottoni) {
            button.setPreferredSize(buttonSize);
            button.setMinimumSize(buttonSize);
            button.setMaximumSize(buttonSize);
            button.setHorizontalAlignment(SwingConstants.LEFT);
            button.setLayout(new BorderLayout());
            

            // Aggiungi JLabel per il punteggio
            JLabel scoreLabel = new JLabel("0");
            scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
            scoreLabel.setForeground(Color.BLACK); // Testo bianco
            scoreLabel.setOpaque(true);
            scoreLabel.setMinimumSize(new Dimension(140, buttonSize.height)); // Larghezza minima
            Integer punteggio=punteggiBottoni.get(button);
            
            button.addActionListener(e -> incrementScore(scoreLabel, totalScoreLabel, punteggio, e));
            buttonsPanel.add(button);
            buttonsPanel.add(Box.createRigidArea(new Dimension(10, 0))); // Spazio tra il pulsante e il punteggio
            buttonsPanel.add(scoreLabel);
            buttonsPanel.add(Box.createRigidArea(new Dimension(20, 0))); // Spazio tra le colonne
        }

        for (JButton button : bottoni2) {
            button.setPreferredSize(buttonSize);
            button.setMinimumSize(buttonSize);
            button.setMaximumSize(buttonSize);
            button.setHorizontalAlignment(SwingConstants.LEFT);
            button.setLayout(new BorderLayout());
            

            // Aggiungi JLabel per il punteggio
            JLabel scoreLabel = new JLabel("0");
            scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
            scoreLabel.setForeground(Color.BLACK); // Testo bianco
            scoreLabel.setOpaque(true);
            scoreLabel.setMinimumSize(new Dimension(140, buttonSize.height)); // Larghezza minima
            Integer punteggio=punteggiBottoni2.get(button);
            button.addActionListener(e -> incrementScore(scoreLabel, totalScoreLabel, punteggio, e));
            buttonsPanel.add(button);
            buttonsPanel.add(Box.createRigidArea(new Dimension(10, 0))); // Spazio tra il pulsante e il punteggio
            buttonsPanel.add(scoreLabel);
            buttonsPanel.add(Box.createRigidArea(new Dimension(20, 0))); // Spazio tra le colonne
            
        }
        
        // Aggiungi il pannello dei tentativi in basso a sinistra
        
        tentativiPanel.setLayout(new BoxLayout(tentativiPanel, BoxLayout.X_AXIS));
        tentativiPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        tentativiPanel.add(tentativiLabel);
        tentativiPanel.add(tentativiCountTextField);
        tentativiPanel.add(tentativiCountLabel);
        PlainDocument doc = (PlainDocument) tentativiCountTextField.getDocument();
        doc.setDocumentFilter(new DocumentFilter() {
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                int currentLength = fb.getDocument().getLength();
                int maxLength = 3;

                if (currentLength + text.length() - length <= maxLength && text.matches("\\d*")) {
                    super.replace(fb, offset, length, text, attrs);
                } else {
                    // Fai qualcosa in caso di inserimento non valido
                    Toolkit.getDefaultToolkit().beep();  // Suono di errore
                }
            }
        });

        
        pannello.add(tentativiPanel, BorderLayout.NORTH);  // Posizionato in alto
        pannello.add(Box.createRigidArea(new Dimension(0, 20))); // Spazio tra le colonne
        

        indietroButton.setHorizontalAlignment(SwingConstants.LEFT);
        totalPanel.add(indietroButton);
        totalPanel.add(Box.createRigidArea(new Dimension(10, 0))); // Spazio tra il nuovo bottone e totalLabel
        indietroButton.addActionListener(e -> annullaUltimoPunteggio());
        
        // Aggiungi la voce "TOTALE" al pannello in basso a destra
        totalPanel.add(totalLabel);
        totalPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        totalPanel.add(Box.createHorizontalGlue());
        totalPanel.setLayout(new BoxLayout(totalPanel, BoxLayout.X_AXIS));
        // Aggiungi la voce "TOTALE" al pannello in basso a destra
        totalPanel.add(totalLabel);
        totalPanel.add(Box.createRigidArea(new Dimension(10, 0)));

        totalScoreLabel.setPreferredSize(new Dimension(140, 30));
        totalScoreLabel.setMinimumSize(new Dimension(140, 30));
        totalScoreLabel.setMaximumSize(new Dimension(140, 30));
        totalScoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
        totalScoreLabel.setForeground(Color.WHITE);
        totalScoreLabel.setOpaque(true);
        totalScoreLabel.setBackground(Color.BLACK);

        // Aggiungi il conteggio totale al pannello in basso a destra
        totalPanel.add(totalScoreLabel);

        // Aggiungi il pannello in basso a destra al pannello principale
        pannello.add(totalPanel, BorderLayout.SOUTH);
        pannello.add(buttonsPanel, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    private void decrementScore(JLabel scoreLabel, JLabel totalScoreLabel, Integer detrazione) {
    	Integer currentScore = Integer.parseInt(scoreLabel.getText());
    	Integer totalScore = Integer.parseInt(totalScoreLabel.getText());
        currentScore -= detrazione;
        totalScore -= detrazione;
        scoreLabel.setText(Integer.toString(currentScore));
        totalScoreLabel.setText(Integer.toString(totalScore));
    }
    
    private void incrementScore(JLabel scoreLabel, JLabel totalScoreLabel, Integer punteggio, ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            JButton sourceButton = (JButton) e.getSource();

            // Verifica se il bottone sorgente è tra i bottoni principali
            if (Arrays.asList(bottoni).contains(sourceButton) || Arrays.asList(bottoni2).contains(sourceButton)) {
                Integer currentScore = Integer.parseInt(scoreLabel.getText());
                Integer totalScore = Integer.parseInt(totalScoreLabel.getText());
                currentScore += punteggio;
                totalScore += punteggio;
                scoreLabel.setText(Integer.toString(currentScore));
                totalScoreLabel.setText(Integer.toString(totalScore));

                // Aggiungi il bottone allo stack
                bottoniCliccati.push(sourceButton);
            }
        }
    }

private void annullaUltimoPunteggio() {
    if (!bottoniCliccati.isEmpty()) {
        JButton ultimoBottoneCliccato = bottoniCliccati.pop();
        JLabel scoreLabel = getScoreLabelForButton(ultimoBottoneCliccato);
        decrementScore(scoreLabel, totalScoreLabel, punteggiBottoni.get(ultimoBottoneCliccato));
    }
}

// Metodo per ottenere la JLabel del punteggio associata a un bottone
private JLabel getScoreLabelForButton(JButton button) {
    Container parent = button.getParent();
    Component[] components = parent.getComponents();
    for (Component component : components) {
        if (component instanceof JLabel) {
            return (JLabel) component;
        }
    }
    return null;
}
    private void showInputDialog() {
        PopupDialog dialog = new PopupDialog(this);
        dialog.setVisible(true);

        int tentativiScelti = dialog.getTentativi();
        if (dialog.getTentativi() > 0 && dialog.getTentativi() <= 500) {
            tentativiCountLabel.setText("/ " + tentativiScelti);
            // Visualizza la finestra principale solo se il numero è valido
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setVisible(true);
        }
    }
    
}