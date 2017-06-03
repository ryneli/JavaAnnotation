package com.example.zhenqiangli.javaannotation;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.zhenqiangli.javaannotation.generated.GeneratedClass;
import com.example.zhenqiangli.javaannotation.processor.CustomAnnotation;

public class MainActivity extends AppCompatActivity {

    @CustomAnnotation
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showAnnotationMessage();
    }

    private void showAnnotationMessage() {
        GeneratedClass generatedClass = new GeneratedClass();
        String message = generatedClass.getMessage();
        new AlertDialog.Builder(this)
                .setPositiveButton("Ok", null)
                .setTitle("Annotation Processor Messages")
                .setMessage(message)
                .show();
    }
}
