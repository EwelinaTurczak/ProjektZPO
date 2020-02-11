import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.Locale;

/**
 * Klasa App umożliwia obsługę umożlwia urochomienie aplikacji służącej do pomiaru jakości powietrzaw danym mieście.
 *  @author Ewelina Turczak
 *  @version 1.0 10/02/2020
 */
public class App extends Application {


    /**
     * Metoda pozwalająca uruchomić aplikację.
     * @param primaryStage
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("view.fxml"));
        primaryStage.setTitle("Pomiar jakości powietrza");
        primaryStage.setScene(new Scene(root, 1200, 800));
        primaryStage.show();
    }


    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        launch(args);
    }

}