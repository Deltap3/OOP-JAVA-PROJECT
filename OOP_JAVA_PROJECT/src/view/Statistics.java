package view;
import model.*;
import DAO.*;

import java.awt.*;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import org.jfree.chart.*;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.MultiplePiePlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.util.TableOrder;
/**
 * ZHONG David
 * MAISTERRENA Pierre
 * DANIEL Juliette
 * ING3 TDE02
 */

//Class that manages all the statistics
public class Statistics extends JFrame {
    
    //Allows you to know which statistic you want to display
    private String statType;
    
    //We call the method that corresponds to the statistic that we want to display
    public Statistics(String statType) throws HeadlessException {
        this.statType = statType;
        //If we wznt to display the most viewed movies
        //(who had the most tickets sold)
        if("Most viewed film".equals(statType)){
            mostViewedFilm();
        }
        //If we want to display the percentage of tickets solds compared to empty seats
        else if("Percentage tickets per seats".equals(statType)){
            percentageTicketsPerSeats();
        }
        
        //If we want to display the most viewed genre
        //(who had the most tickets sold)
        else if("Most viewed Genre".equals(statType)){
            mostViewedGenre();
        }
        
        //If we want to display the discount of all the screenings
        else if("Discount per Screenings".equals(statType)){
            discountPerScreenings();
        }
    }
    
