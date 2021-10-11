package adivinapp;

import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AdivinApp extends Application {
    
    private TextField numText;
    private Alert alerta;
    private int numAleatorio = (int) (Math.random() * 100 + 1);
    private int contador = 0;

    @Override
    public void start(Stage primaryStage) throws Exception {

        Label numLabel = new Label();
        numLabel.setText("Introduce un numero del 1 al 100");

        numText = new TextField();

        Button verificButton = new Button();
        verificButton.setText("Comprobar");
        verificButton.setDefaultButton(true);
        verificButton.setOnAction(e -> onNumeroButtonAction(e));

        VBox root = new VBox();
        root.setSpacing(10);
        root.setFillWidth(false);
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(numLabel, numText, verificButton);

        Scene escena = new Scene(root, 320, 200);

        primaryStage.setScene(escena);
        primaryStage.setTitle("AdivinApp");
        primaryStage.show();
    }

    private void onNumeroButtonAction(ActionEvent e) {
    	int numUsuario = 0;

        String numUsuarioText = numText.getText();

        if (!numUsuarioText.chars().allMatch(Character::isDigit) || numUsuarioText.equals("")) {
            alerta = new Alert(AlertType.ERROR);
            alerta.setTitle("AdivinApp");
            alerta.setHeaderText("Error");
            alerta.setContentText("Debes introducir un numero");
            alerta.showAndWait();

        } else {
            numUsuario = Integer.parseInt(numUsuarioText);
            if (numUsuario < 0 || numUsuario > 100) {
                alerta = new Alert(AlertType.ERROR);
                alerta.setTitle("AdivinApp");
                alerta.setHeaderText("Error");
                alerta.setContentText("El numero introducido no es valido tiene que estar entre 0 y 100");
                alerta.showAndWait();
            } else if (numUsuario > numAleatorio) {
                alerta = new Alert(AlertType.WARNING);
                alerta.setTitle("AdivinApp");
                alerta.setHeaderText("¡Has fallado!");
                alerta.setContentText(
                        "El numero a adivinar es menor que " + numUsuario + "." + "\n Vuelve a intentarlo.");
                alerta.showAndWait();
                contador++;
            } else if (numUsuario == numAleatorio) {
                alerta = new Alert(AlertType.INFORMATION);
                alerta.setTitle("AdivinApp");
                alerta.setHeaderText("¡Has Ganado!");
                alerta.setContentText(
                        "Solo haz necesitado " + contador + " intentos.\n" + "Vuelve a jugar y hazlo mejor");
                numAleatorio = (int) (Math.random() * 100 + 1);
                alerta.showAndWait();
                contador = 0;
            } else {
                alerta = new Alert(AlertType.WARNING);
                alerta.setTitle("AdivinApp");
                alerta.setHeaderText("¡Has fallado!");
                alerta.setContentText(
                        "El numero a adivinar es mayor que " + numUsuario + "." + "\n Vuelve a intentarlo.");
                alerta.showAndWait();
                contador++;
            }
        }

    }

    public static void main(String[] args) {
        launch(args);
    }

}
    
