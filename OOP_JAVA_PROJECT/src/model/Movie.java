/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
/**
 * ZHONG David
 * MAISTERRENA Pierre
 * DANIEL Juliette
 * ING3 TDE02
 */

//Classe qui gère les films
public class Movie {
    //Un film est définis par son titre, genre, date de sortie, durée et son affiche
    private String title;
    private String genre;
    private String release_date;
    private int run_time_minute;
    private BufferedImage image;

    public Movie(){}
    
    //Son contructeur initialse toutes les variables à celles passées en paramètre
    public Movie(String title, String genre, String release_date, int run_time_minute, String image_name) {
        this.title = title;
        this.genre = genre;
        this.release_date = release_date;
        this.run_time_minute = run_time_minute;
        
        try{
            //On essaye de trouver l'image correspondante
            //Si on la trouve on l'affecte à "image"
            this.image= ImageIO.read(new File("images/"+image_name));
        }
        catch (IOException e) {
            //Si on ne la trouve pas on en informe l'utilisateur
            System.out.println("couldn't load image "+image_name);
        }
    }
    
    //Setter du titre
    public void setTitle(String m_title)
    {
    this.title = m_title;
    }
    
    //Getter du titre
    public String getTitle()
    {
    return this.title;
    }
    
    //Setter du genre
    public void setGenre(String m_genre)
    {
    this.genre = m_genre;
    }
    
    //Getter du genre
    public String getGenre()
    {
    return this.genre;
    }
    
    //Setter de la date de sortie
    public void setReleaseDate(String m_release_date)
    {
        this.release_date = m_release_date;
    }
    
    //Getter de la date de sortie
    public String getReleaseDate()
    {
        return this.release_date;
    }
    
    //Setter de la durée
    public void setRunTime(int m_run_time)
    {
    this.run_time_minute = m_run_time;
    }
    
    //Getter de la durée
    public int getRunTime()
    {
    return this.run_time_minute;
    }
    
    //Setter de l'image
    public void setImage(BufferedImage m_image)
    {
        this.image=m_image;
    }
    
    //Getter de la durée
    public BufferedImage getImage()
    {
        return this.image;
    }
}
