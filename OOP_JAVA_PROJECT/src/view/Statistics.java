/**
 * ZHONG David
 * MAISTERRENA Pierre
 * DANIEL Juliette
 * ING3 TDE02
 */
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
import org.jfree.util.TableOrder;


public class Statistics extends JPanel {

    private String statType;
    public Statistics(String statType) throws HeadlessException {
        this.statType = statType;

        
        if(statType.equals("Most viewed film") ){
            mostViewedFilm();
        }
        else if(statType.equals("Percentage tickets per seats")){

            percentageTicketsPerSeats();
        }
        else if("Most viewed Genre".equals(statType)){
            mostViewedGenre();
        }
        else if("Discount per Screenings".equals(statType)){
            discountPerScreenings();
        }
                
    }
    public void discountPerScreenings(){
        try {
            DefaultCategoryDataset data = new DefaultCategoryDataset();
            Connections co = new Connections("project", "root", "password");
            ScreeningDAO screenCo = new ScreeningDAO(co.getInstance());
            Screening select = new Screening();
            ArrayList<Screening> allScreenings = screenCo.getAllScreening();
            boolean display = true;
            ArrayList<Integer> counter = new ArrayList<Integer>();
            for(int i = 0; i < allScreenings.size() ; ++i){
               counter.add(1);
               select = screenCo.find(allScreenings.get(i).getDateTime());
               data.addValue(select.getDiscount(),"Screening " + i,select.getMovieName());
            }
            JFreeChart chart = ChartFactory.createBarChart("Discounts Per Screenings",
                    "Screenings", "Discount (in percentage)",data,PlotOrientation.VERTICAL,
                    true,true,false);

            

            ChartPanel panel = new ChartPanel(chart);
            this.add(panel);
	   // panel.setVisible(true);


        } catch (SQLException ex) {
            Logger.getLogger(Statistics.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Statistics.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void mostViewedGenre(){
        try {
            DefaultPieDataset data = new DefaultPieDataset();
            Connections co = new Connections("project", "root", "password");
            ScreeningDAO screenCo = new ScreeningDAO(co.getInstance());
            Screening select = new Screening();
            MovieDAO movieCo = new MovieDAO(co.getInstance());
            Movie selectMovie = new Movie();
            int numberTickets = 0;
            ArrayList<Screening> allScreenings = screenCo.getAllScreening();
            ArrayList<Movie> allMovies = movieCo.getAllMovie();
            for(int i = 0; i < allScreenings.size() ; ++i){
               select = screenCo.find(allScreenings.get(i).getDateTime());
               selectMovie = movieCo.find(select.getMovieName());
               numberTickets = select.getTicketsBoughts();
               for(int j = 0; j < i ; ++j)
                   if(j > 0 && selectMovie.getGenre().equals(data.getKey(j-1))){
                       numberTickets = (int) ((double)data.getValue(j-1) + allScreenings.get(i).getTicketsBoughts());   
                System.out.println("lol");}
               data.setValue(selectMovie.getGenre(),numberTickets);
            }
            JFreeChart chart = ChartFactory.createPieChart("Most Viewed Genre", data, true, true, false);
            ChartPanel panel = new ChartPanel(chart);
            this.add(panel);
            //panel.setVisible(true);

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
               select = screenCo.find(allScreenings.get(i).getDateTime());
               numberTickets = select.getTicketsBoughts();
               for(int j = 0; j < allScreenings.size() ; ++j)
                   if(i != j && allScreenings.get(i).getMovieName().equals(allScreenings.get(j).getMovieName()))
                       numberTickets = numberTickets + allScreenings.get(j).getTicketsBoughts();     
               data.addValue(numberTickets,select.getMovieName(),"Movies");
            }
            JFreeChart chart = ChartFactory.createBarChart("Tickets Boughts Per Movies",
                    "Movie", "Tickets Bought",data,PlotOrientation.VERTICAL,
                    true,true,false);
            ChartPanel panel = new ChartPanel(chart);
            this.add(panel);
           // panel.setVisible(true);
            
        } catch (SQLException ex) {
            Logger.getLogger(Statistics.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Statistics.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void percentageTicketsPerSeats(){
        final MultiplePieChartDemo1 demo = new MultiplePieChartDemo1("Statistics");
        
        this.add(demo);
      //  RefineryUtilities.centerFrameOnScreen(demo);
        //demo.setVisible(true);
    }
    public class MultiplePieChartDemo1 extends JPanel {
    public MultiplePieChartDemo1(final String title) {
        super();
        final CategoryDataset dataset = createDataset();
        final JFreeChart chart = createChart(dataset);
        final ChartPanel chartPanel = new ChartPanel(chart, true, true, true, false, true);
        //chartPanel.setPreferredSize(new java.awt.Dimension(1366, 768));
        this.add(chartPanel);

    }
    private CategoryDataset createDataset() {
        try {
            Connections co = new Connections("project", "root", "password");
            ScreeningDAO screenCo = new ScreeningDAO(co.getInstance());
            Screening select = new Screening();
            final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            int numberTickets = 0, numberSeats = 0;
           // int counter = 1;
            ArrayList<Screening> allScreenings = screenCo.getAllScreening();
            for(int i = 0; i < allScreenings.size() ; ++i){
                select = screenCo.find(allScreenings.get(i).getDateTime());
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