    //The statistic that display discounts per screenings
    public void discountPerScreenings(){
        try {
            //We create a dataset for the bar chart
            DefaultCategoryDataset data = new DefaultCategoryDataset();
            //We try to connect to the database
            Connections co = new Connections("project", "root", "password");
            //We acces screening DAO
            ScreeningDAO screenCo = new ScreeningDAO(co.getInstance());
            //This is the screening we choose for the bar chart
            Screening select = new Screening();
            ArrayList<Screening> allScreenings = screenCo.getAllScreening();
            //For all the screenings
            for(int i = 0; i < allScreenings.size() ; ++i){
               //We select a screening 
               select = screenCo.find(allScreenings.get(i).getDateTime());
               //We had its data to the dataset
               data.addValue(select.getDiscount(),"Screening " + i,select.getMovieName());
            }
            //We create the bar chart
            JFreeChart chart = ChartFactory.createBarChart("Discounts Per Screenings",
                    "Screenings", "Discount (in percentage)",data,PlotOrientation.VERTICAL,
                    true,true,false);
            //We create a panel with the bar chart and make this panel visible
            ChartPanel frame = new ChartPanel(chart);
            frame.setVisible(true);
        } catch (SQLException ex) {
            //If we cna't connect to the database we displat it
            Logger.getLogger(Statistics.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            //If we don't find the DAO class we display it
            Logger.getLogger(Statistics.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //The statistic that display the most viewed genre
    public void mostViewedGenre(){
        try {
            //We create a dataset for the pie chart
            DefaultPieDataset data = new DefaultPieDataset();
            //We try to connect to the database
            Connections co = new Connections("project", "root", "password");
            //We acces screening DAO
            ScreeningDAO screenCo = new ScreeningDAO(co.getInstance());
            //This is the screening we choose for the bar chart
            Screening select = new Screening();
            //We acces movies DAO
            MovieDAO movieCo = new MovieDAO(co.getInstance());
            //This is the movie we choose for the bar chart
            Movie selectMovie = new Movie();
            //Number of total tocikets if a movie had several screenings
            int numberTickets = 0;
            ArrayList<Screening> allScreenings = screenCo.getAllScreening();
            ArrayList<Movie> allMovies = movieCo.getAllMovie();
            //For all the screenings
            for(int i = 0; i < allScreenings.size() ; ++i){
               //We select a screening and a movie
               select = screenCo.find(allScreenings.get(i).getDateTime());
               selectMovie = movieCo.find(select.getMovieName());
               numberTickets = select.getTicketsBoughts();
               //We search if a genre appears in several screenings
               for(int j = 0; j < allScreenings.size() ; ++j)
                   if(i != j && allMovies.get(i).getGenre().equals(allMovies.get(j).getGenre()))
                       //If it appears several times we add them
                       numberTickets = numberTickets + allScreenings.get(j).getTicketsBoughts();  
               //We had its data to the dataset
               data.setValue(selectMovie.getGenre(),numberTickets);
            }
            //We create the pie chart
            JFreeChart chart = ChartFactory.createPieChart("Most Viewed Genre", data, true, true, false);
            //We create a panel with the pie chart and make this panel visible
            ChartPanel frame = new ChartPanel(chart);
            frame.setVisible(true);
        } catch (SQLException ex) {
            //If we cna't connect to the database we displat it
            Logger.getLogger(Statistics.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            //If we don't find the DAO class we display it
            Logger.getLogger(Statistics.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //La statistique qui affiche les films les plus vus
    public void mostViewedFilm(){
        try {
            //We create a dataset for the bar chart
            DefaultCategoryDataset data = new DefaultCategoryDataset();
            //We try to connect to the database
            Connections co = new Connections("project", "root", "password");
            //We acces screening DAO
            ScreeningDAO screenCo = new ScreeningDAO(co.getInstance());
            Screening select = new Screening();
            //Number of total tickets if a movie had several screenings
            int numberTickets = 0;
            ArrayList<Screening> allScreenings = screenCo.getAllScreening();
            //For all the screenings
            for(int i = 0; i < allScreenings.size() ; ++i){
               //We select a screening 
               select = screenCo.find(allScreenings.get(i).getDateTime());
               numberTickets = select.getTicketsBoughts();
               //We search if a movie appears in several screenings
               for(int j = 0; j < allScreenings.size() ; ++j)
                   if(i != j && allScreenings.get(i).getMovieName().equals(allScreenings.get(j).getMovieName()))
                       //If it appears several times we add the new number of tickets to the ancient one
                       numberTickets = numberTickets + allScreenings.get(j).getTicketsBoughts();
               //We had its data to the dataset
               data.addValue(numberTickets,select.getMovieName(),"Movies");
            }
            //We create the bar chart
            JFreeChart chart = ChartFactory.createBarChart("Tickets Boughts Per Movies",
                    "Movie", "Tickets Bought",data,PlotOrientation.VERTICAL,
                    true,true,false);
            //We create a panel with the bar chart and make this panel visible
            ChartPanel frame = new ChartPanel(chart);
            frame.setVisible(true);
        } catch (SQLException ex) {
            //If we cna't connect to the database we displat it
            Logger.getLogger(Statistics.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            //If we don't find the DAO class we display it
            Logger.getLogger(Statistics.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //The statistic that display the percentage of tickets solds compared to empty seats
    //This code was arranged from a code found on internet : http://www.java2s.com/Code/Java/Chart/JFreeChartMultiplePieChartDemo1.htm
    public void percentageTicketsPerSeats(){
        //We create a multiple pie chart
        final MultiplePieChartDemo1 demo = new MultiplePieChartDemo1("Statistics");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
    
    //We create the chart with all the pie charts
    private JFreeChart createChart(final CategoryDataset dataset) {
        //We create the chart
        final JFreeChart chart = ChartFactory.createMultiplePieChart(
            "Percentage of tickets boughts per seats",
            dataset,
            TableOrder.BY_ROW,
            true,
            true,
            false
        );
        //We create the plot
        final MultiplePiePlot plot = (MultiplePiePlot) chart.getPlot();
       //We create the subchart
        final JFreeChart subchart = plot.getPieChart();
        final PiePlot p = (PiePlot) subchart.getPlot();
        //We set the label and font
        p.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}"));
        p.setLabelFont(new Font("SansSerif", Font.PLAIN, 8));
        p.setInteriorGap(0.30);

        return chart;
    }
    
    //Class that create the multiple pie chart
    public class MultiplePieChartDemo1 extends ApplicationFrame {
        public MultiplePieChartDemo1(final String title) {
            //We put the multiple pie chatr in a panel
            super(title);
            final CategoryDataset dataset = createDataset();
            final JFreeChart chart = createChart(dataset);
            final ChartPanel chartPanel = new ChartPanel(chart, true, true, true, false, true);
            chartPanel.setPreferredSize(new java.awt.Dimension(1366, 768));
            setContentPane(chartPanel);
        }
        
        //We create the dataset for this chart
        private CategoryDataset createDataset() {
            try {
                //We try to connect to the database
                Connections co = new Connections("project", "root", "password");
                //We acces screening DAO
                ScreeningDAO screenCo = new ScreeningDAO(co.getInstance());
                Screening select = new Screening();
                //We create a dataset for the multiple pie chart
                final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
                //Number of total tickets and seats if a movie had several screenings
                int numberTickets = 0, numberSeats = 0;
                ArrayList<Screening> allScreenings = screenCo.getAllScreening();
                //For all the screenings
                for(int i = 0; i < allScreenings.size() ; ++i){
                    //We select a screening
                    select = screenCo.find(allScreenings.get(i).getDateTime());
                    numberTickets = select.getTicketsBoughts();
                    numberSeats = select.getNumberseat();
                    //We search if a movie appears in several screenings
                    for(int j = 0; j < allScreenings.size() ; ++j)
                       if(i != j && allScreenings.get(i).getMovieName().equals(allScreenings.get(j).getMovieName())){
                            //If it appears several times we add the new number of tickets and seats to the ancient one
                            numberTickets = numberTickets + allScreenings.get(j).getTicketsBoughts(); 
                            numberSeats = numberSeats + allScreenings.get(j).getNumberseat();
                       }
                    //We add the data in the dataset
                    dataset.addValue(numberTickets, select.getMovieName(), "Taken Seats");
                    dataset.addValue(numberSeats-numberTickets, select.getMovieName(), "Empty Seats");
                }
                return dataset;
            } catch (SQLException ex) {
                //If we cna't connect to the database we displat it
                Logger.getLogger(Statistics.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            } catch (ClassNotFoundException ex) {
                //If we don't find the DAO class we display it
                Logger.getLogger(Statistics.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        }
    }
}