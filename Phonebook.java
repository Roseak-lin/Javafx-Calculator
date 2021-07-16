package package1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Phonebook extends Application {
	final String FILE_PATH = "phonebook.txt";
	String fName, lName, email, school, number;
	Text t = new Text();
	HashMap<Integer, String> entries = new HashMap<Integer, String>();
	int currentAccount = 0;
	ArrayList<Integer> arr = new ArrayList<Integer>();
	boolean findButtonClick = true, arrowButtonClick = true;;
	String previous;

	Button submit = new Button("Submit");
	Button clear = new Button("Clear");
	Button find = new Button("Find account By First Name");
	Button update = new Button("Update");

	public static void main(String[] args) {
		launch("Phonebook");

	}

	public void start(Stage primaryStage) {
		//// set up ////
		Group group = new Group();
		Scene scene = new Scene(group);
		primaryStage.setHeight(350);
		primaryStage.setWidth(375);
		primaryStage.setTitle("Phonebook");
		primaryStage.show();
		primaryStage.setScene(scene);

		Text f = new Text("First Name");
		f.setLayoutY(20);
		f.setLayoutX(5);

		TextField t1 = new TextField();
		t1.setLayoutX(100);

		Text l = new Text("Last Name");
		l.setLayoutX(5);
		l.setLayoutY(48);

		TextField t2 = new TextField();
		t2.setLayoutX(100);
		t2.setLayoutY(30);

		Text em = new Text("E-mail Address");
		em.setLayoutX(5);
		em.setLayoutY(78);

		TextField t3 = new TextField();
		t3.setLayoutX(100);
		t3.setLayoutY(60);

		Text num = new Text("Phone Number");
		num.setLayoutX(5);
		num.setLayoutY(108);

		TextField t4 = new TextField();
		t4.setLayoutX(100);
		t4.setLayoutY(90);

		Text s = new Text("School name");
		s.setLayoutX(5);
		s.setLayoutY(138);

		TextField t5 = new TextField();
		t5.setLayoutX(100);
		t5.setLayoutY(120);

		t.setVisible(false);
		t.setX(100);
		t.setY(165);

		File left = new File("C:\\Users\\rosea\\Pictures\\Java\\arrow1.png");
		File right = new File("C:\\Users\\rosea\\Pictures\\Java\\arrow2.png");

		Image l1 = new Image(left.toURI().toString(), 20, 20, false, false);
		Image r = new Image(right.toURI().toString(), 20, 20, false, false);

		///// Arrow Keys /////
		Button leftA = new Button();
		leftA.setGraphic(new ImageView(l1));
		leftA.setLayoutX(275);
		leftA.setLayoutY(150);
		leftA.setVisible(false);
		leftA.setOnAction(event -> {
			if (currentAccount == 0) {
				currentAccount--;
				currentAccount = arr.size() - 1;
				t1.setText(entries.get(arr.get(currentAccount)));
				t2.setText(entries.get(arr.get(currentAccount) + 1));
				t3.setText(entries.get(arr.get(currentAccount) + 2));
				t4.setText(entries.get(arr.get(currentAccount) + 3));
				t5.setText(entries.get(arr.get(currentAccount) + 4));
			} else {
				currentAccount--;
				t1.setText(entries.get(arr.get(currentAccount)));
				t2.setText(entries.get(arr.get(currentAccount) + 1));
				t3.setText(entries.get(arr.get(currentAccount) + 2));
				t4.setText(entries.get(arr.get(currentAccount) + 3));
				t5.setText(entries.get(arr.get(currentAccount) + 4));
			}

		});

		Button rightA = new Button();
		rightA.setGraphic(new ImageView(r));
		rightA.setLayoutX(315);
		rightA.setLayoutY(150);
		rightA.setVisible(false);
		rightA.setOnAction(event -> {
			if (currentAccount == arr.size() - 1) {
				currentAccount = 0;
				t1.setText(entries.get(arr.get(currentAccount)));
				t2.setText(entries.get(arr.get(currentAccount) + 1));
				t3.setText(entries.get(arr.get(currentAccount) + 2));
				t4.setText(entries.get(arr.get(currentAccount) + 3));
				t5.setText(entries.get(arr.get(currentAccount) + 4));
			} else {
				currentAccount++;
				t1.setText(entries.get(arr.get(currentAccount)));
				t2.setText(entries.get(arr.get(currentAccount) + 1));
				t3.setText(entries.get(arr.get(currentAccount) + 2));
				t4.setText(entries.get(arr.get(currentAccount) + 3));
				t5.setText(entries.get(arr.get(currentAccount) + 4));
			}
		});

		///// main buttons and functions /////

		submit.setLayoutX(5);
		submit.setLayoutY(175);
		submit.setOnAction(event -> {
			try {
				fName = t1.getText();
				lName = t2.getText();
				email = t3.getText();
				number = t4.getText();
				school = t5.getText();

				FileWriter fw = new FileWriter(FILE_PATH, true);
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write("First Name: " + fName);
				bw.newLine();
				bw.write("Last Name: " + lName);
				bw.newLine();
				bw.write("E-mail Address: " + email);
				bw.newLine();
				bw.write("Phone Number: " + number);
				bw.newLine();
				bw.write("School: " + school);
				bw.newLine();
				bw.write("");
				bw.newLine();
				bw.close();
				fw.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		find.setLayoutX(5);
		find.setLayoutY(205);

		find.setOnAction(event -> {
			try {
				arr.clear();
				int totalLines = 0;
				currentAccount = 0;
				int count = 0;
				BufferedReader br = new BufferedReader(
						new FileReader(FILE_PATH));
				String line;
				while ((line = br.readLine()) != null) {
					String[] parts = line.split(": ");
					if (parts.length > 1) {
						if (parts[0].equals("First Name") && parts[1].equals(t1.getText())) {
							arr.add(totalLines);
							count++;
						}
						entries.put(totalLines, parts[1]);
						totalLines++;
					}
				}

				br = new BufferedReader(new FileReader(FILE_PATH));
				if (count == 1) {
					leftA.setVisible(false);
					rightA.setVisible(false);
					t1.setText(entries.get(arr.get(0)));
					t2.setText(entries.get(arr.get(0) + 1));
					t3.setText(entries.get(arr.get(0) + 2));
					t4.setText(entries.get(arr.get(0) + 3));
					t5.setText(entries.get(arr.get(0) + 4));
					t.setText("");
					while (!br.readLine().equals("First Name: " + t1.getText())) {
						continue;
					}
					update.setVisible(true);
				} else if (count > 1) {
					t.setText("There were " + count + " accounts found");
					update.setVisible(true);
					t.setVisible(true);
					leftA.setVisible(true);
					rightA.setVisible(true);
				} else {
					t.setText("There were no accounts found");
					t.setVisible(true);
					leftA.setVisible(false);
					rightA.setVisible(false);
					update.setVisible(false);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		});

		clear.setLayoutX(5);
		clear.setLayoutY(235);

		clear.setOnAction(event -> {
			t1.clear();
			t2.clear();
			t3.clear();
			t4.clear();
			t5.clear();
		});

		update.setLayoutX(5);
		update.setLayoutY(265);
		update.setVisible(true);

		update.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				try {
					FileWriter fw = new FileWriter(FILE_PATH);
					BufferedWriter bw = new BufferedWriter(fw);
					try {
						entries.replace(arr.get(currentAccount), t1.getText());
						entries.replace(arr.get(currentAccount) + 1, t2.getText());
						entries.replace(arr.get(currentAccount) + 2, t3.getText());
						entries.replace(arr.get(currentAccount) + 3, t4.getText());
						entries.replace(arr.get(currentAccount) + 4, t5.getText());
					} catch (IndexOutOfBoundsException e) {
						e.printStackTrace();
					}

					for (int i = 0; i < entries.size(); i += 5) {
						bw.write("First Name: " + entries.get(i));
						bw.newLine();
						bw.write("Last Name: " + entries.get(i + 1));
						bw.newLine();
						bw.write("E-mail Address: " + entries.get(i + 2));
						bw.newLine();
						bw.write("Phone Number: " + entries.get(i + 3));
						bw.newLine();
						bw.write("School: " + entries.get(i + 4));
						bw.newLine();
						bw.write(" ");
						bw.newLine();
					}
					bw.close();
					fw.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		group.getChildren().add(f);
		group.getChildren().add(t1);
		group.getChildren().add(l);
		group.getChildren().add(t2);
		group.getChildren().add(em);
		group.getChildren().add(t3);
		group.getChildren().add(num);
		group.getChildren().add(t4);
		group.getChildren().add(s);
		group.getChildren().add(t5);
		group.getChildren().add(submit);
		group.getChildren().add(clear);
		group.getChildren().add(find);
		group.getChildren().add(update);
		group.getChildren().add(t);
		group.getChildren().add(rightA);
		group.getChildren().add(leftA);

	}
}