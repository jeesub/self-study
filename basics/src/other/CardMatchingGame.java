package other;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CardMatchingGame extends JFrame {
    /**
     * Serial Number.
     */
    private static final long serialVersionUID = 1L;
    private static final int CARDS_SIZE = 16;
    private static final String DEFAULT_CARD_FACE = "X";
    private static final String LINE_BREAK = new String("-").repeat(60);
    private static enum GameIs {
        WAITING,
        LISTENING,
        ONE_CARD_IS_FLIPPED,
        TWO_CARDS_ARE_FLIPPED,
    }
    private GameIs gameManager;

    private JButton startButton;
    private JTextField countTextField;
    private Map<JButton, Card> cards;
    private String[] cardValues;
    private int count;
    private int matched;
    private Card flipped;

    public CardMatchingGame() {
        gameManager = GameIs.WAITING;
        cards = new HashMap<>();
        setTitle("Card Matching Game");
        setSize(500, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Font font = new Font(Font.SANS_SERIF, Font.PLAIN, 24);
        Font cardFont = new Font(Font.MONOSPACED, Font.BOLD, 100);

        MyActionListener listener = new MyActionListener();

        JPanel pane = new JPanel();
        pane.setFont(font);
        JLabel topLineBreakLabel = new JLabel(LINE_BREAK);
        pane.add(topLineBreakLabel);
        startButton = new JButton("Start");
        startButton.addActionListener(listener);
        pane.add(startButton);
        JLabel label = new JLabel("Count: ");
        pane.add(label);
        countTextField = new JTextField(5);
        countTextField.setEditable(false);
        pane.add(countTextField);
        JLabel bottomLineBreakLabel = new JLabel(LINE_BREAK);
        pane.add(bottomLineBreakLabel);

        cardValues = new String[CARDS_SIZE];
        for (int i = 0; i < CARDS_SIZE; i++) {
            cardValues[i] = Integer.toString((i + 2) / 2);
        }

        for (int i = 0; i < CARDS_SIZE; i++) {
            JButton button = new JButton(cardValues[i]);
            button.addActionListener(listener);
            button.setFont(cardFont);
            button.setEnabled(false);
            Card card = new Card(button, cardValues[i]);
            cards.put(button, card);
            pane.add(button);
        }

        setContentPane(pane);
        setVisible(true);
    }

    private class MyActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            if (event.getSource() == startButton) {
                shuffleArray(cardValues);
                int i = 0;
                for (Map.Entry<JButton, Card> entry : cards.entrySet()) {
                    Card card = entry.getValue();
                    card.setValue(cardValues[i]);
                    card.hide();
                    cards.put(entry.getKey(), card);
                    i++;
                }
                count = 0;
                countTextField.setText(Integer.toString(count));
                matched = 0;
                gameManager = GameIs.LISTENING;
                startButton.setEnabled(false);
            }
            if (cards.containsKey(event.getSource())) {
                Card card = cards.get(event.getSource());
                card.reveal();
                count++;
                countTextField.setText(Integer.toString(count));
                if (gameManager == GameIs.LISTENING) {
                    flipped = card;
                    gameManager = GameIs.ONE_CARD_IS_FLIPPED;
                    return;
                }
                if (gameManager == GameIs.ONE_CARD_IS_FLIPPED) {
                    flipped.getButton().setText(flipped.getValue());
                    if (card != flipped && card.getValue().equals(flipped.getValue())) {
                        matched += 2;
                        flipped.isMatched();
                        card.isMatched();
                        gameManager = GameIs.LISTENING;
                    } else {
                        gameManager = GameIs.LISTENING;
                        FlipWrongCardsThread thread = new FlipWrongCardsThread(flipped, card);
                        thread.start();
                    }
                }
                if (matched == CARDS_SIZE) {
                    gameManager = GameIs.WAITING;
                    startButton.setEnabled(true);
                }
            }
        }
    }

    private class Card {
        private JButton button;
        private String value;

        private Card(JButton newButton, String newValue) {
            button = newButton;
            value = newValue;
        }

        private JButton getButton() {
            return button;
        }

        private void setValue(String newValue) {
            value = newValue;
        }

        private String getValue() {
            return value;
        }

        private void hide() {
            button.setText(DEFAULT_CARD_FACE);
            button.setEnabled(true);
        }

        private void reveal() {
            button.setText(value);
            button.setEnabled(false);
        }

        private void isMatched() {
            button.setText(value);
            button.setEnabled(false);
        }
    }

    private class FlipWrongCardsThread extends Thread {
        Card firstCard;
        Card secondCard;

        private FlipWrongCardsThread(Card newFirstCard, Card newSecondCard) {
            firstCard = newFirstCard;
            secondCard = newSecondCard;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(1000);
                firstCard.hide();
                secondCard.hide();
            } catch (InterruptedException e) {
                throw new AssertionError(e);
            }
        }
    }

    private static void shuffleArray(String[] input) {
        Random random = new Random();
        for (int i = 1; i < input.length; i++) {
            int j = random.nextInt(i);
            swap(input, i, j);
        }
    }

    private static void swap(String[] input, int i, int j) {
        String tmp = input[i];
        input[i] = input[j];
        input[j] = tmp;
    }

    public static void main(String[] args) {
        new CardMatchingGame();
    }

}
