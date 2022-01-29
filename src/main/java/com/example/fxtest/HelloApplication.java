package com.example.fxtest;
import javafx.scene.control.*;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        //更换主题
        //
        //添加按钮
        Button btn = new Button();
        btn.setText("Start");
        btn.setPrefSize(280,20);
        btn.setStyle("-fx-background-color: #000000");
        btn.setTextFill(Color.WHITE);
        //btn四周空白距离
        HBox.setMargin(btn,new Insets(50));
        //homepage设置按钮事件
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //talent_subpage
                Stage talent_page = new Stage();
                Button talent_start_btn = new Button();
                talent_start_btn.setText("Next");
                talent_start_btn.setPrefSize(280,20);
                talent_start_btn.setStyle("-fx-background-color: #000000");
                talent_start_btn.setTextFill(Color.WHITE);
                //btn四周空白距离
                HBox start_box= new HBox();//按钮面板
                HBox.setMargin(talent_start_btn,new Insets(50));
                start_box.setAlignment(Pos.CENTER);
                //设置talent_page按钮事件
                talent_start_btn.setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent event) {
                        //创建游戏页面
                        try {
                            Thread t = new Thread(() -> {
                                backendalgo(5, 5, 5, 5);
                            });
                            t.start();
                        }catch(Exception e){}
                        Stage main_page = new Stage();
                        TextFlow text_flow = new TextFlow();
                        Text text_1=new Text("Click to show your life\n");
                        text_1.setFill(Color.WHITE);
                        text_1.setFont(Font.font("Verdana", 20));
                        ScrollPane scro= new ScrollPane(text_flow);
                        text_flow.setPrefWidth(400);
                        text_flow.setTextAlignment(TextAlignment.CENTER);
                        text_flow.setLineSpacing(20.0f);
                        //添加下一轮按钮
                        HBox main_box= new HBox();//按钮面板
                        HBox.setMargin(talent_start_btn,new Insets(30));
                        main_box.setAlignment(Pos.BOTTOM_CENTER);

                        Button main_page_btn = new Button();
                        main_page_btn.setText("Next");
                        main_page_btn.setPrefSize(280,20);
                        main_page_btn.setStyle("-fx-background-color: #FFFFFF");
                        main_page_btn.setTextFill(Color.BLACK);

                        //设置main_page按钮事件
                        main_page_btn.setOnAction(new EventHandler<ActionEvent>(){
                            @Override
                            public void handle(ActionEvent event) {
                                Stage result_page = new Stage();
                                result_page.show();
                                main_page.close();
                            }
                        });
                        VBox vbox = new VBox(text_flow,main_box);
                        vbox.setStyle("-fx-background-color: #000000");
                        vbox.setAlignment(Pos.CENTER);
                        vbox.setSpacing(50);
                        text_flow.getChildren().add(text_1);
                        vbox.setOnMouseClicked(new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent mouseEvent) {
                                text_1.setVisible(false);
                                String fileName = "C:\\Users\\76938\\Desktop\\fxtest\\test.txt";
                                File file = new File(fileName);
                                try {
                                    FileReader fr = new FileReader(file);
                                    BufferedReader br = new BufferedReader(fr);
                                    String line;
                                    while ((line = br.readLine()) != null) {
                                        Text text= new Text(line+"\n");
                                        text.setFill(Color.WHITE);
                                        text.setFont(Font.font("Verdana", 15));
                                        text_flow.getChildren().add(text);
                                    }
                                }catch(Exception e){
                                    System.out.println(e);
                                }

                            }
                        });
                        main_box.getChildren().add(main_page_btn);
                        Scene main_scene = new Scene(vbox,720, 1600, Color.BLACK);
                        main_page.setScene(main_scene);
                        main_page.initStyle(StageStyle.TRANSPARENT);
                        main_page.show();
                        talent_page.close();
                    }
                });
                //创建边界面板
                BorderPane borderPane = new BorderPane();
                borderPane.setBottom(start_box);//将单行面板放置底部
                //创建场景
                Scene talent_scene = new Scene(borderPane, 720, 1200, Color.BLACK);
                talent_page.setScene(talent_scene);
                start_box.getChildren().add(talent_start_btn);//显示按钮
                talent_page.initStyle(StageStyle.TRANSPARENT);
                talent_page.show();
                stage.close();
            }
        });

        //显示label
        Label label1 = new Label("life restart simulator ");
        Font font = new Font("微软雅黑",60);
        label1.setFont(font);
        label1.setTextFill(Color.BLACK);
        label1.setPrefWidth(550);
        label1.setWrapText(true);

        //创建单行面板
        HBox hb= new HBox();//按钮面板
        hb.setAlignment(Pos.CENTER);
        HBox hBox = new HBox();//label面板
        hBox.setAlignment(Pos.CENTER);
        //创建边界面板
        BorderPane borderPane = new BorderPane();
        borderPane.setBottom(hb);//将单行面板放置底部
        borderPane.setCenter(hBox);
        //创建场景
        Scene scene = new Scene(borderPane, 720, 1200, Color.BLACK);
        stage.setScene(scene);
        hb.getChildren().add(btn);//显示按钮
        hBox.getChildren().add(label1);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();

    }

    public void backendalgo(int appearance, int wealth, int wisdom, int strength){


        Map<Integer,String> dictionary = new HashMap<Integer,String>();
        //normal event
        dictionary.put(0,"you died");
        dictionary.put(1,"You start watching anime");
        dictionary.put(2,"Your parents had new daughter");
        dictionary.put(3,"Your parents had new son");
        dictionary.put(4,"Your parents didn't take good care of you");
        dictionary.put(5,"You're in love with women cloth.");
        dictionary.put(6,"In your spare time, you wear women's clothing youtuber at home.");
        dictionary.put(7,"Alone at home, very lonely");
        dictionary.put(8,"Your mood is getting lower and lower, trying to commit suicide. Didn't work out.");
        dictionary.put(9,"nothing special");
        dictionary.put(10,"You're married to programmer");
        dictionary.put(11,"You're starting elementary school");
        dictionary.put(12,"You like to have nothing to do, you try to climb over the wall, you die");
        dictionary.put(13,"Encountering the underworld confrontation on the street, it is very shocking");
        dictionary.put(14,"You want to look closer and get hit by a stray bullet");

        //str<5
        dictionary.put(100,"You get cold,cost lot of money");
        dictionary.put(101,"Just learned to walk,accidentally fall off the table,get injured");

        //wealth<5
        dictionary.put(200,"You grew up in the countryside");
        dictionary.put(201,"Your mother died of illness. Families are more difficult.");
        dictionary.put(202,"Your father died of illness. Families are more difficult.");
        dictionary.put(203,"You are talented, but your family does not know how to educate");
        dictionary.put(204,"You want to eat meat every day, but your parents tell you you can't afford it.");
        dictionary.put(205,"I was admitted to the county junior high school, but I couldn't afford it");
        dictionary.put(206,"");

        //wealth>5
        dictionary.put(300,"You grew up in the city");
        dictionary.put(301,"You were born in the United States and have American citizenship");
        dictionary.put(302,"Your parents treat you like a treasure and take good care of you.");
        dictionary.put(303,"You are talented and your family is very concerned about your studies");

        //app>5
        dictionary.put(400,"You look cuter than other kids.");
        dictionary.put(401,"Your high appearance is famous in the surrounding area");


        //wis>5
        dictionary.put(500,"You like to play Glory of Kings with your parents' phones");
        dictionary.put(501,"You easily hit the challenger rank.");
        dictionary.put(502,"Your mathematical talent seems to be good");
        dictionary.put(503,"There are scouts and companies that want to take you to big cities to develop");
        dictionary.put(504,"Your intelligence is too high and you are taken by aliens to do experiments");
        dictionary.put(505,"You scored 100 points on your final exam");
        dictionary.put(506,"Admitted to the junior high school, have to walk a long way to go to school every day");
        dictionary.put(507,"The level of the game is very high");

        //wis<5
        dictionary.put(600,"You are scammed while playing with your parents' phones. Lost a year of hard work of my parents.");
        dictionary.put(601,"You are mentally retarded and still can't speak.");
        dictionary.put(602,"You study hard, but your grades just can't be improved");
        dictionary.put(603,"Start going to internet cafes and get into games");
        dictionary.put(604,"The middle school entrance examination was very poor, and I was not admitted to high school");


        //strength>5
        dictionary.put(700,"You beat a child to death. Parents lost a lot of money.");
        dictionary.put(701,"The inspected gymnastics were excavated. Go to gymnastics for training.");
        dictionary.put(702,"Enter the provincial team. Train hard.");
        dictionary.put(703,"Outstanding performance, into the national team.");
        dictionary.put(704,"Participate in the Olympic Games.");
        dictionary.put(705,"Breaking world records");
        dictionary.put(706,"retire. Start being a coach");


        //app<5
        dictionary.put(800,"your learn to draw");
        dictionary.put(801,"your drawings look much better than your looks");


        Random r= new Random();
        int age=0;
        int age_max=0;

        // sex determine

        //age determine
        if(strength<=5){
            age_max=strength*3;
            age=r.nextInt(1,age_max);
        }
        if(strength>5){
            age_max=strength*5;
            age=r.nextInt(20,age_max);
        }

        int loop_number=age;
        try {
            //create new thread
            Thread write_file = new Thread(() -> {
                try {
                    PrintWriter pw = new PrintWriter("test.txt");
                    for (int i = 0; i < loop_number; i++) {
                        int event_counter = r.nextInt(14);
                        String content = dictionary.get(event_counter) + "\n";
                        pw.print(content);
                        if (event_counter == 0 || event_counter == 14 || event_counter == 12) {
                            pw.close();
                            break;
                        }

                    }
                }
                catch (Exception e){}
            });
            write_file.start();
        }catch(Exception e){
            System.out.println(e);
        }
    }
    public static void main(String[] args) {
        launch();
    }
}