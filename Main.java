import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Калькулятор локальных максимумов и минимумов");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 700);
        frame.setLayout(null);


        JTextArea arrayArea = new JTextArea();
        arrayArea.setBounds(20, 60, 360, 100);
        arrayArea.setLineWrap(true);
        arrayArea.setEditable(true);
        frame.add(arrayArea);

        JButton submitButton = new JButton("посчитать");
        submitButton.setBounds(20, 180, 100, 30);
        frame.add(submitButton);

        JTextArea resultArea = new JTextArea();
        resultArea.setBounds(20, 220, 360, 50);
        resultArea.setEditable(false);
        frame.add(resultArea);

        JButton clearButton = new JButton("очистить");
        clearButton.setBounds(140, 180, 100, 30);
        frame.add(clearButton);

        JButton randomButton = new JButton("случайные числа");
        randomButton.setBounds(260, 180, 130, 30);
        frame.add(randomButton);

        NumberList numberList = new NumberList();

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                arrayArea.setText("");
                resultArea.setText("");
            }
        });

        randomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Random random = new Random();
                int size = random.nextInt(10) + 1; // Генерируем случайное число от 1 до 10
                int[] randomNumbers = numberList.generateRandomNumbers(size);
                String numbersText = "";
                for (int num : randomNumbers) {
                    numbersText += num + " ";
                }
                arrayArea.setText(numbersText);
            }
        });


        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] inputNumbers = arrayArea.getText().split("\\s+");

                int[] numbers = new int[inputNumbers.length];
                for (int i = 0; i < inputNumbers.length; i++) {
                    numbers[i] = Integer.parseInt(inputNumbers[i]);
                }

                // макс и мин
                int localMaxCount = 0;
                int localMinCount = 0;
                for (int i = 1; i < numbers.length - 1; i++) {
                    if (numbers[i - 1] < numbers[i] && numbers[i] > numbers[i + 1]) {
                        localMaxCount++;
                    } else if (numbers[i - 1] > numbers[i] && numbers[i] < numbers[i + 1]) {
                        localMinCount++;
                    }
                }

                resultArea.setText("Количество локальных максимумов: " + localMaxCount + "\n" +
                        "Количество локальных минимумов: " + localMinCount);
            }
        });

        frame.setVisible(true);
    }

    static class NumberList {
        public int[] generateRandomNumbers(int size) {
            int[] randomNumbers = new int[size];
            for (int i = 0; i < size; i++) {
                randomNumbers[i] = (int) (Math.random() * 100);
            }
            return randomNumbers;
        }
    }
}
