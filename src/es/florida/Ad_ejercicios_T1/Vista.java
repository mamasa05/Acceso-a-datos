package es.florida.Ad_ejercicios_T1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Vista extends JFrame {
    private JTextField directorioField;
    private JTextField extensionField;
    private JTextField textoABuscarField;
    private JButton buscarButton;
    private JButton fusionarButton;

    public Vista() {
        super("Aplicación de Gestión de Archivos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);

        JPanel panel = new JPanel();
        getContentPane().add(panel);
        panel.setLayout(null);

        JLabel directorioLabel = new JLabel("Directorio de búsqueda:");
        directorioLabel.setBounds(10, 10, 150, 20);
        panel.add(directorioLabel);

        directorioField = new JTextField();
        directorioField.setBounds(170, 10, 200, 20);
        panel.add(directorioField);

        JLabel extensionLabel = new JLabel("Extensión de archivo:");
        extensionLabel.setBounds(10, 40, 150, 20);
        panel.add(extensionLabel);

        extensionField = new JTextField();
        extensionField.setBounds(170, 40, 200, 20);
        panel.add(extensionField);

        JLabel textoABuscarLabel = new JLabel("Texto a buscar:");
        textoABuscarLabel.setBounds(10, 70, 150, 20);
        panel.add(textoABuscarLabel);

        textoABuscarField = new JTextField();
        textoABuscarField.setBounds(170, 70, 200, 20);
        panel.add(textoABuscarField);

        buscarButton = new JButton("Buscar y Contar Coincidencias");
        buscarButton.setBounds(10, 100, 200, 30);
        panel.add(buscarButton);

        fusionarButton = new JButton("Fusionar Ficheros");
        fusionarButton.setBounds(10, 140, 200, 30);
        panel.add(fusionarButton);
    }

    public String getDirectorio() {
        return directorioField.getText();
    }

    public String getExtension() {
        return extensionField.getText();
    }

    public String getTextoABuscar() {
        return textoABuscarField.getText();
    }

    public void setBuscarButtonListener(ActionListener listener) {
        buscarButton.addActionListener(listener);
    }

    public void setFusionarButtonListener(ActionListener listener) {
        fusionarButton.addActionListener(listener);
    }
}