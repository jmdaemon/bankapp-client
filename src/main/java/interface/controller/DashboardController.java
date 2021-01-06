package app.ui;

import app.bankAccount.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.IOException;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.input.InputEvent;

import org.kordamp.ikonli.*;
import org.kordamp.ikonli.javafx.*;
import org.kordamp.ikonli.fontawesome5.*;

public class DashboardController {
  private BankAccount user;

  @FXML
  private OverlayController overlayController;

  public void setUser(BankAccount user) {
    this.user = user;
    overlayController.setUser(user);
  }

  @FXML
  void gotoDepositView(InputEvent event) {
      AppNavigator.loadApp(AppNavigator.DEPOSIT);
  }


  public void loadDepositView(InputEvent event) {
    gotoDepositView(event);
  }
}
