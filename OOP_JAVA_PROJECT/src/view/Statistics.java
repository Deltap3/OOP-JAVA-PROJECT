package view;
import model.*;
import DAO.*;

import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import static model.Connections.getInstance;
import org.jfree.chart.*;
import org.jfree.data.general.DefaultPieDataset;

public class Statistics extends JFrame {
    private static Connection conn;
    private Statement stmt;
    private ResultSet rset;
    private ResultSetMetaData rsetMeta;
    private static String urlDatabase;
    private static String loginDatabase;
    private static String passwordDatabase;
    
    public void Connections(String nameDatabase, String loginDatabase, String passwordDatabase) throws SQLException, ClassNotFoundException{
        // chargement driver "com.mysql.jdbc.Driver"
        Class.forName("com.mysql.jdbc.Driver");
        // url de connexion
        this.urlDatabase = "jdbc:mysql://localhost:3306/" + nameDatabase+"?useSSL=false";
        this.loginDatabase=loginDatabase;
        this.passwordDatabase=passwordDatabase;
        getInstance();
        // création d'un ordre SQL (statement)
        stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
    }
    private String statType;
    public Statistics(String statType) throws HeadlessException {
        this.statType = statType;
        if(statType == "Member/Customer Ratio"){
            memberRatio();
        }
        else if(statType == "Tickets sold per film"){
            ticketsPerFilm();
        }
        else {
            
        }
    }
    public void memberRatio(){
        try {
            DefaultPieDataset data = new DefaultPieDataset();
            Connections co = new Connections("Project", "root", "password");
            data.setValue("Member", co.getNbRows("members\n where login is not null"));
            data.setValue("Customer", co.getNbRows("members\n where login is null"));
            JFreeChart chart = ChartFactory.createPieChart("Member/Customer Ratio",
                    data,true,true,false);
            ChartFrame frame = new ChartFrame("Statistics", chart);
            frame.pack();
            frame.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(Statistics.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Statistics.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void ticketsPerFilm(){
        try {
            DefaultPieDataset data = new DefaultPieDataset();
            Connections co = new Connections("project", "root", "password");
            data.setValue("Member", 5);
            data.setValue("Customer", 10);
            JFreeChart chart = ChartFactory.createPieChart("Member/Customer Ratio",
                    data,true,true,false);
            ChartFrame frame = new ChartFrame("Statistics", chart);
            frame.pack();
            frame.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(Statistics.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Statistics.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}