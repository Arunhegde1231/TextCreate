package com.example.textcreate;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private EditText editText;

    private File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editTextText);
        Button createButton = findViewById(R.id.createButton);
        Button saveButton = findViewById(R.id.saveButton);
        Button openButton = findViewById(R.id.openButton);

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createFile();
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveToFile();
            }
        });

        openButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFile();
            }
        });
    }

    private void createFile() {
        String fileName = "example.txt";
        File documentsFolder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
        file = new File(documentsFolder, fileName);
        try {
            if (file.createNewFile()) {
                Toast.makeText(MainActivity.this, "File created successfully", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "File already exists", Toast.LENGTH_SHORT).show();
            }
        } catch (IOException e) {
            Toast.makeText(MainActivity.this, "Failed to create file", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    private void saveToFile() {
        if (file == null) {
            Toast.makeText(MainActivity.this, "First create a file", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            FileWriter writer = new FileWriter(file);
            writer.write(editText.getText().toString());
            writer.close();
            Toast.makeText(MainActivity.this, "Content saved successfully", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(MainActivity.this, "Failed to save content", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    private void openFile() {
        if (file == null || !file.exists()) {
            Toast.makeText(MainActivity.this, "File does not exist", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            StringBuilder stringBuilder = new StringBuilder();
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append('\n');
            }
            reader.close();

            editText.setText(stringBuilder.toString());
            Toast.makeText(MainActivity.this, "File opened successfully", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(MainActivity.this, "Failed to open file", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
}
