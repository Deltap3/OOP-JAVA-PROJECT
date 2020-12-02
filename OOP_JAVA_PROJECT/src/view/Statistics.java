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
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.MultiplePiePlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.util.TableOrder;

public class Statistics extends JFrame {
    private String statType;
    public Statistics(String statType) throws HeadlessException {
        this.statType = statType;
        if(statType == "Most viewed film"){
            mostViewedFilm();
        }
        else if(statType == "Percentage tickets per seats"){
            percentageTicketsPerSeats();
        }
        else {
            
        }
    }
    public void timePassedWatchingFilm(){
        try {
            DefaultCategoryDataset data = new DefaultCategoryDataset();
            Connections co = new Connections("project", "root", "password");
            ScreeningDAO screenCo = new ScreeningDAO(co.getInstance());
            Screening screen = new Screening();
            int numberTickets = 0, price = 0;
            ArrayList<Screening> allScreenings = screenCo.getAllScreening();
            for(int i = 0; i < allScreenings.size() ; ++i){
               screen = screenCo.getScreeningByDateTime(allScreenings.get(i).getDateTime());
               numberTickets = screen.getTicketsBoughts();
               for(int j = 0; j < allScreenings.size() ; ++j)
                   if(i != j && allScreenings.get(i).getMovieName().equals(allScreenings.get(j).getMovieName()))
                       numberTickets = numberTickets + allScreenings.get(j).getTicketsBoughts();     
               data.addValue(numberTickets,screen.getMovieName(),"Movies");
            }
            JFreeChart chart = ChartFactory.createBarChart("Tickets Boughts Per Movies",
                    "Movie", "Tickets Bought",data,PlotOrientation.VERTICAL,
                    true,true,false);
            ChartFrame frame = new ChartFrame("Statistics", chart);
            frame.pack();
            frame.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(Statistics.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Statistics.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void financialStats(){
        try {
            DefaultCategoryDataset data = new DefaultCategoryDataset();
            Connections co = new Connections("project", "root", "password");
            ScreeningDAO screenCo = new ScreeningDAO(co.getInstance());
            Screening screen = new Screening();
            int numberTickets = 0, price = 0;
            ArrayList<Screening> allScreenings = screenCo.getAllScreening();
            for(int i = 0; i < allScreenings.size() ; ++i){
               screen = screenCo.getScreeningByDateTime(allScreenings.get(i).getDateTime());
               numberTickets = screen.getTicketsBoughts();
               for(int j = 0; j < allScreenings.size() ; ++j)
                   if(i != j && allScreenings.get(i).getMovieName().equals(allScreenings.get(j).getMovieName()))
                       numberTickets = numberTickets + allScreenings.get(j).getTicketsBoughts();     
               data.addValue(numberTickets,screen.getMovieName(),"Movies");
            }
            JFreeChart chart = ChartFactory.createBarChart("Tickets Boughts Per Movies",
                    "Movie", "Tickets Bought",data,PlotOrientation.VERTICAL,
                    true,true,false);
            ChartFrame frame = new ChartFrame("Statistics", chart);
            frame.pack();
            frame.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(Statistics.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Statistics.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void mostViewedFilm(){
        try {
            DefaultCategoryDataset data = new DefaultCategoryDataset();
            Connections co = new Connections("project", "root", "password");
            ScreeningDAO screenCo = new ScreeningDAO(co.getInstance());
            Screening select = new Screening();
            int numberTickets = 0;
            ArrayList<Screening> allScreenings = screenCo.getAllScreening();
            for(int i = 0; i < allScreenings.size() ; ++i){
               select = screenCo.getScreeningByDateTime(allScreenings.get(i).getDateTime());
               numberTickets = select.getTicketsBoughts();
               for(int j = 0; j < allScreenings.size() ; ++j)
                   if(i != j && allScreenings.get(i).getMovieName().equals(allScreenings.get(j).getMovieName()))
                       numberTickets = numberTickets + allScreenings.get(j).getTicketsBoughts();     
               data.addValue(numberTickets,select.getMovieName(),"Movies");
            }
            JFreeChart chart = ChartFactory.createBarChart("Tickets Boughts Per Movies",
                    "Movie", "Tickets Bought",data,PlotOrientation.VERTICAL,
                    true,true,false);
            ChartFrame frame = new ChartFrame("Statistics", chart);
            frame.pack();
            frame.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(Statistics.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Statistics.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void percentageTicketsPerSeats(){
        final MultiplePieChartDemo1 demo = new MultiplePieChartDemo1("Statistics");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
    public class MultiplePieChartDemo1 extends ApplicationFrame {
    public MultiplePieChartDemo1(final String title) {
        super(title);
        final CategoryDataset dataset = createDataset();
        final JFreeChart chart = createChart(dataset);
        final ChartPanel chartPanel = new ChartPanel(chart, true, true, true, false, true);
        chartPanel.setPreferredSize(new java.awt.Dimension(1366, 768));
        setContentPane(chartPanel);

    }
    private CategoryDataset createDataset() {
        try {
            Connections co = new Connections("project", "root", "password");
            ScreeningDAO screenCo = new ScreeningDAO(co.getInstance());
            Screening select = new Screening();
            final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            int numberTickets = 0, numberSeats = 0;
            int counter = 1;
            ArrayList<Screening> allScreenings = screenCo.getAllScreening();
            for(int i = 0; i < allScreenings.size() ; ++i){
                select = screenCo.getScreeningByDateTime(allScreenings.get(i).getDateTime());
                numberTickets = select.getTicketsBoughts();
                numberSeats = select.getNumberseat();
                for(int j = 0; j < allScreenings.size() ; ++j)
                   if(i != j && allScreenings.get(i).getMovieName().equals(allScreenings.get(j).getMovieName())){
                        numberTickets = numberTickets + allScreenings.get(j).getTicketsBoughts(); 
                        numberSeats = numberSeats + allScreenings.get(j).getNumberseat();
                   }
                        
                dataset.addValue(numberTickets, select.getMovieName(), "Taken Seats");
                dataset.addValue(numberSeats-numberTickets, select.getMovieName(), "Empty Seats");
                
            }
            return dataset;
        } catch (SQLException ex) {
            Logger.getLogger(Statistics.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Statistics.class.getName()).log(Level.SEVERE, null, ex);
        }
            return null;
        }
    }
    private JFreeChart createChart(final CategoryDataset dataset) {
        final JFreeChart chart = ChartFactory.createMultiplePieChart(
            "Percentage of tickets boughts per seats",  // chart title
            dataset,               // dataset
            TableOrder.BY_ROW,
            true,                  // include legend
            true,
            false
        );
        final MultiplePiePlot plot = (MultiplePiePlot) chart.getPlot();
        final JFreeChart subchart = plot.getPieChart();
        final PiePlot p = (PiePlot) subchart.getPlot();
        p.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}"));
        p.setLabelFont(new Font("SansSerif", Font.PLAIN, 8));
        p.setInteriorGap(0.30);
        
        return chart;
    }
}