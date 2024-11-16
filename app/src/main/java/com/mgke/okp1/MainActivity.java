package com.mgke.okp1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private EditText inputD1, inputD2, inputGamma;
    private TextView resultText;
    private Button calculateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Инициализация элементов
        inputD1 = findViewById(R.id.input_d1);
        inputD2 = findViewById(R.id.input_d2);
        inputGamma = findViewById(R.id.input_gamma);
        resultText = findViewById(R.id.result_text);
        calculateButton = findViewById(R.id.calculate_button);

        // Обработчик нажатия кнопки для расчета
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Получаем значения из полей ввода
                String aString = inputD1.getText().toString();
                String bString = inputD2.getText().toString();
                String alphaString = inputGamma.getText().toString();

                // Проверка на пустые строки
                if (aString.isEmpty() || bString.isEmpty() || alphaString.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Заполните все поля", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Проверка на наличие недопустимых символов
                if (!aString.matches("[0-9.]+") || !bString.matches("[0-9.]+") || !alphaString.matches("[0-9.]+")) {
                    Snackbar.make(v, "Ввод содержит недопустимые символы!", Snackbar.LENGTH_LONG).show();
                    return;
                }

                // Преобразуем строки в числа
                double a = Double.parseDouble(aString);
                double b = Double.parseDouble(bString);
                double alpha = Double.parseDouble(alphaString);

                // Проверяем, что значения не отрицательные и угол не больше 90 градусов
                if (a < 0 || b < 0) {
                    Snackbar.make(v, "Значения a и b не могут быть отрицательными!", Snackbar.LENGTH_LONG).show();
                    return;
                }

                if (alpha < 0 || alpha > 90) {
                    Snackbar.make(v, "Угол α должен быть в диапазоне от 0 до 90 градусов!", Snackbar.LENGTH_LONG).show();
                    return;
                }

                // Рассчитываем результат
                double result = a * b * Math.sin(Math.toRadians(alpha));

                // Выводим результат
                resultText.setText("Результат: " + result);
            }
        });
    }
}