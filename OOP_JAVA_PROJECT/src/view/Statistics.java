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

//Classe qui gère toutes les statistiques
public class Statistics extends JFrame {
    
    //Permet de savoir quelle statistique on veux afficher
    private String statType;
    
    //On appelle la méthode correspondante à la statistique qu'on veux afficher
    public Statistics(String statType) throws HeadlessException {
        this.statType = statType;
        //Si on veux afficher les films les plus vus 
        //(qui ont eu le plus de tickets vendus)
        if("Most viewed film".equals(statType)){
            mostViewedFilm();
        }
        //Si on veux afficher le pourcentage de tickets achetés 
        //par rapport aux places disponibles de chaque film
        else if("Percentage tickets per seats".equals(statType)){
            percentageTicketsPerSeats();
        }
        
        //Si on veux afficher les genre de films les plus vus 
        //(qui ont eu le plus de tickets vendus)
        else if("Most viewed Genre".equals(statType)){
            mostViewedGenre();
        }
        
        //Si on veux afficher la réduction de chaque scéance
        else if("Discount per Screenings".equals(statType)){
            discountPerScreenings();
        }
    }
    
    //La statistique qui affiche les réductions de chaque scéances
    public void discountPerScreenings(){
        try {
            //On crée un dataset pour l'histogramme
            DefaultCategoryDataset data = new DefaultCategoryDataset();
            //On essaye de se connecter à la base de données
            Connections co = new Connections("project", "root", "password");
            //On accède au DAO des scéances
            ScreeningDAO screenCo = new ScreeningDAO(co.getInstance());
            //La scéance qu'on sélectionnera pour l'histogramme
            Screening select = new Screening();
            //Toutes les scéances de la base de donnée
            ArrayList<Screening> allScreenings = screenCo.getAllScreening();
            //Pour toutes les scéances
            for(int i = 0; i < allScreenings.size() ; ++i){
               //On sélectionne une scéance
               select = screenCo.find(allScreenings.get(i).getDateTime());
               //On ajoute ses données au dataset
               data.addValue(select.getDiscount(),"Screening " + i,select.getMovieName());
            }
            //On créé l'histogramme
            JFreeChart chart = ChartFactory.createBarChart("Discounts Per Screenings",
                    "Screenings", "Discount (in percentage)",data,PlotOrientation.VERTICAL,
                    true,true,false);
            //On créé un panel qui contient cet histogramme et on le rend visible
            ChartPanel frame = new ChartPanel(chart);
            frame.setVisible(true);
        } catch (SQLException ex) {
            //Si on ne peux pas se connecter à la base de données on l'affiche
            Logger.getLogger(Statistics.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            //Si on ne trouve pas la classe du DAO on l'affiche
            Logger.getLogger(Statistics.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //La statistique qui affiche les genre de films les plus vus
    public void mostViewedGenre(){
        try {
            //On crée un dataset pour le camembert
            DefaultPieDataset data = new DefaultPieDataset();
            //On essaye de se connecter à la base de données
            Connections co = new Connections("project", "root", "password");
            //On accède au DAO des scéances
            ScreeningDAO screenCo = new ScreeningDAO(co.getInstance());
            //La scéance qu'on sélectionnera pour le camembert
            Screening select = new Screening();
            //On accède au DAO des films
            MovieDAO movieCo = new MovieDAO(co.getInstance());
            //Le film qu'on sélectionnera pour la statistique
            Movie selectMovie = new Movie();
            //Nombre de tickets qui sera actualisé si un film possède plusieures scéances
            int numberTickets = 0;
            //Toutes les scéances de la base de donnée
            ArrayList<Screening> allScreenings = screenCo.getAllScreening();
            //Tous les films de la base de donnée
            ArrayList<Movie> allMovies = movieCo.getAllMovie();
            //Pour toutes les scéances
            for(int i = 0; i < allScreenings.size() ; ++i){
               //On sélectionne un film et une scéance
               select = screenCo.find(allScreenings.get(i).getDateTime());
               selectMovie = movieCo.find(select.getMovieName());
               //Le nombre de tickets de la scéance sélectionnée
               numberTickets = select.getTicketsBoughts();
               //On cherche si un genre appartait dans plusieures scéances
               for(int j = 0; j < allScreenings.size() ; ++j)
                   if(i != j && allMovies.get(i).getGenre().equals(allMovies.get(j).getGenre()))
                       //Si il apparati dans plusieures scéances on actualise le nombre de tickets vendus pour ce genre
                       numberTickets = numberTickets + allScreenings.get(j).getTicketsBoughts();  
               //On ajoute les données dans le dataset 
               data.setValue(selectMovie.getGenre(),numberTickets);
            }
            //On créé le camembert
            JFreeChart chart = ChartFactory.createPieChart("Most Viewed Genre", data, true, true, false);
            //On créé un panel qui contient ce camembert et on le rend visible
            ChartPanel frame = new ChartPanel(chart);
            frame.setVisible(true);
        } catch (SQLException ex) {
            //Si on ne peux pas se connecter à la base de données on l'affiche
            Logger.getLogger(Statistics.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            //Si on ne trouve pas la classe du DAO on l'affiche
            Logger.getLogger(Statistics.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //La statistique qui affiche les films les plus vus
    public void mostViewedFilm(){
        try {
            //On crée un dataset pour l'histogramme
            DefaultCategoryDataset data = new DefaultCategoryDataset();
            //On essaye de se connecter à la base de données
            Connections co = new Connections("project", "root", "password");
            //On accède au DAO des scéances
            ScreeningDAO screenCo = new ScreeningDAO(co.getInstance());
            //La scéance qu'on sélectionnera pour l'histogramme
            Screening select = new Screening();
            //Nombre de tickets qui sera actualisé si un film possède plusieures scéances
            int numberTickets = 0;
            //Toutes les scéances de la base de donnée
            ArrayList<Screening> allScreenings = screenCo.getAllScreening();
            //Pour toutes les scéances
            for(int i = 0; i < allScreenings.size() ; ++i){
                //On sélectionne une scéance
               select = screenCo.find(allScreenings.get(i).getDateTime());
               //Le nombre de tickets de la scéance sélectionnée
               numberTickets = select.getTicketsBoughts();
               //On cherche si un film appartait dans plusieures scéances
               for(int j = 0; j < allScreenings.size() ; ++j)
                   if(i != j && allScreenings.get(i).getMovieName().equals(allScreenings.get(j).getMovieName()))
                       //Si il apparati dans plusieures scéances on actualise le nombre de tickets vendus pour ce film
                       numberTickets = numberTickets + allScreenings.get(j).getTicketsBoughts();
               //On ajoute ses données au dataset
               data.addValue(numberTickets,select.getMovieName(),"Movies");
            }
            //On créé l'histogramme
            JFreeChart chart = ChartFactory.createBarChart("Tickets Boughts Per Movies",
                    "Movie", "Tickets Bought",data,PlotOrientation.VERTICAL,
                    true,true,false);
            //On créé un panel qui contient cet histogramme et on le rend visible
            ChartPanel frame = new ChartPanel(chart);
            frame.setVisible(true);
        } catch (SQLException ex) {
            //Si on ne peux pas se connecter à la base de données on l'affiche
            Logger.getLogger(Statistics.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            //Si on ne trouve pas la classe du DAO on l'affiche
            Logger.getLogger(Statistics.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //La statistique qui affiche le pourcentage de tickets achetés 
    //par rapport aux places disponibles de chaque film
    //Ce code à été adapté d'un code trouvé sur internet : http://www.java2s.com/Code/Java/Chart/JFreeChartMultiplePieChartDemo1.htm
    public void percentageTicketsPerSeats(){
        //On créé une statistique avec plusieurs camemberts
        final MultiplePieChartDemo1 demo = new MultiplePieChartDemo1("Statistics");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
    
    //Créé la chart avec tous les camemberts
    private JFreeChart createChart(final CategoryDataset dataset) {
        //On créé la chart
        final JFreeChart chart = ChartFactory.createMultiplePieChart(
            "Percentage of tickets boughts per seats",
            dataset,
            TableOrder.BY_ROW,
            true,
            true,
            false
        );
        //On créé le plot
        final MultiplePiePlot plot = (MultiplePiePlot) chart.getPlot();
        //On créé la sous-chart
        final JFreeChart subchart = plot.getPieChart();
        final PiePlot p = (PiePlot) subchart.getPlot();
        //On initialise le label et la police d'écriture
        p.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}"));
        p.setLabelFont(new Font("SansSerif", Font.PLAIN, 8));
        p.setInteriorGap(0.30);

        return chart;
    }
    
    //Classe qui créé le multi camembert
    public class MultiplePieChartDemo1 extends ApplicationFrame {
        public MultiplePieChartDemo1(final String title) {
            //On créé le multi camembert et on le place dans un panel
            super(title);
            final CategoryDataset dataset = createDataset();
            final JFreeChart chart = createChart(dataset);
            final ChartPanel chartPanel = new ChartPanel(chart, true, true, true, false, true);
            chartPanel.setPreferredSize(new java.awt.Dimension(1366, 768));
            setContentPane(chartPanel);
        }
        
        //On créé le dataset pour le multi camembert
        private CategoryDataset createDataset() {
            try {
                //On essaye de se connecter à la base de données
                Connections co = new Connections("project", "root", "password");
                //On accède au DAO des scéances
                ScreeningDAO screenCo = new ScreeningDAO(co.getInstance());
                //La scéance qu'on sélectionnera pour le camembert
                Screening select = new Screening();
                //On crée un dataset pour le multi camembert
                final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
                //Nombre de tickets et de places qui sera actualisé si un film possède plusieures scéances
                int numberTickets = 0, numberSeats = 0;
                //Toutes les scéances de la base de donnée
                ArrayList<Screening> allScreenings = screenCo.getAllScreening();
                //Pour toutes les scéances
                for(int i = 0; i < allScreenings.size() ; ++i){
                    //On sélectionne une scéance
                    select = screenCo.find(allScreenings.get(i).getDateTime());
                    //Le nombre de tickets de la scéance sélectionnée
                    numberTickets = select.getTicketsBoughts();
                    //Le nombre de places de la scéance sélectionnée
                    numberSeats = select.getNumberseat();
                    //On cherche si un film appartait dans plusieures scéances
                    for(int j = 0; j < allScreenings.size() ; ++j)
                       if(i != j && allScreenings.get(i).getMovieName().equals(allScreenings.get(j).getMovieName())){
                           //Si il apparati dans plusieures scéances on actualise le nombre de tickets vendus et de places pour ce film
                            numberTickets = numberTickets + allScreenings.get(j).getTicketsBoughts(); 
                            numberSeats = numberSeats + allScreenings.get(j).getNumberseat();
                       }
                    //On ajoute ses données au dataset
                    dataset.addValue(numberTickets, select.getMovieName(), "Taken Seats");
                    dataset.addValue(numberSeats-numberTickets, select.getMovieName(), "Empty Seats");
                }
                return dataset;
            } catch (SQLException ex) {
                //Si on ne peux pas se connecter à la base de données on l'affiche et on ne retourne rien
                Logger.getLogger(Statistics.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            } catch (ClassNotFoundException ex) {
                //Si on ne trouve pas la classe du DAO on l'affiche et on ne retourne rien
                Logger.getLogger(Statistics.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        }
    }
}